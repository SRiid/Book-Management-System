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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false)
	private String author;
	private int yearPublished;
}
