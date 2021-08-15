package net.xiahuajie.english_dog.controller.api;

import net.xiahuajie.english_dog.controller.entity.ResponseData;
import net.xiahuajie.english_dog.entity.Question;
import net.xiahuajie.english_dog.entity.TestResult;
import net.xiahuajie.english_dog.service.QuestionService;
import net.xiahuajie.english_dog.service.TestResultService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource(name = "questionService")
    private QuestionService questionService;

    @Resource(name = "testResultService")
    private TestResultService testResultService;

    /**
     * 按分页获取题目
     *
     * @param page 页码
     * @param size 总页数
     * @return 题目分页对象
     */
    @GetMapping("/findByPage/{page}/{size}")
    @ResponseBody
    public ResponseData findByPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Page<Question> pageObj = questionService.findPage(page, size);
        return ResponseData.ok(pageObj);
    }

    /**
     * 按题干获取题
     *
     * @param title 题干
     * @return 题
     */
    @GetMapping("/findByTitle/{title}")
    @ResponseBody
    public ResponseData findByTitle(@PathVariable("title") String title) {
        Question question = questionService.findByTitle(title);
        return ResponseData.ok(question);
    }

    /**
     * 随机获取50道题
     *
     * @return
     */
    @GetMapping("/find50Questions/{type}")
    @ResponseBody
    public ResponseData find50Questions(@PathVariable("type") String type) {
        List<Question> questions = questionService.find50QuestionsByType(type);
        return ResponseData.ok(questions);
    }

    /**
     * 提交测试
     *
     * @param questions 题目与答案
     * @return 测试结果
     */
    @PostMapping("/doQuestions")
    @ResponseBody
    public ResponseData doQuestions(@RequestBody List<Question> questions) {

        String batchNumber = UUID.randomUUID().toString();
        List<TestResult> testResults = new ArrayList<>();
        questions.forEach(item -> {
            Question question = questionService.findByTitleAndAnswer(item.getTitle(), item.getAnswer());
            TestResult testResult = new TestResult();
            testResult.setBatchNumber(batchNumber);

            if (null == question) {
                question = questionService.findByTitle(item.getTitle());
                testResult.setResult("F");
            } else {
                testResult.setResult("S");
            }
            testResult.setQuestionId(question.getId());
            testResult.setQuestionType(item.getType());
            testResults.add(testResult);
        });

        testResultService.saveTestResults(testResults);
        return ResponseData.ok(testResults);
    }

}
