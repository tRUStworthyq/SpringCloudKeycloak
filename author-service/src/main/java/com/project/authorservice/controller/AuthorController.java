package com.project.authorservice.controller;

import com.project.authorservice.dto.AuthorDTO;
import com.project.authorservice.entity.Author;
import com.project.authorservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService service;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public ResponseEntity<List<Author>> findAllAuthors() {
        return new ResponseEntity<>(service.findAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> findAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findAuthorById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(service.createAuthor(authorDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(service.updateAuthor(author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAuthor(@PathVariable Long id) {
        restTemplate.delete("http://resourse-service:8082/del/" + id);

        return new ResponseEntity<>(service.deleteAuthor(id), HttpStatus.OK);
    }


}
