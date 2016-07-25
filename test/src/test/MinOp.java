package test;

public class MinOp {

	public static void main(String[] args) {

		MinOp min = new MinOp() ; 
		String []nvalues = { "123.5666"} ;
		for(int i : min.minOperations(nvalues)){
			System.out.println(i);
		}

	}
	int[] minOperations(String[] nvalues) {
		int[] minOperations ; 
		/*if(nvalues == null ){
			minOperations = new int[0] ;
			return minOperations ; 
		}else if (nvalues.length == 0 || nvalues.length == 1 ){
			minOperations = new int[0] ;
			return minOperations ; 
		}*/
		minOperations = new int[nvalues.length];
		for(int i = 0 ; i < nvalues.length ; i++){
			String value = nvalues[i];
			try{
				int goal = Math.abs(Integer.parseInt(value)) ;
				goal = Math.round(goal) ;
				if( goal == 0 ){
					minOperations[i] = 0;
				}else if (goal == 1 ){
					minOperations[i] = 1;
				}else{
					double upperBound = Math.log10(goal) ;
					if(upperBound > 17){
						throw new IllegalArgumentException("upper bound reached");
					}
					int number_of_steps = 0 ; 
					while(goal > 1){
						goal = goal / 2 ;
						if((goal % 2) == 1){
							number_of_steps++ ;
						}
						number_of_steps++ ;
					}

					if(goal == 1 ){
						number_of_steps++;
					}
					minOperations[i] = number_of_steps ; 
				}


			}catch(Exception e){
				minOperations[i] = 0;
			}
		}
		return minOperations ; 
	}

}
