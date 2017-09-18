package com.lyy.sales;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis = new Jedis("localhost");
		System.out.println(jedis.ping());
		//字符串的存储
		//jedis.set("§关山难越§", "此岸ǒ彼岸");
		//System.out.println(jedis.get("§关山难越§"));
		//存储列表数据 lpush
		//jedis.lpush("students", "M  ,");
		//jedis.lpush("students", "jame");
		//jedis.lpush("students", "anna");
		//jedis.lpush("students", "amy");
		//start  end(包含)  0 1 2
		/*List<String> list = jedis.lrange("students", 0, 3);
		for(int i = 0 ;i<list.size();i++){
			System.out.println(list.get(i));
		}*/
		Set<String> keys = jedis.keys("*");
		Iterator<String> itor = keys.iterator();
		while(itor.hasNext()){
			System.out.println(itor.next());
		}
		
		
	}

}
