package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;

import ge.umas.config.LettersMap;
import ge.umas.hiveDb.HiveDbApi;
import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.models.NameModel;
import ge.umas.models.SDA_Citizenship;
import ge.umas.translate.Translator;

public class TestHDFS {
	static Map<String, ArrayList<String>> geoLetterMap = new HashMap<String, ArrayList<String>>();
	static Map<String, ArrayList<String>> engLetterMap = new HashMap<String, ArrayList<String>>();

	static ArrayList<String> fromGeoTranslatedList = new ArrayList<String>();
	static ArrayList<String> fromEngTranslatedList = new ArrayList<String>();

	final static String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\";
	// final static String folderNameRead =
	// "\\UMAS\\Production\\Source\\SDA\\Citizenship\\";
	final static String folderNameRead = "\\UMAS\\test\\Beka_Name_Dictionary\\Test Data\\";
	// static String fileNameRead = "citizenship.json";
	static String fileNameRead = "testCit.json";
	static String fileNameWrite = "Dictionary.txt";

	static Path pathFileWrite = new Path(folderNameWrite + fileNameWrite);
	static Path pathFileRead = new Path(folderNameRead + fileNameRead);
	
	BufferedReader br = null;

	static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) throws Exception {
		Translator translator = new Translator();
		HiveDbApi hiveDB = new HiveDbApi();
		System.out.println("-- APP started ---");
		try {
			FileSystem fs = FileSystem.get(HdfsConnector.loginUser());
			LettersMap.mapLetters(fs);
			FSDataOutputStream outputStream = fs.create(pathFileWrite);
			
			//JsonReader SDA_DATA_READER = new JsonReader(new InputStreamReader(fs.open(pathFileRead), "UTF-8"));
			BufferedReader SDA_DATA_READER = new BufferedReader(new InputStreamReader(fs.open(pathFileRead), "UTF-8"));
			
			BufferedWriter writeFromGeoNames = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			SDA_Citizenship personData = new SDA_Citizenship();
			String sCurrentLine;
			while ((sCurrentLine = SDA_DATA_READER.readLine()) != null) {
				boolean flag = true;
				try {
					personData = gson.fromJson(sCurrentLine, SDA_Citizenship.class);
					flag = true;
				} catch (com.google.gson.JsonSyntaxException ex) {
					flag = false;
				}
			
				if (flag) {
					//personData = gson.fromJson(SDA_DATA_READER, SDA_Citizenship.class);
					NameModel nameG = null;
					fromGeoTranslatedList = translator.translateFromGeo(personData.getFname());
					/*
					 * Set<String> hs = new HashSet<>(); hs.addAll(fromGeoTranslatedList);
					 * fromGeoTranslatedList.clear(); fromGeoTranslatedList.addAll(hs);
					 */
					for (int i = 0; i < fromGeoTranslatedList.size() - 1; i++) {
						nameG = new NameModel(fromGeoTranslatedList.get(i), personData.getFname(), "Geo", "U",
								"SDA_Citizenship");
						// if (!hiveDB.checkIfExist(nameG.getNameEng(), nameG.getNameGeo(),
						// nameG.getTransDirection(), nameG.getTransMetod()))
						writeFromGeoNames.write(nameG.toString());
					}
					nameG = new NameModel(fromGeoTranslatedList.get(fromGeoTranslatedList.size() - 1),
							personData.getFname(), "Geo", "G", "SDA_Citizenship");
					// if (!hiveDB.checkIfExist(nameG.getNameEng(), nameG.getNameGeo(),
					// nameG.getTransDirection(), nameG.getTransMetod()))
					writeFromGeoNames.write(nameG.toString());
					System.out.println(nameG.toString());

					fromGeoTranslatedList = translator.translateFromGeo(personData.getLname());
					/*
					 * hs = new HashSet<>(); hs.addAll(fromGeoTranslatedList);
					 * fromGeoTranslatedList.clear(); fromGeoTranslatedList.addAll(hs);
					 */
					for (int i = 0; i < fromGeoTranslatedList.size() - 1; i++) {
						nameG = new NameModel(fromGeoTranslatedList.get(i), personData.getLname(), "Geo", "U",
								"SDA_Citizenship");
						// if (!hiveDB.checkIfExist(nameG.getNameEng(), nameG.getNameGeo(),
						// nameG.getTransDirection(), nameG.getTransMetod()))
						writeFromGeoNames.write(nameG.toString());
					}
					nameG = new NameModel(fromGeoTranslatedList.get(fromGeoTranslatedList.size() - 1),
							personData.getLname(), "Geo", "G", "SDA_Citizenship");
					// if (!hiveDB.checkIfExist(nameG.getNameEng(), nameG.getNameGeo(),
					// nameG.getTransDirection(), nameG.getTransMetod()))
					writeFromGeoNames.write(nameG.toString());
					System.out.println(nameG.toString());
				}

			}
			fs.close();
			outputStream.close();
			SDA_DATA_READER.close();
			writeFromGeoNames.close();

		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex);
		} finally {

		}

		System.out.println("-- App ended --");
	}

}
