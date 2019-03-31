package scs;

public class Lab1 {
	   public boolean fo(int b) {
	    	int[] arr = { 50,20,5,5,1,1,1 };
	        int nbit = 1 << arr.length;

	        for (int i = 0; i < nbit; i++) {
	            int total=0;

	            for (int j = 0; j < arr.length; j++) {
	                int tmp = 1 << j; 
	                if ((tmp & i) != 0) { 
	                    total += arr[j];
	                   
	                }
	            }
	            if (total == b)
	                return true;
	        }
	        return false;
	    }
}
