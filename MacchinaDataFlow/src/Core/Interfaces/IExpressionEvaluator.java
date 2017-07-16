package Core.Interfaces;

import Models.ArithmeticExpression;


/**
 * 
 * @author massimiliano
 * Interfaccia da implementare per valutare un'espressione
 *
 */

public interface IExpressionEvaluator {
	
	public Double evaluate(ArithmeticExpression expression);

}
