package Core;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Models.AbstractExpression;
import Models.ArithmeticExpression;
import Models.AbstractExpression.Operator;


public class ExpressionBuilder implements IExpressionTokenStreamer {
	
	private AbstractExpression baseExpression;
	
	private Queue<Object> expressionTokensQueue;
	
	private boolean isStreamFinished = false;
	
	private StringExpressionParser parser;

	
	public ExpressionBuilder() {
		expressionTokensQueue = new LinkedList<>();
	}
	
	public ExpressionBuilder(StringExpressionParser parser) {
		this.parser = parser;
		expressionTokensQueue = new LinkedList<>();
	}
	
	
	public void startListening() {
		this.parser.addListener(this);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			while (!isStreamFinished || !expressionTokensQueue.isEmpty()) {
				Wait(10);
				
				synchronized (this) {
					System.out.println("La coda nel ciclo è lunga: " + expressionTokensQueue.size());
					analyzeToken();
				}
				
			}
			
		});
		this.parser.parse("* + 3.14 3.67 / 4.56 22.4");
	}
	
	
	private void analyzeToken() {
		synchronized (this) {
			if (expressionTokensQueue.isEmpty())
				return;
			
			Object token = expressionTokensQueue.poll();
			if (token instanceof Double)
				addValue((Double)token);
			else if (token instanceof Operator)
				addOperator((Operator)token);
		}
	}
	
	
	
	private void Wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	
	
	
	public void addOperator(Operator operator) {
		if (baseExpression == null) {
			baseExpression = new ArithmeticExpression(operator);
			return;
		}
		
		addOperator(baseExpression, operator);
	}
	
	
	public void addValue(Double value) {
		if (baseExpression == null)
			return;
		
		addValue(baseExpression, value);
	}
	
	public AbstractExpression build() {
		return baseExpression;
	}
	
	
	synchronized public boolean canBuild() {
		return isStreamFinished && expressionTokensQueue.isEmpty();
	}
	
	
	private boolean addOperator(AbstractExpression expression, Operator operator) {
		if (expression == null || operator == null || operator == Operator.Nothing)
			return false;
		
		if (expression.operator() == null || expression.operator() == Operator.Nothing) {
			expression.SetOperator(operator);
			return true;
		}
		
		if (expression.firstExpression() == null && expression.firstValue() == null) {
			expression.SetFirstExpression(new ArithmeticExpression(operator));
			return true;
		}
		else if (expression.firstExpression() != null && addOperator(expression.firstExpression(), operator))
			return true;
		
		if (expression.secondExpression() == null && expression.secondValue() == null) {
			expression.SetSecondExpression(new ArithmeticExpression(operator));
			return true;
		}
		else if (expression.secondExpression() != null && addOperator(expression.secondExpression(), operator))
			return true;
		
		return false;
	}
	
	
	private boolean addValue(AbstractExpression expression, Double value) {
		if (expression == null || value == null)
			return false;
		
		if (expression.firstExpression() == null && expression.firstValue() == null) {
			expression.SetFirstValue(value);
			return true;
		}
		else if (expression.firstExpression() != null && addValue(expression.firstExpression(), value))
			return true;
		
		if (expression.secondExpression() == null && expression.secondValue() == null) {
			expression.SetSecondValue(value);
			return true;
		}
		else if (expression.secondExpression() != null && addValue(expression.secondExpression(), value))
			return true;
		
		return false;
	}


	@Override
	public void operatorAvailable(Operator operator) {
		synchronized(this) {
			expressionTokensQueue.add(operator);
			analyzeToken();
			// Commentiamo analyzeToken e vediamo la differenza di tempo
		}
	}


	@Override
	public void valueAvailable(Double value) {
		synchronized(this) {
			expressionTokensQueue.add(value);
			analyzeToken();
			// Commentiamo analyzeToken e vediamo la differenza di tempo
		}
	}

	@Override
	public void streamEnded() {
		synchronized(this) {
			isStreamFinished = true;
		}
	}

}
