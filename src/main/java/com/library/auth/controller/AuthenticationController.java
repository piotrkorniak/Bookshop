package com.library.auth.controller;

import com.library.auth.dto.AuthResponseDto;
import com.library.auth.dto.LoginDto;
import com.library.auth.dto.RegisterDto;
import com.library.auth.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) throws AuthenticationException {
        var authDto = userService.login(loginDto.getEmail(), loginDto.getPassword());
        return ResponseEntity.ok(authDto);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDto> saveUser(@Valid @RequestBody RegisterDto user) throws Exception {
        userService.save(user);
        var authDto = userService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(authDto);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/adminping", method = RequestMethod.GET)
    public ResponseEntity<String> adminPing() {
        return ResponseEntity.ok("Only Admins Can Read This");
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/userping", method = RequestMethod.GET)
    public ResponseEntity<String> userPing() {
        return ResponseEntity.ok("Any User Can Read This");
    }

    @RequestMapping(value = "/noAuthPing", method = RequestMethod.GET)
    public ResponseEntity<String> noAuthPing() {
        return ResponseEntity.ok("no Auth User Can Read This");
    }
}