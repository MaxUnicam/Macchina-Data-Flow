package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Core.ExpressionBuilder;
import Core.ExpressionEvaluator;
import Core.StringExpressionParser;
import Core.Interfaces.IExpressionEvaluator;
import Models.ArithmeticExpression;


public class ExpressionParserTester extends BaseTester {
	
	@Test
	public void TestFileInteraction() {
		StringExpressionParser parser = new StringExpressionParser("path random");
		assertFalse(parser.parse());
		parser = new StringExpressionParser(null);
		assertFalse(parser.parse());
		parser = new StringExpressionParser("./src/Resources/expression.txt");
		assertTrue(parser.parse());
	}
	
	
	@Test
	public void TestFileParsing() {
		StringExpressionParser parser = new StringExpressionParser("./src/Resources/expression.txt");
		ExpressionBuilder builder = new ExpressionBuilder(parser);
		builder.startListening();
		parser.parse();
		while (!builder.canBuild())
			continue;
		
		IExpressionEvaluator evaluator = new ExpressionEvaluator();
		Double result = evaluator.evaluate((ArithmeticExpression)builder.build());
		assertTrue(Equals(result, 1.38632142));
		
		parser = new StringExpressionParser("./src/Resources/test-expression.txt");
		builder = new ExpressionBuilder(parser);
		builder.startListening();
		parser.parse();
		while (!builder.canBuild())
			continue;
		
		result = evaluator.evaluate((ArithmeticExpression)builder.build());
		assertTrue(Equals(result, 5));
	}
	
	
	@Test
	public void StringParsing() {
		StringExpressionParser parser = new StringExpressionParser();
		ExpressionBuilder builder = new ExpressionBuilder(parser);
		builder.startListening();
		parser.parse("+ + - 3.17 1 0.83 * 1 1");
		while (!builder.canBuild())
			continue;
		
		IExpressionEvaluator evaluator = new ExpressionEvaluator();
		Double result = evaluator.evaluate((ArithmeticExpression)builder.build());
		assertTrue(Equals(result, 4));
		
		builder = new ExpressionBuilder(parser);
		builder.startListening();
		parser.parse("/ + 3 4 + * 2 * 2 * 2 2.5 / 100 100");
		while (!builder.canBuild())
			continue;
		
		result = evaluator.evaluate((ArithmeticExpression)builder.build());
		assertTrue(Equals(result, 0.33333));
	}

}
