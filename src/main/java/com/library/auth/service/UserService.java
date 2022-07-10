package com.library.auth.service;

import com.library.auth.config.TokenProvider;
import com.library.auth.dao.UserDao;
import com.library.auth.dto.AuthResponseDto;
import com.library.auth.dto.RegisterDto;
import com.library.auth.entity.Role;
import com.library.auth.entity.User;
import com.library.auth.service.interfaces.IRoleService;
import com.library.auth.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    public User loadUserByUsername(String username) {
        User user = userDao
                .findByEmail(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userNotFound"));

        return user;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String email) {
        return findByEmail(email);
    }

    @Override
    public User save(RegisterDto user) {
        if (checkIfUserExist(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "ApiError.Register.EmailInUse");
        }

        User newUser = user.getUserFromDto();
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Set<Role> roleSet = new HashSet<>();


        Role role;
        if (newUser.getEmail().split("@")[1].equals("admin.admin")) {
            role = roleService.findByName("ROLE_ADMIN");
        } else {
            role = roleService.findByName("ROLE_USER");
        }
        roleSet.add(role);

        newUser.setRoles(roleSet);
        return userDao.save(newUser);
    }

    @Override
    public AuthResponseDto login(String username, String password) {
        final String token = getJwt(username, password);

        var user = findByEmail(username);

        return new AuthResponseDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getMainRole(), token);
    }

    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ApiError.Login.InvalidCredentials");
        }
    }

    private String getJwt(String userName, String password) {
        var authenticate = authenticate(userName, password);
        return jwtTokenUtil.generateToken(authenticate);
    }

    @Override
    public boolean checkIfUserExist(String email) {
        var user = userDao.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userDao.findByEmail(email);
        return user.orElse(null);
    }
}