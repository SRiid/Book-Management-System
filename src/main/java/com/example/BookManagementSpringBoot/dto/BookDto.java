package com.example.BookManagementSpringBoot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookDto {

	private Long id;
	  
   
	@NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Year is required")
    private Integer yearPublished;

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

	public Integer getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}

	public BookDto(Long id, @NotBlank(message = "Title is required") String title,
			@NotBlank(message = "Author is required") String author,
			@NotNull(message = "Year is required") Integer yearPublished) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.yearPublished = yearPublished;
	}
	

}

