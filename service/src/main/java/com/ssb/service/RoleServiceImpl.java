package com.ssb.service;

import com.ssb.data.model.*;
import com.ssb.data.person.role.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public RoleDto getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public int addRole(RoleDto roleDto) {
        return roleDao.addRole(roleDto);
    }

    @Override
    public boolean deleteRole(int id) {
        return roleDao.deleteRole(id);
    }
}

