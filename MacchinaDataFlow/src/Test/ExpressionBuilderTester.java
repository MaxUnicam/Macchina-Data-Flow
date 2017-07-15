package Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import Core.ExpressionBuilder;
import Core.ExpressionEvaluator;
import Core.Interfaces.IExpressionEvaluator;
import Models.ArithmeticExpression;
import Models.AbstractExpression.Operator;


public class ExpressionBuilderTester {

	@Test
	public void test() {
		IExpressionEvaluator evaluator = new ExpressionEvaluator();
		
		ArithmeticExpression exp = expressionOne();
		Double result = evaluator.evaluate(exp);
		assertTrue(result == 15);

		exp = expressionTwo();
		result = evaluator.evaluate(exp);
		assertTrue(Equals(result, 1.38632142));
		
		exp = expressionThree();
		result = evaluator.evaluate(exp);
		assertTrue(result == 5);
	}
	
	// * + / 5 3 1 / + 8 2 - 3 1	= 15
	private ArithmeticExpression expressionOne() {
		ExpressionBuilder builder = new ExpressionBuilder();
		builder.addOperator(Operator.Multiplication);
		builder.addOperator(Operator.Sum);
		builder.addOperator(Operator.Difference);
		builder.addValue(Double.valueOf(5));
		builder.addValue(Double.valueOf(3));
		builder.addValue(Double.valueOf(1));
		builder.addOperator(Operator.Division);
		builder.addOperator(Operator.Sum);
		builder.addValue(Double.valueOf(8));
		builder.addValue(Double.valueOf(2));
		builder.addOperator(Operator.Difference);
		builder.addValue(Double.valueOf(3));
		builder.addValue(Double.valueOf(1));	
		return (ArithmeticExpression) builder.build();
	}
	
	// * + 3.14 3.67 / 4.56 22.4	= 1.3863
	private ArithmeticExpression expressionTwo() {
		ExpressionBuilder builder = new ExpressionBuilder();
		builder.addOperator(Operator.Multiplication);
		builder.addOperator(Operator.Sum);
		builder.addValue(3.14);
		builder.addValue(3.67);
		builder.addOperator(Operator.Division);
		builder.addValue(4.56);
		builder.addValue(22.4);
		return (ArithmeticExpression) builder.build();
	}
	
	// + 4 1	= 5
	private ArithmeticExpression expressionThree() {
		ExpressionBuilder builder = new ExpressionBuilder();
		builder.addOperator(Operator.Sum);
		builder.addValue(Double.valueOf(4));
		builder.addValue(Double.valueOf(1));
		return (ArithmeticExpression)builder.build();
	}
	
	
	private boolean Equals(double firstValue, double secondValue) {
		BigDecimal aa = new BigDecimal(firstValue);
		BigDecimal bb = new BigDecimal(secondValue);
		aa = aa.setScale(4, BigDecimal.ROUND_DOWN);
		bb = bb.setScale(4, BigDecimal.ROUND_DOWN);
		return aa.equals(bb);
	}
	
}
