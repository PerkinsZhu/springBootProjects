package com.perkins.service;

import com.perkins.beans.Person;
import com.perkins.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> listPerson() {
//        return personRepository.findAll();
        List list = new ArrayList(1);
        list.add(personRepository.findByUsername("jack"));
        return list;
    }


}
