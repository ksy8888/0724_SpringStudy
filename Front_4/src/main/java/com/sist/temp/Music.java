package com.sist.temp;

import lombok.Getter;
import lombok.Setter;

/*
 * mno NUMBER,
	cno NUMBER,
	title VARCHAR2(500) CONSTRAINT gm_title_nn NOT NULL,
	singer VARCHAR2(200) CONSTRAINT gm_singer_nn NOT NULL,
	album VARCHAR2(500) CONSTRAINT gm_album_nn NOT NULL,
	poster VARCHAR2(260) CONSTRAINT gm_poster_nn NOT NULL,
	idcrement NUMBER,
	state VARCHAR2(20),
	KEY VARCHAR2(30),
	CONSTRAINT gm_mno_pk PRIMARY KEY(mno)
 */
@Getter
@Setter
public class Music {
	private int mno,cno,idcrement;
	private String title,singer,album,poster,state,key;
}
