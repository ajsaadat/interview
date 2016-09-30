package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;


public class Solution {

	public static void main(String[] args) {
		/* Scanner sc = new Scanner(System.in);

         int numberLines = sc.nextInt();
         List<Integer> nodeInformation = new ArrayList<Integer>() ; 
         if(numberLines >= 1 && numberLines <=1024){
        	 int numberOfNodes = 2 * numberLines; 
        	 while(numberOfNodes >= 1){
        		 nodeInformation.add(sc.nextInt()) ; 
        		 numberOfNodes-- ;
        	 }
        	 System.out.println(nodeInformation);
         }else{
        	 sc.close();
        	 throw new IllegalArgumentException() ; 
         }

         int numberOfSwaps = sc.nextInt() ; 
         List<Integer> swapLocations = new ArrayList<Integer>(numberOfSwaps) ; 
         if (numberOfSwaps >= 1 && numberOfSwaps <= 100){
        	 while(numberOfSwaps >= 1){
        		 int swapLocation = sc.nextInt() ; 
        		 if(swapLocation < 1 || swapLocation > numberLines){
        			 sc.close();
        			 throw new IllegalArgumentException("invalid swap location.");
        		 }
        		 swapLocations.add(swapLocation) ; 
        		 numberOfSwaps-- ;
        	 }
        	 System.out.println(swapLocations);
         }else{
        	 sc.close();
        	 throw new IllegalArgumentException() ; 
         }

         sc.close();

         Solution s = new Solution() ; 
         Node treeNode = s.buildATree(nodeInformation) ; 
         System.out.println(treeNode);
         for(int swapPosition : swapLocations){
        	 treeNode = s.swap(treeNode, swapPosition) ; 
        	 s.inorderTraversal(treeNode) ; 
         }*/

		Solution s = new Solution() ;
		List<Integer> nodeInformation = new ArrayList<Integer>();
		//Collections.addAll(nodeInformation,0, 2, 3, -1, 4, -1, 5, -1, -1, -1, -1) ;
		Collections.addAll(nodeInformation,0,2, 3,4, -1,5, -1, 6, -1, 7, 8,-1, 9, -1, -1,10, 11,-1, -1,-1, -1,-1, -1);
		s.inorderTraversal( s.buildATree(nodeInformation) );

	}


	private Node buildATree(List<Integer> nodeInformation){
		Deque<Node> nodes = new ArrayDeque<Node>() ; 
		Node currentNode = new Node(1) ; 
		Node tree = currentNode ; 
		nodes.add(tree) ; 
		while(!nodes.isEmpty()){
			currentNode = nodes.removeFirst() ; 
			int node = currentNode.getNodeValue() ; 
			if(node != -1){
				int nodeValue = nodeInformation.get(node*2-1) ; 
				if(nodeValue != -1){
					Node leftNode = new Node(nodeInformation.get(node*2-1)) ;
					nodes.add(leftNode) ; 
					currentNode.setLeftNode(leftNode);
				}
				nodeValue = nodeInformation.get(node*2) ;
				if(nodeValue != -1){
					Node rightNode = new Node(nodeValue ) ;
					nodes.add(rightNode) ; 
					currentNode.setRightNode(rightNode);
				}
			}else{
				continue ;
			}




		}
		return tree ; 
	}

	private Node swap(Node tree, int swapPosition){
		return null;
	}

	private void inorderTraversal(Node tree){
		if(tree != null){
			inorderTraversal(tree.getLeftNode()) ;
			System.out.print(tree.nodeValue + ",");
			inorderTraversal(tree.getRightNode()) ;
		}
	}


	class Node{
		Node leftNode ;
		Node rightNode ; 
		int nodeValue ; 

		public Node(int nodeValue){
			this.nodeValue = nodeValue ; 
		}

		public Node getRightNode() {
			return rightNode;
		}

		public void setRightNode(Node rightNode) {
			this.rightNode = rightNode;
		}

		public int getNodeValue() {
			return nodeValue;
		}

		public void setNodeValue(int nodeValule) {
			this.nodeValue = nodeValule;
		}

		public Node getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(Node leftNode) {
			this.leftNode = leftNode;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[nodeValue='" + nodeValue + "'\n" +
			"\tleftNode='" + leftNode +  "'\n" +
			"\trightNode='" + rightNode + "']\n\t";
		}

	}
}