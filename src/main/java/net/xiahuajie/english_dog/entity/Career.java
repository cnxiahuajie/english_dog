package net.xiahuajie.english_dog.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 生涯信息
 *
 * @author xiahuajie
 */
@Entity(name = "career")
@Table(name = "career")
@Data
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userId;

    /**
     * 学习时长-分钟
     */
    @Column
    private Integer learnDuration;

    @Column
    private Integer testCount;

    @Column
    private BigDecimal passRate;

}
