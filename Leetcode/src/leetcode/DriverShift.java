package leetcode;
public class DriverShift {
	  public boolean sol(int[][] shifts,int current) {
	    int total = 0;
	    int prev = current;
	    for(int i=shifts.length - 1;i >=0; i--){
	      int[] shift = shifts[i];
	      int end = shift[1];
	      int _break = prev - end;
	      if(_break >= 8)
	        return true;
	      total += shift[1] - shift[0];
	      if(total >= 12)
	        return false;
	      prev = shift[0];
	    }
	    return true;
	  }
	  public static void main(String[] args) {
		DriverShift ds = new DriverShift();
		int[][] arr = {{1,10},{13,14},{23,26},{30,37},{38,39}};
		System.out.printf("%B", ds.sol(arr,42));
	  }
}
