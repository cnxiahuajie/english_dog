package net.xiahuajie.english_dog.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 测试结果
 *
 * @author xiahuajie
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
    private Integer userId;

    @Column
    private Integer questionId;

    /**
     * 题型
     */
    @Column
    private String questionType;

    @Column
    private String result;

    @Column
    private LocalDateTime createDateTime;

}
