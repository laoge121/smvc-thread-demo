package redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by pei.xu on 2015/11/25.
 */
@Component
public class RedisUtil {

    @Resource
    private StringRedisTemplate redisTemplate;

    public void test() {
        redisTemplate.opsForValue().set("","");
    }
}
