package ge.umas.models.naprXML;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.w3c.dom.Node;

public class NaprEtl {

	static String fieldsEtl(Node node) {
		String nodeName = node.getNodeName();
		String nodeValue = node.getFirstChild().getTextContent();
		if (nodeName.equals("FirstName") || nodeName.equals("LasName")) {
			nodeName = parseValidNameString(nodeName);
		} else if (nodeName.equals("Status") || nodeName.equals("RegistrationMunicipality")
				|| nodeName.equals("LegalForm") || nodeName.equals("EntityType") || nodeName.equals("Country")
				|| nodeName.equals("PersonType") || nodeName.equals("ContactType") || nodeName.equals("Name")
				|| nodeName.equals("AccountNumber")) {
			nodeValue = parseValidTextString(nodeValue);
		} else if (nodeName.equals("IdNumber") || nodeName.equals("StatusId") || nodeName.equals("TaxAuthorityId")
				|| nodeName.equals("RegistrationMunicipalityID") || nodeName.equals("EntityTypeId")
				|| nodeName.equals("CountryID") || nodeName.equals("CardTypeId") || nodeName.equals("PersonId")
				|| nodeName.equals("PersonTypeId") || nodeName.equals("RepresentRightsId")) {
			nodeValue = parseValidNumberString(nodeValue);
		} else if (nodeName.equals("CardNumber")) {
			nodeName = nodeName.replaceAll("[^\\w]+", "");
		} else if (nodeName.equals("GovRegDate") || nodeName.equals("LastChangeDate")
				|| nodeName.equals("BirthDate")) {
			nodeValue = ParseDate(nodeValue);
		}
		return nodeValue;
	}

	private static String parseValidNumberString(String str) {
		if (str != null) {
			str = str.replaceAll("[^\\d\\s]+", "");
			str = str.trim();
		}
		return str;
	}

	private static String ParseDate(String str) {
		DateTimeFormatter f = DateTimeFormatter.ISO_OFFSET_DATE;
		DateTimeFormatter fz = DateTimeFormatter.ISO_DATE;
		if (str != null) {

			try {
				LocalDate date = LocalDate.parse(str, f);
				str = date.format(fz);

			} catch (DateTimeException e) {
				System.out.println("ERROR: " + str + " is malformed! " + e.getMessage());
			}

		}
		return str;
	}

	private static String parseValidTextString(String str) {
		if (str != null) {
			str = str.replaceAll("[^\\wა-ჰ-@,.\\s]+", "");
			str = str.trim();
		}
		return str;
	}

	private static String parseValidNameString(String str) {
		String result = str;
		if ((result != null) && (result.length() > 0)) {
			result = result.replaceAll("[,.\\-\\-\r\n]", " ");
			result = result.replaceAll("[0-9`~!@#$%^&*()+=}{|\":;?><|_]+", "");
			result = result.trim();
			result = result.toUpperCase();
		};
		return result;
	}

}
