package com.example.nanda.sample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nanda.sample.models.Person;
import com.example.nanda.sample.repositories.PersonRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
	@GetMapping("/persons")
    public List<Person> getPeople() {
		System.out.println("Getting all customers...");
        return personRepository.findAll();
    }

    @PostMapping("/persons")
    public Person createPerson(@Valid @RequestBody Person person) {
    	System.out.println("Adding Person -> " +person.getFirstName() +" "+ person.getLastName() );
        return personRepository.save(person);
    }

    @GetMapping(value="/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") String id) {
        System.out.println("Getting Person with ID -> " +id);
    	
    	Person person = personRepository.findOne(id);
        if(person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @PutMapping(value="/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") String id,
                                           @Valid @RequestBody Person person) {
        Person personData = personRepository.findOne(id);
        if(personData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personData.setFirstName(person.getFirstName());
        personData.setLastName(person.getLastName());
        Person updatedPerson = personRepository.save(personData);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping(value="/person/{id}")
    public void deletePerson(@PathVariable("id") String id) {
    	System.out.println("Deleting Person with ID -> " +id);
    	personRepository.delete(id);
    }

}
