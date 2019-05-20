package sample.helper;

public class Utils {
	public static String append(String... stuff) {
		StringBuilder sb = new StringBuilder(128);
		for (String s : stuff)
			sb.append(s);
		return sb.toString();
	}
}
