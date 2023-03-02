package com.example.restws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.example.restws.model.Book;

@Service
public class BookServiceImpl implements BookService {
    Map<Long, Book> books = new HashMap<>();
    long currentId = 0;

    public BookServiceImpl() {
        currentId++;
        Book book1 = new Book((int) currentId, "First Book", new Date());
        currentId++;
        Book book2 = new Book((int) currentId, "Second Book", new Date());
        currentId++;
        Book book3 = new Book((int) currentId, "Third Book", new Date());

        books.put(book1.getId(), book1);
        books.put(book2.getId(), book2);
        books.put(book3.getId(), book3);
    }

    @Override
    public List<Book> getBooks() {
        Collection<Book> results = books.values();
        List<Book> response = new ArrayList<>(results);
        return response;
    }

    @Override
    public Book getBook(Long id) {
        return books.get(id);
    }

    @Override
    public Response createBook(Book book) {
        currentId++;
        book.setId(currentId);
        book.setDate(new Date());
        books.put(book.getId(), book);
        return Response.ok(book).build();
    }

    @Override
    public Response updateBook(Book book) {
        Book currentBook = books.get(book.getId());
        Response response;
        if (currentBook != null) {
            books.put(book.getId(), book);
            response = Response.ok().build();
        } else {
            response = Response.notModified().build();
        }
        return response;
    }

    @Override
    public Response deleteBook(Long id) {
        Book book = books.get(id);
        Response response;
        if (book != null) {
            books.remove(id);
            response = Response.ok().build();
        } else {
            response = Response.notModified().build();
        }
        return response;
    }
}