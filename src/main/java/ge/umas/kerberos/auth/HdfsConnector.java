package ge.umas.kerberos.auth;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

public class HdfsConnector {

    static LoginUsingCredentials login = new LoginUsingCredentials();
	
    final static String serverName = "master2.sda.gov.ge"; 
    
    public static Configuration loginUser() throws IOException {
    	org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
    	conf.set("fs.defaultFS", "hdfs://" + serverName);
    	conf.set("hadoop.security.authentication", "kerberos");

    	UserGroupInformation.setConfiguration(conf);
        LoginContext lc = null;
		try {
			//System.out.println("Start loging user!..");
			lc = login.kinit();
			System.out.println("User Logged in successfully");
		} catch (LoginException e) {
			System.out.println("Error occured while Loging User");
			e.printStackTrace();
		}
        UserGroupInformation.loginUserFromSubject(lc.getSubject());
        
        return conf;
    	
    }
	
	
}
