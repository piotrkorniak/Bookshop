package com.library.auth.dao;

import com.library.auth.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends CrudRepository<User, UUID> {
    User findByEmail(String email);
}
