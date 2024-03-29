package net.xiahuajie.english_dog.dao;

import net.xiahuajie.english_dog.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试结果表操作接口
 *
 * @author xiahuajie
 */
@Repository("testResultDao")
public interface ITestResultDao extends JpaRepository<TestResult, Integer> {

    /**
     * 按批次号查询一批测试结果
     *
     * @param batchNumber 批次号
     * @return 测试结果
     */
    List<TestResult> findByBatchNumberAndResultEquals(String batchNumber, String result);

}
