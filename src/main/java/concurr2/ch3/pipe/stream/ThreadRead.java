package concurr2.ch3.pipe.stream;

import java.io.PipedInputStream;

public class ThreadRead extends Thread{

	private ReadData read;
	private PipedInputStream in;
	
	public ThreadRead(ReadData read, PipedInputStream in) {
		this.read = read;
		this.in = in;
	}
	
	@Override
	public void run() {
		read.readMethod(in);
	}
	
}
