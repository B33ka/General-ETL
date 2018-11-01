package ge.umas.mfa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;

import ge.umas.kerberos.auth.HdfsConnector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MfaHDFSReader {
	
/*	  final static String folderNameRead =  "\\UMAS\\test\\MFA\\ETL\\"; 
	  final static String  folderNameWrite = "\\UMAS\\test\\MFA\\ETL_RESULT\\";*/
	 
	final static String folderNameRead = "\\UMAS\\Originals\\Source\\MFA\\Visa\\";
	final static String folderNameWrite = "\\UMAS\\Production\\Source\\MFA\\Visa\\";
	static String fileName;
	final static Logger logger = Logger.getLogger(MfaHDFSReader.class);

	public static void main(String[] args) throws UnsupportedEncodingException {
		org.apache.log4j.BasicConfigurator.configure(new NullAppender());
		try {
			FileSystem fs = FileSystem.get(HdfsConnector.loginUser());
			FileStatus[] fileStatuses = fs.listStatus(new Path(folderNameRead));
			Gson gson = new GsonBuilder().create();

			for (FileStatus fileStatus : fileStatuses) {
				if (!fileStatus.isDirectory()) {
					fileName = fileStatus.getPath().getName();

					Path hdfswritepath = new Path(folderNameWrite + fileName);
					FSDataOutputStream outputStream = fs.create(hdfswritepath);
					InputStream inputStream = fs.open(new Path(folderNameRead + fileName));
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
					String line = "";
					String source = "";
					while ((line = bufferedReader.readLine()) != null) {
						source = source + line;
					}
					Visa[] visaModelList = gson.fromJson(source, Visa[].class);
					source = null;
					line = null;
					BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));

					int i = 0;
					for (Visa visa : visaModelList) {
						visa.ETL();
						if (visa.isValid())
							bufferedWriter.write(gson.toJson(visa) + "\n");

					}
					bufferedWriter.close();
					logger.info(fileName + " file has been cleaned and write into HDFS");
					System.out.println(fileName + " file has been cleaned and write into HDFS");
				}
			}
			fs.close();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("BorderCross ETL Finished !");
	}

}
