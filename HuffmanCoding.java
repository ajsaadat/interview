package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HuffmanCoding {

	public static void  main(String[] args){
		BTree tree = new BTree() ; 
		tree.value = 'A' ; 

		BTree leftTree = new BTree() ; 
		leftTree.value = 'B' ; 

		BTree leftleftTree = new BTree() ; 
		leftleftTree.value = 'C' ; 

		BTree righttleftTree = new BTree() ; 
		righttleftTree.value = 'D' ; 

		leftTree.left = leftleftTree ; 
		leftTree.right = righttleftTree ; 

		BTree rightTree = new BTree() ; 
		rightTree.value = 'E' ; 

		BTree rightrightTree = new BTree() ; 
		rightrightTree.value = 'F' ; 

		BTree leftrightrightTree = new BTree() ; 
		leftrightrightTree.value = 'G' ; 

		rightrightTree.left = leftrightrightTree ; 
		rightTree.right = rightrightTree ; 

		tree.left = leftTree ; 
		tree.right = rightTree ; 

		HuffmanCoding h = new HuffmanCoding() ;
		
		List<BinaryCode> binaryCodes = h.huffmanCoding(tree, new StringBuffer()) ;
		Collections.sort(binaryCodes);
		 
		 String inputToDecode = "0111000110" ; // ;
		 System.out.println(inputToDecode);
		 for(BinaryCode binaryCode : binaryCodes){
			
			System.out.println("code: " + binaryCode.value + " binarycode: " + binaryCode.code );
			inputToDecode = inputToDecode.replaceAll(binaryCode.code, binaryCode.value + "" ) ;
			System.out.println(inputToDecode);
		 } 
		

	}

	private List<BinaryCode>  huffmanCoding(BTree startingPoint, StringBuffer huffmanCoding){
		List<BinaryCode> binaryCodes = new ArrayList<>();
		if(startingPoint != null){
			huffmanCoding.append(0) ; 
			binaryCodes.addAll(huffmanCoding(startingPoint.left, huffmanCoding)); 
			huffmanCoding.append(1) ; 
			binaryCodes.addAll(huffmanCoding(startingPoint.right, huffmanCoding)) ; 
			if(huffmanCoding.length() != 0){
				if(startingPoint.left == null && startingPoint.right == null){
					binaryCodes.add( new BinaryCode(startingPoint.value, new String(huffmanCoding))) ;
				}
				huffmanCoding.deleteCharAt(huffmanCoding.length() - 1) ;
			}
			
			
		}else{
			huffmanCoding.deleteCharAt(huffmanCoding.length() - 1) ;
		}
		return binaryCodes ; 
	}

}

class BinaryCode implements Comparable<BinaryCode>{
	String code ; 
	char value ;
	BinaryCode(char value, String code){
		this.value = value ; 
		this.code = code ; 
	}
	@Override
	public int compareTo(BinaryCode o) {
		// TODO Auto-generated method stub
		return o.code.compareTo(code) ;
	} 
}



class BTree implements Comparable<BTree>{
	BTree left ; 
	@Override
	public String toString() {
		return "BTree [left=" + left + ", right=" + right + ", value=" + value
				+ "]";
	}
	BTree right ; 
	char value ;
	@Override
	public int compareTo(BTree o) {
		return 0;
	} 
}