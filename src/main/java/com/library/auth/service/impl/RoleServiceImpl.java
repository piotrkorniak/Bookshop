package com.library.auth.service.impl;

import com.library.auth.dao.RoleDao;
import com.library.auth.entity.Role;
import com.library.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        return roleDao.findRoleByName(name);
    }
}
