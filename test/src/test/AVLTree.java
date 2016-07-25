package test;

public class AVLTree {

	private AVLNode root ; 

	public void insertANode(AVLNode newNode){
		if(newNode == null){
			throw new IllegalArgumentException("Provided node to be inserted can not be null") ;
		}else{
			AVLNode insertionPoint = root ; 
			AVLNode parentInsertionPoint = null ; 
			while (insertionPoint != null){
				parentInsertionPoint = insertionPoint ;
				if(insertionPoint.nodeValue > newNode.nodeValue ){
					if(insertionPoint.leftNode != null){
						insertionPoint = insertionPoint.leftNode ; 
					}else{
						//add the new node here
						insertionPoint.leftNode = newNode ; 
						balanceTree(parentInsertionPoint) ;
					}
				}else{
					if(insertionPoint.rightNode != null){
						insertionPoint = insertionPoint.rightNode ; 
						balanceTree(parentInsertionPoint) ;
					}else{
						//add the new node here
						insertionPoint.rightNode = newNode ; 
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param inBalanceNode
	 */
	public void balanceTree(AVLNode inBalanceNode){
		setBalance(inBalanceNode);  
		
		if(calculateHeight(inBalanceNode.leftNode) > calculateHeight(inBalanceNode.rightNode)){
			
		}else{
			
		}
		
		
		
	}
	
	public void rotateLeft(AVLNode accessedNode, AVLNode pAccessedNode, AVLNode grpAccessedNode){
		//determining parent node is left or right child of grand parent
		//replacing grand parent node to point to accessNode 
		if(grpAccessedNode.leftNode == pAccessedNode){
			grpAccessedNode.leftNode = accessedNode ; 
		}else{
			grpAccessedNode.rightNode = accessedNode ;
		}
		//making left child of access node the right child of parent node 
		pAccessedNode.rightNode = accessedNode.leftNode ; 
		//making left child of access node point to its parent.
		accessedNode.leftNode = pAccessedNode ; 
	}

	public void rotateRight(AVLNode accessedNode, AVLNode pAccessedNode, AVLNode grpAccessedNode){
		//determining parent node is left or right child of grand parent
		//replacing grand parent node to point to accessNode 
		if(grpAccessedNode.leftNode == pAccessedNode){
			grpAccessedNode.leftNode = accessedNode ; 
		}else{
			grpAccessedNode.rightNode = accessedNode ;
		}
		//making right child of access node the left child of parent node 
		pAccessedNode.leftNode = accessedNode.rightNode ; 
		//making right child of access node point to its parent.
		accessedNode.rightNode = pAccessedNode ; 
	}
	
	public void setBalance(AVLNode subjectNode){
		subjectNode.balanceFactor = calculateHeight(subjectNode.leftNode) - calculateHeight(subjectNode.rightNode) ;
	}
	
	public int calculateHeight(AVLNode subjectNode){
		if(subjectNode == null){
			throw new IllegalArgumentException("Starting node [subject node] can not be null") ;
		}else{
			if(subjectNode.leftNode  == null && subjectNode.rightNode == null ){
				return 0 ; 
			}else if(subjectNode.leftNode  == null && subjectNode.rightNode != null ){
				return 1 + calculateHeight(subjectNode.rightNode) ;
			}else if(subjectNode.leftNode  != null && subjectNode.rightNode == null ){
				return 1 + calculateHeight(subjectNode.leftNode) ;
			}else{
				int rightTreeHeight = 1 + calculateHeight(subjectNode.rightNode) ;
				int leftTreeHeight = 1 + calculateHeight(subjectNode.leftNode) ;
				return (rightTreeHeight >= leftTreeHeight) ? rightTreeHeight : leftTreeHeight ; 
			}
		}
	}

	public class AVLNode{
		private int balanceFactor ; 
		private int nodeValue ; 
		private AVLNode leftNode ; 
		private AVLNode rightNode ; 

		public AVLNode(int nodeValue){
			this.nodeValue = nodeValue ; 
		}

		public int getBalanceFactor (){
			return balanceFactor ; 
		}

		public void setBalanceFactor(int newBalanceFactor){
			balanceFactor = newBalanceFactor ; 
		}
		public int getNodeValue(){
			return nodeValue ; 
		}
		public AVLNode getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(AVLNode leftNode) {
			this.leftNode = leftNode;
		}

		public AVLNode getRightNode() {
			return rightNode;
		}

		public void setRightNode(AVLNode rightNode) {
			this.rightNode = rightNode;
		}
		
		@Override
		public String toString() {
			return "[nodeValue={"+ nodeValue +"}, balanceFactor={" + balanceFactor +"}, "
					+ "leftNode={" + leftNode + "}, rightNode={" + rightNode + "}]";
		}
	}
}
