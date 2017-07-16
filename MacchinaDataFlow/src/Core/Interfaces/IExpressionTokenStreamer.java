package Core.Interfaces;

import Models.AbstractExpression.Operator;


/**
 * 
 * @author massimiliano
 * Interfaccia da implementare per ricevere gli eventi dal parser
 * 
 */

public interface IExpressionTokenStreamer {
	
	void operatorAvailable(Operator operator);

	void valueAvailable(Double value);
	
	void streamEnded();
	
}
