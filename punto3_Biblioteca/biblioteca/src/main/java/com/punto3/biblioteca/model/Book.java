package com.punto3.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;

    @Builder.Default
    private boolean available = true;

    private Long borrowedByMemberId;
}
