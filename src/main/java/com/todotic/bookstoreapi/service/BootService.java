package com.todotic.bookstoreapi.service;

import com.todotic.bookstoreapi.exeption.ResourceNotFounExeption;
import com.todotic.bookstoreapi.model.entity.Book;
import com.todotic.bookstoreapi.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BootService {

    private BookRepository bookRepository;

    public List<Book> findLast6Books(){
        return bookRepository.findTop6ByOrderByCreatedAtDesc();
    }

    public Book findBySlug(String slug){
       return  bookRepository.findBySlug(slug)
                .orElseThrow(ResourceNotFounExeption::new);
    }

    public Page<Book> paginate(Pageable pageable){
        return bookRepository.findAll(pageable);

    }
}
