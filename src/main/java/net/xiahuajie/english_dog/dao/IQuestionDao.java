package net.xiahuajie.english_dog.dao;

import net.xiahuajie.english_dog.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 题库表操作接口
 *
 * @author xiahuajie
 */
@Repository("questionDao")
public interface IQuestionDao extends JpaRepository<Question, Integer> {

    /**
     * 按题干查询题目
     *
     * @param title 题干
     * @return 题目
     */
    Question findByTitle(String title);

    @Query(value = "SELECT id FROM question WHERE type = :type")
    List<Integer> findIds(String type);

    /**
     * 按题干和答案查询题目
     *
     * @param id     题目ID
     * @param answer 答案
     * @return 题目
     */
    Question findByIdAndAnswer(Integer id, String answer);

}
