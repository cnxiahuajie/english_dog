package net.xiahuajie.english_dog.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import net.xiahuajie.english_dog.dao.IUserDao;
import net.xiahuajie.english_dog.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("userService")
public class UserService {
	
	@Resource(name = "userDao")
	private IUserDao userDao;

	@Transactional(rollbackFor = Exception.class)
	public void doUpdateLoginTime(String username) {
		User user = userDao.findByUsername(username);
		user.setLastLoginTime(LocalDateTime.now());
	}

	@Transactional(rollbackFor = Exception.class)
	public void doModify(String username, User user) {
		User modifyUser = userDao.findByUsername(username);
		if (StringUtils.isNotBlank(user.getPhone())) {
			modifyUser.setPhone(user.getPhone());
		}
		if (StringUtils.isNotBlank(user.getEmail())) {
			modifyUser.setEmail(user.getEmail());
		}
		modifyUser.setLastModTime(LocalDateTime.now());
		userDao.save(modifyUser);
	}
	
	public User findUserByUsername(String username) {
		User user = userDao.findByUsername(username);
		user.setPassword(null);
		return user;
	}
	
	public User findUserByPhone(String phone) {
		User user = userDao.findByPhone(phone);
		user.setPassword(null);
		return user;
	}

}
