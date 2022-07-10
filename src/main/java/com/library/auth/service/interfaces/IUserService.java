package com.library.auth.service.interfaces;

import com.library.auth.dto.AuthResponseDto;
import com.library.auth.dto.RegisterDto;
import com.library.auth.entity.User;

import java.util.List;

public interface IUserService {
    User save(RegisterDto user) throws Exception;

    List<User> findAll();

    User findOne(String email);

    AuthResponseDto login(String login, String password);

    boolean checkIfUserExist(String email);

    User findByEmail(String email);
}
