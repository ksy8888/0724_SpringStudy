package com.sist.vo;

/*
 * NO NUMBER,
	fno NUMBER,
	id VARCHAR2(20),
	name VARCHAR2(51) CONSTRAINT str_name_nn NOT NULL,
	msg CLOB CONSTRAINT str_msg_nn NOT NULL,
	regdate DATE DEFAULT SYSDATE,
	CONSTRAINT str_no_pk PRIMARY KEY(no),
	CONSTRAINT str_fno_fk FOREIGN KEY(fno) REFERENCES food_house(fno),
	CONSTRAINT str_id_fk FOREIGN KEY(id) REFERENCES springTestMember(id)
 */
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	private int no,fno;
	private String id,name,msg,dbday;
	private Date regdate;
}
