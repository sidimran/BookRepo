package com.zkteco.book.service;

import java.util.List;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.BookNotFoundException;

public interface BookService {

	public ResultDTO entityToDto(Book book);

	public List<ResultDTO> entityToDto(List<Book> book);

	public Book dtoToEntity(ResultDTO dto);

	public List<Book> dtoToEntity(List<ResultDTO> dto);

	public BookDTO deleteBookById(String id) throws BookNotFoundException;

	public Page<Book> findPaginated(int pageNo, int pageSize);

	public ResponseEntity<Map<String, Object>> getAllBooks(String search, int page, int size, String[] sort)
			throws BookNotFoundException;

	public BookDTO saveBook(ResultDTO dto);

	public BookDTO updateBookById(String id, Book bk);

	public ResultDTO fetchById(String id) throws BookNotFoundException;

}
