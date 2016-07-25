package test;

import java.util.HashMap;
import java.util.Map;

public class WordGame {
	
	
	 public static void main(String[] args) {
	        Trie dict = new Trie();        
	        dict.addWord("are");
	        dict.addWord("area");
	        dict.addWord("base");
	        dict.addWord("cat");
	        dict.addWord("cater");        
	        dict.addWord("basement");
	         
	        String input = "caterer";
	        System.out.print(input + ":   ");
	        System.out.println(dict.getMatchingPrefix(input));              
	 
	        input = "basement";
	        System.out.print(input + ":   ");
	        System.out.println(dict.getMatchingPrefix(input));                      
	         
	        input = "are";
	        System.out.print(input + ":   ");
	        System.out.println(dict.getMatchingPrefix(input));              
	 
	        input = "arex";
	        System.out.print(input + ":   ");
	        System.out.println(dict.getMatchingPrefix(input));              
	 
	        input = "basemexz";
	        System.out.print(input + ":   ");
	        System.out.println(dict.getMatchingPrefix(input));                      
	         
	        input = "xyz";
	        System.out.print(input + ":   ");
	        System.out.println(dict.getMatchingPrefix(input));                      
	    }

	
	public static class Trie{
		TrieNode root ; 
		public Trie(){
			root = new TrieNode((char)0) ;
		}

		public void addWord(String word){
			TrieNode lastNode = root ;
			
			for(int i = 0 ; i < word.length() ; i++){
				Map<Character, TrieNode> children = lastNode.getNodeChildren() ;
				char wordChar = word.charAt(i) ;
				if(children.containsKey(wordChar) ){
					lastNode = children.get(wordChar) ;
				}else{
					TrieNode newTrieNode = new TrieNode(wordChar) ;
					children.put(wordChar, newTrieNode) ;
					lastNode = newTrieNode ; 
				}
			}
			lastNode.setLast(true);
		}
		
		public String getMatchingPrefix(String input){
			String matchingPrefix = new String(); 
			
			TrieNode lastNode = root ; 
			
			int prevMatch = 0 ; 
			for(int i = 0 ; i < input.length() ; i++){
				char inputChar = input.charAt(i) ;
				Map<Character, TrieNode> children = lastNode.getNodeChildren() ;
				if(children.containsKey(inputChar)){
					lastNode = children.get(inputChar) ;
					matchingPrefix += inputChar ;
					if(lastNode.isLast()){
						prevMatch = i + 1 ; 
					}
				}else{
					break ; 
				}
				
			}
			
			if(!lastNode.isLast()){
				return matchingPrefix.substring(0, prevMatch) ;
			}else{
				return matchingPrefix ; 
			}
			
		}
	}
	
	

	public static class TrieNode{
		char nodeValue ; 
		Map<Character, TrieNode> nodeChildren = new HashMap<Character, TrieNode>() ; 
		boolean isLeaf ; 
		boolean isLast ; 

		@Override
		public String toString() {
			return "[nodeValue='" + nodeValue + "', isLeaf='" + isLeaf + "', isLast='" + isLast +"', nodeChildren='" + nodeChildren +"']";
		}
		
		public TrieNode(char nodeValue){
			this.nodeValue = nodeValue ; 
		}

		public TrieNode(char nodeValue, Map<Character, TrieNode> nodeChildren){
			this(nodeValue) ;
			if(nodeChildren != null){
				this.nodeChildren = nodeChildren ;
				isLeaf = false ; 
			}else{
				isLeaf = true ; 	
			}
		}

		public char getNodeValue() {
			return nodeValue;
		}

		public void setNodeValue(char nodeValue) {
			this.nodeValue = nodeValue;
		}

		public Map<Character, TrieNode> getNodeChildren() {
			return nodeChildren;
		}

		public void setNodeChildren(Map<Character, TrieNode> nodeChildren) {
			this.nodeChildren = nodeChildren;
		}

		public boolean isLast() {
			return isLast;
		}

		public void setLast(boolean isLast) {
			this.isLast = isLast;
		}

	}
}
