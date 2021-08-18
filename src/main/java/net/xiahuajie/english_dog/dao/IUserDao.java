package net.xiahuajie.english_dog.dao;

import net.xiahuajie.english_dog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户数据库操作接口
 *
 * @author xiahuajie
 */
@Repository("userDao")
public interface IUserDao extends JpaRepository<User, Integer> {

    /**
     * 按用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 按手机号查找用户
     *
     * @param phone 手机号
     * @return 用户信息
     */
    User findByPhone(String phone);

}
