package ru.vlad.springcourse.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Колонка имя не должна быть пустой")
    @Size(min = 2, max = 50, message = "ФИО должно содержать от 2 до 50 символов")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "Не верно указано ФИО")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value = 2025, message = "Неверный год рождения")
    private int age;

    public Person() {

    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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
