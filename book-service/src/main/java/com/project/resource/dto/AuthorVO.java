package com.project.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorVO {

    private Long id;
    private String firstname;
    private String lastname;
}
