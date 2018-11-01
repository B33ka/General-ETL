package test;


public class MainApp {

	

	public static void main(String[] args) {
		String name = "KEJENASHVILI(GOLD)";
		String str = name.replaceAll("[^A-Za-z,ა-ჰ,\\s,',Ñ,ñ,Ļ,ļ,Ö,ö,Ù,ù,-,-,),(,.]+", "")
				.replaceAll("[\\,]+", " ").replaceAll("^ +", "").replaceAll(" +$", "")
				.replaceAll("[\\s]+", " ").replaceAll("[-]{1,}", " ").replaceAll("[.]{2,}", ".")
				.replaceAll("[-]{1,}", " ");
		System.out.println(str);

	}
}
