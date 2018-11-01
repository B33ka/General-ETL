package ge.borderCross;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ge.borderCross.BorderCross;
import ge.umas.kerberos.auth.HdfsConnector;

public class BorderCrossReader {
	final static String folderNameRead = "\\UMAS\\test\\Beka_Name_Dictionary\\MIA_SHORT\\";
	final static String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\MIA_RESULT\\";
	static String fileNameRead = "bc.json";
	static String fileNameWrite = "borderCross.json";

	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);
	static Path pathFileRead = new Path(folderNameRead + fileNameRead);

	static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) throws IOException {
		FileSystem fs = null;
		BufferedReader SDA_DATA_READER = null;
		BufferedWriter borderCrossWriter = null;
		FSDataOutputStream outputStream = null;
		System.out.println("-- APP started ---");
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());
			outputStream = fs.create(pathFileWrite);

			SDA_DATA_READER = new BufferedReader(new InputStreamReader(fs.open(pathFileRead), "UTF-8"));

			borderCrossWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			BorderCross[] borderCross_model;
			String sCurrentLine;

			while ((sCurrentLine = SDA_DATA_READER.readLine()) != null) {
				try {
					borderCross_model = gson.fromJson(sCurrentLine, BorderCross[].class);

					borderCrossWriter.write(gson.toJson(borderCross_model) + "\n");

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
			if (borderCrossWriter != null)
				borderCrossWriter.close();
			if (outputStream != null)
				outputStream.close();
			if (SDA_DATA_READER != null)
				SDA_DATA_READER.close();
			if (fs != null)
				fs.close();
		}

	}

}
