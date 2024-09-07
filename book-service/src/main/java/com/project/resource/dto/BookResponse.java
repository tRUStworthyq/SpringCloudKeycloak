package com.project.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String description;
    private AuthorVO author;
}
