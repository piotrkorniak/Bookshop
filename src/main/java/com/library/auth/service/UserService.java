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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findOne(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User save(RegisterDto user) throws Exception {
        if(userDao.findByEmail(user.getEmail()) != null)
            throw new Exception("Email jest w użyciu");
        User newUser = user.getUserFromDto();
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if (newUser.getEmail().split("@")[1].equals("admin.admin")) {
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }

        newUser.setRoles(roleSet);
        return userDao.save(newUser);
    }

    @Override
    public AuthResponseDto login(String username, String password) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        var user = userDao.findByEmail(username);

        return new AuthResponseDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getMainRole(), token);
    }
}