package ge.umas.hiveDb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ge.umas.kerberos.auth.HiveConnector;

public class HiveDbApi {
	public Connection conn = null;

	public boolean checkIfExist(String name, boolean lang) {
		ResultSet res = null;
		String selectGeoName = "SELECT * FROM names_ge where name = '" + name + "'";
		String selectEngName = "SELECT * FROM mia_name where name = '" + name + "'";
		try {

			if (lang) {
				
				conn = HiveConnector.getConnection();

				Statement stmt = conn.createStatement();

				res = stmt.executeQuery(selectGeoName);
			} else {
				conn = HiveConnector.getConnection();
				Statement stmt = conn.createStatement();

				res = stmt.executeQuery(selectEngName);

			}

			while (res.next()) {
				System.out.println("Name: '" + name + "' is already in NAMES table!");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error orrued while operating in Hive, System Exit!");
			System.exit(1);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	public boolean checkOriginalIfExist(String nameEng, String nameGeo) {
		ResultSet res = null;
		String selectOriginal = "SELECT * FROM names_dictionary where names_dictionary.name_en = '" + nameEng + "' AND "
				+ " names_dictionary.name_ge = '" + nameGeo + "' AND names_dictionary.trans_method = 'O'";
		try {

			conn = HiveConnector.getConnection();

			Statement stmt = conn.createStatement();

			res = stmt.executeQuery(selectOriginal);

			while (res.next()) {
				System.out.println("Name_En: '" + nameEng + " Name_Ge: " + nameGeo
						+ "'original record is already in names_dictionary table! \n");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	public void loadData(String serviceName) {
		String loadData = null;
		if (serviceName != null) {
			if (serviceName.equals("SDA")) {
				loadData = "LOAD DATA INPATH '/UMAS/test/Beka_Name_Dictionary/Dictionary.txt'"
						+ " INTO TABLE names_dictionary";
			} else if (serviceName.equals("MIA")) {
				loadData = "LOAD DATA INPATH '/UMAS/test/Beka_Name_Dictionary/MiaDictionary.txt'"
						+ " INTO TABLE names_dictionary";
			}
		}
		try {
			conn = HiveConnector.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(loadData);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void loadDataFromFiles(String fileName) {
		String loadData = null;
		if (fileName != null) {
			loadData = "LOAD DATA INPATH '/UMAS/test/Beka_Name_Dictionary/MIA_SHORT/" + fileName
					+ "' INTO TABLE names_dictionary";
		}
		try {
			conn = HiveConnector.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(loadData);
			System.out.println("File" + fileName + " is inserted in Hive succesfuly");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
