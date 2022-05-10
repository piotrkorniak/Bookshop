package com.library.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.card.entity.Card;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Card getCard() {
        return card;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getMainRole() {
        if (isAdmin()) {
            return "ADMIN";
        } else {
            return "USER";
        }
    }

    private Boolean isAdmin() {
        return roles.stream().anyMatch(o -> o.getName().equals("ADMIN"));
    }
}