package sda.visa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ge.borderCross.BorderCross;
import ge.umas.config.LettersMap;
import ge.umas.hiveDb.HiveDbApi;
import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.models.SDA_Citizenship;
import ge.umas.translate.Translator;

public class SDA_Visa_Reader {
	static ArrayList<String> fromGeoTranslatedList = new ArrayList<String>();
	static ArrayList<String> fromEngTranslatedList = new ArrayList<String>();

	final static String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\SDA_VISA_ETL\\";
	final static String folderNameRead = "\\UMAS\\test\\Beka_Name_Dictionary\\SDA_VISA\\";
	static String fileNameRead = "visa.json";
	static String fileNameWrite = "visaEtl.json";

	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);
	static Path pathFileRead = new Path(folderNameRead + fileNameRead);

	static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) throws IOException {
		FileSystem fs = null;
		BufferedReader SDADataReader = null;
		BufferedWriter sdaVisaWriter = null;
		FSDataOutputStream outputStream = null;
		System.out.println("-- APP started ---");
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());
			outputStream = fs.create(pathFileWrite);

			SDADataReader = new BufferedReader(new InputStreamReader(fs.open(pathFileRead), "UTF-8"));

			sdaVisaWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			SDAVisaModel sda_visa_model = new SDAVisaModel();
			String sCurrentLine;

			while ((sCurrentLine = SDADataReader.readLine()) != null) {
				try {
					sda_visa_model = gson.fromJson(sCurrentLine, SDAVisaModel.class);

					sda_visa_model.ETL();

					if (sda_visa_model.isValid())
						sdaVisaWriter.write(gson.toJson(sda_visa_model) + "\n");

				} catch (com.google.gson.JsonSyntaxException ex) {
					System.out.println("Error occured in Json ");
				}

			}
			System.out.println("-- App ended --");
		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
			System.exit(0);
		} finally {
			if (sdaVisaWriter != null)
				sdaVisaWriter.close();
			if (outputStream != null)
				outputStream.close();
			if (SDADataReader != null)
				SDADataReader.close();
			if (fs != null)
				fs.close();
		}

	}

}