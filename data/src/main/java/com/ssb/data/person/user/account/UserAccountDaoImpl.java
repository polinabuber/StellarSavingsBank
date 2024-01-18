package com.ssb.data.person.user.account;

import com.ssb.data.model.*;
import com.ssb.data.pojo.*;
import org.hibernate.*;
import org.hibernate.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
@Transactional
public class UserAccountDaoImpl implements UserAccountDao {
    private final SessionFactory sessionFactory;

    public UserAccountDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<UserAccountDto> getAllUserAccounts() {
        Session session = sessionFactory.getCurrentSession();
        List<UserAccount> userAccountList = session.createQuery("FROM UserAccount", UserAccount.class).getResultList();
        return convertToDtoList(userAccountList);
    }

    private ArrayList<UserAccountDto> convertToDtoList(List<UserAccount> userAccountList) {
        ArrayList<UserAccountDto> userAccountDtoList = new ArrayList<>();
        for (UserAccount userAccount : userAccountList) {
            userAccountDtoList.add(convertToDto(userAccount));
        }
        return userAccountDtoList;
    }

    public UserAccountDto convertToDto(UserAccount userAccount) {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setId(userAccount.getId());
        userAccountDto.setBalance(userAccount.getBalance());
        userAccountDto.setUserId(userAccount.getUser().getId());
        return userAccountDto;
    }

    @Override
    public UserAccountDto getUserAccountById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        UserAccount userAccount = session.get(UserAccount.class, id);
        return userAccount != null ? convertToDto(userAccount) : null;
    }

    @Override
    public int addUserAccount(UserAccountDto userAccountDto) {
        Session session = this.sessionFactory.getCurrentSession();
        UserAccount userAccount = convertToEntity(userAccountDto);
        return (int) session.save(userAccount);
    }

    private UserAccount convertToEntity(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userAccountDto.getId());
        userAccount.setBalance(userAccountDto.getBalance());
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.get(User.class, userAccountDto.getUserId());
        if (user == null) {
            throw new RuntimeException("User with id " + userAccountDto.getUserId() + " not found");
        }
        userAccount.setUser(user);
        return userAccount;
    }


    @Override
    public void updateUserAccount(UserAccountDto userAccountDto) {
        Session session = sessionFactory.getCurrentSession();
        UserAccount userAccount = session.get(UserAccount.class, userAccountDto.getId());
        if (userAccount != null) {
            userAccount.setBalance(userAccountDto.getBalance());
            session = this.sessionFactory.getCurrentSession();
            User user = session.get(User.class, userAccountDto.getUserId());
            if (user != null) {
                userAccount.setUser(user);
            }
            session.update(userAccount);
        }
    }

    @Override
    public boolean deleteUserAccount(int id) {
        Session session = sessionFactory.getCurrentSession();
        UserAccount userAccount = session.get(UserAccount.class, id);
        if (userAccount != null) {
            session.delete(userAccount);
            return true;
        } else {
            return false;
        }
    }
}


