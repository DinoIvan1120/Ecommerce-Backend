package com.todotic.bookstoreapi.repository;

import com.todotic.bookstoreapi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    boolean existsBySlug(String slug);

    boolean existsBySlugAndIdNot(String slug, Integer id);

    Optional<Book> findBySlug(String slug);

    List<Book> findTop6ByOrderByCreatedAtDesc();

  // List<Book> findLast6ByOrderByCreatedAt();

}
