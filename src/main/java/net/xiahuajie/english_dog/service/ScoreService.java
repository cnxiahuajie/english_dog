package net.xiahuajie.english_dog.service;

import net.xiahuajie.english_dog.dao.IScoreDao;
import net.xiahuajie.english_dog.dao.IUserDao;
import net.xiahuajie.english_dog.entity.Score;
import net.xiahuajie.english_dog.entity.User;
import net.xiahuajie.english_dog.service.vo.ScoreVo;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    @Resource(name = "scoreDao")
    private IScoreDao scoreDao;

    @Resource(name = "userDao")
    private IUserDao userDao;

    public List<ScoreVo> findAllBySort(String by) {
        List<ScoreVo> scoreVos = new ArrayList<>();
        List<Score> scores = null;
        if ("asc".equals(by)) {
            scores = scoreDao.findAll(Sort.by(Sort.Order.asc("score")));
        } else if ("desc".equals(by)) {
            scores = scoreDao.findAll(Sort.by(Sort.Order.desc("score")));
        }
        if (null != scores && ArrayUtils.isNotEmpty(scores.toArray())) {
            scores.forEach(item -> {
                User user = userDao.getById(item.getUserId());
                ScoreVo scoreVo = new ScoreVo();
                BeanUtils.copyProperties(item, scoreVo);
                scoreVo.setUserName(user.getUsername());
                scoreVos.add(scoreVo);
            });
        }
        return scoreVos;
    }

    public Score findByUserId(Integer userId) {
        return scoreDao.findByUserId(userId);
    }

}
