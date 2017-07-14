package Models;


public class AbstractExpression {
	

	public enum Operator {
		Nothing,
		Sum,
		Difference,
		Multiplication,
		Division
	}
	

	protected Operator operator = Operator.Nothing;
	
	protected AbstractExpression firstExpression;
	
	protected AbstractExpression secondExpression;
	
	protected Double firstValue;
	
	protected Double secondValue;

	
	// Getters
	
	public Operator operator() { return operator; }
	
	public Double firstValue() { return firstValue; }
	
	public Double secondValue() { return secondValue; }
	
	public ArithmeticExpression firstExpression() { return (ArithmeticExpression)firstExpression; }
	
	public ArithmeticExpression secondExpression() { return (ArithmeticExpression)secondExpression; }
	
	
	// Setters
	
	public void SetOperator(Operator operator) { this.operator = operator; }
	
	public void SetFirstValue(Double firstValue) { this.firstValue = firstValue; }
	
	public void SetSecondValue(Double secondValue) { this.secondValue = secondValue; }
	
	public void SetFirstExpression(AbstractExpression firstExpression) { this.firstExpression = firstExpression; }
	
	public void SetSecondExpression(AbstractExpression secondExpression) { this.secondExpression = secondExpression; }
	
}