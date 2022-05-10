package com.library.auth.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
