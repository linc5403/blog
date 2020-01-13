package club.banyuan.dao;

import club.banyuan.bean.User;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User selectUserByName(@Param("name") String name);
}
