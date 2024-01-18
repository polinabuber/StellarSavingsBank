package com.ssb.service;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;

import java.util.*;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(int id);
    int addUser(UserDto userDto);
    void updateUser(UserDto userDto);
    boolean deleteUser(int id);
    User getUserByPhoneNumber(String phoneNumber);
    List<UserAccountDto> getUserAccounts(int userId);
}
