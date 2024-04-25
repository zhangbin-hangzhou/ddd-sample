package shopping.infrastructure.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StringRedisService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setBatch(String key, List<Integer> bitList, Boolean value) {
        bitList.forEach(s->{
            stringRedisTemplate.opsForValue().setBit(key,s,value);
        });
    }
}
