package pattern.interpretor;

import java.util.HashMap;
/**
 * 变量解析器
 * @author Administrator
 *
 */
public class VarExpression extends Expression {
	private String key; 
	
	public VarExpression(String key) {
		this.key = key;
	}
	/**
	 * 从Map中取值
	 * key值为模型中的参数，如a、b、c等，value为运算时取得的具体数字
	 */
	@Override
	public int interpreter(HashMap<String, Integer> var) {
		int result = var.get(this.key);
		return result;
	}

}
