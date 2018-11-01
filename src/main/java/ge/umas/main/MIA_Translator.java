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

public class MIA_Translator {
	static ArrayList<String> fromEngTranslatedList = new ArrayList<String>();
	static final String folderNameRead = "\\UMAS\\test\\Beka_Name_Dictionary\\Test Data\\MiaOne\\"; // args[1];
	static final String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\";
	static String fileNameWrite = "MiaDictionary.txt";

	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);

	static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) throws IOException {
		System.out.println("-- APP started ---");
		Translator translator = new Translator();
		HiveDbApi hiveDB = new HiveDbApi();
		BufferedReader MIA_DATA_READER = null;
		try {
			FileSystem fs = FileSystem.get(HdfsConnector.loginUser());
			LettersMap.mapLetters(fs);

			FSDataOutputStream outputStream = fs.create(pathFileWrite);
			BufferedWriter writeFromGeoNames = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));

			FileStatus[] fileStatuses = fs.listStatus(new Path(folderNameRead));
			for (FileStatus fileStatus : fileStatuses) {
				if (!fileStatus.isDirectory()) {
					String fileName = fileStatus.getPath().getName();
					System.out.println("Start reading file named: " + fileName + ".json");
					Path pathFileRead = new Path(folderNameRead + fileName);

					BorderCrossModel personData = new BorderCrossModel();
					MIA_DATA_READER = new BufferedReader(new InputStreamReader(fs.open(pathFileRead), "UTF-8"));
					String sCurrentLine = null;
					int nameCount = 0;
					int realCount = 0;
					while ((sCurrentLine = MIA_DATA_READER.readLine()) != null) {
						boolean flag = false;
						try {
							personData = gson.fromJson(sCurrentLine, BorderCrossModel.class);
							flag = true;
						} catch (com.google.gson.JsonSyntaxException ex) {
							flag = false;
						}

						if (flag) {
							NameModel nameEng = null;
							String fNameEn = null;
							String lNameEn = null;

							if (personData.getFIRSTNAMEEN() != null && !personData.getFIRSTNAMEEN().equals("NULL")) {
								fNameEn = personData.getFIRSTNAMEEN().replaceAll("^ +", "").replaceAll(" +$", "")
										.replaceAll("[^A-Za-z,\\s,-]+", "").replaceAll("[\\,]+", " ");
							}
							if (personData.getLASTNAMEEN() != null && !personData.getLASTNAMEEN().equals("NULL")) {
								lNameEn = personData.getLASTNAMEEN().replaceAll("^ +", "").replaceAll(" +$", "")
										.replaceAll("[^A-Za-z,\\s,-]+", "").replaceAll("[\\,]+", " ");
							}

							if (fNameEn != null) {
								if (!hiveDB.checkIfExist(fNameEn, false)) {
									realCount++;
									nameCount++;
									fromEngTranslatedList = translator.translateFromEng(fNameEn);
									
									//Remove Duplicates
									Set<String> hs = new HashSet<>();
									hs.addAll(fromEngTranslatedList);
									fromEngTranslatedList.clear();
									fromEngTranslatedList.addAll(hs);
									
									String geoTrans = null;
									for (int i = 0; i < fromEngTranslatedList.size() - 1; i++) {
										geoTrans = fromEngTranslatedList.get(i);
										nameEng = new NameModel(fNameEn , geoTrans, "ENG", "U", "MIA_BorderCross");
										writeFromGeoNames.write(nameEng.toString());
									}
									geoTrans = fromEngTranslatedList.get(fromEngTranslatedList.size() - 1);
									nameEng = new NameModel(fNameEn, geoTrans, "ENG", "G", "MIA_BorderCross");
									writeFromGeoNames.write(nameEng.toString());
									System.out.println(realCount + " : " + nameEng.toString());
								}
							}
							if (lNameEn != null) {
								if (!hiveDB.checkIfExist(lNameEn, false)) {
									realCount++;
									nameCount++;
									String geoTrans = null;
									fromEngTranslatedList = translator.translateFromEng(lNameEn);
									//Remove Duplicates
									Set<String> hs = new HashSet<>();
									hs.addAll(fromEngTranslatedList);
									fromEngTranslatedList.clear();
									fromEngTranslatedList.addAll(hs);
									
									for (int i = 0; i < fromEngTranslatedList.size() - 1; i++) {
										geoTrans = fromEngTranslatedList.get(i);
										nameEng = new NameModel(lNameEn ,geoTrans, "ENG", "U", "MIA_BorderCross");
										writeFromGeoNames.write(nameEng.toString());
									}
									geoTrans = fromEngTranslatedList.get(fromEngTranslatedList.size() - 1);
									nameEng = new NameModel(lNameEn, geoTrans, "ENG", "G", "MIA_BorderCross");
									writeFromGeoNames.write(nameEng.toString());
									System.out.println(realCount + " : " + nameEng.toString());

								}
							}

						}
						if (nameCount >= 10) {
							nameCount = 0;
							hiveDB.loadData("MIA");
							fs.create(pathFileWrite);
						}
					}
					if (nameCount > 0) {
						hiveDB.loadData("MIA");
						fs.create(pathFileWrite);
					}

				}
			}

			if (writeFromGeoNames != null)
				writeFromGeoNames.close();
			if (outputStream != null)
				outputStream.close();
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
