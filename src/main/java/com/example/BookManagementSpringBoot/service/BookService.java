package com.example.BookManagementSpringBoot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.BookManagementSpringBoot.dto.BookDto;
import com.example.BookManagementSpringBoot.model.Book;
import com.example.BookManagementSpringBoot.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepo;
	
	public List<Book> getAllBooks(){
		return bookRepo.findAll();
	}
	
	public Book getBookById(Long id){
		
		return bookRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Book not found"));
	}
	
	
	public Book create(BookDto dto) {
		Book book=fromDto(dto);
		return bookRepo.save(book);
	} 
	
	
	public Book update(Long id, BookDto dto) {
        var existing = bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));

        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setYearPublished(dto.getYearPublished());

        return bookRepo.save(existing);
		
	}
	
	public void delete(Long id) {
		if(!bookRepo.existsById(id)) {
			throw new EntityNotFoundException("Book Not found: "+id);
		}
		bookRepo.deleteById(id);
	}

	private Book fromDto(BookDto d) {
        return Book.builder()
                .id(d.getId())
                .title(d.getTitle())
                .author(d.getAuthor())
                .yearPublished(d.getYearPublished())
                .build();
    }


}
