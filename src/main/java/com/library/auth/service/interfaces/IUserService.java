package com.library.auth.service.interfaces;

import com.library.auth.dto.UserDto;
import com.library.auth.entity.User;

import java.util.List;

public interface IUserService {
    User save(UserDto user);

    List<User> findAll();

    User findOne(String username);
}
