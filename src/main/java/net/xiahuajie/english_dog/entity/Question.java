package net.xiahuajie.english_dog.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 题库
 *
 * @author xiahuajie
 */
@Entity(name = "question")
@Table(name = "question")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 题型（O表示选择题，W表示填空题）
     */
    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "optionA")
    private String optionA;

    @Column(name = "optionB")
    private String optionB;

    @Column(name = "optionC")
    private String optionC;

    @Column(name = "optionD")
    private String optionD;

    @Column(name = "answer")
    private String answer;

    @Column(name = "createDate")
    private LocalDate createDate;

    @Column(name = "lastModTime")
    private LocalDateTime lastModTime;

}
