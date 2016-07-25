package test;

public class StringReverser {
	
	public String ReverseString (String s) {
		
		if (s == null) {
			System.out.println("thats wrong, fix it!");
		}
					
		Integer stringSize = s.length();
		
		if(stringSize == 0) {
			return s;	
		} else if (stringSize == 1) {
			return s;
		}
	
		char lastChar;
		String shortened;
		String returnString = "";
		
		lastChar = s.charAt(s.length() - 1);
		returnString = returnString + lastChar;
		shortened = s.substring(0, s.length() - 1);
		
		ReverseString(shortened);

		return returnString;

	}
	
	
	public static void main(String args[]) {
		String test = "my string";
		
		StringReverser sr = new StringReverser();
		
		
		
		System.out.println(sr.ReverseString(test));
		
		
	}
	
	
}