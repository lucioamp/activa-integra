package util.integra;

public class Helper {
	public static String truncate(String value, int length) {
		if (value != null && value.length() > length)
			value = value.substring(0, length);
		return value;
	}
}
