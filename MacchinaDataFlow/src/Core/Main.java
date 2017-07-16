package Core;

import java.sql.Timestamp;

import Models.AbstractExpression;
import Models.ArithmeticExpression;


public class Main {
	
	public static void main(String [] args)
	{
		// Initialization
		StringExpressionParser parser = new StringExpressionParser("./src/Resources/expression.txt");
		ExpressionBuilder builder = new ExpressionBuilder(parser);
		ExpressionEvaluator evaluator = new ExpressionEvaluator();
		
		// Start builder listening for token's availability 
		builder.startListening();
		
		Timestamp start = new Timestamp(System.currentTimeMillis());
		
		parser.parse();
		while (!builder.canBuild()) { continue; }
		
		AbstractExpression exp = builder.build();
		Double result = evaluator.evaluate((ArithmeticExpression)exp);
		
		Timestamp end = new Timestamp(System.currentTimeMillis());
		System.out.println("Risultato dell'espressione: " + result + ".");
		System.out.println("Numero di thread utilizzati: 3.");
		System.out.println("Tempo impiegato: " + (end.getTime() - start.getTime()) + " millisecondi.");
	}
	
}
