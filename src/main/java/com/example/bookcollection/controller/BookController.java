package com.example.bookcollection.controller;

import com.example.bookcollection.entity.Book;
import com.example.bookcollection.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listBooks(Model model,
                            @RequestParam(defaultValue = "title") String sortField,
                            @RequestParam(defaultValue = "asc") String sortDir,
                            @RequestParam(required = false) String genreFilter,
                            @RequestParam(required = false) String authorFilter) {
        model.addAttribute("books", bookService.getAllBooks(sortField, sortDir, genreFilter, authorFilter));
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("genreFilter", genreFilter);
        model.addAttribute("authorFilter", authorFilter);
        return "index";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add New Book");
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book book,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", book.getId() == null ? "Add New Book" : "Edit Book");
            return "book-form";
        }
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message", "Book saved successfully!");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Book> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            model.addAttribute("book", bookOptional.get());
            model.addAttribute("pageTitle", "Edit Book");
            return "book-form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Book not found with ID: " + id);
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (bookService.getBookById(id).isPresent()) {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("message", "Book deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cannot delete. Book not found with ID: " + id);
        }
        return "redirect:/";
    }
}