package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Super 
{ 
    public Integer getLength() 
    {
        return new Integer(4); 
    } 
} 

public class Sub 
{ 
   public static void main(String args[]){
	   String str = "88Hello 3World!";      
	    str = str.replaceAll("[^-?0-9]+", " "); 
	    str = str.replaceAll("[^-?0-9]+" , " ") ;
	       String[] strNumbers = str.trim().split(" ");
	       int sum = 0 ; 
	       for(String strNumber : strNumbers){
	            sum += Integer.valueOf(strNumber) ;
	        }
	    System.out.println(sum) ;
   }

    
}

