package com.ssb.service;

import com.ssb.data.model.*;
import com.ssb.data.person.user.*;
import com.ssb.data.pojo.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserDto getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public int addUser(UserDto userDto) {
        return userDao.addUser(userDto);
    }

    @Override
    public void updateUser(UserDto userDto) {
        userDao.updateUser(userDto);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }
    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userDao.getUserByPhoneNumber(phoneNumber);
    }
    @Override
    public List<UserAccountDto> getUserAccounts(int userId) {
        return userDao.getUserAccounts(userId);
    }
}

