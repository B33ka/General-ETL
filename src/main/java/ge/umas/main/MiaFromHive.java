package ge.umas.main;

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
import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.kerberos.auth.HiveConnector;
import ge.umas.translate.Translator;

public class MiaFromHive {

	static final String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\Dictionary_Data\\";
	static String fileNameWrite = "MiaDictionary1.txt";
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
		ArrayList<String> fromEngTranslatedList = new ArrayList<String>();
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());
			LettersMap.mapLetters(fs);
			outputStream = fs.create(pathFileWrite);
			writeNamesFromEng = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			conn = HiveConnector.getConnection();
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery(miaSelect);
			long nameCount = 0;
			long overallCount = 0;
			int fileNumber = 1;
			long miaHivecount = 0;
			while (res.next()) {
				miaHivecount++;
				if (miaHivecount > 0) {
					String nameEn = res.getString(1);
					if (nameEn != null && !nameEn.equals("NULL")) {
						nameEn = nameEn.replaceAll("^ +", "").replaceAll(" +$", "").replaceAll("[^A-Za-z,\\s,-]+", "")
								.replaceAll("[\\,]+", " ");
						String[] listStr = nameEn.split(" ");
						if (listStr.length > 2 || nameEn.length() > 25) {
							fromEngTranslatedList = translator.directTranslateFromEng(nameEn);
						} else {
							fromEngTranslatedList = translator.translateFromEng(nameEn);
							fromEngTranslatedList.addAll(translator.directTranslateFromEng(nameEn));
						}

						// Remove Duplicates
						Set<String> hs = new HashSet<>();
						hs.addAll(fromEngTranslatedList);
						fromEngTranslatedList.clear();
						fromEngTranslatedList.addAll(hs);

						int listSize = fromEngTranslatedList.size();
						int nameSize = nameEn.length();

						for (int k = 0; k < listSize; k++) {
							if (fromEngTranslatedList.get(k).length() + 3 < nameSize
									|| fromEngTranslatedList.get(k).length() - 2 > nameSize) {
								fromEngTranslatedList.remove(k);
								listSize = fromEngTranslatedList.size();
							}
						}
						String geoTrans = null;
						for (int i = 0; i < fromEngTranslatedList.size(); i++) {

							if (nameCount >= 2000000) {
								if (writeNamesFromEng != null)
									writeNamesFromEng.close();
								if (outputStream != null)
									outputStream.close();
								overallCount =overallCount + nameCount;
								fileNumber++;
								Path pathFileWrite = new Path(folderNameWrite + "MiaDictionary" + fileNumber + ".txt");
								
								System.out.println("Filling MiaDictionary" + (fileNumber-1) + ".txt " + " FINISHED!");
								
								outputStream = fs.create(pathFileWrite);
								writeNamesFromEng = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
								writeNamesFromEng.write(nameEn + "," + fromEngTranslatedList.get(i) + ",ENG,U,MIA_BorderCross \n");
								nameCount = 0;
							} else {
								geoTrans = fromEngTranslatedList.get(i);
								writeNamesFromEng.write(nameEn + "," + geoTrans + ",ENG,U,MIA_BorderCross \n");
								nameCount++;
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

}
