package com.library.auth.dao;

import com.library.auth.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleDao extends CrudRepository<Role, UUID> {
    Role findRoleByName(String name);
}