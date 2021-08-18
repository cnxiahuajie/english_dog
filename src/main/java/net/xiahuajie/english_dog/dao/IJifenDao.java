package net.xiahuajie.english_dog.dao;

import net.xiahuajie.english_dog.entity.Jifen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 积分表操作接口
 *
 * @author xiahuajie
 */
@Repository("jifenDao")
public interface IJifenDao extends JpaRepository<Jifen, Integer> {

    Jifen findByUserId(Integer userId);

}
