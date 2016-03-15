package redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: pei.xu
 * Date: 15-4-20
 * Time: 下午12:00
 * To change this template use File | Settings | File Templates.
 */
public class RedisTest {

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("***********);
//        jedis.auth("redis password");
//
//
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("");
//        System.out.println(jedis.get("incrnext"));
//        //jedis.del("aa");
//        //jedis.set("aa", "bbbbb");
//        //System.out.println(jedis.get("aa"));
//
//        //开启事物结束事物
//        Transaction transaction = jedis.multi();
//
//        transaction.watch("aa");
//
//        //结束事物
//        transaction.discard();
//
//        //提交事物
//        transaction.exec();
//
//        //管道异步获取数据
//        Pipeline pipeline = jedis.pipelined();
//        List<Object> ret = pipeline.syncAndReturnAll();
//
//        //分布式直接同步调用
//        List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("************), new JedisShardInfo("***********));
//        ShardedJedis shardedJedis = new ShardedJedis(shards);
//
//        //分布式直接异步调用
//        shardedJedis.pipelined();
//
//        //分布式链接池同步调用
//        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(new JedisPoolConfig(), shards);
//
//        ShardedJedis shardedJedis1 = shardedJedisPool.getResource();
//
//        shardedJedisPool.returnResource(shardedJedis1);
//
//        //分布式链接池异步调用
//        ShardedJedisPipeline pipeline1 = shardedJedis1.pipelined();

        //事物和异步调用中不能同步查询

        new RedisTest().test();
    }

    public void test() {

        //JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //JedisPool jedisPool = new JedisPool(new GenericObjectPoolConfig(), "*****", *****, Protocol.DEFAULT_TIMEOUT, "%",
        //        Protocol.DEFAULT_DATABASE, null);
        //Jedis jedis = jedisPool.getResource();
        String masterName = "mymaster";

        Set<String> sentinelSet = new HashSet<String>();
        sentinelSet.add("***********");
        sentinelSet.add("**********");
        sentinelSet.add("***********");

        JedisSentinelPool jedisPool = new JedisSentinelPool(masterName, sentinelSet,"###");
        System.out.println("current Host:"  + jedisPool.getCurrentHostMaster());
        Jedis jedis = jedisPool.getResource();

        this.testAddGetMethod(jedis);
        jedisPool.returnResourceObject(jedis);
    }

    /**
     * 测试 添加 获取 字符串方法
     *
     * @param jedis
     */
    public void testAddGetMethod(Jedis jedis) {
        if (jedis.exists("aa")) {
            jedis.del("aa");
        }
        jedis.set("aa", "bbbbbb");
        System.out.println(jedis.get("aa"));
        jedis.append("aa", "dddd");
        System.out.println(jedis.get("aa"));
    }

}
