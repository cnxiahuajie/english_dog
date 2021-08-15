package net.xiahuajie.english_dog.controller.api;

import net.xiahuajie.english_dog.controller.entity.ResponseData;
import net.xiahuajie.english_dog.entity.Career;
import net.xiahuajie.english_dog.service.CareerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
@RequestMapping("/career")
public class CareerController {

    @Resource(name = "careerService")
    private CareerService careerService;

    @GetMapping("/findMine")
    public ResponseData findMine(Principal principal) {
        Career career = careerService.findByUsername(principal.getName());
        return ResponseData.ok(career);
    }

}
