package net.xiahuajie.english_dog.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 积分表
 *
 * @author xiahuajie
 */
@Entity(name = "jifen")
@Table(name = "jifen")
@Data
public class Jifen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userId;

    @Column
    private Integer score;

    @Column
    private LocalDateTime lastModTime;

}
