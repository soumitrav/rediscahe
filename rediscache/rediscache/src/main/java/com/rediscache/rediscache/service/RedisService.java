package com.rediscache.rediscache.service;

import java.util.List;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.rediscache.rediscache.model.Person;
import com.rediscache.rediscache.model.RangeDTO;

@Service
public class RedisService {

	final RedisTemplate<String, Object> redisTemplate;
	final ListOperations<String, Object> opsForList;
	
	public RedisService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		opsForList = redisTemplate.opsForList();
	}
	
	public void addAllPerson(String key, List<Person> persons) {
		persons.forEach(x -> {opsForList.leftPush(key, x);});
	}
	
	public void addPerson(String key, Person person) {
		opsForList.rightPush(key, person);
	}
	
	public void getLastElement(String key) {
		opsForList.rightPop(key);
	}
	
	public List<Object> getElementInRange(String key, RangeDTO range) {
		return opsForList.range(key, range.getFrom(), range.getTo());
	}
	
	public void trimElementInRange(String key, RangeDTO range) {
		opsForList.trim(key, range.getFrom(), range.getTo());
	}
}
