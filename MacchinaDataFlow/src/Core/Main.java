package Core;

import java.sql.Timestamp;

import Models.AbstractExpression;
import Models.ArithmeticExpression;


public class Main {
	
	public static void main(String [] args)
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
//		StringExpressionParser parser = new StringExpressionParser();
//		ExpressionBuilder builder = new ExpressionBuilder(parser);
//		builder.startListening();
//		parser.parse("* + 3.14 3.67 / 4.56 22.4");
//		while (!builder.canBuild()) {
//			Wait(1);
//		}
		
		StringExpressionParser parser = new StringExpressionParser("./src/Resources/expression.txt");
		ExpressionBuilder builder = new ExpressionBuilder(parser);
		builder.startListening();
		parser.parse();
		while (!builder.canBuild()) {
			Wait(1);
		}
		
		AbstractExpression exp = builder.build();
		
		ExpressionEvaluator evaluator = new ExpressionEvaluator();
		Double result = evaluator.evaluate((ArithmeticExpression)exp);
		
		Timestamp end = new Timestamp(System.currentTimeMillis());
		long diff = end.getTime() - timestamp.getTime();
		System.out.println("Abbiamo impiegato " + diff + " millisecondi");
		
		System.out.println("Il risultato è: " + result);
	}
	
	private static void Wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
}
