package net.xiahuajie.english_dog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import net.xiahuajie.english_dog.dao.IQuestionDao;
import net.xiahuajie.english_dog.entity.Question;

@Service("questionService")
public class QuestionService {

	@Resource(name = "questionDao")
	private IQuestionDao questionDao;

	public Page<Question> findPage(Integer page, Integer size) {
		Question condition = new Question();
		condition.setType("W");
		Example<Question> example = Example.of(condition);
		return questionDao.findAll(example, PageRequest.of(page, size, Sort.by(Sort.Order.asc("answer"))));
	}

	/**
	 * 按题干查询题目
	 * 
	 * @param title 题干
	 * @return 题目
	 */
	public Question findByTitle(String title) {
		Question question = questionDao.findByTitle(title);
		return question;
	}

	/**
	 * 随机获取50道题目
	 * 
	 * @return 50道题目
	 */
	public List<Question> find50QuestionsByType(String type) {
		List<Integer> ids = questionDao.findIds(type);
		List<Integer> ids50 = new ArrayList<>();

		// 随机抽取50道不重复的题ID
		Random random = new Random();
		int i = 0;
		while (i < 50) {
			Integer id = ids.get(random.nextInt(ids.size()));
			if (!ids50.contains(id)) {
				ids50.add(id);
				i++;
			}
		}

		List<Question> questions = questionDao.findAllById(ids50);
		return questions;
	}

	/**
	 * 按题干和答案查询题目
	 * 
	 * @param title  题干
	 * @param answer 答案
	 * @return 题目
	 */
	public Question findByTitleAndAnswer(String title, String answer) {
		return questionDao.findByTitleAndAnswer(title, answer);
	}

}
