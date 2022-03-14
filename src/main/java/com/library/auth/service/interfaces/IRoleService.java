package com.library.auth.service.interfaces;

import com.library.auth.entity.Role;

public interface IRoleService {
    Role findByName(String name);
}