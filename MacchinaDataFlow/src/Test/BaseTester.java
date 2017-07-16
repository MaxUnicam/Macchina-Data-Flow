package Test;

import java.math.BigDecimal;


public class BaseTester {

	protected boolean Equals(double firstValue, double secondValue) {
		BigDecimal aa = new BigDecimal(firstValue);
		BigDecimal bb = new BigDecimal(secondValue);
		aa = aa.setScale(4, BigDecimal.ROUND_DOWN);
		bb = bb.setScale(4, BigDecimal.ROUND_DOWN);
		return aa.equals(bb);
	}
	
}
