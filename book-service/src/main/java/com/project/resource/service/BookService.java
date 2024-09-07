package com.project.resource.service;

import com.project.resource.dto.BookDTO;
import com.project.resource.entity.Book;
import com.project.resource.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book createBook(BookDTO bookDTO) {
        return bookRepository.save(Book.builder()
                .title(bookDTO.getTitle())
                .description(bookDTO.getDescription())
                .authorId(bookDTO.getAuthorId().longValue())
                .build());
    }


    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public Long deleteBook(Long id) {
        bookRepository.deleteById(id);
        return id;
    }

    public void deleteBooksByAuthorId(Long id) {
        bookRepository.findAll().stream()
                .filter(book -> book.getAuthorId().equals(id))
                .forEach(book -> bookRepository.deleteById(book.getId()));
    }
}
