package ru.vlad.springcourse.models;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Колонка имя не должна быть пустой")
    @Size(min = 2, max = 50, message = "ФИО должно содержать от 2 до 50 символов")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "Не верно указано ФИО")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value = 2025, message = "Неверный год рождения")
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public List<Book> getBooks() {
        if (books == null){
            books = new ArrayList<>();
        }
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
