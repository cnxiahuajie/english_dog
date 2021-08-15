package net.xiahuajie.english_dog.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 用户
 * 
 * @author xiahuajie
 *
 */
@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String phone;

	@Column
	private String email;

	@Column
	private LocalDate createDate;

	@Column
	private LocalDateTime lastLoginTime;

	@Column
	private LocalDateTime lastModTime;

}
