package net.xiahuajie.english_dog.controller.api;

import net.xiahuajie.english_dog.service.TestResultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/testResult")
public class TestResultController {

    @Resource(name = "testResultService")
    private TestResultService testResultService;

}
