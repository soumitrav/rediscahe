package com.rediscache.rediscache.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisPersonCache {
	
	final ValueOperations<String, Object> opsForValue;
		
	public RedisPersonCache(final RedisTemplate<String, Object> redisTemplate) {
		opsForValue = redisTemplate.opsForValue();
	}
	
	public void addValue(String key, Object data) {
		opsForValue.set(key, data);
	}
	
	public Object updateValue(String key, Object data) {
		return opsForValue.getAndSet(key, data);
	}
	
	public Object get(String key) {
		return opsForValue.get(key);
	}
	
	public void delete(String key) {
		opsForValue.getOperations().delete(key);
	}
	
	public List<Object> getAll() {
		Set<String> keys = opsForValue.getOperations().keys("*");
		return opsForValue.multiGet(keys);
	}
	
}
