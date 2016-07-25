package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

public class ExecutorServiceTest {

	private static Future taskTwo = null;
	private static Future taskThree = null;
	
	public int findKComplLinearTimeLinearSpace(int[] arr, int K) {
		  int count = 0;
		 
		 
		  Map<Integer, Integer> complementaries = new HashMap<Integer, Integer>();
		 
		  
		  for (int i : arr) {
		    if (complementaries.get(i) == null) complementaries.put(i, 1);
		    else complementaries.put(i, complementaries.get(i) + 1);
		  }
		 
		  Set<Integer> keys = complementaries.keySet();
		  for (Integer key : keys) {
		    int needed = K - key;
		    if (complementaries.containsKey(needed)) {
		      count += complementaries.get(key) * complementaries.get(needed);
		    }
		  }
		 
		  return count;
		}
	
	public static void main(String args[]){
		int []a = {2,45,7,3,5,1,8,9};
	    printSumPairs(a,10); 
	}
	public static void printSumPairs(int []input, int k){
	    Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();

	    for(int i=0;i<input.length;i++){

	        if(pairs.containsKey(input[i]))
	            System.out.println(input[i] +", "+ pairs.get(input[i]));
	        else
	            pairs.put(k-input[i], input[i]);
	    }

	}

}