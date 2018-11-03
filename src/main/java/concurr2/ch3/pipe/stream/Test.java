package concurr2.ch3.pipe.stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Test {

	public static void main(String[] args) {
//		测试字节流
		test01();
	}
	
	/**
	 * 使用管道流进行通信
	 * 测试字节流
	 * 
	 * PipedInputStream
	 * PipedOutputStream
	 */
	public static void test01() {
		try {
			PipedOutputStream out = new PipedOutputStream();
			PipedInputStream in = new PipedInputStream();
			
			ReadData read = new ReadData();
			WriteData write = new WriteData();
			
			// 使用如下两种方式实现  inputStream 和  outputStream相连
//			out.connect(in);
			in.connect(out);
			
			ThreadRead tRead = new ThreadRead(read, in);
			tRead.start();

			Thread.sleep(1000);
			
			ThreadWrite tWrite = new ThreadWrite(write, out);
			tWrite.start();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 字符流， 和字节流类似
	 * PipedReader
	 * PipedWriter
	 * 
	 */
	public static void test02() {
		
	}
	
}
