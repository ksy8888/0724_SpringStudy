package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.type.TrueFalseType;

import lombok.Getter;
import lombok.Setter;
/*
 * no int AI PK 
name varchar(51) 
subject varchar(2000) 
content text 
pwd varchar(10) 
regdate datetime 
hit int
 */
@Entity
@Table(name="board")
@Getter
@Setter
@DynamicUpdate
public class BoardEntity {
	@Id
	private int no;
	private String name,subject,content;
	@Column(insertable = true, updatable = false)  //추가(insert), 수정(X)   >> insertn,update할때마다 수정한 날짜로 변경됨
	private String pwd;
	private int hit;
	 
	@Column(insertable = true, updatable = false)  //추가(insert), 수정(X)
	private String regdate;
	
	//날짜바꾸기
	@PrePersist
	public void regdate() {
		this.regdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
}
