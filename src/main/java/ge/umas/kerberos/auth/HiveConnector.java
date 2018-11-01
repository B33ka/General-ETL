package ge.umas.kerberos.auth;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.PrivilegedExceptionAction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;


public class HiveConnector {
	// JDBC credentials
	static final String JDBC_DRIVER = "org.apache.hive.jdbc.HiveDriver";
	static final String JDBC_DB_URL = "jdbc:hive2://master2.sda.gov.ge:10000/umasdbtest;principal=hive/master2.sda.gov.ge@CRA.GE;auth=kerberos;kerberosAuthType=fromSubject;";

	static final String USER = "b.saldadze";
	static final String PASS = "Iphone4gs4";

	// KERBEROS Related.
	static final String KERBEROS_REALM = "CRA.GE";
	static final String KERBEROS_KDC = "cra-dc01.cra.ge";
	static final String KERBEROS_PRINCIPAL = "hive/master2.sda.gov.ge@CRA.GE";
	
	
	public static void setSystemProperties() {
		File confLoc = null;
		try {
			confLoc = new File(HiveConnector.class.getResource("/jaas-config.conf").toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		System.setProperty("java.security.auth.login.config", confLoc.getPath());
		System.setProperty("java.security.krb5.realm", KERBEROS_REALM);
		System.setProperty("java.security.krb5.kdc", KERBEROS_KDC);
	}
	
	
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
		setSystemProperties();
		// create a LoginContext based on the entry in the login.conf file
		LoginContext lc;
		try {
			lc = new LoginContext("HdfsMain", new MyCallbackHandler());
			// login (effectively populating the Subject)
			lc.login();
			// get the Subject that represents the signed-on user
			signedOnUserSubject = lc.getSubject();
		} catch (LoginException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		return signedOnUserSubject;
	}

	public static Connection getConnection() throws Exception {
		Subject signedOnUserSubject = getSubject();
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


}
