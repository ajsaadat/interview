package test;
//adding documentation here.
public class ArrayToHeap {
	
	public static void main(String[] args){
		int data[]={2,8,6,1,10,15,3,12,11} ;
		ArrayToHeap heapBuilder = new ArrayToHeap() ; 
		heapBuilder.heapConvertor(data);
	}

	public void heapConvertor(int[] data){
		if(data.length <= 1 ){
			return ;
		}else{
			int lastNonLeafIndex = data.length/2 - 1 ; 
			for(int i = lastNonLeafIndex ; i >= 0 ; i--){
				moveDown(data, i, data.length - 1) ; 
			}
		}
	}
	
	public void moveDown(int[] data, int parentIndex, int lastIndex){
		int lastChildIndex = parentIndex*2 + 2 ; 
		int firstChildIndex = parentIndex*2 + 1 ; 
		
		int largestChildIndex = lastChildIndex ; 
		
		if(largestChildIndex <= lastIndex){
			
			if(data[lastChildIndex] < data[firstChildIndex]){
				largestChildIndex = firstChildIndex  ;
			}
			
			if(data[parentIndex] < data[largestChildIndex]){
				int temp = data[parentIndex] ; 
				data[parentIndex] = data[largestChildIndex] ;
				data[largestChildIndex] = temp ; 
			}
			System.out.println("largest index " + largestChildIndex + " largestValue " + data[largestChildIndex]);
			System.out.println("array value");
			for(int i : data){
				System.out.print("\t" + i);
			}
			moveDown(data, largestChildIndex, lastIndex);
		}
	}
}
