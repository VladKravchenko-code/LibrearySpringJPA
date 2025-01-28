package ru.vlad.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vlad.springcourse.models.Book;

import java.util.List;
import java.util.Map;

@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?", updateBook.getTitle(),
                updateBook.getAuthor(), updateBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void displaysListPeopleForBook(int id, int idPerson){
        jdbcTemplate.update("UPDATE Book SET id_person=? WHERE id=?", idPerson, id); //выводит список людей
    }

    public Integer checksWhichPersonHasThisBook(int id) {
        return jdbcTemplate.queryForObject("SELECT id_person FROM Book WHERE id=?", new Object[]{id},
                Integer.class);
    }

    public void deletesBookFromUser(int id){
        jdbcTemplate.update("UPDATE Book SET id_person=null WHERE id=?", id); //выводит список людей
    }


}
