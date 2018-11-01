package ge.umas.main;

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

import ge.umas.config.LettersMap;
import ge.umas.hiveDb.HiveDbApi;
import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.models.NameModel;
import ge.umas.models.SDA_Citizenship;
import ge.umas.translate.Translator;

public class SDA_Translator {
	static ArrayList<String> fromGeoTranslatedList = new ArrayList<String>();
	static ArrayList<String> fromEngTranslatedList = new ArrayList<String>();

	final static String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\";
	final static String folderNameRead = "\\UMAS\\test\\Beka_Name_Dictionary\\Test Data\\";
	// static String fileNameRead = "testCit.json";
	static String fileNameRead = "Citizenship.json";
	static String fileNameWrite = "Dictionary.txt";

	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);
	static Path pathFileRead = new Path(folderNameRead + fileNameRead);

	static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) throws IOException {
		Translator translator = new Translator();
		HiveDbApi hiveDB = new HiveDbApi();
		FileSystem fs = null;
		BufferedReader SDA_DATA_READER = null;
		BufferedWriter writeFromGeoNames = null;
		FSDataOutputStream outputStream = null;
		System.out.println("-- APP started ---");
		try {
			fs = FileSystem.get(HdfsConnector.loginUser());
			LettersMap.mapLetters(fs);
			outputStream = fs.create(pathFileWrite);

			SDA_DATA_READER = new BufferedReader(new InputStreamReader(fs.open(pathFileRead), "UTF-8"));

			writeFromGeoNames = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			SDA_Citizenship personData = new SDA_Citizenship();
			String sCurrentLine;
			int nameCount = 0;
			int realCount = 0;
			while ((sCurrentLine = SDA_DATA_READER.readLine()) != null) {
				boolean flag = false;
				try {
					personData = gson.fromJson(sCurrentLine, SDA_Citizenship.class);
					flag = true;
				} catch (com.google.gson.JsonSyntaxException ex) {
					flag = false;
				}
				if (flag) {
					NameModel nameG = null;
					String fName = null;
					String lName = null;
					String fNameEn = null;
					String lNameEn = null;

					if (personData.getFname() != null) {
						fName = personData.getFname().replaceAll("^ +", "").replaceAll(" +$", "")
								.replaceAll("[^ა-ჰ,\\s,-]+", "").replaceAll("[\\,]+", " ");
						if (personData.getFnameEn() != null)
							fNameEn = personData.getFnameEn().replaceAll("^ +", "").replaceAll(" +$", "")
									.replaceAll("[^A-Za-z,\\s,-]+", "").replaceAll("[\\,]+", " ");
					} else {
						if (personData.getFNAME() != null)
							fName = personData.getFNAME().replaceAll("^ +", "").replaceAll(" +$", "")
									.replaceAll("[^ა-ჰ,\\s,-]+", "").replaceAll("[\\,]+", " ");
						if (personData.getFNAMEEN() != null)
							fNameEn = personData.getFNAMEEN().replaceAll("^ +", "").replaceAll(" +$", "")
									.replaceAll("[^A-Za-z,\\s,-]+", "").replaceAll("[\\,]+", " ");
					}
					if (personData.getLname() != null) {
						lName = personData.getLname().replaceAll("^ +", "").replaceAll(" +$", "")
								.replaceAll("[^ა-ჰ,\\s,-]+", "").replaceAll("[\\,]+", " ");
						if (personData.getLnameEn() != null)
							lNameEn = personData.getLnameEn().replaceAll("^ +", "").replaceAll(" +$", "")
									.replaceAll("[^A-Za-z,\\s,-]+", "").replaceAll("[\\,]+", " ");
					} else {
						if (personData.getLNAME() != null)
							lName = personData.getLNAME().replaceAll("^ +", "").replaceAll(" +$", "")
									.replaceAll("[^ა-ჰ,\\s,-]+", "").replaceAll("[\\,]+", " ");
						if (personData.getLNAMEEN() != null)
							lNameEn = personData.getLNAMEEN().replaceAll("^ +", "").replaceAll(" +$", "")
									.replaceAll("[^A-Za-z,\\s,-]+", "").replaceAll("[\\,]+", " ");
					}

					if (fName != null && !fName.equals("NULL")) {
						if (!hiveDB.checkIfExist(fName, true)) {
							realCount++;
							nameCount++;
							fromGeoTranslatedList = translator.translateFromGeo(fName);
							
							//Remove Duplicates
							Set<String> hs = new HashSet<>();
							hs.addAll(fromEngTranslatedList);
							fromEngTranslatedList.clear();
							fromEngTranslatedList.addAll(hs);
							
							String engTransName = null;
							for (int i = 0; i < fromGeoTranslatedList.size() - 1; i++) {
								engTransName = fromGeoTranslatedList.get(i);
								nameG = new NameModel(engTransName, fName, "GEO", "U", "SDA_Citizenship");
								writeFromGeoNames.write(nameG.toString());
							}
							engTransName = fromGeoTranslatedList.get(fromGeoTranslatedList.size() - 1);
							nameG = new NameModel(engTransName, fName, "GEO", "G", "SDA_Citizenship");
							writeFromGeoNames.write(nameG.toString());
							System.out.println(realCount + " : " + nameG.toString() + " Translated from Georgian");
						}
						if (fNameEn != null && !fNameEn.equals("NULL")) {
							if (!hiveDB.checkOriginalIfExist(fNameEn, fName)) {
								realCount++;
								nameG = new NameModel(fNameEn, fName, "ENG", "O", "SDA_Citizenship");
								writeFromGeoNames.write(nameG.toString());
								System.out
										.println(realCount + ": " + nameG.toString() + "INSERTED IN names_dictionary");
							}
							if (!hiveDB.checkIfExist(fNameEn, false)) {
								realCount++;
								nameCount++;
								fromEngTranslatedList = translator.translateFromEng(fNameEn);
								
								//Remove Duplicates
								Set<String> hs = new HashSet<>();
								hs.addAll(fromEngTranslatedList);
								fromEngTranslatedList.clear();
								fromEngTranslatedList.addAll(hs);
								
								String geoTransName = null;
								for (int i = 0; i < fromEngTranslatedList.size() - 1; i++) {
									geoTransName = fromEngTranslatedList.get(i);
									nameG = new NameModel(fNameEn, geoTransName, "ENG", "U", "SDA_Citizenship");
									writeFromGeoNames.write(nameG.toString());
								}
								geoTransName = fromEngTranslatedList.get(fromEngTranslatedList.size() - 1);
								nameG = new NameModel(fNameEn, geoTransName, "ENG", "G", "SDA_Citizenship");
								writeFromGeoNames.write(nameG.toString());
								System.out.println(realCount + " : " + nameG.toString() + " Translated from English");
							}
						}
					}
					if (lName != null && !lName.equals("NULL")) {
						if (!hiveDB.checkIfExist(lName, true)) {
							realCount++;
							nameCount++;
							String engName = null;
							fromGeoTranslatedList = translator.translateFromGeo(lName);
							
							//Remove Duplicates
							Set<String> hs = new HashSet<>();
							hs.addAll(fromEngTranslatedList);
							fromEngTranslatedList.clear();
							fromEngTranslatedList.addAll(hs);
							
							for (int i = 0; i < fromGeoTranslatedList.size() - 1; i++) {
								engName = fromGeoTranslatedList.get(i);
								nameG = new NameModel(engName, lName, "GEO", "U", "SDA_Citizenship");
								writeFromGeoNames.write(nameG.toString());
							}
							engName = fromGeoTranslatedList.get(fromGeoTranslatedList.size() - 1);
							nameG = new NameModel(engName, lName, "GEO", "G", "SDA_Citizenship");
							writeFromGeoNames.write(nameG.toString());
							System.out.println(realCount + " : " + nameG.toString() + " Translated from Georgian");

						}
						if (lNameEn != null && !lNameEn.equals("NULL")) {
							if (!hiveDB.checkOriginalIfExist(lNameEn, lName)) {
								realCount++;
								nameG = new NameModel(lNameEn, lName, "ENG", "O", "SDA_Citizenship");
								writeFromGeoNames.write(nameG.toString());
								System.out.println(
										realCount + ": " + nameG.toString() + " Original INSERTED IN names_dictionary");
							}
							if (!hiveDB.checkIfExist(lNameEn, false)) {
								realCount++;
								nameCount++;
								fromEngTranslatedList = translator.translateFromEng(lNameEn);
								
								//Remove Duplicates
								Set<String> hs = new HashSet<>();
								hs.addAll(fromEngTranslatedList);
								fromEngTranslatedList.clear();
								fromEngTranslatedList.addAll(hs);
								
								String geoName = null;
								for (int i = 0; i < fromEngTranslatedList.size() - 1; i++) {
									geoName = fromEngTranslatedList.get(i);
									nameG = new NameModel(lNameEn, geoName, "ENG", "U", "SDA_Citizenship");
									writeFromGeoNames.write(nameG.toString());
								}
								geoName = fromEngTranslatedList.get(fromEngTranslatedList.size() - 1);
								nameG = new NameModel(lNameEn, geoName, "ENG", "G", "SDA_Citizenship");
								writeFromGeoNames.write(nameG.toString());
								System.out.println(realCount + " : " + nameG.toString() + " Translated from English");
							}
						}
					}

				}
				if (nameCount >= 10) {
					nameCount = 0;
					hiveDB.loadData("SDA");
					fs.create(pathFileWrite);
				}
			}
			if (nameCount > 0) {
				hiveDB.loadData("SDA");
				fs.create(pathFileWrite);
			}
			if (writeFromGeoNames != null)
				writeFromGeoNames.close();
			if (outputStream != null)
				outputStream.close();
			if (SDA_DATA_READER != null)
				SDA_DATA_READER.close();
			if (fs != null)
				fs.close();
			System.out.println("-- App ended --");

		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
			System.exit(0);
		} finally {
			if (writeFromGeoNames != null)
				writeFromGeoNames.close();
			if (outputStream != null)
				outputStream.close();
			if (SDA_DATA_READER != null)
				SDA_DATA_READER.close();
			if (fs != null)
				fs.close();
		}

	}

}
