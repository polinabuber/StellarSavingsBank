package com.ssb.data.person.role;

import com.ssb.data.model.*;

import java.util.*;

public interface RoleDao {
    List<RoleDto> getAllRoles();
    RoleDto getRoleById(int id);
    int addRole(RoleDto roleDto);
    boolean deleteRole(int id);
}
