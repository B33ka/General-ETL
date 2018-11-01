package ge.umas.rs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ge.umas.rs.models.methodTag.BorderCrossDeclData;
import ge.umas.rs.models.methodTag.ComplaintsData;
import ge.umas.rs.models.methodTag.ExciseDeclData;
import ge.umas.rs.models.methodTag.IncassoData;
import ge.umas.rs.models.methodTag.MonthPayData;
import ge.umas.rs.models.methodTag.PayData;
import ge.umas.rs.models.methodTag.PayLegalData;
import ge.umas.rs.models.methodTag.PersonBalanceData;
import ge.umas.rs.models.methodTag.PersonData;
import ge.umas.rs.models.methodTag.PersonIncomeData;
import ge.umas.rs.models.methodTag.TaxFreeData;
import ge.umas.rs.models.methodTag.TaxLowData;
import ge.umas.rs.models.methodTag.TaxOffenseData;
import ge.umas.rs.models.resultTag.GetBorderCrossDeclInfoResult;
import ge.umas.rs.models.resultTag.GetComplaintsInfoResult;
import ge.umas.rs.models.resultTag.GetExciseDeclInfoResult;
import ge.umas.rs.models.resultTag.GetIncassoInfoResult;
import ge.umas.rs.models.resultTag.GetMonthPayInfoResult;
import ge.umas.rs.models.resultTag.GetPayInfoResult;
import ge.umas.rs.models.resultTag.GetPayLegalInfoResult;
import ge.umas.rs.models.resultTag.GetPersonBalanceInfoResult;
import ge.umas.rs.models.resultTag.GetPersonIncomeInfoResult;
import ge.umas.rs.models.resultTag.GetPersonInfoResult;
import ge.umas.rs.models.resultTag.GetTaxFreeInfoResult;
import ge.umas.rs.models.resultTag.GetTaxLowInfoResult;
import ge.umas.rs.models.resultTag.GetTaxOffenseInfoResult;

public class Router {
	Gson gson = new GsonBuilder().create();

	protected void TaxFree(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetTaxFreeInfoResult getTaxFreeInfoResult = new GetTaxFreeInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetTaxFreeInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getTaxFreeInfoResult = (GetTaxFreeInfoResult) unmarshaller.unmarshal(stReader);

		TaxFreeData[] taxFreeData = getTaxFreeInfoResult.getRsData().getTaxFreeData();
		for (int i = 0; i < taxFreeData.length; i++) {

			taxFreeData[i].ETL();
			if (!taxFreeData[i].RemoveObject())
				writer.write(gson.toJson(taxFreeData[i]) + "\n");

		}

	}

	protected void BorderCrossDecl(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetBorderCrossDeclInfoResult getBorderCrossDeclInfoResult = new GetBorderCrossDeclInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetBorderCrossDeclInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getBorderCrossDeclInfoResult = (GetBorderCrossDeclInfoResult) unmarshaller.unmarshal(stReader);

		BorderCrossDeclData[] borderCrossDeclData = getBorderCrossDeclInfoResult.getRsData().getBorderCrossDeclData();
		for (int i = 0; i < borderCrossDeclData.length; i++) {

			borderCrossDeclData[i].ETL();
			if (!borderCrossDeclData[i].RemoveObject())
				writer.write(gson.toJson(borderCrossDeclData[i]) + "\n");

		}

	}

	protected void Complaints(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetComplaintsInfoResult getComplaintsInfoResult = new GetComplaintsInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetComplaintsInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getComplaintsInfoResult = (GetComplaintsInfoResult) unmarshaller.unmarshal(stReader);

		ComplaintsData[] complaintsData = getComplaintsInfoResult.getRsData().getComplaintsData();
		for (int i = 0; i < complaintsData.length; i++) {

			complaintsData[i].ETL();
			if (!complaintsData[i].RemoveObject())
				writer.write(gson.toJson(complaintsData[i]) + "\n");

		}

	}

	protected void TaxOffense(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetTaxOffenseInfoResult getTaxOffenseInfoResult = new GetTaxOffenseInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetTaxOffenseInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getTaxOffenseInfoResult = (GetTaxOffenseInfoResult) unmarshaller.unmarshal(stReader);

		TaxOffenseData[] taxOffenseData = getTaxOffenseInfoResult.getRsData().getTaxOffenseData();
		for (int i = 0; i < taxOffenseData.length; i++) {

			taxOffenseData[i].ETL();
			if (!taxOffenseData[i].RemoveObject())
				writer.write(gson.toJson(taxOffenseData[i]) + "\n");

		}

	}

	protected void Incasso(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetIncassoInfoResult getIncassoInfoResult = new GetIncassoInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetIncassoInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getIncassoInfoResult = (GetIncassoInfoResult) unmarshaller.unmarshal(stReader);

		IncassoData[] incassoData = getIncassoInfoResult.getRsData().getIncassoData();
		for (int i = 0; i < incassoData.length; i++) {

			incassoData[i].ETL();
			if (!incassoData[i].RemoveObject())
				writer.write(gson.toJson(incassoData[i]) + "\n");

		}

	}

