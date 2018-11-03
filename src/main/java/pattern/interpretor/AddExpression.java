package pattern.interpretor;

import java.util.HashMap;

public class AddExpression extends SymbolExpression {

	public AddExpression(Expression left, Expression right) {
		super(left, right);
	}

	/**
	 * 把左右两个表达式运算的结果加起来
	 */
	@Override
	public int interpreter(HashMap<String, Integer> var) {
		int result = super.left.interpreter(var)+super.right.interpreter(var);
		return result ;
	}

}
