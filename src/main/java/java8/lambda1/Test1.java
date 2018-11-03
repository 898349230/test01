package java8.lambda1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test1 {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		Runnable rr = () -> {
				System.out.println("process11");
				System.out.println("process11");
						
			};
		process(rr);
		
		// 直接运用lambda 
		process(() -> {
			System.out.println(" process ");
			System.out.println(" process ");
		}
		);
		
		System.out.println(" reader :  " + processFile());
		
		// lambda 表达式
		String str = processFile((BufferedReader br) -> {
				try {
					String readLine = br.readLine();
					readLine += br.readLine();
					return readLine;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "readLine() 错误";
		});
		System.out.println(" processFile " + str);
	}
	
	private static String processFile(BufferdReaderProcessor brp) throws FileNotFoundException {
		try(BufferedReader br = 
				new BufferedReader(new FileReader("C:\\Users\\lzx-t050\\Desktop\\2.txt"))){
			return brp.processor(br);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "读取文件错误";
	}

	public static void process(Runnable r) {
		r.run();
	}
	
	public static String processFile() throws FileNotFoundException, IOException {
		try(BufferedReader br = 
				new BufferedReader(new FileReader("C:\\Users\\lzx-t050\\Desktop\\2.txt"))){
			return br.readLine();
		}
	}
	
}
