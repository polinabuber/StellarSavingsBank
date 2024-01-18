package com.ssb.rest;

import com.ssb.data.model.*;
import com.ssb.data.person.user.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class UserRestController {

    @Autowired
    UserDao userDao;

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id) {
        UserDto user = userDao.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/users/{id}")
    public UserDto createUser(
            @PathVariable("id") int id,
            @RequestBody UserDto userDto) {
        userDto.setId(id);
        int userId = userDao.addUser(userDto);
        return userDao.getUserById(userId);
    }

    @PutMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public UserDto updateUser(
            @PathVariable("id") int id,
            @RequestBody UserDto userDto) {
        userDao.updateUser(userDto);
        return userDao.getUserById(id);
    }

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") int id) {
        userDao.deleteUser(id);
    }

    @GetMapping("/api/users/{userId}/accounts")
    public List<UserAccountDto> getUserAccounts(@PathVariable int userId) {
        return userDao.getUserAccounts(userId);
    }
}

