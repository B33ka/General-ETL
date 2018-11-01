package ge.umas.models.naprXML;

import java.io.IOException;
import java.io.InputStream;

import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;
import org.xml.sax.SAXException;

import ge.umas.kerberos.auth.HdfsConnector;

public class NaprGeneral {
	final static String folderNameRead = "\\UMAS\\test\\Beka_Name_Dictionary\\Napr\\";
	final static String folderNameWrite = "\\UMAS\\test\\Beka_Name_Dictionary\\NaprAfterETL\\";
	static String fileName;
	final static Logger logger = Logger.getLogger(NaprGeneral.class);

	public static void main(String[] args) throws UnsupportedEncodingException {
		org.apache.log4j.BasicConfigurator.configure(new NullAppender());
		try {
			FileSystem fs = FileSystem.get(HdfsConnector.loginUser());
			FileStatus[] fileStatuses = fs.listStatus(new Path(folderNameRead));

			for (FileStatus fileStatus : fileStatuses) {
				if (!fileStatus.isDirectory()) {
					fileName = fileStatus.getPath().getName();

					Path hdfswritepath = new Path(folderNameWrite + fileName);
					FSDataOutputStream outputStream = fs.create(hdfswritepath);
					InputStream inputStream = fs.open(new Path(folderNameRead + fileName));
					NaprReader.Etl(inputStream, outputStream);
					logger.info(fileName + " file has been cleaned and write into HDFS");
					System.out.println(fileName + " file has been cleaned and write into HDFS");
				}
			}

		} catch (SAXException sEx) {
			logger.error(
					"FATAL ERROR : Document: " + fileName + " must be well-formed in " + folderNameRead + " folder ");
			System.out.println(
					"FATAL ERROR : Document: " + fileName + " must be well-formed in " + folderNameRead + " folder ");
		} catch (IOException e) {

			e.printStackTrace();
		} catch (TransformerException e) {

			e.printStackTrace();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}
		System.out.println("Napr ETL Finished !");
	}
}