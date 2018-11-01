package ge.umas.main;

import java.io.BufferedWriter;
import java.io.IOException;
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
import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.kerberos.auth.HiveConnector;
import ge.umas.translate.Translator;

public class MiaSplitNames {

	static final String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\Dictionary_New\\";
	static String fileNameWrite = "MiaDictionary1.txt";
	static String miaSelect = "SELECT mia_name.name FROM mia_name";
	public static Connection conn = null;
	static BufferedWriter writeNamesFromEng = null;
	static FSDataOutputStream outputStream = null;
	static ResultSet res = null;
	static FileSystem fs = null;
	static Translator translator = new Translator();

	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);

	static long nameCount = 0;
	static long overallCount = 0;
	static int fileNumber = 1;
	static long miaHivecount = 0;

	public static void main(String[] args) throws Exception {
		System.out.println("--- Creating MIA names_dictionary STARTED ---");
		ArrayList<String> fromEngTranslatedList = new ArrayList<String>();
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());
			LettersMap.mapLetters(fs);
			outputStream = fs.create(pathFileWrite);
			writeNamesFromEng = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			conn = HiveConnector.getConnection();
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery(miaSelect);
			while (res.next()) {
				miaHivecount++;
				if (miaHivecount > 0) {
					String nameEn = res.getString(1);
					if (nameEn != null && !nameEn.equals("NULL")) {
						nameEn = nameEn.toUpperCase();
						/*
						 * nameEn = nameEn.replaceAll("^ +", "").replaceAll(" +$",
						 * "").replaceAll("[-]+", " ") .replaceAll("[^A-Za-z,\\s,',Ñ,ñ,Ļ,ļ]+",
						 * "").replaceAll("[\\,]+", " ");
						 */
						if (nameEn.length() > 0) {
							String[] listStr = nameEn.split(" ");
							fromEngTranslatedList = translator.translateFromEng(nameEn);
							writeNames(nameEn, fromEngTranslatedList);
							if (listStr.length > 1) {
								for (int c = 0; c < listStr.length; c++) {
									fromEngTranslatedList = translator.translateFromEng(listStr[c]);
									writeNames(listStr[c], fromEngTranslatedList);
								}
							}
							
						}
					}
				}
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

	static void writeNames(String nameEn, ArrayList<String> fromEngTranslatedList) throws IOException {
		for (int i = 0; i < fromEngTranslatedList.size(); i++) {
			if (nameCount >= 3100000) {
				if (writeNamesFromEng != null)
					writeNamesFromEng.close();
				if (outputStream != null)
					outputStream.close();
				overallCount = overallCount + nameCount;
				fileNumber++;
				Path pathFileWrite = new Path(folderNameWrite + "MiaDictionary" + fileNumber + ".txt");

				System.out.println("Filling MiaDictionary" + (fileNumber - 1) + ".txt " + " FINISHED!");

				outputStream = fs.create(pathFileWrite);
				writeNamesFromEng = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
				writeNamesFromEng.write(nameEn + "," + fromEngTranslatedList.get(i) + ",ENG,U,MIA_BorderCross\n");
				nameCount = 0;
			} else {
				String geoTrans = fromEngTranslatedList.get(i);
				writeNamesFromEng.write(nameEn + "," + geoTrans + ",ENG,U,MIA_BorderCross\n");
				System.out.println(nameEn + " is written in dictionary!");
				nameCount++;
			}
		}
	}

	static ArrayList<String> removeDublicates(ArrayList<String> fromEngTranslatedList) {
		Set<String> hs = new HashSet<>();
		hs.addAll(fromEngTranslatedList);
		fromEngTranslatedList.clear();
		fromEngTranslatedList.addAll(hs);

		return fromEngTranslatedList;
	}

	static ArrayList<String> removeShortNames(ArrayList<String> fromEngTranslatedList, int nameSize) {
		long listSize = fromEngTranslatedList.size();
		for (int k = 0; k < listSize; k++) {
			if (fromEngTranslatedList.get(k).length() + 3 < nameSize
					|| fromEngTranslatedList.get(k).length() - 3 > nameSize) {
				fromEngTranslatedList.remove(k);
				listSize = fromEngTranslatedList.size();
			}
		}

		return fromEngTranslatedList;
	}

}
