package net.xiahuajie.english_dog.service;

import net.xiahuajie.english_dog.dao.ICareerDao;
import net.xiahuajie.english_dog.dao.IUserDao;
import net.xiahuajie.english_dog.entity.Career;
import net.xiahuajie.english_dog.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 生涯业务接口
 *
 * @author xiahuajie
 */
@Service("careerService")
public class CareerService {

    @Resource(name = "careerDao")
    private ICareerDao careerDao;

    @Resource(name = "userDao")
    private IUserDao userDao;

    public Career findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return careerDao.findByUserId(user.getId());
    }

}