	protected void PersonIncome(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetPersonIncomeInfoResult getPersonIncomeInfoResult = new GetPersonIncomeInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetPersonIncomeInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getPersonIncomeInfoResult = (GetPersonIncomeInfoResult) unmarshaller.unmarshal(stReader);

		PersonIncomeData[] personIncomeData = getPersonIncomeInfoResult.getRsData().getPersonIncomeData();
		for (int i = 0; i < personIncomeData.length; i++) {

			personIncomeData[i].ETL();
			if (!personIncomeData[i].RemoveObject())
				writer.write(gson.toJson(personIncomeData[i]) + "\n");

		}
	}

	protected void Pay(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetPayInfoResult getPayInfoResult = new GetPayInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetPayInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getPayInfoResult = (GetPayInfoResult) unmarshaller.unmarshal(stReader);

		PayData[] payData = getPayInfoResult.getRsData().getPayData();
		for (int i = 0; i < payData.length; i++) {

			payData[i].ETL();
			if (!payData[i].RemoveObject())
				writer.write(gson.toJson(payData[i]) + "\n");

		}

	}

	protected void PersonBalance(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetPersonBalanceInfoResult getPersonBalanceInfoResult = new GetPersonBalanceInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetPersonBalanceInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getPersonBalanceInfoResult = (GetPersonBalanceInfoResult) unmarshaller.unmarshal(stReader);

		PersonBalanceData[] personBalanceData = getPersonBalanceInfoResult.getRsData().getPersonBalanceData();
		for (int i = 0; i < personBalanceData.length; i++) {

			personBalanceData[i].ETL();
			if (!personBalanceData[i].RemoveObject())
				writer.write(gson.toJson(personBalanceData[i]) + "\n");

		}

	}

	protected void TaxLow(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetTaxLowInfoResult getTaxLowInfoResult = new GetTaxLowInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetTaxLowInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		getTaxLowInfoResult = (GetTaxLowInfoResult) unmarshaller.unmarshal(stReader);

		TaxLowData[] taxLowData = getTaxLowInfoResult.getRsData().getTaxLowData();
		for (int i = 0; i < taxLowData.length; i++) {

			taxLowData[i].ETL();
			if (!taxLowData[i].RemoveObject())
				writer.write(gson.toJson(taxLowData[i]) + "\n");

		}
	}

	protected void MonthPay(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetMonthPayInfoResult GetMonthPayInfoResult = new GetMonthPayInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetMonthPayInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		GetMonthPayInfoResult = (GetMonthPayInfoResult) unmarshaller.unmarshal(stReader);

		MonthPayData[] MonthPayData = GetMonthPayInfoResult.getRsData().getMonthPayData();
		for (int i = 0; i < MonthPayData.length; i++) {

			MonthPayData[i].ETL();
			if (!MonthPayData[i].RemoveObject())
				writer.write(gson.toJson(MonthPayData[i]) + "\n");

		}
	}

	protected void ExiseDecl(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {

		GetExciseDeclInfoResult GetExciseDeclInfoResult = new GetExciseDeclInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetExciseDeclInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		GetExciseDeclInfoResult = (GetExciseDeclInfoResult) unmarshaller.unmarshal(stReader);

		ExciseDeclData[] ExciseDeclData = GetExciseDeclInfoResult.getRsData().getExciseDeclData();
		for (int i = 0; i < ExciseDeclData.length; i++) {

			ExciseDeclData[i].ETL();
			if (!ExciseDeclData[i].RemoveObject())
				writer.write(gson.toJson(ExciseDeclData[i]) + "\n");

		}
	}

	protected void PayLegal(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {

		GetPayLegalInfoResult GetPayLegalInfoResult = new GetPayLegalInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetPayLegalInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		GetPayLegalInfoResult = (GetPayLegalInfoResult) unmarshaller.unmarshal(stReader);

		PayLegalData[] PayLegalData = GetPayLegalInfoResult.getRsData().getPayLegalData();
		for (int i = 0; i < PayLegalData.length; i++) {

			PayLegalData[i].ETL();
			if (!PayLegalData[i].RemoveObject())
				writer.write(gson.toJson(PayLegalData[i]) + "\n");

		}
	}

	protected void Person(BufferedReader reader, BufferedWriter writer) throws JAXBException, IOException {
		GetPersonInfoResult GetPersonInfoResult = new GetPersonInfoResult();

		JAXBContext jaxbContext = JAXBContext.newInstance(GetPersonInfoResult.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		String str = reader.lines().collect(Collectors.joining());
		str = str.replaceFirst("xmlns=\"services.rs.ge\"", "");
		StringReader stReader = new StringReader(str);

		GetPersonInfoResult = (GetPersonInfoResult) unmarshaller.unmarshal(stReader);

		PersonData[] PersonData = GetPersonInfoResult.getRsData().getPersonData();
		for (int i = 0; i < PersonData.length; i++) {

			PersonData[i].ETL();
			if (!PersonData[i].RemoveObject())
				writer.write(gson.toJson(PersonData[i]) + "\n");

		}

	}

}
