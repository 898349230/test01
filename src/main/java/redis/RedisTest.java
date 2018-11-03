package redis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

public class RedisTest {

	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(1);
		str(jedis);
//		hash(jedis);
//		list(jedis);
//		set(jedis);
//		zset(jedis);
//		System.out.println(" sort.... ");
//		sort(jedis);
//		testHgetAll(jedis);
		jedis.close();
	}	

	public static void str(Jedis jedis) {
		jedis.set("a", "厉害了我的国");
		String string = jedis.get("a");
		System.out.println("************ " + string);
	}
	
	public static void hash(Jedis jedis) {
//		String hget = jedis.hget("car", "name");
//		System.out.println(hget);
		
		Map<String, String> hgetAll = jedis.hgetAll("car");
		Iterator<String> iterator = hgetAll.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key + "  " + hgetAll.get(key));
		}
	}
	
	public static void list(Jedis jedis) {
//		List<String> lrange = jedis.lrange("numbers", 0, 2);
		List<String> lrange = jedis.lrange("numbers", 0, -1);
		System.out.println(lrange.toString());
	}
	
	public static void set(Jedis jedis) {
		Set<String> letters = jedis.smembers("letters");
		Set<String> letters2 = jedis.smembers("letters2");
		System.out.println(letters.toString());
		System.out.println(letters2.toString());
		Set<String> sinter = jedis.sinter("letters", "letters2");
		System.out.println(sinter);
	}

	public static void zset(Jedis jedis) {
		Set<String> zrange = jedis.zrange("score", 0, -1);
		System.out.println(zrange.toString());
	}
	
	public static void sort(Jedis jedis) {
		
		SortingParams params = new SortingParams();
		params.desc().limit(0, 2);
		jedis.sort("numbers", params, "destkey");
		List<String> numbers = jedis.lrange("destkey", 0, 1);
		System.out.println("numbers : " + numbers.toString());
		
	}
	
	public static void testHgetAll(Jedis jedis) {
		jedis.hset("test01", "field1", "1");
		jedis.hset("test01", "field2", "2");
		jedis.hset("test01", "field3", "3");
		jedis.hset("test01", "field4", "4");
		Map<String, String> userMap = jedis.hgetAll("test01");
		
		Set<String> keySet = userMap.keySet();
		for (String key : keySet) {
			System.out.println("key : " + key + "  value : " + userMap.get(key));
		}
		
	}
	
	
}
