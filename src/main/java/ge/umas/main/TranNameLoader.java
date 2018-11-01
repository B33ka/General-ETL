package ge.umas.main;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import ge.umas.hiveDb.HiveDbApi;
import ge.umas.kerberos.auth.HdfsConnector;

public class TranNameLoader {
	static ArrayList<String> fromEngTranslatedList = new ArrayList<String>();
	static final String folderNameRead = "\\UMAS\\test\\Beka_Name_Dictionary\\MIA_SHORT\\";
	static FileSystem fs = null;
	public static void main(String[] args) throws IOException {
		System.out.println("-- APP started ---");
		HiveDbApi hiveDB = new HiveDbApi();
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());

			FileStatus[] fileStatuses = fs.listStatus(new Path(folderNameRead));

			for (FileStatus fileStatus : fileStatuses) {
				if (!fileStatus.isDirectory()) {
					String fileName = fileStatus.getPath().getName();
					System.out.println("Start Inserting file named: " + fileName);
					hiveDB.loadDataFromFiles(fileName);

				}
			}

			if (fs != null)
				fs.close();
			System.out.println("-- App ended --");
		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex);
			if (fs != null)
				fs.close();
			System.exit(0);
		} catch (Exception ex) {
			System.out.println(ex);
			if (fs != null)
				fs.close();
			System.exit(0);
		}
	}
}
