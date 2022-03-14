package com.library.auth.service.interfaces;

import com.library.auth.dto.AuthResponseDto;
import com.library.auth.dto.RegisterDto;
import com.library.auth.entity.User;

import java.util.List;

public interface IUserService {
    User save(RegisterDto user);

    List<User> findAll();

    User findOne(String username);

    AuthResponseDto login(String login, String password);
}
