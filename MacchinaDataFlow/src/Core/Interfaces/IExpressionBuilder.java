package Core.Interfaces;

import Models.AbstractExpression;
import Models.AbstractExpression.Operator;


/**
 * 
 * @author massimiliano
 * Interfaccia da implementare in un costruttore di espressioni
 *
 */

public interface IExpressionBuilder {

	public void addOperator(Operator operator);
	
	public void addValue(Double value);
	
	public AbstractExpression build();
	
	public boolean canBuild();
	
}
