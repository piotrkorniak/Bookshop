package com.library.book.dao;

import com.library.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookDao extends JpaRepository<Book, UUID> {
}


