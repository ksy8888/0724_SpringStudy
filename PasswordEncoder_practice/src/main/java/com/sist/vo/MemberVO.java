package com.sist.vo;

/*
 * id VARCHAR2(20),
	pwd VARCHAR2(500) CONSTRAINT stm_pwd_nn NOT NULL,
	name VARCHAR2(51) CONSTRAINT stm_name_nn NOT NULL,
	sex VARCHAR2(20),
	regdate DATE DEFAULT SYSDATE,
	CONSTRAINT stm_id_pk PRIMARY KEY(id),
	CONSTRAINT stm_sex_ck CHECK(sex IN('남자','여자'))
 */
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String id,name,pwd,sex,msg;
	private Date regdate;
}
