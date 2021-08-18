package net.xiahuajie.english_dog.service;

import net.xiahuajie.english_dog.dao.IUserDao;
import net.xiahuajie.english_dog.entity.User;
import net.xiahuajie.english_dog.security.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerDetailService implements UserDetailsService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (null == user) {
            user = userDao.findByPhone(username);
        }

        if (null == user) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser.setPassword(user.getPassword());

        // TODO 权限待定

        return sysUser;
    }

}
