package concurr2.ch3.pipe.stream;

import java.io.IOException;
import java.io.PipedInputStream;

public class ReadData {

	public void readMethod(PipedInputStream in) {
		try {
			System.out.println("read  :");
			byte[] temp = new byte[20];
			int len = in.read(temp);
			while(-1 != len) {
				String inData = new String(temp, 0, len);
				System.out.print(inData);
				len = in.read(temp);
			}
			System.out.println();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
