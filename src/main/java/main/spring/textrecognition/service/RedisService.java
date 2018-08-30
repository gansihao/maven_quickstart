package main.spring.textrecognition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 往redis中存入数据
     * @param key
     * @param value
     */
    public void put(String key,Object value){
        redisTemplate.opsForValue().set(key, value);
    };

    /**
     * 从redis中取数据
     * @param key
     * @return
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    };

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

