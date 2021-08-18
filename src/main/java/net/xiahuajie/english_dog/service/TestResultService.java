package net.xiahuajie.english_dog.service;

import net.xiahuajie.english_dog.dao.ITestResultDao;
import net.xiahuajie.english_dog.entity.TestResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试结果业务类
 *
 * @author xiahuajie
 */
@Service("testResultService")
public class TestResultService {

    @Resource(name = "testResultDao")
    private ITestResultDao testResultDao;

    /**
     * 按批次号查询测试结果
     *
     * @param batchNumber 批次号
     * @return 测试结果
     */
    public List<TestResult> findByBatchNumber(String batchNumber) {
        return testResultDao.findByBatchNumber(batchNumber);
    }

    /**
     * 保存一批测试结果
     *
     * @param testResults 测试结果集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveTestResults(List<TestResult> testResults) {
        testResultDao.saveAll(testResults);
    }

}
