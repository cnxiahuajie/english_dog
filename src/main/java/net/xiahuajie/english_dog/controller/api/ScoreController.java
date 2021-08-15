package net.xiahuajie.english_dog.controller.api;

import net.xiahuajie.english_dog.controller.entity.ResponseData;
import net.xiahuajie.english_dog.service.ScoreService;
import net.xiahuajie.english_dog.service.vo.ScoreVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource(name = "scoreService")
    private ScoreService scoreService;

    @GetMapping("/findAllBySort/{by}")
    public ResponseData findAllBySort(@PathVariable("by") String by) {
        List<ScoreVo> scoreList = scoreService.findAllBySort(by);
        return ResponseData.ok(scoreList);
    }

}
