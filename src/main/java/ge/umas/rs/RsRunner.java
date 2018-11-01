package ge.umas.rs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBException;

public class RsRunner {

	public static void main(String[] method) throws JAXBException, IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		Router route = new Router();

		try {
			// File fr = new File("C:\\Users\\b.saldadze\\Desktop\\RS\\border1");
			// BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new
			// FileInputStream(fr), "UTF8"));

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, StandardCharsets.UTF_8));

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));

			if (method.length > 0 && method[0] != null) {
				String methodName = method[0];
				switch (methodName) {
				case "TaxFree":
					route.TaxFree(bufferedReader, bufferedWriter);
					break;
				case "BorderCrossDecl":
					route.BorderCrossDecl(bufferedReader, bufferedWriter);
					break;
				case "Complaints":
					route.Complaints(bufferedReader, bufferedWriter);
					break;
				case "TaxOffense":
					route.TaxOffense(bufferedReader, bufferedWriter);
					break;
				case "Incasso":
					route.Incasso(bufferedReader, bufferedWriter);
					break;
				case "PersonIncome":
					route.PersonIncome(bufferedReader, bufferedWriter);
					break;
				case "Pay":
					route.Pay(bufferedReader, bufferedWriter);
					break;
				case "PersonBalance":
					route.PersonBalance(bufferedReader, bufferedWriter);
					break;
				case "TaxLow":
					route.TaxLow(bufferedReader, bufferedWriter);
					break;
				case "MonthPay":
					route.MonthPay(bufferedReader, bufferedWriter);
					break;
				case "ExiseDecl":
					route.ExiseDecl(bufferedReader, bufferedWriter);
					break;
				case "PayLegal":
					route.PayLegal(bufferedReader, bufferedWriter);
					break;
				case "Person":
					route.Person(bufferedReader, bufferedWriter);
					break;
				}

			}
			bufferedReader.close();
			bufferedWriter.close();
			outputStream.close();
			inputStream.close();

		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}

	}

}
