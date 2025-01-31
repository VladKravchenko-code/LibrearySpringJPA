package ru.vlad.springcourse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlad.springcourse.models.Book;
import ru.vlad.springcourse.models.Person;
import ru.vlad.springcourse.repository.BooksRepository;

import java.util.List;
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
        //Выводит книги которые принадлежат человеку
    }
}
