package ru.vlad.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vlad.springcourse.Service.PeopleService;
import ru.vlad.springcourse.dao.PersonDAO;
import ru.vlad.springcourse.models.Person;
import ru.vlad.springcourse.repository.PeopleRepository;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(peopleService.findByName(person.getName()).isPresent()){
            errors.rejectValue("name", "", "Человек с таким ФИО уже есть");
        }
    }
}
