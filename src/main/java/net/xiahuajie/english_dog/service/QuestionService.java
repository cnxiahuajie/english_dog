package net.xiahuajie.english_dog.service;

import net.xiahuajie.english_dog.dao.IJifenDao;
import net.xiahuajie.english_dog.dao.IQuestionDao;
import net.xiahuajie.english_dog.dao.IScoreDao;
import net.xiahuajie.english_dog.dao.ITestResultDao;
import net.xiahuajie.english_dog.entity.Jifen;
import net.xiahuajie.english_dog.entity.Question;
import net.xiahuajie.english_dog.entity.Score;
import net.xiahuajie.english_dog.entity.TestResult;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service("questionService")
public class QuestionService {

    @Resource(name = "questionDao")
    private IQuestionDao questionDao;

    @Resource(name = "testResultDao")
    private ITestResultDao testResultDao;

    @Resource(name = "scoreDao")
    private IScoreDao scoreDao;

    @Resource(name = "jifenDao")
    private IJifenDao jifenDao;

    private static String FAIL = "F";

    private static String SUCC = "S";

    private static Integer SINGLE_QUESTION_SCORE = 2;

    @Transactional(rollbackFor = Exception.class)
    public String doQuestions(Integer userId, List<Question> questions) {
        String batchNumber = UUID.randomUUID().toString();
        LocalDateTime createDateTime = LocalDateTime.now();
        Map<String, Integer> count = new ConcurrentHashMap<>(1);

        // 记录答题结果
        questions.forEach(item -> {
            Question question = questionDao.findByIdAndAnswer(item.getId(), item.getAnswer());
            TestResult testResult = new TestResult();
            testResult.setBatchNumber(batchNumber);

            if (null == question) {
                question = questionDao.getById(item.getId());
                testResult.setResult(FAIL);
                count.put(FAIL, count.getOrDefault(FAIL, 0) + 1);
            } else {
                testResult.setResult(SUCC);
                count.put(SUCC, count.getOrDefault(SUCC, 0) + 1);
            }
            if (!count.containsKey("questionType")) {
                // 0表示选择题，1表示填空题
                count.put("questionType", "O".equals(question.getType()) ? 0 : 1);
            }
            testResult.setUserId(userId);
            testResult.setQuestionId(question.getId());
            testResult.setQuestionType(item.getType());
            testResult.setQuestionType(question.getType());
            testResult.setCreateDateTime(createDateTime);
            testResultDao.save(testResult);
        });

        // 记录得分
        Score score = new Score();
        score.setBatchNumber(batchNumber);
        score.setUserId(userId);
        score.setScore(count.getOrDefault(SUCC, 0) * SINGLE_QUESTION_SCORE);
        score.setQuestionType(count.get("questionType") == 0 ? "O" : "W");
        score.setCreateDateTime(createDateTime);
        scoreDao.save(score);

        // 计算积分，计算规则：
        // 1. 答完50道题积1分
        // 2. （胜利积分）正确的个数-错误的个数=n,n>0则积n*A分，A为积分系数，若时间处于早上7点到8点（不包含8点）之间，则系数为2（双倍积分），其他时间段为1.0
        // 3. TODO 每日首次答题额外赠送2积分
        // 4. TODO 单日连续8次获取胜利积分时额外奖励80积分
        Integer jifenInteger = 1;
        Integer A = 1;
        if (LocalDateTime.now().getHour() == 7) {
            A = 2;
        }
        Integer fCount = count.getOrDefault(FAIL, 0);
        Integer sCount = count.getOrDefault(SUCC, 0);
        if (sCount > fCount) {
            jifenInteger += (sCount - fCount) * A;
        }
        Jifen jifen = jifenDao.findByUserId(userId);
        jifen.setScore(jifenInteger);
        jifen.setLastModTime(createDateTime);
        jifenDao.save(jifen);

        return batchNumber;
    }

    public Question findById(Integer id) {
        Question question = questionDao.getById(id);
        return question;
    }

    public Page<Question> findPage(Integer page, Integer size) {
        Question condition = new Question();
        condition.setType("W");
        Example<Question> example = Example.of(condition);
        return questionDao.findAll(example, PageRequest.of(page, size, Sort.by(Sort.Order.asc("answer"))));
    }

    /**
     * 按题干查询题目
     *
     * @param title 题干
     * @return 题目
     */
    public Question findByTitle(String title) {
        Question question = questionDao.findByTitle(title);
        return question;
    }

    /**
     * 随机获取50道题目
     *
     * @return 50道题目
     */
    public List<Question> find50QuestionsByType(String type) {
        List<Integer> ids = questionDao.findIds(type);
        List<Integer> ids50 = new ArrayList<>();

        // 随机抽取50道不重复的题ID
        Random random = new Random();
        int i = 0;
        while (i < 50) {
            Integer id = ids.get(random.nextInt(ids.size()));
            if (!ids50.contains(id)) {
                ids50.add(id);
                i++;
            }
        }

        List<Question> questions = questionDao.findAllById(ids50);
        return questions;
    }

    /**
     * 按题干和答案查询题目
     *
     * @param id     题目ID
     * @param answer 答案
     * @return 题目
     */
    public Question findByIdAndAnswer(Integer id, String answer) {
        return questionDao.findByIdAndAnswer(id, answer);
    }

}
