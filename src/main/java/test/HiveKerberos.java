package test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.PrivilegedExceptionAction;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class HiveKerberos {

	// JDBC credentials
	static final String JDBC_DRIVER = "org.apache.hive.jdbc.HiveDriver";
	static final String JDBC_DB_URL = "jdbc:hive2://master3.sda.gov.ge:10000/umasdbtest;principal=hive/master3.sda.gov.ge@CRA.GE;auth=kerberos;kerberosAuthType=fromSubject;";

	static final String USER = "b.saldadze";
	static final String PASS = "Iphone4gs4";

	// KERBEROS Related.
	static final String KERBEROS_REALM = "CRA.GE";
	static final String KERBEROS_KDC = "cra-dc01.cra.ge";
	static final String KERBEROS_PRINCIPAL = "hive/master3.sda.gov.ge@CRA.GE";
	static final String KERBEROS_PASSWORD = "Iphone4gs1";
	// static final String jaasConfigFilePath =
	// "/Users/myuser/dev/eclipse/workspace/Test/login.conf";
	/*
	 * Contents of login.conf SampleClient {
	 * com.sun.security.auth.module.Krb5LoginModule required debug=true
	 * debugNative=true; };
	 */

	public static class MyCallbackHandler implements CallbackHandler {

		public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
			for (int i = 0; i < callbacks.length; i++) {
				if (callbacks[i] instanceof NameCallback) {
					NameCallback nc = (NameCallback) callbacks[i];
					// nc.setName(KERBEROS_PRINCIPAL);
					nc.setName(USER);
				} else if (callbacks[i] instanceof PasswordCallback) {
					PasswordCallback pc = (PasswordCallback) callbacks[i];
					pc.setPassword(PASS.toCharArray());
				} else
					throw new UnsupportedCallbackException(callbacks[i], "Unrecognised callback");
			}
		}
	}

	static Subject getSubject() {
		Subject signedOnUserSubject = null;

		// create a LoginContext based on the entry in the login.conf file
		LoginContext lc;
		try {
			lc = new LoginContext("HdfsMain", new MyCallbackHandler());
			// login (effectively populating the Subject)
			lc.login();
			// get the Subject that represents the signed-on user
			signedOnUserSubject = lc.getSubject();
		} catch (LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(0);
		}
		return signedOnUserSubject;
	}

	static Connection getConnection(Subject signedOnUserSubject) throws Exception {

		Connection conn = (Connection) Subject.doAs(signedOnUserSubject, new PrivilegedExceptionAction<Object>() {
			public Object run() {
				Connection con = null;
				try {
					Class.forName(JDBC_DRIVER);
					con = DriverManager.getConnection(JDBC_DB_URL);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return con;
			}
		});

		return conn;
	}

	public static void main(String[] args) {

		File confLoc = null;
		try {
			confLoc = new File(HiveKerberos.class.getResource("/jaas-config.conf").toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		System.setProperty("java.security.auth.login.config", confLoc.getPath());
		// System.setProperty("java.security.auth.login.config", jaasConfigFilePath);
		System.setProperty("java.security.krb5.realm", KERBEROS_REALM);
		System.setProperty("java.security.krb5.kdc", KERBEROS_KDC);

		System.out.println("-- Test started ---");
		Subject sub = getSubject();

		Connection conn = null;
		try {

			String geoName = "აქშემსეთთინ";
			String engName = "hojaberdiyev"; 

			conn = getConnection(sub);
			String insert = "INSERT INTO TABLE mia_names VALUES('TEST','ტესტი, 'ENG' , 'U' , 'MIA')";
			String sel3 = "select Distinct mia_names.name from mia_names";
			String set5 = "select Distinct name_en from names_dictionary where direction_from = 'ENG' ";
			String sql = "SELECT * FROM mia_names where name_en = '" + engName + "'";
			String sel4 = "SELECT * FROM mia_names";
			String sql1 = "SELECT * FROM names_dictionary where name_en = '" + engName + "' AND direction_from = 'ENG'";
			String sql2 = "SELECT * FROM names_dictionary where name_en = '" + engName + "' AND direction_from = 'GEO'";
			String drop = "DROP TABLE names_dictionary";

			Statement stmt = conn.createStatement();
			// int instRes = stmt.executeUpdate(insert);
									
			// ResultSet res = stmt.executeQuery(drop);
			// ResultSet rr = stmt.executeQuery("SELECT name_ge FROM names_dictionary WHERE
			// direction_from = 'ENG'");
			// System.out.println("Record count is " + forCount);
			// stmt.executeQuery("LOAD DATA INPATH
			// '/UMAS/test/Beka_Name_Dictionary/names_dictionary.csv'" + " INTO TABLE
			// names_dictionary");
			// System.out.println("Load Data into employee successful");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now)); //2016/11/16 12:08:43
			
			int count = 0;
			String nameEn = "RONAKBHAI VISHNUBHAI";
			String nameGe = "აქშემსეთთინ";
			String ss = "SELECT * FROM names_dictionary where name_en = '" + nameEn + "' AND name_ge = '" + nameGe + "'";
			
			//ResultSet res = stmt.executeQuery("SELECT * FROM names_dictionary where name_en = '" + nameEn + "'");
			ResultSet res = stmt.executeQuery("select name_en from names_ge_en");
			//ResultSet res = stmt.executeQuery(ss);
			
			while (res.next()) {
				count++;
				//System.out.println(res.getString(1));
				if(res.getString(1) != null && res.getString(1).contains(nameEn))
				System.out.println(res.getString(1) + " Count is " + count) ;
			}
			LocalDateTime now1 = LocalDateTime.now();
			System.out.println(dtf.format(now1)); //2016/11/16 12:08:43
			System.out.println("--------------------- Select Result is " + count + " -------------------------");
			// System.out.println(instRes);

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

		System.out.println("Test ended  ");
	}
}
