package net.xiahuajie.english_dog.controller.api;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import net.xiahuajie.english_dog.controller.entity.ResponseData;
import net.xiahuajie.english_dog.entity.User;
import net.xiahuajie.english_dog.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;

	@PostMapping("/doUpdateLoginTime")
	public ResponseData doUpdateLoginTime(Principal principal) {
		userService.doUpdateLoginTime(principal.getName());
		return ResponseData.ok();
	}

	@PostMapping("/doModify")
	public ResponseData doModify(@RequestBody User user, Principal principal) {
		userService.doModify(principal.getName(), user);
		return ResponseData.ok();
	}

	@GetMapping("/findMine")
	public ResponseData findMine(Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		return ResponseData.ok(user);
	}

	@GetMapping("/findByUsername/{username}")
	public ResponseData findByUsername(@PathVariable("username") String username) {
		User user = userService.findUserByUsername(username);
		return ResponseData.ok(user);
	}

	@GetMapping("/findByPhone/{phone}")
	public ResponseData findByPhone(@PathVariable("phone") String phone) {
		User user = userService.findUserByPhone(phone);
		return ResponseData.ok(user);
	}

	@GetMapping("/me")
	public ResponseData me(UserDetails userDetails) {
		return ResponseData.ok(userDetails.getUsername());
	}

}
