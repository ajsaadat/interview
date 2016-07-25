package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


public class Mircosoft {

	
	public static void main(String []args){
		Collection<Node> nodes = new ArrayList<>() ; 
		Node aNode = new Node(5, 50);
		nodes.add(aNode) ;
		Node bNode = new Node(7, 30);
		nodes.add(bNode) ;
		Node cNode = new Node(8, 5);
		nodes.add(cNode) ;
		Node dNode = new Node(2, 6);
		nodes.add(dNode) ;
		Node eNode = new Node(98, 3);
		nodes.add(eNode) ;
		Node fNode = new Node(66, 1);
		nodes.add(fNode) ;
		Node gNode = new Node(57, 1);
		nodes.add(gNode) ;
		Node hNode = new Node(58, 1);
		nodes.add(hNode) ;
		Node iNode = new Node(82, 1);
		nodes.add(iNode) ;
		Node kNode = new Node(44, 2);
		nodes.add(kNode) ;
		final LoadBalancer loadBalancer = new LoadBalancer() ; 
		loadBalancer.setSystemNodes(nodes);
		final Map<Integer, Integer> distribution = new ConcurrentHashMap<Integer, Integer>() ;
		final Mircosoft ms = new Mircosoft() ;
		long sTime = System.currentTimeMillis()  ;
		final List<Thread> threads = new ArrayList<>() ; 
		for(int i = 0 ; i < 1000 ; i++){
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println("before " + Thread.currentThread().getName() + distribution);
					ms.getANode(loadBalancer, distribution) ;
					System.out.println("after " + Thread.currentThread().getName() + distribution);
				}
			}) ;
			t.setName("thread" + i);
			threads.add(t) ;
			t.start(); 
		}
		
		for(Thread t : threads){
			try {
				t.join();
			} catch (InterruptedException e) {
				System.out.println("interrupt.");
			}
		}
		
		
		System.out.println("total time[" + (System.currentTimeMillis() - sTime) + "]");
		System.out.println("outside both thread: " + distribution);
	}
	
	
	public synchronized void getANode(LoadBalancer loadBalancer, Map<Integer, Integer> distribution){
		for(int i = 1 ; i <= 100000 ; i ++){
			int nodeID = loadBalancer.getNextNode()  ; 
			if(distribution.containsKey(nodeID)){
				int frequency = distribution.get(nodeID)  ;
				distribution.put(nodeID, frequency + 1);
			}else{
				distribution.put(nodeID, 1);
			}
			
		}
	}
	
	
	public static class LoadBalancer{
		private Collection<Node> systemNodes = new HashSet<Node>() ; 
		public int getNextNode(){
			
			Collection<Node> allNodes = getSystemNodes() ; 
			Random random = new Random() ; 
			Double randomDouble =random.nextDouble() ; 
			int accumulatedWeigh = 0 ; 
			for(Node aNode : allNodes){
				accumulatedWeigh += aNode.getNodeWeight() ; 
				if(randomDouble*100 < accumulatedWeigh){
					return aNode.getNodeID() ; 
				}
			}
			return -1 ; 
			
		}
		
		public Collection<Node> getSystemNodes(){
			return  systemNodes; 
		}
		
		public void setSystemNodes(Collection<Node> systemNodes){
			this.systemNodes = systemNodes ; 
		}
	}

	
	
	public static class Node{
		private int nodeID ; 
		private int nodeWeight ;
		
		public Node(int nodeID, int nodeWeight){
			this.nodeID = nodeID ; 
			this.nodeWeight = nodeWeight ; 
		}
		public int getNodeID() {
			return nodeID;
		}
		public void setNodeID(int nodeID) {
			this.nodeID = nodeID;
		}
		public int getNodeWeight() {
			return nodeWeight;
		}
		public void setNodeWeight(int nodeWeight) {
			this.nodeWeight = nodeWeight;
		} 
		
	}
}
