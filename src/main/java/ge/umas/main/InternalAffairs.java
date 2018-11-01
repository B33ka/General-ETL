package ge.umas.main;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by e.ormotsadze on 4/11/2017.
 */

public class InternalAffairs {
	private String CROSS_ID;
	private String FIRST_NAME_EN;
	private String LAST_NAME_EN;
	private String FIRST_NAME_GE;
	private String LAST_NAME_GE;
	private String COUNTRY_ID;
	private String CROSS_STATE_ID;
	private String ALLOWED_DATE;
	private String DOCUMENT_NO;
	private String DOCUMENT_COUNTRY_ID;
	private String TRANSPORT_TYPE_ID;
	private String ALLOWED_DAYS;
	private String CROSS_DATE;
	private String PERSONAL_NO;
	private String DOCUMENT_TYPE_ID;
	private String DIRECTION_ID;
	private String VISA_TYPE_ID;
	private String DIVISION_ID;
	private String VISA_VALID_DATE;
	private String GENDER_ID;
	private String BIRTH_DATE;
	private String VISA_SUB_TYPE_ID;
	private String VISA_NUMBER;

	private static String ParseDate(String str, boolean bd, String id) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		dateformat.setTimeZone(TimeZone.getTimeZone("GET"));
		Date date = null;
		if (!(str == null)) {
			String patternText = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}\\+\\d{4}";
			Pattern pattern = Pattern.compile(patternText);
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				try {
					date = dateformat.parse(str);
				} catch (ParseException e) {
					str = null;
				}
			} else {
				patternText = "(\\-?\\d+)";
				pattern = Pattern.compile(patternText);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String longText = matcher.group(0).toString();
					try {
						Long longDate = Long.parseLong(longText);
						date = new Date(longDate);
						date = DateUtils.addHours(date, -4);
					} catch (Exception e) {
						str = null;
					}
					System.out
							.println("cross_id = " + id + " - cross_date = " + longText + "date = " + date.toString());
				} else {
					str = null;
				}
			}
		}
		if (date != null) {
			if (bd) {
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(date);
				int hours = cal.get(Calendar.HOUR_OF_DAY);
				if (hours != 0) {
					if (hours == 1) {
						date = DateUtils.addHours(date, -1);
					} else {
						hours = 24 - hours;
						date = DateUtils.addHours(date, hours);
					}
				}
			}
			SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str = dateformat1.format(date);
		}
		return str;
	}

	private static String ParseDate(String str, boolean bd) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		dateformat.setTimeZone(TimeZone.getTimeZone("GET"));
		Date date = null;
		if (!(str == null)) {
			String patternText = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}\\+\\d{4}";
			Pattern pattern = Pattern.compile(patternText);
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				try {
					date = dateformat.parse(str);
				} catch (ParseException e) {
					str = null;
				}
			} else {
				patternText = "(\\-?\\d+)";
				pattern = Pattern.compile(patternText);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String longText = matcher.group(0).toString();
					try {
						Long longDate = Long.parseLong(longText);
						date = new Date(longDate);
					} catch (Exception e) {
						str = null;
					}
					System.out.println(longText);
				} else {
					str = null;
				}
			}
		}
		if (date != null) {
			// if (bd) {
			// Calendar cal = GregorianCalendar.getInstance();
			// cal.setTime(date);
			// int hours = cal.get(Calendar.HOUR_OF_DAY);
			// if (hours != 0) {
			// if (hours == 1) {
			// date = DateUtils.addHours(date, -1);
			// }
			// else {
			// hours = 24 - hours;
			// date = DateUtils.addHours(date, hours);
			// }
			// }
			// }
			// SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// str = dateformat1.format(date);
			if (bd) {
				date = DateUtils.addHours(date, 12);
				SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
				str = dateformat1.format(date);
				str = str + " 00:00:00";
			} else {
				SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				str = dateformat1.format(date);
			}
		}
		return str;
	}

	private static String parseValidLibraryString(String str) {
		String result = str;
		if (!(result == null)) {
			result = result.replaceAll("[\n\r]", " ");
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
		}
		return result;
	}

	private static String parseValidTextString(String str) {
		if (!(str == null)) {
			str = str.replaceAll("[\n\r]", " ");
			str = str.replaceAll("[`\\\\]", "");
			str = str.trim();
		}
		return str;
	}

	private static String parseValidNameString(String str) {
		String result = str;
		if ((result != null) && (result.length() > 0)) {
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
		}
		return result;
	}

	public String toString(String delimiter) {
		return parseValidTextString(CROSS_ID) + delimiter + parseValidNameString(FIRST_NAME_EN) + delimiter
				+ parseValidNameString(LAST_NAME_EN) + delimiter + parseValidNameString(FIRST_NAME_GE) + delimiter
				+ parseValidNameString(LAST_NAME_GE) + delimiter + parseValidTextString(COUNTRY_ID) + delimiter
				+ parseValidTextString(CROSS_STATE_ID) + delimiter
				+ ParseDate(parseValidTextString(ALLOWED_DATE), false) + delimiter + parseValidTextString(DOCUMENT_NO)
				+ delimiter + parseValidTextString(DOCUMENT_COUNTRY_ID) + delimiter
				+ parseValidTextString(TRANSPORT_TYPE_ID) + delimiter + parseValidTextString(ALLOWED_DAYS) + delimiter
				+ ParseDate(parseValidTextString(CROSS_DATE), false, CROSS_ID) + delimiter
				+ parseValidTextString(PERSONAL_NO) + delimiter + parseValidTextString(DOCUMENT_TYPE_ID) + delimiter
				+ parseValidTextString(DIRECTION_ID) + delimiter + parseValidTextString(VISA_TYPE_ID) + delimiter
				+ parseValidTextString(DIVISION_ID) + delimiter
				+ ParseDate(parseValidTextString(VISA_VALID_DATE), false) + delimiter + parseValidTextString(GENDER_ID)
				+ delimiter + ParseDate(parseValidTextString(BIRTH_DATE), true) + delimiter
				+ parseValidTextString(VISA_SUB_TYPE_ID) + delimiter + parseValidTextString(VISA_NUMBER) + "\n";
	}

	public boolean RemoveObject() {
		boolean result = true;
		if ((FIRST_NAME_EN == null || FIRST_NAME_EN.isEmpty()) && (LAST_NAME_EN == null || LAST_NAME_EN.isEmpty())) {
			result = false;
			return result;
		}
		if ((BIRTH_DATE == null || BIRTH_DATE.isEmpty())) {
			result = false;
			return result;
		}
		if ((GENDER_ID == null || GENDER_ID.isEmpty())) {
			result = false;
			return result;
		}
		if ((CROSS_ID == null || CROSS_ID.isEmpty())) {
			result = false;
			return result;
		}
		if ((CROSS_STATE_ID == null || CROSS_STATE_ID.isEmpty())) {
			result = false;
			return result;
		}
		if ((DIRECTION_ID == null || DIRECTION_ID.isEmpty())) {
			result = false;
			return result;
		}
		if ((CROSS_DATE == null || CROSS_DATE.isEmpty())) {
			result = false;
			return result;
		}
		return result;
	}

	@Override
	public String toString() {
		return "InternalAffairs{" + "CROSS_ID='" + CROSS_ID + '\'' + ", FIRST_NAME_EN='" + FIRST_NAME_EN + '\''
				+ ", LAST_NAME_EN='" + LAST_NAME_EN + '\'' + ", FIRST_NAME_GE='" + FIRST_NAME_GE + '\''
				+ ", LAST_NAME_GE='" + LAST_NAME_GE + '\'' + ", COUNTRY_ID='" + COUNTRY_ID + '\'' + ", CROSS_STATE_ID='"
				+ CROSS_STATE_ID + '\'' + ", ALLOWED_DATE='" + ALLOWED_DATE + '\'' + ", DOCUMENT_NO='" + DOCUMENT_NO
				+ '\'' + ", DOCUMENT_COUNTRY_ID='" + DOCUMENT_COUNTRY_ID + '\'' + ", TRANSPORT_TYPE_ID='"
				+ TRANSPORT_TYPE_ID + '\'' + ", ALLOWED_DAYS='" + ALLOWED_DAYS + '\'' + ", CROSS_DATE='" + CROSS_DATE
				+ '\'' + ", PERSONAL_NO='" + PERSONAL_NO + '\'' + ", DOCUMENT_TYPE_ID='" + DOCUMENT_TYPE_ID + '\''
				+ ", DIRECTION_ID='" + DIRECTION_ID + '\'' + ", VISA_TYPE_ID='" + VISA_TYPE_ID + '\''
				+ ", DIVISION_ID='" + DIVISION_ID + '\'' + ", VISA_VALID_DATE='" + VISA_VALID_DATE + '\''
				+ ", GENDER_ID='" + GENDER_ID + '\'' + ", BIRTH_DATE='" + BIRTH_DATE + '\'' + ", VISA_SUB_TYPE_ID='"
				+ VISA_SUB_TYPE_ID + '\'' + ", VISA_NUMBER='" + VISA_NUMBER + '\'' + '}';
	}

	public void ETL() {
		FIRST_NAME_EN = parseValidNameString(FIRST_NAME_EN);
		LAST_NAME_EN = parseValidNameString(LAST_NAME_EN);
		FIRST_NAME_GE = parseValidNameString(FIRST_NAME_GE);
		LAST_NAME_GE = parseValidNameString(LAST_NAME_GE);
		COUNTRY_ID = parseValidTextString(COUNTRY_ID);
		CROSS_STATE_ID = parseValidTextString(CROSS_STATE_ID);
		ALLOWED_DATE = ParseDate(parseValidTextString(ALLOWED_DATE), false);
		DOCUMENT_NO = parseValidTextString(DOCUMENT_NO);
		DOCUMENT_COUNTRY_ID = parseValidTextString(DOCUMENT_COUNTRY_ID);
		TRANSPORT_TYPE_ID = parseValidTextString(TRANSPORT_TYPE_ID);
		ALLOWED_DAYS = parseValidTextString(ALLOWED_DAYS);
		CROSS_DATE = ParseDate(parseValidTextString(CROSS_DATE), false, CROSS_ID);
		PERSONAL_NO = parseValidTextString(PERSONAL_NO);
		DOCUMENT_TYPE_ID = parseValidTextString(DOCUMENT_TYPE_ID);
		DIRECTION_ID = parseValidTextString(DIRECTION_ID);
		VISA_TYPE_ID = parseValidTextString(VISA_TYPE_ID);
		DIVISION_ID = parseValidTextString(DIVISION_ID);
		VISA_VALID_DATE = ParseDate(parseValidTextString(VISA_VALID_DATE), false);
		GENDER_ID = parseValidTextString(GENDER_ID);
		BIRTH_DATE = ParseDate(parseValidTextString(BIRTH_DATE), true);
		VISA_SUB_TYPE_ID = parseValidTextString(VISA_SUB_TYPE_ID);
		VISA_NUMBER = parseValidTextString(VISA_NUMBER);
	}
}
