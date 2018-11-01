package ge.umas.mfa;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Visa {
	private String Id;
	private String TypeOfVisaId;
	private String PassportNumber;
	private String DocumentSeries;
	private String VisaIssuerOrganizationId;
	private String ResultValue;
	private String IsVirtualVisa;
	private String NumberOfEntries;
	private String Operation;
	private String IsInvited;
	private String PurposeOfVisitInGeorgiaValue;
	private String DateOfBirth;
	private String StayInGeorgiaHotelAddress;
	private String PassportValidUntil;
	private String VisaId;
	private String PersonalNumber;
	private String PurposeOfVisitInGeorgiaId;
	private String ConsulateId;
	private String VisaIssueDate;
	private String TravelPassportTypeValue;
	private String CanLiveInOtherCountry;
	private String VisaIssuePlace;
	private String VisaApplicantId;
	private String InvitationNumber;
	private String Citizenships;
	private String PassportTypeValue;
	private String VisaTypeValue;
	private String TravelPassportTypeId;
	private String ValidFrom;
	private String NumberOfEntriesValue;
	private String PassportType;
	private String HasActiveVisa;
	private String VisitInGeorgiaDateTo;
	private String PlaceOfBirth;
	private String TravelPassportIssuer;
	private String FinishDate;
	private String VisitInGeorgiaDateFrom;
	private String ValidTo;
	private String VisitInGeorgiaReason;
	private String StayInGeorgiaHotelComment;
	private String FirstName;
	private String OtherCountryId;
	private String DateOfDeparture;
	private String OtherCountryValue;
	private String IsFirstVisitInGeorgia;
	private String LastName;
	private String ConsulateName;
	private String Sex;
	private String DocumentNo;
	private String VisaIssuerOrganizationName;
	private String DateOfEntry;
	private String OperationDate;
	private String TypeOfVisaValue;
	private String StayInGeorgiaHotelName;
	private String ResultId;
	private String VisaType;

	private static String ParseDate(String str) {
		String result = null;
		if (str != null) {
			DateTimeFormatter formatterR;
			DateTimeFormatter formatterW = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = null;
			String patternText;
			Pattern pattern;
			String matchedText;
			Long longDate;

			if (str.length() == 10) {
				str = str + " 00:00:00";
			}
			patternText = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
			pattern = Pattern.compile(patternText);
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				try {
					matchedText = matcher.group(0).toString();
					formatterR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					date = LocalDateTime.parse(matchedText, formatterR);
					result = formatterW.format(date);
				} catch (DateTimeException e) {
					result = null;
				}
			} else {
				patternText = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
				pattern = Pattern.compile(patternText);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					try {
						matchedText = matcher.group(0).toString();
						formatterR = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
						date = LocalDateTime.parse(matchedText, formatterR);
						result = formatterW.format(date);
					} catch (DateTimeException e) {
						result = null;
					}
				} else {
					result = null;
				}
			}
		}
		return result;
	}

	private static String parseValidTextString(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			result = str;
			result = result.replaceAll("[\n\r]", " ");
			result = result.replaceAll("[`\\\\]", "");
			result = result.replaceAll("\\u0026amp;", "AND");
			result = result.replaceAll("\\u0026", "AND");

			result = result.trim();
			if (!str.equals(result)) {
			}
			if (result.length() == 0) {
				result = null;
			}
		}
		return result;
	}

	private static String parseValidLibraryString(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			result = str;
			result = result.replaceAll("[\n\r]", " ");
			result = result.replaceAll("[`\\\\]", "");
			int i = 0;
			result = result.replaceAll("\\u0026amp;", "AND");
			result = result.replaceAll("\\u0026", "AND");
			while (result.length() - 1 > i) {
				String ts = result.substring(i, i + 2);
				if ("  ".contains(String.valueOf(ts))) {
					result = result.replace("  ", " ");
				} else {
					i = i + 1;
				}
			}

			result = result.trim();
			if (!str.equals(result))

				if (result.length() == 0) {
					result = null;
				}
		}
		return result;
	}

	private static String parseValidNameString(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			result = str;
			result = result.replaceAll("[,.\\-\\-\r\n]", " ");
			result = result.replaceAll("[0-9]|[`\\\\~!@#$%^&*()+=}{|\":;?\\/><|_\\[\\]]", "");
			int i = 0;
			while (result.length() - 1 > i) {
				String ts = result.substring(i, i + 2);
				if ("  ".contains(String.valueOf(ts))) {
					result = result.replace("  ", " ");
				} else {
					i = i + 1;
				}
			}
			while (result.length() > 0) {
				char ch = result.charAt(0);
				if ("[ ']".contains(String.valueOf(ch))) {
					result = result.substring(1);
				} else {
					break;
				}
			}
			result = result.trim();
			result = result.toUpperCase();
			if (!str.equals(result))
				if (result.length() == 0) {
					result = null;
				}
		}
		return result;
	}

	public void ETL() {
		TypeOfVisaId = parseValidTextString(TypeOfVisaId);
		PassportNumber = parseValidTextString(PassportNumber);
		DocumentSeries = parseValidTextString(DocumentSeries);
		VisaIssuerOrganizationId = parseValidTextString(VisaIssuerOrganizationId);
		ResultValue = parseValidLibraryString(ResultValue);
		IsVirtualVisa = parseValidTextString(IsVirtualVisa);
		NumberOfEntries = parseValidTextString(NumberOfEntries);
		Operation = parseValidLibraryString(Operation);
		IsInvited = parseValidTextString(IsInvited);
		PurposeOfVisitInGeorgiaValue = parseValidLibraryString(PurposeOfVisitInGeorgiaValue);
		DateOfBirth = ParseDate(DateOfBirth);
		StayInGeorgiaHotelAddress = parseValidTextString(StayInGeorgiaHotelAddress);
		PassportValidUntil = ParseDate(PassportValidUntil);
		VisaId = parseValidTextString(VisaId);
		PersonalNumber = parseValidTextString(PersonalNumber);
		PurposeOfVisitInGeorgiaId = parseValidTextString(PurposeOfVisitInGeorgiaId);
		ConsulateId = parseValidTextString(ConsulateId);
		VisaIssueDate = ParseDate(VisaIssueDate);
		TravelPassportTypeValue = parseValidLibraryString(TravelPassportTypeValue);
		CanLiveInOtherCountry = parseValidTextString(CanLiveInOtherCountry);
		VisaIssuePlace = parseValidTextString(VisaIssuePlace);
		VisaApplicantId = parseValidTextString(VisaApplicantId);
		InvitationNumber = parseValidTextString(InvitationNumber);
		Citizenships = parseValidTextString(Citizenships);
		PassportTypeValue = parseValidLibraryString(PassportTypeValue);
		VisaTypeValue = parseValidLibraryString(VisaTypeValue);
		TravelPassportTypeId = parseValidTextString(TravelPassportTypeId);
		ValidFrom = ParseDate(ValidFrom);
		NumberOfEntriesValue = parseValidTextString(NumberOfEntriesValue);
		PassportType = parseValidTextString(PassportType);
		HasActiveVisa = parseValidTextString(HasActiveVisa);
		VisitInGeorgiaDateTo = ParseDate(VisitInGeorgiaDateTo);
		PlaceOfBirth = parseValidTextString(PlaceOfBirth);
		TravelPassportIssuer = parseValidTextString(TravelPassportIssuer);
		FinishDate = ParseDate(FinishDate);
		VisitInGeorgiaDateFrom = ParseDate(VisitInGeorgiaDateFrom);
		ValidTo = ParseDate(ValidTo);
		VisitInGeorgiaReason = parseValidTextString(VisitInGeorgiaReason);
		StayInGeorgiaHotelComment = parseValidTextString(StayInGeorgiaHotelComment);
		FirstName = parseValidNameString(FirstName);
		OtherCountryId = parseValidTextString(OtherCountryId);
		DateOfDeparture = ParseDate(DateOfDeparture);
		OtherCountryValue = parseValidLibraryString(OtherCountryValue);
		IsFirstVisitInGeorgia = parseValidTextString(IsFirstVisitInGeorgia);
		LastName = parseValidNameString(LastName);
		ConsulateName = parseValidLibraryString(ConsulateName);
		Sex = parseValidLibraryString(Sex);
		DocumentNo = parseValidTextString(DocumentNo);
		VisaIssuerOrganizationName = parseValidLibraryString(VisaIssuerOrganizationName);
		DateOfEntry = ParseDate(DateOfEntry);
		OperationDate = ParseDate(OperationDate);
		TypeOfVisaValue = parseValidLibraryString(TypeOfVisaValue);
		StayInGeorgiaHotelName = parseValidTextString(StayInGeorgiaHotelName);
		ResultId = parseValidTextString(ResultId);
		VisaType = parseValidLibraryString(VisaType);
	}

	public boolean isValid() {
		boolean result = true;
		if ((LastName == null || LastName.equals("NULL") || LastName.isEmpty())
				&& (FirstName == null || FirstName.equals("NULL") || FirstName.isEmpty())) {
			result = false;
			return result;
		}
		if ((DateOfBirth == null || DateOfBirth.equals("NULL") || DateOfBirth.isEmpty())) {
			result = false;
			return result;
		}
		if ((Id == null || Id.equals("NULL") || Id.isEmpty())) {
			result = false;
			return result;
		}
		if ((OperationDate == null || OperationDate.equals("NULL") || OperationDate.isEmpty())) {
			result = false;
			return result;
		}
		if ((FinishDate == null || FinishDate.equals("NULL") || FinishDate.isEmpty())) {
			result = false;
			return result;
		}
		if ((VisaApplicantId == null || VisaApplicantId.equals("NULL") || VisaApplicantId.isEmpty())) {
			result = false;
			return result;
		}
		if ((Citizenships == null || Citizenships.equals("NULL") || Citizenships.isEmpty())) {
			result = false;
			return result;
		}
		if ((Sex == null || Sex.equals("NULL") || Sex.isEmpty())) {
			result = false;
			return result;
		}
		if ((ResultId != null && ResultId.equals("1"))
				&& (VisaIssueDate == null || VisaIssueDate.equals("NULL") || VisaIssueDate.isEmpty())) {
			result = false;
			return result;
		}
		if ((ResultId != null && ResultId.equals("1"))
				&& (ValidFrom == null || ValidFrom.equals("NULL") || ValidFrom.isEmpty())) {
			result = false;
			return result;
		}
		if ((ResultId != null && ResultId.equals("1"))
				&& (ValidTo == null || ValidTo.equals("NULL") || ValidTo.isEmpty())) {
			result = false;
			return result;
		}
		return result;
	}
}