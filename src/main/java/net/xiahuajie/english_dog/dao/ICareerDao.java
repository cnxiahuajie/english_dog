package net.xiahuajie.english_dog.dao;

import net.xiahuajie.english_dog.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 生涯表操作接口
 *
 * @author xiahuajie
 */
@Repository("careerDao")
public interface ICareerDao extends JpaRepository<Career, Integer> {

    Career findByUserId(Integer userId);

}
