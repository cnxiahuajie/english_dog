package net.xiahuajie.english_dog.service;

import net.xiahuajie.english_dog.dao.IQuestionDao;
import net.xiahuajie.english_dog.dao.ITestResultDao;
import net.xiahuajie.english_dog.entity.Question;
import net.xiahuajie.english_dog.entity.TestResult;
import net.xiahuajie.english_dog.service.vo.TestResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource(name = "questionDao")
    private IQuestionDao questionDao;

    /**
     * 按批次号查询测试结果
     *
     * @param batchNumber 批次号
     * @param result      正确或错误的测试结果（S表示正确/F表示错误）
     * @return 测试结果
     */
    public List<TestResultVO> findByBatchNumber(String batchNumber, String result) {
        List<TestResult> testResults = testResultDao.findByBatchNumberAndResultEquals(batchNumber, result);
        List<TestResultVO> testResultVOS = new ArrayList<>();
        testResults.forEach(item -> {
            Question question = questionDao.getById(item.getQuestionId());
            TestResultVO testResultVO = new TestResultVO();
            BeanUtils.copyProperties(item, testResultVO);
            testResultVO.setQuestion(question);
            testResultVOS.add(testResultVO);
        });
        return testResultVOS;
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
