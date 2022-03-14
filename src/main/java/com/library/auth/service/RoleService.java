package com.library.auth.service;

import com.library.auth.dao.RoleDao;
import com.library.auth.entity.Role;
import com.library.auth.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleService implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        return roleDao.findRoleByName(name);
    }
}
