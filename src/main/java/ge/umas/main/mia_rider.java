package ge.umas.main;

import org.apache.log4j.varia.NullAppender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.*;

public class mia_rider {
	static final String DELIMITER = "`";

	public static void main(String[] args) throws IOException {
		// Get rid of stupid log4j warning
		org.apache.log4j.BasicConfigurator.configure(new NullAppender());

		// Production\\

		final String serverName = "master2.sda.gov.ge";
		final String folderNameRead = "\\UMAS\\Production\\Source\\MIA\\BorderCrossOld\\";
		final String folderNameWrite = "\\UMAS\\Production\\Source\\MIA\\BorderCross\\";
		final String userName = "m.koberidze@CRA.GE"; // args[2];
		int recordCiunt = 0;
		int validRecordCount = 0;

		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://" + serverName);
		conf.set("hadoop.security.authentication", "kerberos");
		UserGroupInformation.setConfiguration(conf);
		UserGroupInformation.loginUserFromKeytab(userName, "C:\\Users\\m.koberidze\\Desktop\\m.koberidze.keytab");
		FileSystem fs = FileSystem.get(conf);

		FileStatus[] fileStatuses = fs.listStatus(new Path(folderNameRead));
		for (FileStatus fileStatus : fileStatuses) {
			if (!fileStatus.isDirectory()) {
				try {
					String fileName = fileStatus.getPath().getName();
					// if (fileName.contains("."))
					// {
					// fileName = fileName.substring(0, fileName.lastIndexOf("."));
					// }
					Path hdfswritepath = new Path(folderNameWrite + fileName);
					// Init output stream
					FSDataOutputStream outputStream = fs.create(hdfswritepath);
					JsonReader reader = new JsonReader(new InputStreamReader(
							fs.open(new Path(folderNameRead + fileStatus.getPath().getName())), "UTF-8"));

					reader.setLenient(true);
					Gson gson = new GsonBuilder().create();
					BufferedWriter br = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
					while (reader.hasNext()) {
						if (reader.peek().toString().matches("END_DOCUMENT"))
							break;
						InternalAffairs bcObject = gson.fromJson(reader, InternalAffairs.class);
						bcObject.ETL();
						if (bcObject.RemoveObject()) {
							br.write(gson.toJson(bcObject) + "\n");
							validRecordCount++;
						}

						// br.write(bcObject.toString(DELIMITER));
						// String temp = json.toString(DELIMITER);
						recordCiunt++;
						System.out
								.println("recordCiunt = " + recordCiunt + ";   validRecordCount = " + validRecordCount);
						///////////////////////////////////
						//
						//
						// if
						// (json.toString(DELIMITER).contains("ecc084f2-dc55-40ad-90cb-564d25ab942c")) {
						// // String[] split = json.toString(DELIMITER).split("`");
						// temp = temp + json.toString();
						// System.out.println(json.toString(DELIMITER));
						// System.out.println(json.toString());
						// // br.write(json.toString(DELIMITER));
						// }
						///////////////////////////////////////////
					}
					br.close();
					outputStream.close();
					reader.close();
					// System.out.println(recordCiunt);
				} catch (UnsupportedEncodingException ex) {
					System.out.println(ex);
				}
			}
		}
	}
}
