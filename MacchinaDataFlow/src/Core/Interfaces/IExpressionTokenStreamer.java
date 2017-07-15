package Core.Interfaces;

import Models.AbstractExpression.Operator;


public interface IExpressionTokenStreamer {
	
	void operatorAvailable(Operator operator);

	void valueAvailable(Double value);
	
	void streamEnded();
	
}
