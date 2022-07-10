package com.library.book.dao;

import com.library.book.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentalDao extends JpaRepository<Rental, UUID> {
}
