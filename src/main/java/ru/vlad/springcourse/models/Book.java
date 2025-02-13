package ru.vlad.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Колонка название книги не должна быть пустой")
    @Size(min = 2, max = 50, message = "Колонка название книги должна содержать от 2 до 50 символов")
    private String title;

    @NotEmpty(message = "Колонка автор не должна быть пустой")
    @Size(min = 2, max = 50, message = "Колонка автор должно содержать от 2 до 50 символов")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "Колонка автор должна содержать Имя и Фамилию")
    @Column(name = "author")
    private String author;

    @Min(value = 1800, message = "Год написания книги должен быть больше 1800")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private Person owner;

    @Column(name = "date")
    private LocalDate date;

    @Transient
    private boolean overdue;

    public Book() {

    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
        if (owner != null){
            this.date = LocalDate.now();
        } else
            this.date = null;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getOverdue() {
        long daysBetween = ChronoUnit.DAYS.between(this.date, LocalDate.now());
        this.overdue = daysBetween >= 10;
        return overdue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
