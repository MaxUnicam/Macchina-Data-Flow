package Core.Interfaces;

import Models.AbstractExpression;
import Models.AbstractExpression.Operator;


public interface IExpressionBuilder {

	public void addOperator(Operator operator);
	
	public void addValue(Double value);
	
	public AbstractExpression build();
	
	public boolean canBuild();
	
}
