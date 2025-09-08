package com.example.BookManagementSpringBoot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Books")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String author;
	private int yearPublished;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYearPublished() {
		return yearPublished;
	}
	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	public Book(Long id, String title, String author, int yearPublished) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
	}
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return "Book {id=" + id + ", title=" + title + ", author=" + author + ", yearPublished=" + yearPublished + 
				"}";
	}
	
}
