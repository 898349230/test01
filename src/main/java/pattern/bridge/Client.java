package pattern.bridge;

import org.junit.Test;

public class Client {
	
	@Test
	public void bridgeTest(){
		System.out.println("============房地产公司===============");
		HouseCorp houseCorp = new HouseCorp(new House());
		houseCorp.makeMoney();
		
		System.out.println("===============山寨公司=====================");
		//ShanzhaiCorp shanzhaiCorp = new ShanzhaiCorp(new IPod());
		ShanzhaiCorp shanzhaiCorp = new ShanzhaiCorp(new Clothes());
		shanzhaiCorp.makeMoney();
	}
}
