package net.xiahuajie.english_dog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 测试结果
 * 
 * @author xiahuajie
 *
 */
@Entity(name = "test_result")
@Table(name = "test_result")
@Data
public class TestResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String batchNumber;
	
	@Column
	private Integer questionId;

	/**
	 * 题型
	 */
	@Column
	private String questionType;
	
	@Column
	private String result;

}
