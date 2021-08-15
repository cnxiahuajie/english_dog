package net.xiahuajie.english_dog.runner;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.xiahuajie.english_dog.dao.IQuestionDao;
import net.xiahuajie.english_dog.entity.Question;
import net.xiahuajie.english_dog.utils.JsonUtil;

/**
 * 初始化题库
 * 
 * @author xiahuajie
 *
 */
@Component("initQuestions")
public class InitQuestions implements CommandLineRunner {

	@Resource(name = "questionDao")
	private IQuestionDao questionDao;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String home = System.getProperty("user.home");
		String filepath = home + File.separator + "Downloads" + File.separator + "名词表" + File.separator + "tiku.json";
		String jsonStr = JsonUtil.readJsonFile(filepath);

		JSONObject jsonObj = JSONObject.parseObject(jsonStr);

		JSONArray jsonArr = jsonObj.getJSONArray("questions");

		Iterator<Object> questionsIt = jsonArr.iterator();
		Object obj = null;
		JSONObject questionObj = null;

		Set<String> questionTitles = new HashSet<>();
		while (questionsIt.hasNext()) {
			obj = questionsIt.next();
			questionObj = JSONObject.parseObject(JSONObject.toJSONString(obj));

			questionTitles.add(questionObj.getString("title"));
		}

		List<Question> questions = new ArrayList<>();

		// 移除重复的题干
		jsonArr.forEach(item -> {
			Question question = JSONObject.parseObject(JSONObject.toJSONString(item), Question.class);
			if (questionTitles.size() > 0) {
				if (questionTitles.contains(question.getTitle())) {
					questions.add(question);
					questionTitles.remove(question.getTitle());
				}
			}
		});

		// 将不存在的题干入库
		questions.forEach(question -> {
			Question q = questionDao.findByTitle(question.getTitle());
			if (null == q) {
				((Question) question).setCreateDate(LocalDate.now());
				((Question) question).setLastModTime(LocalDateTime.now());
				questionDao.save(question);
			}
		});
	}

}
