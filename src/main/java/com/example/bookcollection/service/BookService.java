package com.example.bookcollection.service;

import com.example.bookcollection.entity.Book;
import com.example.bookcollection.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(String sortField, String sortDir, String genreFilter, String authorFilter) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);

        boolean hasGenreFilter = StringUtils.hasText(genreFilter);
        boolean hasAuthorFilter = StringUtils.hasText(authorFilter);

        if (hasGenreFilter && hasAuthorFilter) {
            return bookRepository.findByGenreContainingIgnoreCaseAndAuthorContainingIgnoreCase(genreFilter, authorFilter, sort);
        } else if (hasGenreFilter) {
            return bookRepository.findByGenreContainingIgnoreCase(genreFilter, sort);
        } else if (hasAuthorFilter) {
            return bookRepository.findByAuthorContainingIgnoreCase(authorFilter, sort);
        } else {
            return bookRepository.findAll(sort);
        }
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}