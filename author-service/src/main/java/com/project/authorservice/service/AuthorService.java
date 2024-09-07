package com.project.authorservice.service;

import com.project.authorservice.dto.AuthorDTO;
import com.project.authorservice.entity.Author;
import com.project.authorservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;


    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    public Author findAuthorById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Author createAuthor(AuthorDTO authorDTO) {
        return repository.save(Author.builder()
                .firstname(authorDTO.getFirstname())
                .lastname(authorDTO.getLastname())
                .build());
    }

    public Author updateAuthor(Author author) {
        return repository.save(author);
    }

    public Long deleteAuthor(Long id) {
        repository.deleteById(id);
        return id;
    }

}
