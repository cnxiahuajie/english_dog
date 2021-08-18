package net.xiahuajie.english_dog.service;

import net.xiahuajie.english_dog.dao.IJifenDao;
import net.xiahuajie.english_dog.dao.IUserDao;
import net.xiahuajie.english_dog.entity.Jifen;
import net.xiahuajie.english_dog.entity.User;
import net.xiahuajie.english_dog.service.vo.JifenVO;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 积分业务接口
 *
 * @author xiahuajie
 */
@Service("jifenService")
public class JifenService {

    @Resource(name = "jifenDao")
    private IJifenDao jifenDao;

    @Resource(name = "userDao")
    private IUserDao userDao;

    public List<JifenVO> findAllBySort(String by) {
        List<JifenVO> jifenVOS = new ArrayList<>();
        List<Jifen> jifens = null;
        if ("asc".equals(by)) {
            jifens = jifenDao.findAll(Sort.by(Sort.Order.asc("score")));
        } else if ("desc".equals(by)) {
            jifens = jifenDao.findAll(Sort.by(Sort.Order.desc("score")));
        }
        if (null != jifens && ArrayUtils.isNotEmpty(jifens.toArray())) {
            jifens.forEach(item -> {
                User user = userDao.getById(item.getUserId());
                JifenVO jifenVO = new JifenVO();
                BeanUtils.copyProperties(item, jifenVO);
                jifenVO.setUserName(user.getUsername());
                jifenVOS.add(jifenVO);
            });
        }
        return jifenVOS;
    }

}
