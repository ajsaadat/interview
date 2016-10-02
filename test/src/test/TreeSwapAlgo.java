package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;


public class TreeSwapAlgo {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);

         int numberLines = sc.nextInt();
         List<Integer> nodeInformation = new ArrayList<Integer>() ; 
         if(numberLines >= 1 && numberLines <=1024){
        	 int numberOfNodes = 2 * numberLines;
        	 nodeInformation.add(0);
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

         TreeSwapAlgo s = new TreeSwapAlgo() ; 
         Node treeNode = s.buildATree(nodeInformation) ; 
         System.out.println(treeNode);
         for(int swapPosition : swapLocations){
        	 treeNode = s.swap(treeNode, swapPosition) ; 
        	 s.traversal(treeNode) ; 
         }

		/*Solution s = new Solution() ;
		List<Integer> nodeInformation = new ArrayList<Integer>();
		//Collections.addAll(nodeInformation,0, 2, 3, -1, 4, -1, 5, -1, -1, -1, -1) ;
		Collections.addAll(nodeInformation,0, 2, 3,4, -1,5, -1, 6, -1, 7, 8,-1, 9, -1, -1,10, 11,-1, -1,-1, -1,-1, -1);
		Node tree = s.buildATree(nodeInformation) ;
		//System.out.println(tree);
		//System.out.println( s.inorderTraversal(tree));
		System.out.println(s.swap(tree, 2));*/

	}


	private Node buildATree(List<Integer> nodeInformation){
		Deque<Node> nodes = new ArrayDeque<Node>() ; 
		int height = 1 ; 
		Node currentNode = new Node(1, height) ; 
		Node tree = currentNode ; 
		nodes.add(tree) ; 
		while(!nodes.isEmpty()){
			
			currentNode = nodes.removeFirst() ; 
			int node = currentNode.getNodeValue() ; 
			if(node != -1){
				int nodeValue = nodeInformation.get(node*2-1) ; 
				if(nodeValue != -1){
					Node leftNode = new Node(nodeInformation.get(node*2-1), currentNode.height + 1) ;
					
					nodes.add(leftNode) ; 
					currentNode.setLeftNode(leftNode);
				}
				nodeValue = nodeInformation.get(node*2) ;
				if(nodeValue != -1){
					Node rightNode = new Node(nodeValue, currentNode.height + 1 ) ;
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
		List<Node> allNodes = inorderTraversal(tree) ;
		double height = treeHeight(allNodes) ;
		for(int counter = 1 ; counter*swapPosition < height ; counter++){
			List<Node> qualifiedNodes = identifyNodes(allNodes, counter*swapPosition) ;
			changePosition(qualifiedNodes);
		}
		return tree ; 
	}
	
	private int treeHeight(List<Node> allNodes){
		int height = 0 ; 
		for(Node node : allNodes){
			if(node.getHeight() > height){
				height = node.getHeight() ; 
			}
		}
		return height ; 
	}
	
	private void changePosition(List<Node> qualifiedNodes){
		for(Node qualifiedNode : qualifiedNodes){
			Node leftNode = qualifiedNode.leftNode ; 
			qualifiedNode.leftNode = qualifiedNode.rightNode ; 
			qualifiedNode.rightNode = leftNode ; 
		}
	}
	
	private List<Node> identifyNodes(List<Node> allNodes, int swapPosition){
		List<Node> nodes = new ArrayList<>(); 
		for(Node node : allNodes){
			if(node.getHeight() == swapPosition ){
				nodes.add(node) ;
			}
		}
		return nodes ; 
	}
	
	private void traversal(Node tree){
		List<Node> nodes = inorderTraversal(tree);
		for(Node node : nodes){
			System.out.println(node.nodeValue);
		}
	}
	
	private List<Node> inorderTraversal(Node tree){
		List<Node> inorderTraversal = new ArrayList<>() ; 
		if(tree != null){
			inorderTraversal.addAll(inorderTraversal(tree.leftNode));
			inorderTraversal.add(tree);
			inorderTraversal.addAll(inorderTraversal(tree.rightNode)) ;
		}
		return inorderTraversal ; 
	}


	class Node{
		Node leftNode ;
		Node rightNode ; 
		int nodeValue ; 
		int height ;

		public Node(int nodeValue, int height){
			this.nodeValue = nodeValue ; 
			this.height = height ; 
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

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[nodeValue='" + nodeValue + 
					"\tatHeight=" + height +"'\n" +
			"\tleftNode='" + leftNode +  "'\n" +
			"\trightNode='" + rightNode + "']\n\t";
		}

	}
}