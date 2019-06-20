package example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.domain.User;
import example.service.UserService;

@RestController
@RequestMapping(path="/user")
public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping
	public Integer create(@RequestBody User entity)
	{
		return service.create(entity);
	}
	
	@PutMapping(path="/{id}")
	public User update(@PathVariable Integer id, @RequestBody User entity)
	{
		return service.update(id, entity);
	}
	@DeleteMapping(path="/{id}")
	public void delete(@PathVariable Integer id)
	{
		service.delete(id);
	}
	@GetMapping(path="/{id}")
	public User search(@PathVariable Integer id)
	{
		return service.findById(id);
	}
	@GetMapping
	public List<User> search()
	{
		return service.search();
	}
}
