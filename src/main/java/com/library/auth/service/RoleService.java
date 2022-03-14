package com.library.auth.service;

import com.library.auth.entity.Role;

public interface RoleService {
    Role findByName(String name);
}