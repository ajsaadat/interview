package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
	BinaryNode root ; 

	public BinaryTree(int nodeValue){
		BinaryNode root = new BinaryNode(nodeValue) ;
		this.root = root ; 
	}

	public BinaryNode addNode(BinaryNode modifiedNode, int nodeValue){
		if(modifiedNode.getNodeValue() > nodeValue){
			BinaryNode leftNode = new BinaryNode(nodeValue) ;
			modifiedNode.setLeftNode(leftNode);
			return leftNode ; 
		}else{
			BinaryNode rightNode = new BinaryNode(nodeValue) ;
			modifiedNode.setRightNode(rightNode);
			return rightNode ;
		}
	}

	public Queue<BinaryNode> printDFS(){
		//Queue<BinaryNode> unprocessedNodes = new LinkedList<>() ; 
		Deque<BinaryNode> unprocessedNodes = new ArrayDeque<BinaryNode>();
		unprocessedNodes.add(root) ;
		Queue<BinaryNode> processedNodes = new LinkedList<>() ; 


		while(!unprocessedNodes.isEmpty()){
			BinaryNode startingNode = unprocessedNodes.peekLast(); 
			if(startingNode.hasLeftNode() && !processedNodes.contains(startingNode.leftNode)){
				unprocessedNodes.add(startingNode.leftNode) ;
			}else if(startingNode.hasRightNode() && !processedNodes.contains(startingNode.rightNode)){
				unprocessedNodes.pollLast() ; 
				unprocessedNodes.add(startingNode.rightNode) ; 
				processedNodes.add(startingNode) ;
			}else{
				processedNodes.add(startingNode) ;
				unprocessedNodes.pollLast() ; 
			}
		}

		return processedNodes ; 

	}
	
	public void BFT(){
		if(root == null){
			return ; 
		}
		
		Queue<BinaryNode> nodes = new LinkedList<>() ; 
		nodes.add(root) ; 
		while(!nodes.isEmpty()){
			BinaryNode currentNode = nodes.poll() ;
			System.out.println(currentNode.getNodeValue());
			if(currentNode.hasLeftNode()){
				nodes.add(currentNode.leftNode) ; 
			}if(currentNode.hasRightNode()){
				nodes.add(currentNode.rightNode) ;
			}
		}
		
	}
	
	public void iterativeDFS(){
		List<BinaryNode> stack = new LinkedList<>() ; 
		stack.add(root) ;
		while(!stack.isEmpty()){
			BinaryNode currentNode = stack.get(stack.size() - 1) ; 
			System.out.println(currentNode.getNodeValue());
			if(currentNode.hasLeftNode()){
				stack.add(currentNode.leftNode) ; 
			}else{
				stack.add(currentNode.rightNode) ; 
				stack.remove(currentNode);
			}
			
		}
	}

	public List<BinaryNode> recursivePrintDFS(BinaryNode sNode){
		List<BinaryNode> dfs = new ArrayList<BinaryNode>() ; 
		if(sNode != null){
			dfs.addAll(recursivePrintDFS(sNode.leftNode)) ;
			dfs.add(sNode) ; 
			dfs.addAll(recursivePrintDFS(sNode.rightNode)) ;
		}
		return dfs ;
	}

	public static void main(String[] args){
		BinaryTree bTree = new BinaryTree(10) ; 
		BinaryNode sixthNode = bTree.addNode(bTree.root, 6) ;
		BinaryNode forteenthNode = bTree.addNode(bTree.root, 14) ;
		bTree.addNode(sixthNode, 4) ;
		bTree.addNode(sixthNode, 8) ;
		bTree.addNode(forteenthNode, 12) ;
		bTree.addNode(forteenthNode, 16) ;
		long sTime = System.currentTimeMillis() ; 
		/*Queue<BinaryNode> queue = bTree.printDFS() ; 
		System.out.println("none recursive version:" + (System.currentTimeMillis() - sTime));
		for(BinaryNode node : queue)
			System.out.println(node.nodeValue);

		sTime = System.currentTimeMillis() ; 
		System.out.println("recursive version:" + (System.currentTimeMillis() - sTime));
		List<BinaryNode> dfs = bTree.recursivePrintDFS(bTree.root); 
		for(BinaryNode node : dfs)
			System.out.println(node.nodeValue);*/
		
		
		//bTree.iterativeDFS();
		TreeUtilities utils = new TreeUtilities() ; 
		//System.out.println("leafs\n" + utils.leafFinder(bTree.root, new ArrayList<BinaryNode>())) ;
		 bTree.root = utils.createRightVine(bTree.root); 
		 System.out.println(bTree.root);
	}


	public static class BinaryNode{
		int nodeValue ; 
		BinaryNode leftNode ; 
		BinaryNode rightNode ; 

		@Override
		public int hashCode() {
			return nodeValue + (leftNode!=null? leftNode.hashCode() : 0 )+ (rightNode!=null? rightNode.hashCode(): 0)  ;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj != null){
				if(obj instanceof BinaryNode){
					BinaryNode objNode =(BinaryNode)obj ; 
					if(nodeValue == objNode.getNodeValue()){
						if(objNode.leftNode == leftNode && objNode.rightNode == rightNode){
							return true ; 
						}else{
							return false ; 
						}
					}else{
						return false ; 
					}
				}else{
					return false ; 
				}
			}else{
				return false ;
			}

		}
		public BinaryNode(int nodeValue){
			this(nodeValue, null, null) ;
		}

		public BinaryNode(int nodeValue, BinaryNode leftNode, BinaryNode rightNode){
			this.nodeValue = nodeValue ; 
			this.leftNode = leftNode ; 
			this.rightNode = rightNode ; 
		}

		public BinaryNode getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(BinaryNode leftNode) {
			this.leftNode = leftNode;
		}

		public BinaryNode getRightNode() {
			return rightNode;
		}

		public boolean hasRightNode(){
			if(rightNode != null){
				return true ;
			}else{
				return false ; 
			}
		}

		public boolean hasLeftNode(){
			if(leftNode != null){
				return true ;
			}else{
				return false ; 
			}
		}

		public void setRightNode(BinaryNode rightNode) {
			this.rightNode = rightNode;
		}

		public int getNodeValue() {
			return nodeValue;
		}

		@Override
		public String toString() {
			return "[Node value='"+ nodeValue + "', \n\tleftNode='" + leftNode + "', \n\trightNode='" + rightNode + "']\n\t";
		}

	}
}
