package test;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveJdbcClientv1 {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static String ConnectionURL = "jdbc:hive2://master3.sda.gov.ge:10000/umasdbtest;"
			+ "principal=hive/master3.sda.gov.ge@CRA.GE;" + "auth=KERBEROS;";

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		// replace "hive" here with the name of the user the queries should run as
		Connection con = DriverManager.getConnection(ConnectionURL);
		System.out.println("got connection");
		con.close();

	}
}