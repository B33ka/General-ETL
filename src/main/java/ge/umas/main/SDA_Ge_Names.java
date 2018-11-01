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

public class SDA_Ge_Names {

	static final String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\Dictionary_Data\\";
	static String fileNameWrite = "SDADictionary1.txt";
	static String sdaSelect = "SELECT name FROM names_ge";
	public static Connection conn = null;
	static BufferedWriter writeNamesFromGeo = null;
	static FSDataOutputStream outputStream = null;
	static ResultSet res = null;
	static FileSystem fs = null;
	static Translator translator = new Translator();

	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);

	static long nameCount = 0;
	static long overallCount = 0;
	static int fileNumber = 1;
	static long sdaHivecount = 0;

	public static void main(String[] args) throws Exception {
		System.out.println("--- Creating MIA names_dictionary STARTED ---");
		ArrayList<String> fromEngTranslatedList = new ArrayList<String>();
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());
			LettersMap.mapLetters(fs);
			outputStream = fs.create(pathFileWrite);
			writeNamesFromGeo = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			conn = HiveConnector.getConnection();
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery(sdaSelect);
			while (res.next()) {
				sdaHivecount++;
				if (sdaHivecount > 0) {
					String nameGe = res.getString(1);
					if (nameGe != null && !nameGe.equals("NULL") && !nameGe.equals("'NULL'")) {
						nameGe = nameGe.replaceAll("[^A-Za-z,ა-ჰ,\\s,',Ñ,ñ,Ļ,ļ,Ö,ö,Ù,ù,-,–]+", "")
								.replaceAll("[\\,]+", " ").replaceAll("^ +", "").replaceAll(" +$", "").replaceAll("[\\s]+", " ");
						if (nameGe.length() > 0) {
							fromEngTranslatedList = translator.translateFromGeo(nameGe);
							writeNames(nameGe, fromEngTranslatedList);
							
						}
					}
				}
			}

			if (writeNamesFromGeo != null)
				writeNamesFromGeo.close();
			if (outputStream != null)
				outputStream.close();
			if (fs != null)
				fs.close();
			if (conn != null)
				conn.close();

			System.out.println("-- App ended --");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			if (writeNamesFromGeo != null)
				writeNamesFromGeo.close();
			if (outputStream != null)
				outputStream.close();
			if (fs != null)
				fs.close();
			if (conn != null)
				conn.close();
			System.exit(0);
		}

	}

	static void writeNames(String nameGe, ArrayList<String> fromEngTranslatedList) throws IOException {
		for (int i = 0; i < fromEngTranslatedList.size(); i++) {
			if (nameCount >= 1000000) {
				if (writeNamesFromGeo != null)
					writeNamesFromGeo.close();
				if (outputStream != null)
					outputStream.close();
				overallCount = overallCount + nameCount;
				fileNumber++;
				Path pathFileWrite = new Path(folderNameWrite + "SDADictionary" + fileNumber + ".txt");

				System.out.println("Filling SDADictionary" + (fileNumber - 1) + ".txt " + " FINISHED!");

				outputStream = fs.create(pathFileWrite);
				writeNamesFromGeo = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
				writeNamesFromGeo.write(fromEngTranslatedList.get(i) + "," + nameGe + ",GEO,U,SDA\n");
				nameCount = 0;
			} else {
				String engTrans = fromEngTranslatedList.get(i);
				writeNamesFromGeo.write(engTrans + "," + nameGe + ",GEO,U,SDA\n");
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
