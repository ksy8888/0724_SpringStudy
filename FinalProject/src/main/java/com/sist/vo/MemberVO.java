package com.sist.vo;
import  java.util.*;

import lombok.Getter;
import lombok.Setter;
/*
ID                                        NOT NULL VARCHAR2(1000)
 PWD                                       NOT NULL VARCHAR2(1000)
 NAME                                      NOT NULL VARCHAR2(51)
 SEX                                                VARCHAR2(6)
 BIRTHDAY                                  NOT NULL VARCHAR2(30)
 EMAIL                                              VARCHAR2(100)
 POST                                      NOT NULL VARCHAR2(10)
 ADDR1                                     NOT NULL VARCHAR2(200)
 ADDR2                                              VARCHAR2(200)
 PHONE                                              VARCHAR2(30)
 CONTENT                                            CLOB
 ADMIN                                              CHAR(1)
 REGDATE                                            DATE
 ROLE                                               VARCHAR2(30)
 */
@Getter
@Setter
public class MemberVO {
	private String id,pwd,name,sex,birthday,email,post,addr1,addr2,content,admin;
	private String phone,phone1,dbday,msg; //msg >> id가 있는지없는지 유효성검사 변수
	private Date regdate;
	private String role;
}
