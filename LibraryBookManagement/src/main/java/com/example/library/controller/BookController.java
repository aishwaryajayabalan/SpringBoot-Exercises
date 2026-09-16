package com.example.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.library.model.Book;
import com.example.library.service.BookService;

import jakarta.validation.Valid;


@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/books")
	public Book addBook(@Valid @RequestBody Book book) {
	    return bookService.addBook(book);
	}
	
	@GetMapping("/books")
	public List<Book> getAllBooks() {
	    return bookService.getAllBooks();
	}
	
	@GetMapping("/books/{bookId}")
	public Book getBookById(@PathVariable int bookId) {
	    return bookService.getBookById(bookId);
	}
	
	@PutMapping("/books/{bookId}/availability")
	public Book updateAvailability(@PathVariable int bookId,
	                               @RequestParam boolean available) {
	    return bookService.updateAvailability(bookId, available);
	}
	
	@DeleteMapping("/books/{bookId}")
	public String deleteBook(@PathVariable int bookId) {

	    boolean deleted = bookService.deleteBook(bookId);

	    if (deleted) {
	        return "Book deleted successfully";
	    }

	    return "Book not found";
	}

}
