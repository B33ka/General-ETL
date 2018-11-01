package test;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import ge.umas.config.LettersMap;
import ge.umas.hiveDb.HiveDbApi;
import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.kerberos.auth.HiveConnector;
import ge.umas.models.NameModel;
import ge.umas.translate.Translator;

public class TestMiIA {
	static final String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\";
	static String fileNameWrite = "MiaDictionary.txt";
	static ArrayList<String> fromEngTranslatedList = new ArrayList<String>();
	static String miaSelect = "SELECT mia_names.name FROM mia_names";
	public static Connection conn = null;
	static BufferedWriter writeNamesFromEng = null;
	static FSDataOutputStream outputStream = null;
	static ResultSet res = null;
	static FileSystem fs = null;
	static Translator translator = new Translator();

	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);

	public static void main(String[] args) throws Exception {
		System.out.println("--- Creating MIA names_dictionary STARTED ---");
		NameModel nameEng = null;
		HiveDbApi hiveDB = new HiveDbApi();
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());
			LettersMap.mapLetters(fs);
			outputStream = fs.create(pathFileWrite);
			writeNamesFromEng = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			conn = HiveConnector.getConnection();
			Statement stmt = conn.createStatement();
			// res = stmt.executeQuery(miaSelect);
			int nameCount = 0;

			String nameEn = ".";

			if (nameEn != null && !nameEn.equals("NULL")) {
				/*
				 * nameEn = nameEn.replaceAll("^ +", "").replaceAll(" +$",
				 * "").replaceAll("[^A-Za-z,\\s,-]+", "") .replaceAll("[\\,]+", " ");
				 */
				String[] listStr = nameEn.split(" ");
				if (listStr.length > 2)
					fromEngTranslatedList = translator.directTranslateFromEng(nameEn);
				else {
					//fromEngTranslatedList = translator.directTranslateFromEng(nameEn);
					fromEngTranslatedList.addAll(translator.translateFromEng(nameEn));
				}

				// Remove Duplicates
				Set<String> hs = new HashSet<>();
				hs.addAll(fromEngTranslatedList);
				fromEngTranslatedList.clear();
				fromEngTranslatedList.addAll(hs);

				System.out.println("Removed dublicates " + fromEngTranslatedList.size());



				System.out.println("Removed short names  " + fromEngTranslatedList.size());

				String geoTrans = null;
				for (int i = 0; i < fromEngTranslatedList.size(); i++) {
					geoTrans = fromEngTranslatedList.get(i);
					nameEng = new NameModel(nameEn, geoTrans, "ENG", "U", "MIA_BorderCross");
					writeNamesFromEng.write(nameEng.toString());
					nameCount++;
				}
				if (fromEngTranslatedList.size() > 5000 || nameCount >= 10) {
					nameCount = 0;
					hiveDB.loadData("MIA");
					fs.create(pathFileWrite);
				}
				System.out.println(nameEn.toUpperCase() + " is INSERTED IN names_dictionary");

			}

			if (nameCount > 0) {
				hiveDB.loadData("MIA");
				fs.create(pathFileWrite);
			}
			if (writeNamesFromEng != null)
				writeNamesFromEng.close();
			if (outputStream != null)
				outputStream.close();
			if (fs != null)
				fs.close();
			if (conn != null)
				conn.close();

			System.out.println("-- App ended --");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			if (writeNamesFromEng != null)
				writeNamesFromEng.close();
			if (outputStream != null)
				outputStream.close();
			if (fs != null)
				fs.close();
			if (conn != null)
				conn.close();
			System.exit(0);
		}

	}

}
