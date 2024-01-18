package com.ssb.service;

import com.ssb.data.model.*;
import org.springframework.stereotype.*;

import java.util.*;
public interface RoleService {
    List<RoleDto> getAllRoles();
    RoleDto getRoleById(int id);
    int addRole(RoleDto roleDto);
    boolean deleteRole(int id);
}
