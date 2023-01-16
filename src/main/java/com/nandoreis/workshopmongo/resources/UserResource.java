package com.nandoreis.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nandoreis.workshopmongo.domain.User;
import com.nandoreis.workshopmongo.dto.UserDTO;
import com.nandoreis.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	// @RequestMapping(method=RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){		
		List<User> list = service.findAll();
		
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
				
		return ResponseEntity.ok().body(listDto);		
	}
	
	@GetMapping(value="/{id}")
 	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

}
