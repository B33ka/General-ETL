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
import ge.umas.hiveDb.HiveDbApi;
import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.kerberos.auth.HiveConnector;
import ge.umas.translate.Translator;

public class SDA_Both_Names {

	static final String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\SDA_SHORT\\";
	static String fileNameWrite = "SDADictionary3.txt";
	static String sdaSelect = "SELECT name_ge,name_en FROM names_ge_en";
	public static Connection conn = null;
	static BufferedWriter writeNames = null;
	static FSDataOutputStream OutputStream = null;
	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);
	static ResultSet res = null;
	static FileSystem fs = null;
	static Translator translator = new Translator();

	static long nameCount = 0;
	static long overallCount = 0;
	static int fileNumber = 3;
	static long sdaHivecount = 0;

	public static void main(String[] args) throws Exception {
		System.out.println("--- Creating SDA names_dictionary STARTED ---");
		HiveDbApi hiveDb = new HiveDbApi();
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());
			LettersMap.mapLetters(fs);
			OutputStream = fs.create(pathFileWrite);
			writeNames = new BufferedWriter(new OutputStreamWriter(OutputStream, "utf-8"));
			conn = HiveConnector.getConnection();
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery(sdaSelect);
			boolean geoFlag = false;
			boolean engFlag = false;
			while (res.next()) {
				sdaHivecount++;
				if (sdaHivecount > 0) {
					System.out.println(sdaHivecount + " name is read from names Dictionary");
					String nameGe = res.getString(1);
					String nameEn = res.getString(2);
					if (nameGe != null && !nameGe.equals("NULL") && !nameGe.equals("'NULL'")) {
						nameGe = nameGe.toUpperCase();
						nameGe = nameGe.replaceAll("[^A-Za-z,ა-ჰ,\\s,',Ñ,ñ,Ļ,ļ,Ö,ö,Ù,ù,-,-,),(,.]+", "")
								.replaceAll("[\\,]+", " ").replaceAll("^ +", "").replaceAll(" +$", "")
								.replaceAll("[\\s]+", " ").replaceAll("[-]{1,}", " ").replaceAll("[.]{2,}", ".")
								.replaceAll("[-]{1,}", " ");
						if (nameGe.length() > 0) {
							geoFlag = true;
							// String withoutApostrophe = nameGe.replaceAll("[']+", " ");
							// if (!hiveDb.checkIfExist(withoutApostrophe, true)) {
							/*String forSplit = nameGe.replaceAll("[(,)]", "");
							String[] listStr = forSplit.split(" ");
							writeGeoNames(nameGe, translator.translateFromGeo(nameGe));
							if (listStr.length > 1) {
								for (int c = 0; c < listStr.length; c++) {
									writeGeoNames(listStr[c], translator.translateFromGeo(listStr[c]));
								}
							}*/
							// }
						}
					}
					if (nameEn != null && !nameEn.equals("NULL") && !nameEn.equals("'NULL'")) {
						nameEn = nameEn.toUpperCase();
						nameEn = nameEn.replaceAll("[^A-Za-z,ა-ჰ,\\s,',Ñ,ñ,Ļ,ļ,Ö,ö,Ù,ù,-,-,),(,.]+", "")
								.replaceAll("[\\,]+", " ").replaceAll("^ +", "").replaceAll(" +$", "")
								.replaceAll("[\\s]+", " ").replaceAll("[-]{1,}", " ").replaceAll("[.]{2,}", ".")
								.replaceAll("[-]{1,}", " ");
						if (nameEn.length() > 0) {
							engFlag = true;
							// String withoutApostrophe = nameEn.replaceAll("[']+", " ");

							// if (!hiveDb.checkIfExist(withoutApostrophe, false)) {
							/*String forSplit = nameEn.replaceAll("[(,)]", "");
							String[] listStr = forSplit.split(" ");
							writeEngNames(nameEn, translator.translateFromEng(nameEn));
							if (listStr.length > 1) {
								for (int c = 0; c < listStr.length; c++) {
									writeEngNames(listStr[c], translator.translateFromEng(listStr[c]));
								}
							}*/

							// }
						}
					}
					if (geoFlag && engFlag) {
						writeOriginalNames(nameGe, nameEn);
						geoFlag = false;
						engFlag = false;
					}

				}
			}

			if (writeNames != null)
				writeNames.close();
			if (OutputStream != null)
				OutputStream.close();
			if (fs != null)
				fs.close();
			if (conn != null)
				conn.close();

			System.out.println("-- App ended --");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			if (writeNames != null)
				writeNames.close();
			if (OutputStream != null)
				OutputStream.close();
			if (fs != null)
				fs.close();
			if (conn != null)
				conn.close();
			System.exit(0);
		}

	}

	static void writeGeoNames(String nameGe, ArrayList<String> fromGeoTranslatedList) throws IOException {
		for (int i = 0; i < fromGeoTranslatedList.size(); i++) {
			if (nameCount > 1000000) {
				overallCount = overallCount + nameCount;
				fileNumber++;
				Path pathFileWrite = new Path(folderNameWrite + "SDADictionary" + fileNumber + ".txt");

				System.out.println(overallCount + " Filling SDADictionary" + (fileNumber - 1) + ".txt " + " FINISHED!");

				OutputStream = fs.create(pathFileWrite);
				writeNames = new BufferedWriter(new OutputStreamWriter(OutputStream, "utf-8"));
				writeNames.write(fromGeoTranslatedList.get(i) + "," + nameGe + ",GEO,U,SDA\n");
				nameCount = 0;
			} else {
				System.out.println(overallCount + nameCount + "th name: " + nameGe + "  TRANSLATED");
				writeNames.write(fromGeoTranslatedList.get(i) + "," + nameGe + ",GEO,U,SDA\n");
				nameCount++;
			}
		}
	}

	static void writeEngNames(String nameEn, ArrayList<String> fromEngTranslatedList) throws IOException {
		for (int i = 0; i < fromEngTranslatedList.size(); i++) {
			if (nameCount > 1000000) {
				overallCount = overallCount + nameCount;
				fileNumber++;
				Path pathFileWrite = new Path(folderNameWrite + "SDADictionary" + fileNumber + ".txt");

				System.out.println(overallCount + " Filling SDADictionary" + (fileNumber - 1) + ".txt " + " FINISHED!");

				OutputStream = fs.create(pathFileWrite);
				writeNames = new BufferedWriter(new OutputStreamWriter(OutputStream, "utf-8"));
				writeNames.write(fromEngTranslatedList.get(i) + "," + nameEn + ",ENG,U,SDA\n");
				nameCount = 0;
			} else {
				//System.out.println(overallCount + nameCount + "th name: " + nameEn + "  TRANSLATED");
				writeNames.write(nameEn + "," + fromEngTranslatedList.get(i) + ",ENG,U,SDA\n");
				nameCount++;
			}
			System.out.println(overallCount + nameCount + "th name: " + nameEn + "  TRANSLATED");
		}
	}

	static void writeOriginalNames(String nameGe, String nameEn) throws IOException {

		if (nameCount > 1000000) {
			overallCount = overallCount + nameCount;
			fileNumber++;
			Path pathFileWrite = new Path(folderNameWrite + "SDADictionary" + fileNumber + ".txt");

			System.out.println(overallCount + "Filling SDADictionary" + (fileNumber - 1) + ".txt " + " FINISHED!");

			OutputStream = fs.create(pathFileWrite);
			writeNames = new BufferedWriter(new OutputStreamWriter(OutputStream, "utf-8"));
			writeNames.write(nameEn + "," + nameGe + ",ORIGINAL,O,SDA\n");
			nameCount = 0;
		} else {
			writeNames.write(nameEn + "," + nameGe + ",ORIGINAL,O,SDA\n");
			nameCount++;
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