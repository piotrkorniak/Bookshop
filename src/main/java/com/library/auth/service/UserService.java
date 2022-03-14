package com.library.auth.service;

import com.library.auth.dto.UserDto;
import com.library.auth.entity.User;

import java.util.List;

public interface UserService {
    User save(UserDto user);

    List<User> findAll();

    User findOne(String username);
}
