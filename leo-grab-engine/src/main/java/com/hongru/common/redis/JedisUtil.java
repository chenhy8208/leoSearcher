package com.hongru.common.redis;


import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

public class JedisUtil {
	
    public static Jedis createJedis(String host, int port) {
        Jedis jedis = new Jedis(host, port);

        return jedis;
    }

    public static Jedis createJedis(String host, int port, String passwrod) {
        Jedis jedis = new Jedis(host, port);

        if (!StringUtils.isEmpty(passwrod))
            jedis.auth(passwrod);
        
        return jedis;
    }
}
