package com.example.BookManagementSpringBoot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookManagementSpringBoot.dto.BookDto;
import com.example.BookManagementSpringBoot.model.Book;
import com.example.BookManagementSpringBoot.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController

@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<List<Book>> getAll(){
		return ResponseEntity.ok(service.getAllBooks());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<Book> getById(@PathVariable Long id){
		return ResponseEntity.ok(service.getBookById(id));
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Book> create(@Valid @RequestBody BookDto dto){
		var created=service.create(dto);
		return ResponseEntity
				.created(URI.create("/api/books/" + created.getId()))
				.body(created);
	}
	
	@PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody BookDto dto) {
		
        return ResponseEntity.ok(service.update(id, dto));
    }
	
	@DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
	public String delete(@PathVariable Long id) {
	    service.delete(id);
	    return "Record Deleted Successfully!";
	

	}
}
