package com.zkteco.book.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "book_id")
	private String bookId;

	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "title")
	private String title;

	@Column(name = "language")
	private String language;

	@Column(name = "Publisher")
	private String publisher;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "published_date")
	private Date publishedDate = new Date();

	@Column(name = "Phone")
	private Long publisher_phone;

	@Column(name = "Address")
	private String publisher_address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Update_Date")
	private Date pub_updated_date = new Date();

	@Column(name = "Price")
	private int price;

	@Column(name = "Volume")
	private int volume;

	@Column(name = "Author_ID")
	private Long authorId;

	@Column(name = "Author_Name")
	private String authorName;

	@Column(name = "Author_Email")
	private String author_emailId;

}
