package ru.vlad.springcourse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.vlad.springcourse.models.Person;
import ru.vlad.springcourse.repository.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<Person> findById(int id) {
        return peopleRepository.findById(id);
    }

    public Optional<Person> findByName(String name) {
        return Optional.ofNullable(peopleRepository.findByName(name));
    }

    @Transactional
    public void saveAndFlush(Person person) {
        peopleRepository.saveAndFlush(person);
    }

    @Transactional
    public void deleteById(int id) {
        peopleRepository.deleteById(id);
    }
}
