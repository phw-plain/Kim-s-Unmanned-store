package code;

public class isType {
	public static boolean isNum(String str) {
		char tmp;
		for(int i=0; i<str.length(); i++) {
			tmp = str.charAt(i);
			if(!('0' <= tmp && tmp <= '9' || tmp == '.')) {
				return false;
			}
		}
		return true;
	}
	public static boolean isString1(String str) {
		boolean result;
		if(!str.matches("[0-9|a-z|A-Z|]*"))
			return false;
		return true;
	}
	public static boolean isString2(String str) {
		boolean result;
		if(!str.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) 
			return false;
		return true;
	}
}