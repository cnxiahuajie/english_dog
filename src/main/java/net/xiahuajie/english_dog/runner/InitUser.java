package net.xiahuajie.english_dog.runner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Resource;

import net.xiahuajie.english_dog.dao.ICareerDao;
import net.xiahuajie.english_dog.dao.IScoreDao;
import net.xiahuajie.english_dog.entity.Career;
import net.xiahuajie.english_dog.entity.Score;
import org.checkerframework.checker.units.qual.C;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.xiahuajie.english_dog.dao.IUserDao;
import net.xiahuajie.english_dog.entity.User;

@Component
public class InitUser implements CommandLineRunner {

	@Resource(name = "userDao")
	private IUserDao userDao;

	@Resource(name = "bCryptPasswordEncoder")
	private PasswordEncoder passwordEncoder;

	@Resource(name = "careerDao")
	private ICareerDao careerDao;

	@Resource(name = "scoreDao")
	private IScoreDao scoreDao;

	@Override
	public void run(String... args) throws Exception {
		// 初始化管理员用户
		User user = userDao.findByUsername("sa");
		if (null == user) {
			user = new User();
			user.setUsername("sa");
			user.setPhone("15527462726");
			user.setEmail("cnxiahuajie@icloud.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setCreateDate(LocalDate.now());
			user.setLastModTime(LocalDateTime.now());
			userDao.save(user);
		}
		Career career = careerDao.findByUserId(user.getId());
		if (null == career) {
			career = new Career();
			career.setUserId(user.getId());
			career.setLearnDuration(0);
			career.setTestCount(0);
			career.setPassRate(BigDecimal.ZERO);
			careerDao.save(career);
		}
		Score score = scoreDao.findByUserId(user.getId());
		if (null == score) {
			score = new Score();
			score.setUserId(user.getId());
			score.setScore(0);
			score.setLastModTime(LocalDateTime.now());
			scoreDao.save(score);
		}
	}

}
