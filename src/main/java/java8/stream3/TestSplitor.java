package java8.stream3;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Spliterator定义了并行流如何拆分它要遍历的数据
 * @author 
 *
 */
public class TestSplitor {

	public static void main(String[] args) {
		final String SENTENCE = "Nel mezzo del cammin di nostra vita " + "mi ritrovai in una selva oscura"
				+ " ché la dritta via era smarrita ";

		System.out.println("普通方式：countWordIteratively ： " + countWordIteratively(SENTENCE));

		// 函数式风格 coutWord
		Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
		System.out.println("函数式风格 ：  " + countWords(stream) + " words");
		Stream<Character> stream2 = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
		// 并行流处理有问题（原始的 String会在任意位置进行拆分，有可能一个单词背拆分成两个单词）
		System.out.println("函数式风格 （并行流）：" + countWords(stream2.parallel()) + " words");

		// 测试自定义的Splitor
		Spliterator<Character> splitor = new WordCounterSpliterator(SENTENCE);
		// 第二个参数true表示使用创建一个并行流
		Stream<Character> stream3 = StreamSupport.stream(splitor, true);
		System.out.println("自定义的Splitor （并行流）：" + countWords(stream3) + " words");

	}

	/**
	 * 计算单词数量<br>
	 * （迭代算法）
	 * 
	 * @param s
	 * @return
	 */
	public static int countWordIteratively(String s) {
		int count = 0;
		boolean lastSpace = true;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace) {
					// 上一个字符是空格时并且当前字符不是空格的时候计数器 +1
					count++;
				}
				lastSpace = false;
			}
		}
		return count;
	}

	/**
	 * 规约Character流
	 * 
	 * @param stream
	 * @return
	 */
	private static int countWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate,
				WordCounter::combine);
		return wordCounter.getCounter();
	}

	/**
	 * 保留两个变量组成的状态 <br>
	 * int 用于规约时记录目前为止数过的字数，<br>
	 * boolean用来记得上一个遇到的Character是不是空格
	 * @author
	 *
	 */
	static class WordCounter {

		private final int counter;
		private final boolean lastSpace;

		public WordCounter(int counter, boolean lastSpace) {
			this.counter = counter;
			this.lastSpace = lastSpace;
		}

		/**
		 * 和迭代算法一样，一个个遍历字符
		 * 
		 * @param c
		 * @return
		 */
		public WordCounter accumulate(Character c) {
			if (Character.isWhitespace(c)) {
				return lastSpace ? this : new WordCounter(counter, true);
			} else {
				// 上一个字符是空格时并且当前字符不是空格的时候计数器 +1
				return lastSpace ? new WordCounter(counter + 1, false) : this;
			}
		}

		/**
		 * 合并两个wordCounter，把其计数器加起来
		 * 
		 * @param wordCounter
		 * @return
		 */
		public WordCounter combine(WordCounter wordCounter) {
			// 只需要计数器的总和，无需关心的lastSpace
			return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
		}

		public int getCounter() {
			return counter;
		}
	}

}
