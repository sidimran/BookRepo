package com.zkteco.book.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.BookNotFoundException;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.BookService;

@Component
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookService bookService;

	@Override
	public ResultDTO entityToDto(Book book) {

		ResultDTO dto = new ResultDTO();
		dto.setBookId(book.getBookId());
		dto.setIsbn(book.getIsbn());
		dto.setBookName(book.getBookName());
		dto.setTitle(book.getTitle());
		dto.setLanguage(book.getLanguage());
		dto.setPublisher(book.getPublisher());
		dto.setPublishedDate(book.getPublishedDate());
		dto.setPublisher_phone(book.getPublisher_phone());
		dto.setPublisher_address(book.getPublisher_address());
		dto.setPub_updated_date(book.getPub_updated_date());
		dto.setPrice(book.getPrice());
		dto.setVolume(book.getVolume());
		dto.setAuthorId(book.getAuthorId());
		dto.setAuthorName(book.getAuthorName());
		dto.setAuthor_emailId(book.getAuthor_emailId());
		return dto;

	}

	@Override
	public List<ResultDTO> entityToDto(List<Book> book) {

		return book.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

	}

	@Override
	public Book dtoToEntity(ResultDTO resultdto) {
		Book book = new Book();
		book.setBookId(resultdto.getBookId());
		book.setIsbn(resultdto.getIsbn());
		book.setBookName(resultdto.getBookName());
		book.setTitle(resultdto.getTitle());
		book.setLanguage(resultdto.getLanguage());
		book.setPublisher(resultdto.getPublisher());
		book.setPublishedDate(resultdto.getPublishedDate());
		book.setPublisher_phone(resultdto.getPublisher_phone());
		book.setPublisher_address(resultdto.getPublisher_address());
		book.setPub_updated_date(resultdto.getPub_updated_date());
		book.setPrice(resultdto.getPrice());
		book.setVolume(resultdto.getVolume());
		book.setAuthorId(resultdto.getAuthorId());
		book.setAuthorName(resultdto.getAuthorName());
		book.setAuthor_emailId(resultdto.getAuthor_emailId());

		return book;

	}

	@Override
	public List<Book> dtoToEntity(List<ResultDTO> dto) {

		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());

	}

	@Override
	public BookDTO updateBookById(String id, Book bk) {

		Book book = bookRepository.findById(id).get();

		if (Objects.nonNull(bk.getIsbn()) && !"".equalsIgnoreCase(bk.getIsbn())) {
			book.setIsbn(bk.getIsbn());
		}
		if (Objects.nonNull(bk.getBookName()) && !"".equalsIgnoreCase(bk.getBookName())) {
			book.setBookName(bk.getBookName());
		}
		if (Objects.nonNull(bk.getTitle()) && !"".equalsIgnoreCase(bk.getTitle())) {
			book.setTitle(bk.getTitle());
		}
		if (Objects.nonNull(bk.getLanguage()) && !"".equalsIgnoreCase(bk.getLanguage())) {
			book.setLanguage(bk.getLanguage());
		}
		if (Objects.nonNull(bk.getPublisher()) && !"".equalsIgnoreCase(bk.getPublisher())) {
			book.setPublisher(bk.getPublisher());
		}
		if (Objects.nonNull(bk.getPublishedDate())) {
			book.setPublishedDate(bk.getPublishedDate());
		}
		if (Objects.nonNull(bk.getPublisher_phone())) {
			book.setPublisher_phone(bk.getPublisher_phone());
		}

		if (Objects.nonNull(bk.getPublisher_address()) && !"".equalsIgnoreCase(bk.getPublisher_address())) {
			book.setPublisher_address(bk.getPublisher_address());
		}
		if (Objects.nonNull(bk.getPub_updated_date())) {
			book.setPub_updated_date(bk.getPub_updated_date());
		}
		if (Objects.nonNull(bk.getPrice())) {
			book.setPrice(bk.getPrice());
		}
		if (Objects.nonNull(bk.getVolume())) {
			book.setVolume(bk.getVolume());
		}
		if (Objects.nonNull(bk.getAuthorId())) {
			book.setAuthorId(bk.getAuthorId());
		}
		if (Objects.nonNull(bk.getAuthorName()) && !"".equalsIgnoreCase(bk.getAuthorName())) {
			book.setPublisher_address(bk.getPublisher_address());
		}
		if (Objects.nonNull(bk.getAuthor_emailId()) && !"".equalsIgnoreCase(bk.getAuthor_emailId())) {
			book.setAuthor_emailId(bk.getAuthor_emailId());
		}

		Book book1 = bookRepository.save(book);
		ResultDTO res = bookService.entityToDto(book1);
		BookDTO dto = new BookDTO();
		dto.setCode("1011");
		dto.setMessage("Updated Successfully");
		dto.setData(res);
		return dto;
	}

	@Override
	public BookDTO deleteBookById(String id) throws BookNotFoundException {

		List<Book> dto = bookRepository.findAll();
		for (Book b : dto) {
			if (!id.equals(b.getBookId())) {

			} else {

				bookRepository.deleteById(id);
			}
		}

		BookDTO bookDTO = new BookDTO();
		bookDTO.setCode("1011");
		bookDTO.setMessage("Deleted Successfully");
		return bookDTO;
	}

	@Override
	public Page<Book> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.bookRepository.findAll(pageable);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getAllBooks(String search, int page, int size, String[] sort) {
		try {
			List<Order> orders = new ArrayList<Order>();
			if (sort[0].contains(",")) {
				for (String sortOrder : sort) {
					String[] sortA = sortOrder.split(",");
					orders.add(new Order(getSortDirection(sortA[1]), sortA[0]));
				}
			} else {
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}
			Pageable paging = PageRequest.of(page, size, Sort.by(orders));

			Page<Book> pageTuts;
			if (search == null) {
				pageTuts = bookRepository.findAll(paging);
			} else {
				pageTuts = bookRepository.bookContaining(search, paging);
			}
			List<Book> book = pageTuts.getContent();

			Map<String, Object> res = new HashMap<>();
			res.put("book", book);
			res.put("currentPage", pageTuts.getNumber());
			res.put("totalItems", pageTuts.getTotalElements());
			res.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

	@Override
	public BookDTO saveBook(ResultDTO dto) {
		Book book = bookService.dtoToEntity(dto);
		book = bookRepository.save(book);
		ResultDTO res = bookService.entityToDto(book);
		BookDTO bookDTO = new BookDTO();
		bookDTO.setCode("book001");
		bookDTO.setMessage("Books Successfully Created");
		bookDTO.setData(res);
		return bookDTO;
	}

	@Override
	public ResultDTO fetchById(String id) throws BookNotFoundException {

		ResultDTO dto = new ResultDTO();

		if (id != dto.getBookId()) {
			throw new BookNotFoundException("Id does not exist!..");
		} else {
			Book orElse = bookRepository.findById(id).orElse(null);

			return bookService.entityToDto(orElse);
		}
	}

}