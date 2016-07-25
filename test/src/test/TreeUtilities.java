package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import test.AVLTree.AVLNode;
import test.BinaryTree.BinaryNode;

public class TreeUtilities {

	public Collection<Leaf> leafFinder(BinaryNode bt, List<BinaryNode> path){
		Set<Leaf> leafs = new HashSet<Leaf>() ;
		BinaryNode sp = bt;  

		if(bt == null){
			return leafs ;
		}

		else if(!sp.hasLeftNode() && !sp.hasRightNode() ){
			//we found a leaf
			Leaf leaf = new Leaf(sp, path) ;
			leafs.add(leaf) ;
		}else{
			path.add(sp) ;
			leafs.addAll(leafFinder(sp.leftNode, new ArrayList<BinaryNode>(path))) ;
			leafs.addAll(leafFinder(sp.rightNode, new ArrayList<BinaryNode>(path))) ;

		}

		return leafs ; 
	}

	public BinaryNode rotateLeft(BinaryNode node){
		if(node == null){
			return null; 
		}else{
			BinaryNode moveup = node.rightNode ; 
			BinaryNode leftNode = moveup.leftNode ; 

			node.rightNode = leftNode ; 
			moveup.leftNode = node ; 
			node = moveup ; 
			return node ; 
		}
	}


	public BinaryNode rotateRight(BinaryNode node){
		if(node == null){
			return null; 
		}else{
			BinaryNode moveup = node.leftNode ; 
			BinaryNode rightNode = moveup.rightNode ; 

			node.leftNode = rightNode ; 
			moveup.rightNode = node ; 
			node = moveup ; 
			return node ; 
		}
	}


	public BinaryNode createRightVine(BinaryNode node){
		while(node.hasLeftNode()){
			node = rotateRight(node);
		}
		if(node.hasRightNode()){
			node.rightNode = createRightVine(node.rightNode);
		}
		return node ;	

	}

	public class Leaf implements Comparable<Leaf>{
		BinaryNode leafNode ; 
		List<BinaryNode> path = new ArrayList<BinaryNode>() ; 

		public Leaf(BinaryNode leafNode, Collection<BinaryNode> path){
			this.leafNode = leafNode ; 
			this.path.addAll( path ); 
		}

		public BinaryNode getLeafNode() {
			return leafNode;
		}

		public List<BinaryNode> getPath() {
			return path;
		}

		@Override
		public String toString() {
			return "[leafNode='" + leafNode + "', path='" + path + "']";
		}

		@Override
		public int hashCode() {
			return leafNode.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if(obj != null){
				if(obj instanceof Leaf){
					Leaf leafObj = (Leaf)obj ;
					if(leafObj.leafNode.equals(leafNode)){
						return true ; 
					}else{
						return false ; 
					}
				}else{
					return false ; 
				}

			}else{
				return false;
			}
		}

		@Override
		public int compareTo(Leaf o) {
			if(o.equals(this)){
				return 0 ; 
			}else{
				return -1 ; 
			}
		}
	}
}
