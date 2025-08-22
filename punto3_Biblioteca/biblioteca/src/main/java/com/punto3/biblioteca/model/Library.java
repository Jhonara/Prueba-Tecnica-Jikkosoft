package com.punto3.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Library {

    @Builder.Default
    private String name = "Biblioteca Central";

    @Builder.Default
    private Map<Long, Book> books = new ConcurrentHashMap<>();

    @Builder.Default
    private Map<Long, Member> members = new ConcurrentHashMap<>();
}
