package test;


public class RecursiveStringReverser {




	public String reverseString(String inputString){
		if(inputString == null){
			throw new IllegalArgumentException("Provided string can not be null") ;
		}else if(inputString.isEmpty()){
			return new String() ; 
		}else if(inputString.length() == 1){
			return inputString ; 
		}else{
			String reversedString = new String() ; 
			char lastCharacter = inputString.charAt(inputString.length() - 1) ;
			String substring = inputString.substring(0, inputString.length() - 1) ; 

			reversedString = reverseString(substring) ;
			reversedString = lastCharacter + reversedString ; 
			return reversedString ;
		}


	}

	public static void main (String args[]){
		RecursiveStringReverser rsr = new RecursiveStringReverser() ; 
		System.out.println(rsr.reverseString("aj was here"));
		System.out.println(rsr.reverseString("a"));
		//System.out.println(rsr.reverseString(null));
		System.out.println(rsr.reverseString(new String()));
	}

}
