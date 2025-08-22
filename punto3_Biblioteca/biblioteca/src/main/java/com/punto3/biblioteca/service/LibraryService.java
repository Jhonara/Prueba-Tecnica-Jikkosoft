package com.punto3.biblioteca.service;

import com.punto3.biblioteca.model.Book;
import com.punto3.biblioteca.model.Library;
import com.punto3.biblioteca.model.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    // “Persistencia” en memoria
    private final Library library = new Library();
    private final AtomicLong bookSeq = new AtomicLong(1);
    private final AtomicLong memberSeq = new AtomicLong(1);

    private static final int MAX_BORROWED = 3; // regla simple de negocio

    // ---------------- DTOs internos ----------------

    public record CreateBookRequest(@NotBlank String title,
                                    @NotBlank String author,
                                    @NotBlank String isbn) {
    }

    public record CreateMemberRequest(@NotBlank String name,
                                      @Email String email) {
    }

    // ---------------- Libros ----------------

    public Book addBook(CreateBookRequest req) {
        Long id = bookSeq.getAndIncrement();
        Book book = Book.builder()
                .id(id)
                .title(req.title())
                .author(req.author())
                .isbn(req.isbn())
                .build();
        library.getBooks().put(id, book);
        return book;
    }

    public List<Book> listBooks() {
        return new ArrayList<>(library.getBooks().values());
    }

    public List<Book> listAvailableBooks() {
        return library.getBooks().values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    // ---------------- Miembros ----------------

    public Member registerMember(CreateMemberRequest req) {
        Long id = memberSeq.getAndIncrement();
        Member member = Member.builder()
                .id(id)
                .name(req.name())
                .email(req.email())
                .build();
        library.getMembers().put(id, member);
        return member;
    }

    public List<Member> listMembers() {
        return new ArrayList<>(library.getMembers().values());
    }

    public List<Book> listBorrowedByMember(Long memberId) {
        Member member = library.getMembers().get(memberId);
        if (member == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Miembro no existe");

        return member.getBorrowedBookIds().stream()
                .map(library.getBooks()::get)
                .filter(Objects::nonNull)
                .toList();
    }

    // ---------------- Préstamos ----------------

    public void borrowBook(Long bookId, Long memberId) {
        Book book = library.getBooks().get(bookId);
        Member member = library.getMembers().get(memberId);

        if (book == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no existe");
        if (member == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Miembro no existe");
        if (!book.isAvailable())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Libro no disponible");
        if (member.getBorrowedBookIds().size() >= MAX_BORROWED)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Miembro alcanzó el máximo de préstamos");

        book.setAvailable(false);
        book.setBorrowedByMemberId(memberId);
        member.getBorrowedBookIds().add(bookId);
    }

    public void returnBook(Long bookId, Long memberId) {
        Book book = library.getBooks().get(bookId);
        Member member = library.getMembers().get(memberId);

        if (book == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no existe");
        if (member == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Miembro no existe");
        if (!Objects.equals(book.getBorrowedByMemberId(), memberId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ese miembro no tiene este libro");

        book.setAvailable(true);
        book.setBorrowedByMemberId(null);
        member.getBorrowedBookIds().remove(bookId);
    }

    // ---------------- Estado general (opcional) ----------------

    public Library getLibraryState() {
        return library;
    }
}
