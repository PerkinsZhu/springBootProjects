package com.perkins.service

import com.perkins.beans.Person
import com.perkins.repo.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonServiceScala {
  @Autowired
  val personRepository: PersonRepository = null;

  def listPerson(): java.util.List[Person] = {
    println(s"personRepository = ${personRepository}")
    return personRepository.findAll()
  }
}
