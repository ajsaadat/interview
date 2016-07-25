package test;

public class CycleDetection {
	
	public static void main(String[] args){
		int[] data = {1,2,3,4,5,6,3,4,5,6} ; 
		isLoop(data, 0, 0) ;
	}
	
	public static boolean isLoop(int[] data, int slow, int fast){
		while(true){
			slow = slow + 1 ; 
			fast = fast + 2 ; 
			
			if(slow > data.length){
				return false ; 
			}else if(fast > data.length){
				return false ; 
			}else if(data[slow] == data[fast]){
				System.out.println("data[slow]= " + data[slow] + " data[fast]=" + data[fast]);
				return true ; 
			}
		}
	}
}
