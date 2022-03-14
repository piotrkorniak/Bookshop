package com.library.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Role getMainRole() {
        if (isAdmin()) {
            return roles.stream().filter(x -> x.getName() == "ADMIN")
                    .findFirst().get();
        } else {
            return roles.stream().filter(x -> x.getName() == "USER")
                    .findFirst().get();
        }
    }

    private Boolean isAdmin() {
        return roles.stream().anyMatch(o -> o.getName() == "ADMIN");
    }
}