package com.perkins.repo;

import com.perkins.beans.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    // 注意这里的字段名称要和bean的字段保持一致，
    Person findByUsername(String username);
}