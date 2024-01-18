package com.ssb.data.person.user;

import com.ssb.data.model.*;
import com.ssb.data.person.user.account.*;
import com.ssb.data.pojo.*;
import org.hibernate.*;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.sql.*;
import java.util.*;
import java.util.stream.*;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE phoneNumber = :phoneNumber", User.class);
        query.setParameter("phoneNumber", phoneNumber);
        return query.uniqueResult();
    }

    @Override
    public ArrayList<UserDto> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("FROM User", User.class).getResultList();
        return convertToDtoList(userList);
    }

    private ArrayList<UserDto> convertToDtoList(List<User> userList) {
        ArrayList<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(convertToDto(user));
        }
        return userDtoList;
    }

    private UserDto convertToDto(User user) {
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

    @Override
    public UserDto getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user != null ? convertToDto(user) : null;
    }

    @Override
    public int addUser(UserDto userDto) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = convertToEntity(userDto);
        return (int) session.save(user);
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        Session session = this.sessionFactory.getCurrentSession();
        Role role = session.get(Role.class, userDto.getRoleId());
        if (role != null) {
            user.setRole(role);
        }
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return user;
    }

    @Override
    public void updateUser(UserDto userDto) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userDto.getId());
        if (user != null) {
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
            session = this.sessionFactory.getCurrentSession();
            Role role = session.get(Role.class, userDto.getRoleId());
            if (role != null) {
                user.setRole(role);
            }
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            session.update(user);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserAccountDto> getUserAccounts(int userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        if (user != null) {
            UserAccountDaoImpl userAccountDao = new UserAccountDaoImpl(sessionFactory);
            return user.getUserAccounts().stream()
                    .map(userAccountDao::convertToDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}


