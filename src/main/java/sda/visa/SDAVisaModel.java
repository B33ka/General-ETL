package sda.visa;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SDAVisaModel {

	@SerializedName("VisaRemark")
	@Expose
	public String VisaRemark;
	@SerializedName("Lname")
	@Expose
	public String Lname;
	@SerializedName("VisaValidFrom")
	@Expose
	public String VisaValidFrom;
	@SerializedName("PersonBirtPlaceAddress")
	@Expose
	public String PersonBirtPlaceAddress;
	@SerializedName("EntryDate")
	@Expose
	public String EntryDate;
	@SerializedName("CitizenshipID")
	@Expose
	public String CitizenshipID;
	@SerializedName("Citizenship")
	@Expose
	public String Citizenship;
	@SerializedName("PrivateNumber")
	@Expose
	public String PrivateNumber;
	@SerializedName("FamilyStatusID")
	@Expose
	public String FamilyStatusID;
	@SerializedName("DoubleCitizenship")
	@Expose
	public String DoubleCitizenship;
	@SerializedName("ID")
	@Expose
	public String ID;
	@SerializedName("VisaDurationDays")
	@Expose
	public String VisaDurationDays;
	@SerializedName("JobPlace")
	@Expose
	public String JobPlace;
	@SerializedName("PassIssuedDate")
	@Expose
	public String PassIssuedDate;
	@SerializedName("PassSerialNumber")
	@Expose
	public String PassSerialNumber;
	@SerializedName("DegreeDate")
	@Expose
	public String DegreeDate;
	@SerializedName("DoubleCitizenshipID")
	@Expose
	public String DoubleCitizenshipID;
	@SerializedName("Fname")
	@Expose
	public String Fname;
	@SerializedName("VisitPuropuse")
	@Expose
	public String VisitPuropuse;
	@SerializedName("PrintDate")
	@Expose
	public String PrintDate;
	@SerializedName("PriorityAmount")
	@Expose
	public String PriorityAmount;
	@SerializedName("ReceiverIssuerID")
	@Expose
	public String ReceiverIssuerID;
	@SerializedName("JobTitle")
	@Expose
	public String JobTitle;
	@SerializedName("StartDate")
	@Expose
	public String StartDate;
	@SerializedName("RealEndDate")
	@Expose
	public String RealEndDate;
	@SerializedName("PriorityDurDay")
	@Expose
	public String PriorityDurDay;
	@SerializedName("IntendedStayDay")
	@Expose
	public String IntendedStayDay;
	@SerializedName("OperationType")
	@Expose
	public String OperationType;
	@SerializedName("BirthDate")
	@Expose
	public String BirthDate;
	@SerializedName("Education")
	@Expose
	public String Education;
	@SerializedName("PriorityStatus")
	@Expose
	public String PriorityStatus;
	@SerializedName("PriorityDesc")
	@Expose
	public String PriorityDesc;
	@SerializedName("PassIssuer")
	@Expose
	public String PassIssuer;
	@SerializedName("VisaSerie")
	@Expose
	public String VisaSerie;
	@SerializedName("ReceiverIssuer")
	@Expose
	public String ReceiverIssuer;
	@SerializedName("Pname")
	@Expose
	public String Pname;
	@SerializedName("VisaType")
	@Expose
	public String VisaType;
	@SerializedName("FamilyStatus")
	@Expose
	public String FamilyStatus;
	@SerializedName("VisaEntryTypeID")
	@Expose
	public String VisaEntryTypeID;
	@SerializedName("AddressID")
	@Expose
	public String AddressID;
	@SerializedName("PassPrivateNumber")
	@Expose
	public String PassPrivateNumber;
	@SerializedName("VisaNumber")
	@Expose
	public String VisaNumber;
	@SerializedName("Gender")
	@Expose
	public String Gender;
	@SerializedName("InvitingParameter")
	@Expose
	public String InvitingParameter;
	@SerializedName("IssuerID")
	@Expose
	public String IssuerID;
	@SerializedName("EducationID")
	@Expose
	public String EducationID;
	@SerializedName("DepartureDate")
	@Expose
	public String DepartureDate;
	@SerializedName("TaskStatusID")
	@Expose
	public String TaskStatusID;
	@SerializedName("PassType")
	@Expose
	public String PassType;
	@SerializedName("VisaEntryType")
	@Expose
	public String VisaEntryType;
	@SerializedName("BirthPlaceID")
	@Expose
	public String BirthPlaceID;
	@SerializedName("LnameEn")
	@Expose
	public String LnameEn;
	@SerializedName("PersonFullAddress")
	@Expose
	public String PersonFullAddress;
	@SerializedName("EndDate")
	@Expose
	public String EndDate;
	@SerializedName("Issuer")
	@Expose
	public String Issuer;
	@SerializedName("PassExpDate")
	@Expose
	public String PassExpDate;
	@SerializedName("DeliverDate")
	@Expose
	public String DeliverDate;
	@SerializedName("TaskStatus")
	@Expose
	public String TaskStatus;
	@SerializedName("PassTypeID")
	@Expose
	public String PassTypeID;
	@SerializedName("FnameEn")
	@Expose
	public String FnameEn;
	@SerializedName("VisitPurposeID")
	@Expose
	public String VisitPurposeID;

	public void ETL() {

		Lname = parseValidNameString(Lname);
		Fname = parseValidNameString(Fname);
		Pname = parseValidNameString(Pname);
		LnameEn = parseValidNameString(LnameEn);
		FnameEn = parseValidNameString(FnameEn);

		VisaValidFrom = ParseDate(VisaValidFrom);
		EntryDate = ParseDate(EntryDate);
		DegreeDate = ParseDate(DegreeDate);
		PrintDate = ParseDate(PrintDate);
		StartDate = ParseDate(StartDate);
		RealEndDate = ParseDate(RealEndDate);
		BirthDate = ParseDate(BirthDate);
		DepartureDate = ParseDate(DepartureDate);
		EndDate = ParseDate(EndDate);
		DeliverDate = ParseDate(DeliverDate);
		PassExpDate = ParseDate(PassExpDate);
		PassIssuedDate = ParseDate(PassIssuedDate);

		OperationType = parseValidLibraryString(OperationType);
		TaskStatus = parseValidLibraryString(TaskStatus);
		ReceiverIssuer = parseValidLibraryString(ReceiverIssuer);
		VisaType = parseValidLibraryString(VisaType);
		PassType = parseValidLibraryString(PassType);
		VisaEntryType = parseValidLibraryString(VisaEntryType);
		TaskStatus = parseValidLibraryString(TaskStatus);

		ID = ParseLong(ID);
		AddressID = ParseLong(AddressID);
		BirthPlaceID = ParseLong(BirthPlaceID);
		CitizenshipID = ParseLong(CitizenshipID);
		FamilyStatusID = ParseLong(FamilyStatusID);
		VisaDurationDays = ParseLong(VisaDurationDays);
		PassSerialNumber = ParseLong(PassSerialNumber);
		DoubleCitizenshipID = ParseLong(DoubleCitizenshipID);
		PriorityAmount = ParseLong(PriorityAmount);
		ReceiverIssuerID = ParseLong(ReceiverIssuerID);
		PriorityDurDay = ParseLong(PriorityDurDay);
		IntendedStayDay = ParseLong(IntendedStayDay);
		PriorityStatus = ParseLong(PriorityStatus);
		VisaEntryTypeID = ParseLong(VisaEntryTypeID);
		PassPrivateNumber = ParseLong(PassPrivateNumber);
		VisaNumber = ParseLong(VisaNumber);
		IssuerID = ParseLong(IssuerID);
		EducationID = ParseLong(EducationID);
		TaskStatusID = ParseLong(TaskStatusID);
		PassTypeID = ParseLong(PassTypeID);
		VisitPurposeID = ParseLong(VisitPurposeID);

		PersonBirtPlaceAddress = parseValidTextString(PersonBirtPlaceAddress);
		Citizenship = parseValidTextString(Citizenship);
		JobPlace = parseValidTextString(JobPlace);
		VisitPuropuse = parseValidTextString(VisitPuropuse);
		JobTitle = parseValidTextString(JobTitle);
		Education = parseValidTextString(Education);
		FamilyStatus = parseValidTextString(FamilyStatus);
		Gender = parseValidTextString(Gender);
		PersonFullAddress = parseValidTextString(PersonFullAddress);
		PrivateNumber = parseValidTextString(PrivateNumber);
		DoubleCitizenship = parseValidTextString(DoubleCitizenship);
		PriorityDesc = parseValidTextString(PriorityDesc);
		PassIssuer = parseValidTextString(PassIssuer);
		VisaSerie = parseValidTextString(VisaSerie);
		InvitingParameter = parseValidTextString(InvitingParameter);
		Issuer = parseValidTextString(Issuer);

		ETL2();
	}

	private static String ParseLong(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			Logger.LongsCount = Logger.LongsCount + 1;
			result = str.trim();
			Long longData;
			try {
				longData = Long.parseLong(result);
				result = longData.toString();
			} catch (NumberFormatException e) {
				result = null;
				Logger.BadFormatLongCount = Logger.BadFormatLongCount + 1;
			}
		}
		return result;
	}

	private static String ParseDate(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			Logger.DateCount = Logger.DateCount + 1;

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			if (str != null) {
				try {
					LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
					str = dateTime.format(formatter);

				} catch (DateTimeException e) {
					System.out.println("ERROR: " + str + " is malformed! " + e.getMessage());
					Logger.NotValidatedDateCount = Logger.NotValidatedDateCount + 1;
				}

			}
		}
		return str;

	}

	private static String parseValidLibraryString(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			Logger.LibraryCount = Logger.LibraryCount + 1;
			result = str;
			result = result.replaceAll("[\n\r]", " ");
			result = result.replaceAll("[`\\\\]", "");
			result = result.replaceAll(" {1,}", " ");
			result = result.trim();
			if (!str.equals(result)) {
				Logger.ChangedLibraryCount = Logger.ChangedLibraryCount + 1;
			}
			if (result.length() == 0) {
				Logger.DeletedLibraryCount = Logger.DeletedLibraryCount + 1;
				result = null;
			}
		}
		return result;
	}

	private static String parseValidTextString(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			Logger.TextCount = Logger.TextCount + 1;
			result = str;
			result = result.replaceAll("[\n\r]", " ");
			result = result.replaceAll("[`\\\\]", "");
			result = result.trim();
			if (!str.equals(result)) {
				Logger.ChangedTextCount = Logger.ChangedTextCount + 1;
			}
			if (result.length() == 0) {
				Logger.DeletedTextCount = Logger.DeletedTextCount + 1;
				result = null;
			}
		}
		return result;
	}

	private static String parseValidNameString(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			result = str;
			Logger.NamesCount = Logger.NamesCount + 1;
			result = result.replaceAll("[,.\\-\\-\r\n]", " ");
			result = result.replaceAll("[0-9]|[`\\\\~!@#$%^&*()+=}{|\":;?\\/><|_\\[\\]]", "");
			result = result.replaceAll(" {1,}", " ");
			result = result.replace("^'", "");
			result = result.trim();
			result = result.toUpperCase();
			if (!str.equals(result)) {
				Logger.ChangedNameCount = Logger.ChangedNameCount + 1;
			}
			if (result.length() == 0) {
				Logger.DeletedNameCount = Logger.DeletedNameCount + 1;
				result = null;
			}
		}
		return result;
	}

	private void ETL2() {
		if (VisaValidFrom == null && VisaDurationDays == null) {
			VisaDurationDays = null;
		}
		if (TaskStatus != null && !TaskStatus.equals("მიმდინარე") && RealEndDate == null) {
			RealEndDate = DegreeDate;
		}
		if (TaskStatus != null && TaskStatus.equals("დაკმაყოფილებული") && DegreeDate == null) {
			DegreeDate = PrintDate;
		}

	}

	public boolean isValid() {
		boolean result = true;
		if (TaskStatus.equals("მიმდინარე")) {
			return false;
		} else
			return result;
	}

}