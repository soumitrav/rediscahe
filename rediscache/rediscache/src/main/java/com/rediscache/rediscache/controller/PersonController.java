package com.rediscache.rediscache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rediscache.rediscache.model.Person;
import com.rediscache.rediscache.model.RangeDTO;
import com.rediscache.rediscache.service.RedisPersonCache;
import com.rediscache.rediscache.service.RedisService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

	@Autowired
	RedisPersonCache cacheService;
	
	@Autowired
	RedisService listService;
	
	@PostMapping
	@ApiResponse(description = "This to add person")
	@Hidden
	public void addPerson(@RequestBody final Person person) {
		cacheService.addValue(person.getId(), person);
	}
	
	@GetMapping("/{id}")
	public Person getPerson(@PathVariable(value = "id") final String id) {
		return (Person)cacheService.get(id);
	}
	
	@PatchMapping
	public void updatePerson(@RequestBody final Person person) {
		cacheService.addValue(person.getId(), person);
	}
	
	
	@DeleteMapping
	public void deletePerson(@PathVariable(value = "id") final String id) {
		cacheService.delete(id);
	}
	
	@GetMapping("/all")
	@Hidden
	public List<Object> getAll() {
		return cacheService.getAll();
	}
	
	@PostMapping("/list/addAll/{key}")
	public void addAllPerson(@PathVariable String key, @RequestBody List<Person> persons) {
		listService.addAllPerson(key, persons);
	}
	
	@PostMapping("/list/add")
	public void addPerson(String key, Person person) {
		listService.addPerson(key, person);
	}
	
	@GetMapping("/list/last")
	public void getLastPersonFromList(String key, Person person) {
		listService.addPerson(key, person);
	}
	
	@PostMapping("/list/range/{key}")
	public List<Object> getRangePersons(@PathVariable String key, @RequestBody RangeDTO range) {
		return listService.getElementInRange(key, range);
	}
	
	@PostMapping("/list/trim/range/{key}")
	public void trimRangePersons(@PathVariable String key, @RequestBody RangeDTO range) {
		listService.trimElementInRange(key, range);
	}
}
