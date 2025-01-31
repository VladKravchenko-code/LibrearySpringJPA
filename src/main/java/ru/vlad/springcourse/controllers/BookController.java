package ru.vlad.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlad.springcourse.Service.BooksService;
import ru.vlad.springcourse.Service.PeopleService;
import ru.vlad.springcourse.models.Person;
import ru.vlad.springcourse.repository.PeopleRepository;

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
    public String index(Model model) {
        model.addAttribute("books", booksService.findByAll());
        return "/books/index";     // выводит всех
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findBuId(id));
        model.addAttribute("peopleForBook", booksService.findBuId(id).getOwner());
        model.addAttribute("people", peopleService.findAll());
        return "/books/show";    //выводит одного
    }

//    @PatchMapping("/{id}")
//    public String allowsYouGiveBookPerson(@PathVariable("id") int id,
//                                          @ModelAttribute("person") Person person){
//        if (bookDAO.checksWhichPersonHasThisBook(id) != null) {
//            bookDAO.deletesBookFromUser(id);
//            return "redirect:/books/{id}";
//        }
//
//        bookDAO.displaysListPeopleForBook(id, person.getId()); //позволяет давать книгу человеку
//        return "redirect:/books/{id}";
//    }
//
//    @GetMapping("/new")
//    public String newBook(@ModelAttribute("book") Book book) {
//        return "/books/new";   //get запрос который переводит на представление
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("book") @Valid Book book,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "books/new";  //если есть ошибка, то вернет обратно на туже страницу
//
//        bookDAO.save(book);
//
//        return "redirect:/books";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("book", bookDAO.show(id));
//        return "/books/edit";
//    }
//
//    @PatchMapping("/{id}/edit")
//    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "books/edit";  //если есть ошибка, то вернет обратно на туже страницу
//
//        bookDAO.update(id, book);
//
//        return "redirect:/books";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id){
//        bookDAO.delete(id);
//        return "redirect:/books";
//    }
}
