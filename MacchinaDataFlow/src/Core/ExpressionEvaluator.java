package Core;

import Core.Interfaces.IExpressionEvaluator;
import Models.AbstractExpression.Operator;
import Models.ArithmeticExpression;


public class ExpressionEvaluator implements IExpressionEvaluator {

	@Override
	public Double evaluate(ArithmeticExpression expression) {
		
		Operator operator = expression.operator(); 
		if (operator == null || operator == Operator.Nothing)
			return expression.firstValue();
		
		Double firstValue = null;		
		if (expression.firstValue() == null)
			firstValue = evaluate(expression.firstExpression());
		else 
			firstValue = expression.firstValue();
		
		Double secondValue = null;		
		if (expression.secondValue() == null)
			secondValue = evaluate(expression.secondExpression());
		else 
			secondValue = expression.secondValue();
		
		return calculate(operator, firstValue, secondValue);
	}
	
	
	private Double calculate(Operator operator, Double firstValue, Double secondValue)
	{
		Double result = null;
		switch (operator) {
			case Nothing:
				break;
			case Sum:
				result = firstValue + secondValue;
				break;
			case Difference:
				result = firstValue - secondValue;
				break;
			case Multiplication:
				result = firstValue * secondValue;
				break;
			case Division:
				result = firstValue / secondValue;
				break;
		}
		return result;
	}

}