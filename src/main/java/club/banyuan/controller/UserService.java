package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    User getUserByName(String username) {
        return userDao.selectUserByName(username);
    }
}
