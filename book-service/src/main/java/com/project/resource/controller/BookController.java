package com.project.resource.controller;

import com.project.resource.dto.AuthorVO;
import com.project.resource.dto.BookDTO;
import com.project.resource.dto.BookResponse;
import com.project.resource.entity.Book;
import com.project.resource.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public ResponseEntity<List<BookResponse>> findAllBooks() {
        List<AuthorVO> authors = Arrays.asList(restTemplate.getForObject("http://author-service:8083/", AuthorVO[].class));
        List<BookResponse> books = bookService.findAllBooks().stream()
                .map(book -> new BookResponse(
                        book.getId(),
                        book.getTitle(),
                        book.getDescription(),
                        authors.stream()
                                .filter(authorVO -> authorVO.getId().equals(book.getAuthorId()))
                                .findFirst()
                                .get()
                ))
                .toList();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        AuthorVO author = restTemplate.getForObject("http://author-service:8083/author/" + book.getAuthorId(), AuthorVO.class);
        BookResponse response = new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                author
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookResponse> createBook(@RequestBody BookDTO bookDTO) {
        AuthorVO authorVO = restTemplate.getForObject("http://author-service:8083/author/" + bookDTO.getAuthorId(), AuthorVO.class);
        Book book = bookService.createBook(bookDTO);
        BookResponse response = new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                authorVO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BookResponse> updateBook(@RequestBody Book book) {
        AuthorVO authorVO = restTemplate.getForObject("http://author-service:8083/author/" + book.getAuthorId(), AuthorVO.class);
        bookService.updateBook(book);

        BookResponse response = new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                authorVO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public HttpStatus deleteBooksByAuthorId(@PathVariable Long id) {
        bookService.deleteBooksByAuthorId(id);

        return HttpStatus.OK;
    }
}
