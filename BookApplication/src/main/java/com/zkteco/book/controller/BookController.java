package com.zkteco.book.controller;

import java.util.Map;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.BookNotFoundException;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.BookService;

@EnableAutoConfiguration
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	private BookService bookService;
	

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Map<String, Object>> findPaginated(@RequestParam(required = false) String search,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "bookId,asc") String[] sort) throws BookNotFoundException {
		ResponseEntity<Map<String, Object>> res = bookService.getAllBooks(search, page, size, sort);
		return res;
	}

	@GetMapping("/{id}")
	public ResultDTO fetchBookById(@PathVariable(value = "id") String id) throws BookNotFoundException {
		return bookService.fetchById(id);
	}

	@PostMapping
	public BookDTO saveBook(@Valid @RequestBody ResultDTO dto) {
		return bookService.saveBook(dto);
	}

	@PutMapping("/{id}")
	public BookDTO updateBook(@PathVariable(value = "id") String id, @Valid @RequestBody Book bk) {
		return bookService.updateBookById(id, bk);
	}

	@DeleteMapping("/{id}")
	public BookDTO deleteBookById(@PathVariable(value = "id") String id) throws BookNotFoundException {
		return bookService.deleteBookById(id);
	}

}
