package ru.vlad.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vlad.springcourse.models.Book;
import ru.vlad.springcourse.models.Person;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class PersonDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//    public Optional<Person> show(String name) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findFirst();
//        //Для проверки есть ли человек с таким именем
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person (name, age) VALUES(?, ?)", person.getName(),
//                person.getAge());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET name=?, age=? WHERE id=?", updatedPerson.getName(),
//                updatedPerson.getAge(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public List<Book> displayAllBooksOnePerson (int id) {
//        List<Book> list = jdbcTemplate.query("SELECT * FROM Book WHERE id_person=?",
//                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
//        return list;
//    }
}
