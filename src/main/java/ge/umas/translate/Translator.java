package ge.umas.translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.WordUtils;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.Translate.TranslateOption;

import ge.umas.config.LettersMap;

public class Translator {
	Map<String, ArrayList<String>> geoLetterMap = new HashMap<String, ArrayList<String>>();
	Map<String, ArrayList<String>> engLetterMap = new HashMap<String, ArrayList<String>>();
	ArrayList<String> variants = new ArrayList<String>();
	public static final Map<String, String> directAlphabet = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			put("A", "ა");
			put("ა", "A");
			put("B", "ბ");
			put("ბ", "B");
			put("C", "ც");
			put("გ", "G");
			put("D", "დ");
			put("დ", "D");
			put("E", "ე");
			put("ე", "E");
			put("F", "ფ");
			put("ვ", "V");
			put("G", "გ");
			put("ზ", "Z");
			put("H", "ჰ");
			put("თ", "T");
			put("I", "ი");
			put("ი", "I");
			put("J", "ჯ");
			put("კ", "K");
			put("K", "კ");
			put("ლ", "L");
			put("L", "ლ");
			put("მ", "M");
			put("M", "მ");
			put("ნ", "N");
			put("N", "ნ");
			put("ო", "O");
			put("O", "ო");
			put("პ", "P");
			put("P", "პ");
			put("ჟ", "ZH");
			put("Q", "ქ");
			put("რ", "R");
			put("R", "რ");
			put("ს", "S");
			put("S", "ს");
			put("ტ", "T");
			put("T", "ტ");
			put("უ", "U");
			put("U", "უ");
			put("ფ", "P");
			put("V", "ვ");
			put("ქ", "Q");
			put("W", "ვ");
			put("ღ", "GH");
			put("X", "ქს");
			put("ყ", "K");
			put("Y", "ი");
			put("შ", "SH");
			put("Z", "ზ");
			put("ც", "TS");
			put("TH", "თ");
			put("ძ", "DZ");
			put("PH", "ფ");
			put("ჭ", "TCH");
			put("CH", "ჩ");
			put("ხ", "KH");
			put("SH", "შ");
			put("ჯ", "J");
			put("TS", "ც");
			put("ჰ", "H");
			put("ZH", "ჟ");
			put("ჩ", "CH");
			put("DZ", "ძ");
			put("TCH", "ჭ");
			put("KH", "ხ");
			put("IY", "ი");
			put("DH", "დ");
			put(" ", " ");
			put("Ñ", "ნ");
			put("Ļ", "ლ");
			put("'", "'");
			put("Ù", "უ");
			put("Ö", "ო");
			put("–", "–");
			put("–", "–");
		}
	};

	public static final Map<String, Integer> letterWeight = new HashMap<String, Integer>() {

		private static final long serialVersionUID = 1L;

		{
			put("A", 1);
			put("თ", 2);
			put("B", 1);
			put("ი", 3);
			put("C", 2);
			put("ჟ", 3);
			put("D", 1);
			put("ს", 3);
			put("E", 1);
			put("ფ", 3);
			put("F", 1);
			put("ქ", 4);
			put("G", 1);
			put("ღ", 2);
			put("H", 3);
			put("შ", 3);
			put("I", 2);
			put("ჩ", 3);
			put("J", 2);
			put("ც", 2);
			put("K", 3);
			put("ძ", 2);
			put("L", 1);
			put("ჭ", 2);
			put("M", 1);
			put("ხ", 2);
			put("N", 1);
			put("ჯ", 2);
			put("O", 1);
			put("P", 2);
			put("Q", 1);
			put("R", 1);
			put("S", 1);
			put("T", 2);
			put("U", 1);
			put("V", 1);
			put("W", 1);
			put("X", 1);
			put("Y", 1);
			put("Z", 1);
			put("Ñ", 1);
			put("Ļ", 1);
			put("Ù", 1);
			put("Ö", 1);
		}
	};

	public ArrayList<String> translateFromGeo(String geoName) throws Exception {
		ArrayList<String> translatedList = new ArrayList<String>();
		geoLetterMap = LettersMap.getGeoLetterMap();
		String name = geoName;
		// long nameWeight = getNameWeight(name);
		for (int i = 0; i < name.length(); i++) {
			String letter = name.substring(i, i + 1);
			variants = geoLetterMap.get(letter);
			if (variants != null) {
				if (translatedList.size() < 1) {
					for (int j = 0; j < variants.size(); j++)
						translatedList.add(variants.get(j));
				} else {
					int tranLen = translatedList.size();
					int variantLen = variants.size();
					for (int k = 0; k < tranLen; k++) {
						int j = 0;
						String temp = translatedList.get(k);
						String s = temp + variants.get(j);
						translatedList.set(k, s);
						for (int v = j + 1; v < variantLen; v++) {
							String ss = temp + variants.get(v);
							translatedList.add(ss);
						}
					}

				}
			}

		}

		// translatedList.add(googleTranslate(name, true).toLowerCase());
		// System.out.println("Name: " + name + " translated!");

		/*
		 * int tranSize = translatedList.size();
		 * System.out.println("Real Trans Names Count: " + tranSize);
		 */

		return translatedList;
	}

	public ArrayList<String> translateFromEng(String engName) throws Exception {
		ArrayList<String> translatedList = new ArrayList<String>();
		int k = 0;
		engLetterMap = LettersMap.getEngLetterMap();
		int length = engName.length();
		String three = "NULL";
		String two = "NULL";
		int visit = 0;
		for (int i = 0; i < length; i++) {
			if (length >= i + 3)
				three = engName.substring(i, i + 3);
			if (length >= i + 2)
				two = engName.substring(i, i + 2);
			String one = engName.substring(i, i + 1);
			if (engLetterMap.get(three) != null) {
				variants = engLetterMap.get(three);
				three = "NULL";
				i += 2;
			} else if (engLetterMap.get(two) != null) {
				variants = engLetterMap.get(two);
				two = "NULL";
				i += 1;
			} else if (engLetterMap.get(one) != null) {
				variants = engLetterMap.get(one);
			}
			if (variants != null) {
				int temp = 0;
				for (int j = 0; j < variants.size(); j++) {
					if (visit == 0)
						translatedList.add(variants.get(j));
					else {
						long tranLength = translatedList.size();

						for (k = temp; k < tranLength; k++) {
							if (j + 1 < variants.size()) {
								translatedList.add(translatedList.get(k));
							}
							String s = translatedList.get(k) + variants.get(j);
							translatedList.set(k, s);
						}
						temp = k;
					}
				}
				visit++;
			}
		}

		// translatedList.add(googleTranslate(WordUtils.capitalizeFully(name), false));

		return translatedList;
	}

	String googleTranslate(String name, boolean lang) throws Exception {
		Translate translate = TranslateOptions.getDefaultInstance().getService();
		Translation geoToEng;
		if (lang) {
			geoToEng = translate.translate(name, TranslateOption.sourceLanguage("ka"),
					TranslateOption.targetLanguage("en"));
		} else {
			String engName = WordUtils.capitalizeFully(name);
			geoToEng = translate.translate(engName, TranslateOption.sourceLanguage("en"),
					TranslateOption.targetLanguage("ka"));
		}

		return geoToEng.getTranslatedText();
	}

	public ArrayList<String> directTranslateFromEng(String engName) throws Exception {
		ArrayList<String> translatedList = new ArrayList<String>();
		StringBuilder translated = new StringBuilder("");
		int length = engName.length();
		String triple = null;
		String pair = null;
		for (int i = 0; i < length; i++) {
			if (i <= length - 2) {
				pair = directAlphabet.get(engName.substring(i, i + 2));
				if (i <= length - 3)
					triple = directAlphabet.get(engName.substring(i, i + 3));
			}

			String s = engName.substring(i, i + 1);
			String single = "";
			if (directAlphabet.get(s) != null)
				single = directAlphabet.get(s);
			else
				single = s;

			if (triple != null) {
				translated.append(triple);
				i += 2;
			} else if (pair != null) {
				translated.append(pair);
				i += 1;
			} else {
				translated.append(single);
			}
			pair = null;
			triple = null;

		}
		translatedList.add(translated.toString());

		return translatedList;
	}

	public ArrayList<String> directTranslateFromGeo(String geoName) throws Exception {
		ArrayList<String> translatedList = new ArrayList<String>();
		StringBuilder translated = new StringBuilder("");
		int length = geoName.length();
		for (int i = 0; i < length; i++) {
			String s = geoName.substring(i, i + 1);
			String single = "";
			if (directAlphabet.get(s) != null)
				single = directAlphabet.get(s);
			else
				single = s;
			translated.append(single);

		}
		translatedList.add(translated.toString());

		return translatedList;
	}

	public long getNameWeight(String name) {
		long result = 1;
		for (int i = 0; i < name.length(); i++) {
			String letter = name.substring(i, i + 1);
			if (letterWeight.get(letter) != null)
				result *= letterWeight.get(letter);
		}

		return result;
	}

}
