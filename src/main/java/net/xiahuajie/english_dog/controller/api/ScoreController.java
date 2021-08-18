package net.xiahuajie.english_dog.controller.api;

import net.xiahuajie.english_dog.controller.entity.ResponseData;
import net.xiahuajie.english_dog.entity.Score;
import net.xiahuajie.english_dog.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 得分接口
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource(name = "scoreService")
    private ScoreService scoreService;

    @GetMapping("/findByBatchNumber/{batchNumber}")
    public ResponseData findByBatchNumber(@PathVariable("batchNumber") String batchNumber) {
        Score score = scoreService.findByBatchNumber(batchNumber);
        return ResponseData.ok(score);
    }

}
