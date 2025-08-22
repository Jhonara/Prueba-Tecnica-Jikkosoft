package com.punto3.biblioteca.controller;

import com.punto3.biblioteca.model.Book;
import com.punto3.biblioteca.model.Member;
import com.punto3.biblioteca.model.Library;
import com.punto3.biblioteca.service.LibraryService;
import com.punto3.biblioteca.service.LibraryService.CreateBookRequest;
import com.punto3.biblioteca.service.LibraryService.CreateMemberRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryController {

    private final LibraryService service;

    public LibraryController(LibraryService service) {
        this.service = service;
    }

    // ---------------- Libros ----------------

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody CreateBookRequest req) {
        return service.addBook(req);
    }

    @GetMapping("/books")
    public List<Book> listBooks() {
        return service.listBooks();
    }

    @GetMapping("/books/available")
    public List<Book> listAvailableBooks() {
        return service.listAvailableBooks();
    }

    // ---------------- Miembros ----------------

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public Member registerMember(@Valid @RequestBody CreateMemberRequest req) {
        return service.registerMember(req);
    }

    @GetMapping("/members")
    public List<Member> listMembers() {
        return service.listMembers();
    }

    @GetMapping("/members/{memberId}/books")
    public List<Book> listBorrowedBooks(@PathVariable Long memberId) {
        return service.listBorrowedByMember(memberId);
    }

    // ---------------- Préstamos ----------------

    @PostMapping("/loans/borrow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrowBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        service.borrowBook(bookId, memberId);
    }

    @PostMapping("/loans/return")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        service.returnBook(bookId, memberId);
    }

    // ---------------- Estado general (opcional) ----------------

    @GetMapping("/state")
    public Library getLibraryState() {
        return service.getLibraryState();
    }
}