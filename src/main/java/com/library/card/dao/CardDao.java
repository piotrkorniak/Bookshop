package com.library.card.dao;

import com.library.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardDao extends JpaRepository<Card, UUID> {
}
