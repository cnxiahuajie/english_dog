package net.xiahuajie.english_dog.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 得分表
 *
 * @author xiahuajie
 */
@Entity(name = "score")
@Table(name = "score")
@Data
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userId;

    @Column
    private String batchNumber;

    @Column
    private String questionType;

    @Column
    private Integer score;

    @Column
    private LocalDateTime createDateTime;

}
