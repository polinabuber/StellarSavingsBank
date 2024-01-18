package com.ssb.data.person.role;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
import org.hibernate.*;
import org.hibernate.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<RoleDto> getAllRoles() {
        List<Role> roleList = getRoleList();
        return convertToDtoList(roleList);
    }

    @Override
    public RoleDto getRoleById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = session.get(Role.class, id);
        if (role != null) {
            return convertToDto(role);
        }
        return null;
    }

    @Override
    public int addRole(RoleDto roleDto) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = convertToEntity(roleDto);
        return (int) session.save(role);
    }

    @Override
    public boolean deleteRole(int id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = session.get(Role.class, id);
        if (role != null) {
            session.delete(role);
            return true;
        } else {
            throw new IllegalArgumentException("Role with id " + id + " not found");
        }
    }

    private List<Role> getRoleList() {
        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery("FROM Role", Role.class);
        return query.list();
    }

    private RoleDto convertToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());
        roleDto.setUserId(role.getUser().getId());
        return roleDto;
    }

    private ArrayList<RoleDto> convertToDtoList(List<Role> roleList) {
        ArrayList<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : roleList) {
            roleDtoList.add(convertToDto(role));
        }
        return roleDtoList;
    }

    private Role convertToEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setRole(roleDto.getRole());
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.get(User.class, roleDto.getUserId());
        if (user != null) {
            role.setUser(user);
        }
        return role;
    }
}


