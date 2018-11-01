package ge.umas.rs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.varia.NullAppender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ge.umas.kerberos.auth.HdfsConnector;
import ge.umas.rs.models.methodTag.TaxOffenseData;
import ge.umas.rs.models.resultTag.GetTaxOffenseInfoResult;

public class RsHDFSReader {

	final static String folderNameRead = "\\UMAS\\Originals\\Source\\RS\\GetTaxOffenseInfo\\";
	final static String folderNameWrite = "\\UMAS\\Production\\Source\\RS\\GetTaxOffenseInfo\\";
	static String fileName;

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
					InputStream inputStream = fs.open(new Path(folderNameRead + fileName));
					BufferedWriter bufferedWriter = null;
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(inputStream, StandardCharsets.UTF_8));
					String line = "";
					String source = "";
					while ((line = bufferedReader.readLine()) != null) {
						source = source + line;
					}

// Changeable Snippet Start

					GetTaxOffenseInfoResult GetTaxOffenseInfoResult = new GetTaxOffenseInfoResult();

					JAXBContext jaxbContext = JAXBContext.newInstance(GetTaxOffenseInfoResult.class);

					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
					source = source.replaceFirst("xmlns=\"services.rs.ge\"", "");
					StringReader stReader = new StringReader(source);

					GetTaxOffenseInfoResult = (GetTaxOffenseInfoResult) unmarshaller.unmarshal(stReader);
					boolean checker = false;
					if (GetTaxOffenseInfoResult.getRsData() != null) {
						checker = true;
						FSDataOutputStream outputStream = fs.create(hdfswritepath);
						bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
						TaxOffenseData[] TaxOffenseData = GetTaxOffenseInfoResult.getRsData().getTaxOffenseData();
						for (int i = 0; i < TaxOffenseData.length; i++) {
							TaxOffenseData[i].ETL();
							if (TaxOffenseData.length > 0)
								bufferedWriter.write(gson.toJson(TaxOffenseData[i]) + "\n");

						}
					}

// Changeable Snippet End
					if (bufferedWriter != null)
						bufferedWriter.close();
					if (checker)
						System.out.println(fileName + " file has been cleaned and write into HDFS");
					else
						System.out.println((fileName + " file is empy"));
					checker = false;
				}
			}
			fs.close();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("RS ETL Finished !");
	}

}
