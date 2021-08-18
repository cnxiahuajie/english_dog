package net.xiahuajie.english_dog.service;

import net.xiahuajie.english_dog.dao.IScoreDao;
import net.xiahuajie.english_dog.entity.Score;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScoreService {

    @Resource(name = "scoreDao")
    private IScoreDao scoreDao;

    public Score findByBatchNumber(String batchNumber) {
        return scoreDao.findByBatchNumber(batchNumber);
    }

}
