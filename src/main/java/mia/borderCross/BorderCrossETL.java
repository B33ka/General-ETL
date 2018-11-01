package mia.borderCross;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mia.borderCross.DOCUMENT;
import mia.borderCross.Result;

public class BorderCrossETL {

	public static Result initBorderCrossResult(Result borderCrossResult) {
		Result result = borderCrossResult;

		result.setAUTO_TYPE_ID(ParseLong(borderCrossResult.getAUTO_TYPE_ID()));
		result.setCROSS_STATE_ID(ParseLong(borderCrossResult.getCROSS_STATE_ID()));
		result.setDIVISION_ID(ParseLong(borderCrossResult.getDIVISION_ID()));
		result.setGENDER_ID(ParseLong(borderCrossResult.getGENDER_ID()));
		result.setCROSS_ID(ParseValidTextString(borderCrossResult.getCROSS_ID()));
		result.setLAST_NAME_EN(ParseValidNameString(borderCrossResult.getLAST_NAME_EN()));
		result.setVISA_VALID_DATE(ParseDate(borderCrossResult.getVISA_VALID_DATE()));
		result.setALLOWED_DATE(ParseDate(borderCrossResult.getALLOWED_DATE()));
		result.setALLOWED_DAYS(ParseLong(borderCrossResult.getALLOWED_DAYS()));
		result.setCOUNTRY_ID(ParseLong(borderCrossResult.getCOUNTRY_ID()));
		result.setFIRST_NAME_EN(ParseValidNameString(borderCrossResult.getFIRST_NAME_EN()));
		result.setBIRTH_DATE(ParseDate(borderCrossResult.getBIRTH_DATE()));
		result.setTRANSPORT_TYPE_ID(ParseLong(borderCrossResult.getTRANSPORT_TYPE_ID()));
		result.setVISA_TYPE_ID(ParseLong(borderCrossResult.getVISA_TYPE_ID()));
		result.setVISA_NUMBER(ParseValidTextString(borderCrossResult.getVISA_NUMBER()));
		result.setLAST_NAME_GE(ParseValidNameString(borderCrossResult.getLAST_NAME_GE()));
		result.setPERSONAL_NO(ParseValidTextString(borderCrossResult.getPERSONAL_NO()));
		result.setDIRECTION_ID(ParseLong(borderCrossResult.getDIRECTION_ID()));
		result.setVISA_SUB_TYPE_ID(ParseLong(borderCrossResult.getVISA_SUB_TYPE_ID()));
		result.setCROSS_DATE(ParseDate(borderCrossResult.getCROSS_DATE()));
		result.setFIRST_NAME_GE(ParseValidNameString(borderCrossResult.getFIRST_NAME_GE()));

		if (borderCrossResult.getLAST_NAME_GE() != null && !IsGeorgianCodePage(borderCrossResult.getLAST_NAME_GE())) {
			result.setLAST_NAME_GE(null);
		}
		if (borderCrossResult.getFIRST_NAME_GE() != null && !IsGeorgianCodePage(borderCrossResult.getFIRST_NAME_GE())) {
			result.setFIRST_NAME_GE(null);
		}

		List<DOCUMENT> DOCUMENTS = new ArrayList<DOCUMENT>();
		DOCUMENTS = borderCrossResult.getDOCUMENTS();
		if (DOCUMENTS == null) {
			DOCUMENTS = new ArrayList<DOCUMENT>();
		}
		List<DOCUMENT> unicDocs = new ArrayList<DOCUMENT>();
		Set<DOCUMENT> s = new HashSet<DOCUMENT>();
		if (DOCUMENTS != null && !DOCUMENTS.isEmpty()) {
			s.addAll(DOCUMENTS);
			unicDocs.addAll(s);
		}

		if (unicDocs != null) {
			for (int i = 0; i < unicDocs.size(); i++) {
				unicDocs.get(i).setISMAIN(ParseLong(unicDocs.get(i).getISMAIN()));
				unicDocs.get(i).setDOCUMENTSUBTYPEID(ParseLong(unicDocs.get(i).getDOCUMENTSUBTYPEID()));
				unicDocs.get(i).setDOCUMENTNO(ParseValidTextString(unicDocs.get(i).getDOCUMENTNO()));
				unicDocs.get(i).setDOCUMENTCOUNTRYID(ParseLong(unicDocs.get(i).getDOCUMENTCOUNTRYID()));

			}

		}

		result.setDOCUMENTS(unicDocs);

		return result;

	}

	private static boolean IsGeorgianCodePage(String text) {
		boolean result = false;
		if (text != null) {
			String patternText = "[ა-ჰ]";
			Pattern pattern = Pattern.compile(patternText);
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				result = true;
			}
		}
		return result;
	}

	private static String ParseDate(String str) {
		String result = null;
		if (str != null) {
			DateTimeFormatter formatterR;
			DateTimeFormatter formatterW = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime date = null;
			String patternText;
			Pattern pattern;
			String matchedText;
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

	private static String ParseLong(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			result = str.trim();
			Long longData;
			try {
				longData = Long.parseLong(result);
				result = longData.toString();
			} catch (NumberFormatException e) {
				result = null;
			}
		}
		return result;
	}

	private static String ParseValidTextString(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			result = str;
			result = result.replaceAll("[\n\r]", " ");
			result = result.replaceAll("[`\\\\]", "");
			result = result.replaceAll("\\u0026amp;", "AND");
			result = result.replaceAll("\\u0026", "AND");
			result = result.trim();
			if (result.length() == 0) {
				result = null;
			}
		}
		return result;
	}

	private static String ParseValidNameString(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			result = str;
			result = result.replaceAll("[,.\\-\\-\r\n]", " ");
			result = result.replaceAll("[0-9]|[`\\\\~!@#$%^&*()+=}{|\":;?\\/><|_\\[\\]]", "");
			result = result.replaceAll("\\u0026amp;", "AND");
			result = result.replaceAll("\\u0026", "AND");
			result = result.replaceAll(" {1,}", " ");
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
			if (result.length() == 0) {
				result = null;
			}
		}
		return result;
	}

	private static String ParseDecimal(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			if (str.equals("NULL")) {
				return result;
			}
			if (str != null)
				result = str.replaceAll("[ ]", "");
			double decimalData;
			try {
				DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
				DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
				char sep = symbols.getDecimalSeparator();
				result = result.replaceAll("[,.]", String.valueOf(sep));
				decimalData = Double.parseDouble(result);
				result = String.valueOf(decimalData);
			} catch (NumberFormatException e) {
				result = null;
			}
		}
		return result;
	}

	private static boolean CodePageControl(String text) {
		boolean result = false;
		if (text != null) {
			String patternText = "[a-zA-Z]";
			Pattern pattern = Pattern.compile(patternText);
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				result = true;
			}
		}
		return result;
	}

}
