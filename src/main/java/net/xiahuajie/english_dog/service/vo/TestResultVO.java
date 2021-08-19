package net.xiahuajie.english_dog.service.vo;

import lombok.Data;
import net.xiahuajie.english_dog.entity.Question;
import net.xiahuajie.english_dog.entity.TestResult;

@Data
public class TestResultVO extends TestResult {

    private Question question;

}
