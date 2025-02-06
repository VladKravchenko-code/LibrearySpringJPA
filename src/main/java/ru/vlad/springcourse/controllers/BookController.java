package ru.vlad.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vlad.springcourse.Service.BooksService;
import ru.vlad.springcourse.Service.PeopleService;
import ru.vlad.springcourse.models.Book;
import ru.vlad.springcourse.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(required = false, value = "sort_by_year") boolean sort,
                        @RequestParam(required = false, value = "page") Integer page,
                        @RequestParam(required = false, value = "books_per_page") Integer booksPerPage) {
        // required = false нужен, чтоб можно было писать в поисковике Books без этих параметров и все будет работать
        if (sort && page != null && booksPerPage != null) {
            model.addAttribute("books", booksService.findAllPagesAndSorted(page, booksPerPage));
            return "/books/index";
            // Сортировка и пагинация
        }
        if (sort) {
            model.addAttribute("books", booksService.findAllSort());
            return "/books/index";
            // Сортировка
        }
        if (page != null && booksPerPage != null) {
            model.addAttribute("books", booksService.findAllPages(page, booksPerPage));
            return "/books/index";
            // Пагинация
        }
        model.addAttribute("books", booksService.findByAll());
        return "/books/index";     // выводит всех
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findBuId(id));
        model.addAttribute("peopleForBook", booksService.findBuId(id).getOwner());
        model.addAttribute("people", peopleService.findAll());
        return "/books/show";    //выводит одного
    }

    @PatchMapping("/{id}")
    public String allowsYouGiveBookPerson(@PathVariable("id") int id,
                                          @ModelAttribute("person") Person person) {
        if (booksService.findBuId(id).getOwner() != null) {
            booksService.freesBook(id);
            return "redirect:/books/{id}";
        }

        booksService.bookHasMaster(id, person); //позволяет давать книгу человеку
        return "redirect:/books/{id}";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new";   //get запрос, который переводит на представление
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";  //если есть ошибка, то вернет обратно на туже страницу

        booksService.saveAndFlush(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id).orElse(null));
        //так как Optional, то без orElse(null) не заработает thymeleaf представление
        return "/books/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/edit";  //если есть ошибка, то вернет обратно на туже страницу

        booksService.saveAndFlashUpdate(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("book") Book book) {
        // Пустая книга отправляется в представление
        return "/books/search";
    }

    @PatchMapping("/search")
    public String searchCompleted(Model model, @ModelAttribute Book book) {
        model.addAttribute("book", booksService.findByTitleStartingWith(book.getTitle()));
        // Тут книга уже со значением отправляется в поиск

        model.addAttribute("newBook", new Book());
        // Новая книга, чтоб поиск можно было продолжить
        return "/books/found";
    }
}
