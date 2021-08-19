package net.xiahuajie.english_dog.controller.api;

import net.xiahuajie.english_dog.controller.entity.ResponseData;
import net.xiahuajie.english_dog.service.TestResultService;
import net.xiahuajie.english_dog.service.vo.TestResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/testResult")
public class TestResultController {

    @Resource(name = "testResultService")
    private TestResultService testResultService;

    @GetMapping("/findByBatchNumber/{batchNumber}/{result}")
    public ResponseData findByBatchNumber(@PathVariable("batchNumber") String batchNumber, @PathVariable("result") String result) {
        List<TestResultVO> testResultVOS = testResultService.findByBatchNumber(batchNumber, result);
        return ResponseData.ok(testResultVOS);
    }

}
