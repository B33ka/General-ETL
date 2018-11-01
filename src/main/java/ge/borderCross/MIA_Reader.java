package ge.borderCross;

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
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ge.umas.config.LettersMap;
import ge.umas.hiveDb.HiveDbApi;
import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.models.BorderCrossModel;
import ge.umas.models.NameModel;
import ge.umas.translate.Translator;

public class MIA_Reader {
	static ArrayList<String> fromEngTranslatedList = new ArrayList<String>();
	final static String folderNameRead = "\\UMAS\\test\\Beka_Name_Dictionary\\MIA_SHORT\\";
	final static String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\MIA_RESULT\\";
	static String fileNameWrite = "MiaDictionary.txt";

	static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) throws IOException {
		System.out.println("-- APP started ---");
		Translator translator = new Translator();
		HiveDbApi hiveDB = new HiveDbApi();
		BufferedReader MIA_DATA_READER = null;
		try {
			FileSystem fs = FileSystem.get(HdfsConnector.loginUser());

			FileStatus[] fileStatuses = fs.listStatus(new Path(folderNameRead));
			for (FileStatus fileStatus : fileStatuses) {
				if (!fileStatus.isDirectory()) {
					String fileName = fileStatus.getPath().getName();

					Path pathFileWrite = new Path(folderNameWrite + fileName);
					FSDataOutputStream outputStream = fs.create(pathFileWrite);
					BufferedWriter borderCrossWriter = new BufferedWriter(
							new OutputStreamWriter(outputStream, "utf-8"));

					System.out.println("Start reading file named: " + fileName + ".json");
					Path pathFileRead = new Path(folderNameRead + fileName);

					BorderCrossModel borderCross_model = new BorderCrossModel();

					MIA_DATA_READER = new BufferedReader(new InputStreamReader(fs.open(pathFileRead), "UTF-8"));
					String sCurrentLine = null;

					while ((sCurrentLine = MIA_DATA_READER.readLine()) != null) {

						borderCross_model = gson.fromJson(sCurrentLine, BorderCrossModel.class);

						borderCrossWriter.write(gson.toJson(borderCross_model) + "\n");

					}

				}

			}
			if (MIA_DATA_READER != null)
				MIA_DATA_READER.close();
			if (fs != null)
				fs.close();
			System.out.println("-- App ended --");
		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex);
			System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex);
			System.exit(0);
		}
	}
}