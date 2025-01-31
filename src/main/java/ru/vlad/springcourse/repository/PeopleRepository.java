package ru.vlad.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlad.springcourse.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Person findByName(String name);

    void deleteById(int id);

}
