package ge.umas.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class LettersMap {
	static Map<String, ArrayList<String>> geoLetterMap = new HashMap<String, ArrayList<String>>();
	static Map<String, ArrayList<String>> engLetterMap = new HashMap<String, ArrayList<String>>();
	final static String geoConfLocation = "\\UMAS\\test\\Beka_Name_Dictionary\\LetterConfig\\GeoToEngMapper.JSON";
	final static String engConfLocation = "\\UMAS\\test\\Beka_Name_Dictionary\\LetterConfig\\EngToGeoMapper.JSON";

	static Path geoConfPath = new Path(geoConfLocation);
	static Path engConfPath = new Path(engConfLocation);

	public static void mapLetters(FileSystem fs) throws IOException {

		JsonReader geoReader = new JsonReader(new InputStreamReader(fs.open(geoConfPath), "UTF-8"));
		JsonReader engReader = new JsonReader(new InputStreamReader(fs.open(engConfPath), "UTF-8"));

		Gson gson = new GsonBuilder().create();
		geoLetterMap = gson.fromJson(geoReader, geoLetterMap.getClass());
		engLetterMap = gson.fromJson(engReader, engLetterMap.getClass());

		System.out.println("Letter Map JSON files has read succesfuly!");
		geoReader.close();
		engReader.close();
	}

	public static Map<String, ArrayList<String>> getGeoLetterMap() {
		return geoLetterMap;
	}

	public static Map<String, ArrayList<String>> getEngLetterMap() {
		return engLetterMap;
	}

}
