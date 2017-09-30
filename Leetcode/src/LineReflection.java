import java.util.Arrays;
import java.util.Comparator;

public class LineReflection {
    public boolean isReflected(int[][] points) {
    	int min = Integer.MAX_VALUE;
    	int max = Integer.MIN_VALUE;
    	double line;
    	for(int[] point:points){
    		min = Math.min(min, point[0]);
    		max = Math.max(max, point[0]);
    	}
    	line = min+max;
    	Arrays.sort(points,new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]){
					//on the left, ascending order
					if(o1[0] < line/2){
						return o1[1] - o2[1];
					}
					else{
						// descending order
						return o2[1] - o1[1];
					}
				}
				return o1[0] - o2[0];
			}
		});
    	int l = points.length-1;
    	int i = 0;
    	while(i<=l){
    		while(i+1<=l && points[i][0] == points[i+1][0] && points[i][1] == points[i+1][1]){
    			i++;
    		}
    		while(i<=l && line %2== 0 && points[i][0] == line/2)
    			i++;
    		while(i<=l-1 && points[l][0] == points[l-1][0] && points[l][1] == points[l-1][1]){
    			l--;
    		}
    		while(i<=l && line%2 == 0 && points[l][0] == line/2)
    			l--;
    		if(i<=l && (points[i][0] + points[l][0] != line || points[i][1] != points[l][1]))
    			return false;
    		i++;
    		l--;
    	}
    	return true;
    }
    public static void main(String[] args) {
		int[][] points = new int[][]{{1,2},{2,2},{1,4},{2,4}};
		LineReflection lr = new LineReflection();
		System.out.println(lr.isReflected(points));
	}
}
