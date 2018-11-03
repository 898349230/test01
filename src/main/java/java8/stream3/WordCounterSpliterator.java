package java8.stream3;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 自定义Spliterator
 * Spliterator定义了并行流如何拆分它要遍历的数据
 * @author 
 *
 */
public class WordCounterSpliterator implements Spliterator<Character>{

	private final String string;
	private int currentChar = 0; 
	
	public WordCounterSpliterator(String string) {
		this.string = string;
	}

	/**
	 * 它会按顺序一个一个使用Spliterator中的元素，并且如果还有其他元素要遍历就返回true
	 */
	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		// 处理当前字符
		action.accept(string.charAt(currentChar++));
		// 如果还有字符要处理 返回true
		return currentChar < string.length();
	}

//	public static void main(String[] args) {
//		WordCounterSpliterator str = new WordCounterSpliterator("a b c d e");
//		str.trySplit();
//	
//	}	
	
	/**
	 * 把一些元素划分出去分给第二个Spliterator（由该方法返回），让它们两个并行处理
	 */
	@Override
	public Spliterator<Character> trySplit() {
		int currentSize = string.length() - currentChar;
		// 如果要解析的String足够小，则返回null，按顺序处理就行
		if(currentSize < 3) {
			return null;
		}
		// 将试探拆分位置设定为要解析的string的中间
		for(int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
			// 拆分位置一直向前直到下一个空格
			if(Character.isWhitespace(string.charAt(splitPos))) {
				// 创建一个新的WordCounterSpliterator来解析string，从开始到拆分位置的部分
				Spliterator<Character> spliterator =
						new WordCounterSpliterator(string.substring(currentChar, splitPos));
				// 将这个WordCounterSpliterator的拆分位置设置为起始位置
				currentChar = splitPos;
				return spliterator;
			}
		}
		return null;
	}

	/**
	 * 估计还剩下多少元素需要遍历，有助于拆分更均匀些
	 */
	@Override
	public long estimateSize() {
		return string.length() - currentChar;
	}

	/**
	 *  它将返回一个int，代表Spliterator本身特性集的编码
	 *  ORDERED 元素有既定的顺序（例如List），因此Spliterator在遍历和划分时也会遵循这一顺序
	 *	DISTINCT 对于任意一对遍历过的元素x和y， x.equals(y)返回false
	 *	SORTED 遍历的元素按照一个预定义的顺序排序
     *  SIZED 该Spliterator由一个已知大小的源建立（例如Set），因此estimatedSize()返回的是准确值
	 *	NONNULL 保证遍历的元素不会为null
	 *	IMMUTABLE Spliterator的数据源不能修改。这意味着在遍历时不能添加、删除或修改任何元素
	 *	CONCURRENT 该Spliterator的数据源可以被其他线程同时修改而无需同步
	 *	SUBSIZED 该Spliterator和所有从它拆分出来的Spliterator都是SIZED
	 */
	@Override
	public int characteristics() {
		return  ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}

}
