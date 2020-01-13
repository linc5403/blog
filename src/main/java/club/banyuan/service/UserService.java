package club.banyuan.service;

import club.banyuan.bean.User;
import club.banyuan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findByName(String username) {
        User user = userDao.selectUserByName(username);
        return user;
    }

    public User register(User user) {
        return new User();
    }

    public User login(String s1, String s2) {
        User user = userDao.selectUserByName(s1);
        return (s2.equals(user.getPassword())) ? user : null;

    }

}
