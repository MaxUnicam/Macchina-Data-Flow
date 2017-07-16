package Core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Core.Interfaces.IExpressionTokenStreamer;
import Models.AbstractExpression.Operator;
import Models.ArithmeticExpression;


public class StringExpressionParser {
	
    private List<IExpressionTokenStreamer> listeners = new ArrayList<IExpressionTokenStreamer>();
    
    private String expressionLine;
    
    
    public StringExpressionParser() { }
    
    public StringExpressionParser(String filePath) {
    	try {
    		Scanner scanner = new Scanner(new File(filePath));
    		if (!scanner.hasNextLine()){
        		System.out.println("Error: " + filePath + " is empty.");
        		scanner.close();
        		return;
        	}
    		
    		expressionLine = scanner.nextLine();
    		scanner.close();
    		
    	} catch (Exception e) {
    		System.out.println("Error: " + filePath + " doesn't exist.");
    	}
    }
    

    public void addListener(IExpressionTokenStreamer toAdd) {
        listeners.add(toAdd);
    }

    public void operatorAvailable(Operator operator) {
    	for (IExpressionTokenStreamer listener : listeners)
            listener.operatorAvailable(operator);
    }
       
    public void valueAvailable(Double value) {
    	for (IExpressionTokenStreamer listener : listeners)
            listener.valueAvailable(value);
    }
    
    public void streamEnded() {
    	for (IExpressionTokenStreamer listener : listeners)
            listener.streamEnded();
    }

    
    public boolean parse() {
    	if (expressionLine == null || expressionLine.isEmpty()) {
    		System.out.println("Error: 'expressionLine' is empty");
    		return false;
    	}

		if (!ArithmeticExpression.IsValid(expressionLine)) {
			System.out.println("Error: " + expressionLine + " is an invalid expression.");
			return false;
		}
		
		parse(expressionLine);
		return true;
    }
    
	
	public void parse(String value)
	{	
		Scanner scanner = new Scanner(value);
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			while (scanner.hasNext())
			{
				String newValue = scanner.next();
				
				if (newValue.matches("[+-/]") || newValue.trim().equals("*")) {
					Operator op = parseOperator(newValue);
					if (op == Operator.Nothing)
						continue;
					operatorAvailable(op);
				}
				else {
					Double val = parseValue(newValue);
					if (val == null)
						continue;
					valueAvailable(val);
				}
			}
			
			streamEnded();
			scanner.close();
		});
		
	}
	
	
	private Operator parseOperator(String op)
	{
		if (op == null || op.isEmpty())
			return Operator.Nothing;
		
		if (op.equals("+"))
			return Operator.Sum;
		else if (op.equals("-"))
			return Operator.Difference;
		else if (op.equals("*"))
			return Operator.Multiplication;
		else if (op.equals("/"))
			return Operator.Division;
		else 
			return Operator.Nothing;
	}
	
	
	private Double parseValue(String val)
	{
		if (val == null || val.isEmpty())
			return null;

		try {
			return Double.valueOf(val);
		} catch (Exception ex) { return null; }
	}

}
