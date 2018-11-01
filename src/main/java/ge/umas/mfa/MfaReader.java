package ge.umas.mfa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MfaReader {

	public static void main(String[] args) throws IOException {
		try {
			InputStream inputStream = System.in;
			//FileReader fr = new FileReader("C:\\Users\\b.saldadze\\Desktop\\MFA\\2017-11-01");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			//BufferedReader bufferedReader = new BufferedReader(fr);
			
			OutputStream outputStream = System.out;
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			
			Gson gson = new GsonBuilder().create();

			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				Visa visaModel = gson.fromJson(line, Visa.class);
				visaModel.ETL();
				if (visaModel.isValid()) {
					bufferedWriter.write(gson.toJson(visaModel) + "\n");
                }
			}

			

			bufferedReader.close();
			bufferedWriter.close();
			outputStream.close();
			//inputStream.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
