package com.example.library.service;

import java.util.ArrayList;
import java.util.List;

import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.DuplicateBookException;
import com.example.library.model.Book;
import org.springframework.stereotype.Service;

@Service

public class BookService {
	
	public List<Book> getAllBooks() {
	    return books;
	}
	
	private List<Book> books = new ArrayList<>();
	
	public Book addBook(Book book) {

	    for (Book existingBook : books) {

	        if (existingBook.getBookId() == book.getBookId()) {
	            throw new DuplicateBookException(
	                "Book already exists with id: " + book.getBookId()
	            );
	        }
	    }

	    books.add(book);
	    return book;
	}
	
	
	public Book getBookById(int bookId) {

	    for (Book book : books) {

	        if (book.getBookId() == bookId) {
	            return book;
	        }
	    }

	    throw new BookNotFoundException("Book not found with id: " + bookId);
	}
	
	public Book updateAvailability(int bookId, boolean available) {

	    for (Book book : books) {

	        if (book.getBookId() == bookId) {
	            book.setAvailable(available);
	            return book;
	        }
	    }

	    throw new BookNotFoundException("Book not found with id: " + bookId);
	}
	
	public boolean deleteBook(int bookId) {

	    for (Book book : books) {

	        if (book.getBookId() == bookId) {
	            books.remove(book);
	            return true;
	        }
	    }

	    throw new BookNotFoundException("Book not found with id: " + bookId);
	}
	

}
