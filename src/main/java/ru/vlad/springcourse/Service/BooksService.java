package ru.vlad.springcourse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlad.springcourse.models.Book;
import ru.vlad.springcourse.models.Person;
import ru.vlad.springcourse.repository.BooksRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findByAll() {
        return booksRepository.findAll();
    }

    public Book findBuId(int id){
        return booksRepository.findById(id).orElse(null);
    }

    public Optional<List<Book>> findByOwnerId(int id) {
        return Optional.ofNullable(booksRepository.findByOwner_Id(id));
        //Выводит книги, которые принадлежат человеку
    }

    @Transactional
    public void freesBook(int id) {
        Objects.requireNonNull(booksRepository.findById(id).orElse(null)).setOwner(null);
        //Удаляет у книги пользователя
    }

    @Transactional
    public void bookHasMaster(int id, Person person) {
        Objects.requireNonNull(booksRepository.findById(id).orElse(null)).setOwner(person);
        //Добавляет у книги пользователя
    }

    @Transactional
    public void saveAndFlush(Book book) {
        booksRepository.saveAndFlush(book);
    }

    public Optional<Book> findById(int id) {
        return booksRepository.findById(id);
    }

    @Transactional
    public void saveAndFlashUpdate(int id, Book book) {
        book.setId(id);
        booksRepository.saveAndFlush(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    public Book findByTitleStartingWith(String titleBook) {
        List<Book> books = booksRepository.findByTitleStartingWith(titleBook);
        if (!books.isEmpty()) {
            return books.get(0);
        }
        return null;
    }

    public List<Book> findAllSort(){
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> findAllPages(Integer page, Integer itemsPerPage) {
        return booksRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
    }

    public List<Book> findAllPagesAndSorted(Integer page, Integer itemsPerPage) {
        return booksRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("year"))).getContent();
    }
}
