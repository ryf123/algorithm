import java.math.BigDecimal;


public class Test {
	public static void main(String[] args) {
		double val = 0.012;
		BigDecimal bigValue = new BigDecimal(Double.toString(val));
		bigValue = bigValue.add(bigValue).add(bigValue);
		System.out.print(bigValue);
	}
}
