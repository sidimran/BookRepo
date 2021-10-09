package com.zkteco.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.BookNotFoundException;

public interface BookRepository extends JpaRepository<Book, String> {

	public Book findByBookNameIgnoreCase(String bookName);

	@Query(value = "Select t from Book t where t.isbn like ?1 OR t.bookName like ?1 OR t.title like ?1 OR t.language like ?1 OR t.publisher like ?1 OR t.publisher_phone like ?1 OR t.publisher_address like ?1 OR t.price like ?1 OR t.authorId like ?1 OR t.authorName like ?1 OR t.author_emailId like ?1 OR t.volume like ?1")
	List<Book> getByName(String value) throws BookNotFoundException;

//	@Query(value = "Select t from Book t where t.isbn like ?1 OR t.bookName like ?1 OR t.title like ?1 OR t.language like ?1 OR t.publisher like ?1 OR t.publisher_phone like ?1 OR t.publisher_address like ?1 OR t.price like ?1 OR t.authorId like ?1 OR t.authorName like ?1 OR t.author_emailId like ?1 OR t.volume like ?1")
//	Page<Book> bookContaining(String search, org.springframework.data.domain.Pageable paging)
//			throws BookNotFoundException;

	@Query(value = "Select t from Book t where t.isbn like ?1 OR t.bookName like ?1 OR t.title like ?1 OR t.language like ?1 OR t.publisher like ?1 OR t.publisher_phone like ?1 OR t.publisher_address like ?1 OR t.price like ?1 OR t.authorId like ?1 OR t.authorName like ?1 OR t.author_emailId like ?1 OR t.volume like ?1")
	Page<Book> bookContaining(String search, Pageable paging) throws BookNotFoundException;

//	@Modifying
//	@Query(value = "delete t from Book t where t.book_id = ?1")
//	void delById(String bookId);

}
