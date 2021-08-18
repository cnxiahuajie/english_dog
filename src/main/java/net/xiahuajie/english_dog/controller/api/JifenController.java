package net.xiahuajie.english_dog.controller.api;

import net.xiahuajie.english_dog.controller.entity.ResponseData;
import net.xiahuajie.english_dog.service.JifenService;
import net.xiahuajie.english_dog.service.vo.JifenVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 积分接口
 *
 * @author xiahuajie
 */
@RestController
@RequestMapping("/jifen")
public class JifenController {

    @Resource(name = "jifenService")
    private JifenService jifenService;

    @GetMapping("/findAllBySort/{by}")
    public ResponseData findAllBySort(@PathVariable("by") String by) {
        List<JifenVO> scoreList = jifenService.findAllBySort(by);
        return ResponseData.ok(scoreList);
    }

}
