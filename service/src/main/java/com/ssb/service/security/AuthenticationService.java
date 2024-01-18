package com.ssb.service.security;

import com.ssb.data.model.*;
import com.ssb.data.person.user.*;
import com.ssb.data.pojo.User;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;


import java.util.*;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        try {
            User user = userDao.getUserByPhoneNumber(phoneNumber);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getPhoneNumber(),
                    user.getPassword(),
                    true, true, true, true,
                    List.of(new SimpleGrantedAuthority(user.getRole().getRole())));

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found", e);
        }
    }
    public UserDto register(UserDto userDto, String phoneNumber) {
        userDto.setPhoneNumber(phoneNumber);
        userDto.setRoleId(2);

        int userId = userDao.addUser(userDto);
        return userDao.getUserById(userId);
    }

    public UserDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String phoneNumber = authentication.getName();
            User user = userDao.getUserByPhoneNumber(phoneNumber);
            if (user != null) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setFirstname(user.getFirstname());
                userDto.setLastname(user.getLastname());
                userDto.setPhoneNumber(user.getPhoneNumber());
                userDto.setPassword(user.getPassword());
                userDto.setRoleId(user.getRole().getId());
                userDto.setCreatedAt(user.getCreatedAt());
                return userDto;
            }
        }
        return null;
    }
}

