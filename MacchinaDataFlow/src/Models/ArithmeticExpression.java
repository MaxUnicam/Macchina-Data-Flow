package Models;


public class ArithmeticExpression extends AbstractExpression {
	
	// Constructors
	
	public ArithmeticExpression() { }
	
	
	public ArithmeticExpression(Operator operator) {
		this.operator = operator;
	}
	
	
	public ArithmeticExpression(Operator operator, ArithmeticExpression firstExpression, ArithmeticExpression secondExpression) {
		this.operator = operator;
		this.firstExpression = firstExpression;
		this.secondExpression = secondExpression;
	}
	
	
	public ArithmeticExpression(Operator operator, ArithmeticExpression firstExpression, Double secondValue) {
		this.operator = operator;
		this.firstExpression = firstExpression;
		this.secondValue = secondValue;
	}
	
	
	public ArithmeticExpression(Operator operator, Double firstValue, ArithmeticExpression secondExpression) {
		this.operator = operator;
		this.firstValue = firstValue;
		this.secondExpression = secondExpression;
	}
	
	
	public ArithmeticExpression(Operator operator, Double firstValue, Double secondValue) {
		this.operator = operator;
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}
	
	
	/*
	 * Static method
	 */
	
	public static boolean IsValid(String expression) {
		return expression.matches("^[ 0-9,.+*-/]+$");
	}
	
}
