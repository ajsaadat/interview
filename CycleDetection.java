package test;

public class CycleDetection {
	
	public static void main(String []args){
		CycleDetection s2 = new CycleDetection() ;
		Node startingPoint =  new Node(1) ;
		
		Node secondPosition = new Node(2) ; 
		startingPoint.nextNode = secondPosition ;
		Node thirdPosition = new Node(3) ;
		secondPosition.nextNode = thirdPosition ; 
		Node fourthPosition = new Node(4) ;
		thirdPosition.nextNode = fourthPosition ; 
		//fourthPosition.nextNode = startingPoint ; 
		
		System.out.println("is there a cycle:[" +s2.hasCycle(startingPoint) + "]"); 
	}
	
	private boolean hasCycle(Node startingPoint){
		if(startingPoint == null){
			return false ;
		}else if(startingPoint.nextNode == null){
			return false ; 
		}else{
			
			Node rabbitPosition = rabbitMovement(startingPoint) ;
			Node turtlePosition = turtleMovement(startingPoint) ;
			while(true){
				if(rabbitPosition == null || turtlePosition == null){
					return false ; 
				}else if(rabbitPosition == turtlePosition){
					return true ; 
				}
				rabbitPosition = rabbitMovement(rabbitPosition) ;
				turtlePosition = turtleMovement(turtlePosition) ;
			}
			
		}
		
	}
	
	private Node rabbitMovement(Node startingPoint){
		return startingPoint.nextNode.nextNode ; 
	}
	
	private Node turtleMovement(Node startingPoint){
		return startingPoint.nextNode ; 
		
	}
	
}

class Node{
	int value ; 
	Node nextNode ;
	
	public Node(int value){
		this.value = value ; 
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	} 
	
}
