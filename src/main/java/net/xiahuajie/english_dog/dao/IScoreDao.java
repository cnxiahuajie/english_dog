package net.xiahuajie.english_dog.dao;

import net.xiahuajie.english_dog.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 得分表操作接口
 *
 * @author xiahuajie
 */
@Repository("scoreDao")
public interface IScoreDao extends JpaRepository<Score, Integer> {

    Integer countAllByUserIdEqualsAndScoreIsAfter(Integer userId, Integer score);

    Integer countAllByUserIdEquals(Integer userId);

    Score findByUserId(Integer userId);

    Score findByBatchNumber(String batchNumber);

}
