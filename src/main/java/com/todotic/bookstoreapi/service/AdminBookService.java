package com.todotic.bookstoreapi.service;

import com.todotic.bookstoreapi.exeption.BackRequestException;
import com.todotic.bookstoreapi.exeption.ResourceNotFounExeption;
import com.todotic.bookstoreapi.model.entity.Book;
import com.todotic.bookstoreapi.model.dto.BookFromDTO;
import com.todotic.bookstoreapi.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class AdminBookService {

    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Page<Book> paginate(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Book create(@RequestBody BookFromDTO bookFromDTO){

        String slug = bookFromDTO.getSlug();

        boolean slugAlreadyExists = bookRepository.existsBySlug(slug);

        if(slugAlreadyExists){
            throw new BackRequestException("El slug ya esta siendo usado por otro libro");
        }

        Book book = new Book();
        book.setTitle(bookFromDTO.getTitle());
        book.setDescription(bookFromDTO.getDescription());
        book.setPrice(bookFromDTO.getPrice());
        book.setSlug(bookFromDTO.getSlug());
        book.setCoverPath(bookFromDTO.getCoverPath());
        book.setFilePath(bookFromDTO.getFilePath());
        book.setCreatedAt(LocalDateTime.now());
        return bookRepository.save(book);
    }

    public Book get(@PathVariable Integer id){
        return bookRepository.findById(id).
                orElseThrow(ResourceNotFounExeption::new);
    }

    public Book update(@PathVariable Integer id, @RequestBody BookFromDTO bookFromDTO){
        Book bookFrom = bookRepository.findById(id)
                .orElseThrow(ResourceNotFounExeption::new);

        String slug = bookFromDTO.getSlug();
        boolean slugAlreadyExists = bookRepository.existsBySlugAndIdNot(slug, id);

        if(slugAlreadyExists){
            throw new BackRequestException("El slug ya esta siendo usado por otro libro");
        }
        bookFrom.setTitle(bookFromDTO.getTitle());
        bookFrom.setDescription(bookFromDTO.getDescription());
        bookFrom.setPrice(bookFromDTO.getPrice());
        bookFrom.setSlug(bookFromDTO.getSlug());
        bookFrom.setFilePath(bookFromDTO.getFilePath());
        return bookRepository.save(bookFrom);

    }

    public void delete(@PathVariable Integer id){
        Book book = bookRepository.findById(id)
                .orElseThrow(ResourceNotFounExeption::new);
        bookRepository.delete(book);
    }
}
