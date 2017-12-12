package com.example.nanda.sample.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.nanda.sample.models.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

}
