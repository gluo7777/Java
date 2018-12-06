package basicredis;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class App {

	public static void main(String[] args) {
		// standalone, not thread-safe
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		jedis.del("nonExistentKey");
		
		// strings
		if(jedis.exists("name")) jedis.del("name");
		jedis.set("name", "William Luo");
		System.out.println(jedis.get("name"));
		System.out.println(jedis.get("res:token"));
		
		// lists
		String collection = "animals";
		jedis.del(collection);
		jedis.lpush(collection,"dog","cat","panda","tiger");
		jedis.rpop(collection);
		System.out.println(jedis.lrange(collection, 0, -1));
		
		// sets
		jedis.del(collection);
		jedis.sadd(collection,"dog","cat","panda","tiger");
		jedis.sadd(collection, "dog");
		jedis.srem(collection, "tiger");
		System.out.println(jedis.smembers(collection));
		System.out.println(jedis.sismember(collection, "tiger"));
		System.out.println(jedis.sismember(collection, "panda"));
		
		// hashes or maps
		jedis.del(collection);
		jedis.hset(collection, "firstName", "William");
		jedis.hset(collection, "lastName", "Luo");
		jedis.hset(collection, "age", "23");
		System.out.println(jedis.hget(collection, "firstName"));
		
		// combine multiple commands into a transaction
		// a transaction guarantees atomicity
		Transaction tran = jedis.multi();
		List<Response<Long>> responses = new LinkedList<>();
		String num = "count";
		tran.set(num, "3");
		responses.add(tran.incr(num));
		responses.add(tran.incr(num));
		responses.add(tran.incr(num));
		responses.add(tran.incr(num));
		System.out.println("Results: " + tran.exec());
		System.out.println("Response list: " + responses.stream().map(r -> r.get()).collect(Collectors.toList()));
		
		// combine multiple commands into a pipeline
		// a pipeline does not guarantee atomicity, but does everything in one connection request
		// good for slower networks
		responses.clear();
		Pipeline pipe = jedis.pipelined();
		num = "count";
		pipe.set(num, "3");
		responses.add(pipe.incr(num));
		responses.add(pipe.incr(num));
		responses.add(pipe.incr(num));
		responses.add(pipe.incr(num));
		pipe.sync();
		// can only get value from Response after calling pipeline.sync()
		System.out.println("Response list: " + responses.stream().map(r -> r.get()).collect(Collectors.toList()));
		
		jedis.close();
		
		// using a Jedis Pool for safety
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
		try(Jedis jedis2 = pool.getResource()) {
			if(jedis.exists("name")) jedis.del("name");
			jedis.set("name", "William Luo");
			System.out.println(jedis.get("name"));
			System.out.println(jedis.get("res:token"));
		}
		pool.close();
	}

}
