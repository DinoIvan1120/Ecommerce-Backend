package com.todotic.bookstoreapi.controller;

import com.todotic.bookstoreapi.service.AdminBookService;
import com.todotic.bookstoreapi.model.entity.Book;
import com.todotic.bookstoreapi.model.dto.BookFromDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin/books")
@RestController
public class AdminBookController {

    private final AdminBookService adminBookService;

    public AdminBookController(AdminBookService adminBookService) {
        this.adminBookService = adminBookService;
    }

    @GetMapping("/list")
    public List<Book> list(){
        return adminBookService.findAll();
    }

    @GetMapping
    public Page<Book> paginate(@PageableDefault (size = 5, sort = "title") Pageable pageable){
        return adminBookService.paginate(pageable)                                                                                                                                                      ;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book create(@RequestBody @Validated BookFromDTO bookFromDTO){
        return adminBookService.create(bookFromDTO);
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Integer id){
      return adminBookService.get(id);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Integer id, @RequestBody @Validated BookFromDTO bookFromDTO){
        return adminBookService.update(id, bookFromDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        adminBookService.delete(id);
    }
}
