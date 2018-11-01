package ge.umas.mfa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MfaModel {

	@SerializedName("Id")
	@Expose
	private String id;
	@SerializedName("Operation")
	@Expose
	private Integer operation;
	@SerializedName("OperationDate")
	@Expose
	private String operationDate;
	@SerializedName("VisaType")
	@Expose
	private Integer visaType;
	@SerializedName("VisaTypeValue")
	@Expose
	private String visaTypeValue;
	@SerializedName("VisaId")
	@Expose
	private Integer visaId;
	@SerializedName("VisaIssuePlace")
	@Expose
	private String visaIssuePlace;
	@SerializedName("FinishDate")
	@Expose
	private String finishDate;
	@SerializedName("ConsulateId")
	@Expose
	private Object consulateId;
	@SerializedName("ConsulateName")
	@Expose
	private Object consulateName;
	@SerializedName("VisaIssuerOrganizationId")
	@Expose
	private Object visaIssuerOrganizationId;
	@SerializedName("VisaIssuerOrganizationName")
	@Expose
	private Object visaIssuerOrganizationName;
	@SerializedName("IsInvited")
	@Expose
	private Object isInvited;
	@SerializedName("InvitationNumber")
	@Expose
	private Object invitationNumber;
	@SerializedName("ResultId")
	@Expose
	private Integer resultId;
	@SerializedName("ResultValue")
	@Expose
	private String resultValue;
	@SerializedName("VisaApplicantId")
	@Expose
	private Integer visaApplicantId;
	@SerializedName("HasActiveVisa")
	@Expose
	private Boolean hasActiveVisa;
	@SerializedName("CanLiveInOtherCountry")
	@Expose
	private Boolean canLiveInOtherCountry;
	@SerializedName("OtherCountryId")
	@Expose
	private Object otherCountryId;
	@SerializedName("OtherCountryValue")
	@Expose
	private Object otherCountryValue;
	@SerializedName("StayInGeorgiaHotelName")
	@Expose
	private Object stayInGeorgiaHotelName;
	@SerializedName("StayInGeorgiaHotelAddress")
	@Expose
	private Object stayInGeorgiaHotelAddress;
	@SerializedName("StayInGeorgiaHotelComment")
	@Expose
	private Object stayInGeorgiaHotelComment;
	@SerializedName("DateOfEntry")
	@Expose
	private String dateOfEntry;
	@SerializedName("DateOfDeparture")
	@Expose
	private String dateOfDeparture;
	@SerializedName("PurposeOfVisitInGeorgiaId")
	@Expose
	private Integer purposeOfVisitInGeorgiaId;
	@SerializedName("PurposeOfVisitInGeorgiaValue")
	@Expose
	private String purposeOfVisitInGeorgiaValue;
	@SerializedName("IsFirstVisitInGeorgia")
	@Expose
	private Object isFirstVisitInGeorgia;
	@SerializedName("VisitInGeorgiaReason")
	@Expose
	private Object visitInGeorgiaReason;
	@SerializedName("VisitInGeorgiaDateFrom")
	@Expose
	private String visitInGeorgiaDateFrom;
	@SerializedName("VisitInGeorgiaDateTo")
	@Expose
	private String visitInGeorgiaDateTo;
	@SerializedName("IsVirtualVisa")
	@Expose
	private Boolean isVirtualVisa;
	@SerializedName("TypeOfVisaId")
	@Expose
	private Integer typeOfVisaId;
	@SerializedName("TypeOfVisaValue")
	@Expose
	private String typeOfVisaValue;
	@SerializedName("NumberOfEntries")
	@Expose
	private Integer numberOfEntries;
	@SerializedName("NumberOfEntriesValue")
	@Expose
	private String numberOfEntriesValue;
	@SerializedName("FirstName")
	@Expose
	private Object firstName;
	@SerializedName("LastName")
	@Expose
	private String lastName;
	@SerializedName("Citizenships")
	@Expose
	private String citizenships;
	@SerializedName("DateOfBirth")
	@Expose
	private String dateOfBirth;
	@SerializedName("Sex")
	@Expose
	private Integer sex;
	@SerializedName("PlaceOfBirth")
	@Expose
	private Object placeOfBirth;
	@SerializedName("PassportNumber")
	@Expose
	private String passportNumber;
	@SerializedName("PassportValidUntil")
	@Expose
	private String passportValidUntil;
	@SerializedName("PassportType")
	@Expose
	private Integer passportType;
	@SerializedName("PassportTypeValue")
	@Expose
	private String passportTypeValue;
	@SerializedName("PersonalNumber")
	@Expose
	private Object personalNumber;
	@SerializedName("TravelPassportTypeId")
	@Expose
	private Object travelPassportTypeId;
	@SerializedName("TravelPassportTypeValue")
	@Expose
	private Object travelPassportTypeValue;
	@SerializedName("TravelPassportIssuer")
	@Expose
	private Object travelPassportIssuer;
	@SerializedName("VisaIssueDate")
	@Expose
	private String visaIssueDate;
	@SerializedName("DocumentSeries")
	@Expose
	private String documentSeries;
	@SerializedName("DocumentNo")
	@Expose
	private String documentNo;
	@SerializedName("ValidFrom")
	@Expose
	private String validFrom;
	@SerializedName("ValidTo")
	@Expose
	private String validTo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	public Integer getVisaType() {
		return visaType;
	}

	public void setVisaType(Integer visaType) {
		this.visaType = visaType;
	}

	public String getVisaTypeValue() {
		return visaTypeValue;
	}

	public void setVisaTypeValue(String visaTypeValue) {
		this.visaTypeValue = visaTypeValue;
	}

	public Integer getVisaId() {
		return visaId;
	}

	public void setVisaId(Integer visaId) {
		this.visaId = visaId;
	}

	public String getVisaIssuePlace() {
		return visaIssuePlace;
	}

	public void setVisaIssuePlace(String visaIssuePlace) {
		this.visaIssuePlace = visaIssuePlace;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public Object getConsulateId() {
		return consulateId;
	}

	public void setConsulateId(Object consulateId) {
		this.consulateId = consulateId;
	}

	public Object getConsulateName() {
		return consulateName;
	}

	public void setConsulateName(Object consulateName) {
		this.consulateName = consulateName;
	}

	public Object getVisaIssuerOrganizationId() {
		return visaIssuerOrganizationId;
	}

	public void setVisaIssuerOrganizationId(Object visaIssuerOrganizationId) {
		this.visaIssuerOrganizationId = visaIssuerOrganizationId;
	}

	public Object getVisaIssuerOrganizationName() {
		return visaIssuerOrganizationName;
	}

	public void setVisaIssuerOrganizationName(Object visaIssuerOrganizationName) {
		this.visaIssuerOrganizationName = visaIssuerOrganizationName;
	}

	public Object getIsInvited() {
		return isInvited;
	}

	public void setIsInvited(Object isInvited) {
		this.isInvited = isInvited;
	}

	public Object getInvitationNumber() {
		return invitationNumber;
	}

	public void setInvitationNumber(Object invitationNumber) {
		this.invitationNumber = invitationNumber;
	}

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public Integer getVisaApplicantId() {
		return visaApplicantId;
	}

	public void setVisaApplicantId(Integer visaApplicantId) {
		this.visaApplicantId = visaApplicantId;
	}

	public Boolean getHasActiveVisa() {
		return hasActiveVisa;
	}

	public void setHasActiveVisa(Boolean hasActiveVisa) {
		this.hasActiveVisa = hasActiveVisa;
	}

	public Boolean getCanLiveInOtherCountry() {
		return canLiveInOtherCountry;
	}

	public void setCanLiveInOtherCountry(Boolean canLiveInOtherCountry) {
		this.canLiveInOtherCountry = canLiveInOtherCountry;
	}

	public Object getOtherCountryId() {
		return otherCountryId;
	}

	public void setOtherCountryId(Object otherCountryId) {
		this.otherCountryId = otherCountryId;
	}

	public Object getOtherCountryValue() {
		return otherCountryValue;
	}

	public void setOtherCountryValue(Object otherCountryValue) {
		this.otherCountryValue = otherCountryValue;
	}

	public Object getStayInGeorgiaHotelName() {
		return stayInGeorgiaHotelName;
	}

	public void setStayInGeorgiaHotelName(Object stayInGeorgiaHotelName) {
		this.stayInGeorgiaHotelName = stayInGeorgiaHotelName;
	}

	public Object getStayInGeorgiaHotelAddress() {
		return stayInGeorgiaHotelAddress;
	}

	public void setStayInGeorgiaHotelAddress(Object stayInGeorgiaHotelAddress) {
		this.stayInGeorgiaHotelAddress = stayInGeorgiaHotelAddress;
	}

	public Object getStayInGeorgiaHotelComment() {
		return stayInGeorgiaHotelComment;
	}

	public void setStayInGeorgiaHotelComment(Object stayInGeorgiaHotelComment) {
		this.stayInGeorgiaHotelComment = stayInGeorgiaHotelComment;
	}

	public String getDateOfEntry() {
		return dateOfEntry;
	}

	public void setDateOfEntry(String dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	public String getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(String dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public Integer getPurposeOfVisitInGeorgiaId() {
		return purposeOfVisitInGeorgiaId;
	}

	public void setPurposeOfVisitInGeorgiaId(Integer purposeOfVisitInGeorgiaId) {
		this.purposeOfVisitInGeorgiaId = purposeOfVisitInGeorgiaId;
	}

	public String getPurposeOfVisitInGeorgiaValue() {
		return purposeOfVisitInGeorgiaValue;
	}

	public void setPurposeOfVisitInGeorgiaValue(String purposeOfVisitInGeorgiaValue) {
		this.purposeOfVisitInGeorgiaValue = purposeOfVisitInGeorgiaValue;
	}

	public Object getIsFirstVisitInGeorgia() {
		return isFirstVisitInGeorgia;
	}

	public void setIsFirstVisitInGeorgia(Object isFirstVisitInGeorgia) {
		this.isFirstVisitInGeorgia = isFirstVisitInGeorgia;
	}

	public Object getVisitInGeorgiaReason() {
		return visitInGeorgiaReason;
	}

	public void setVisitInGeorgiaReason(Object visitInGeorgiaReason) {
		this.visitInGeorgiaReason = visitInGeorgiaReason;
	}

	public String getVisitInGeorgiaDateFrom() {
		return visitInGeorgiaDateFrom;
	}

	public void setVisitInGeorgiaDateFrom(String visitInGeorgiaDateFrom) {
		this.visitInGeorgiaDateFrom = visitInGeorgiaDateFrom;
	}

	public String getVisitInGeorgiaDateTo() {
		return visitInGeorgiaDateTo;
	}

	public void setVisitInGeorgiaDateTo(String visitInGeorgiaDateTo) {
		this.visitInGeorgiaDateTo = visitInGeorgiaDateTo;
	}

	public Boolean getIsVirtualVisa() {
		return isVirtualVisa;
	}

	public void setIsVirtualVisa(Boolean isVirtualVisa) {
		this.isVirtualVisa = isVirtualVisa;
	}

	public Integer getTypeOfVisaId() {
		return typeOfVisaId;
	}

	public void setTypeOfVisaId(Integer typeOfVisaId) {
		this.typeOfVisaId = typeOfVisaId;
	}

	public String getTypeOfVisaValue() {
		return typeOfVisaValue;
	}

	public void setTypeOfVisaValue(String typeOfVisaValue) {
		this.typeOfVisaValue = typeOfVisaValue;
	}

	public Integer getNumberOfEntries() {
		return numberOfEntries;
	}

	public void setNumberOfEntries(Integer numberOfEntries) {
		this.numberOfEntries = numberOfEntries;
	}

	public String getNumberOfEntriesValue() {
		return numberOfEntriesValue;
	}

	public void setNumberOfEntriesValue(String numberOfEntriesValue) {
		this.numberOfEntriesValue = numberOfEntriesValue;
	}

	public Object getFirstName() {
		return firstName;
	}

	public void setFirstName(Object firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCitizenships() {
		return citizenships;
	}

	public void setCitizenships(String citizenships) {
		this.citizenships = citizenships;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Object getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(Object placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassportValidUntil() {
		return passportValidUntil;
	}

	public void setPassportValidUntil(String passportValidUntil) {
		this.passportValidUntil = passportValidUntil;
	}

	public Integer getPassportType() {
		return passportType;
	}

	public void setPassportType(Integer passportType) {
		this.passportType = passportType;
	}

	public String getPassportTypeValue() {
		return passportTypeValue;
	}

	public void setPassportTypeValue(String passportTypeValue) {
		this.passportTypeValue = passportTypeValue;
	}

	public Object getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(Object personalNumber) {
		this.personalNumber = personalNumber;
	}

	public Object getTravelPassportTypeId() {
		return travelPassportTypeId;
	}

	public void setTravelPassportTypeId(Object travelPassportTypeId) {
		this.travelPassportTypeId = travelPassportTypeId;
	}

	public Object getTravelPassportTypeValue() {
		return travelPassportTypeValue;
	}

	public void setTravelPassportTypeValue(Object travelPassportTypeValue) {
		this.travelPassportTypeValue = travelPassportTypeValue;
	}

	public Object getTravelPassportIssuer() {
		return travelPassportIssuer;
	}

	public void setTravelPassportIssuer(Object travelPassportIssuer) {
		this.travelPassportIssuer = travelPassportIssuer;
	}

	public String getVisaIssueDate() {
		return visaIssueDate;
	}

	public void setVisaIssueDate(String visaIssueDate) {
		this.visaIssueDate = visaIssueDate;
	}

	public String getDocumentSeries() {
		return documentSeries;
	}

	public void setDocumentSeries(String documentSeries) {
		this.documentSeries = documentSeries;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

}