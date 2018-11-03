package pattern.builder;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Client {
	
	@Test
	public void builderTest(){
		List<String> seq = new ArrayList<String>();
		seq.add("start");
		seq.add("alarm");
		seq.add("stop");
		BenziModel benzi = new BenziModel();
		benzi.setSequence(seq);
		benzi.run();
	}
	
	@Test
	public void directorTest(){
		Director director = new Director();
		director.getABenciModel().run();
		director.getBBMWModel().run();
		director.getABMWModel().run();
		director.getBBenciModel().run();
		
	}
}
