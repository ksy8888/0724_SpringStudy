package com.sist.vo;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBoardVO {
	private int no,hit,filecount;
	private String name,subject,content,pwd,filename,filesize,dbday;
	private List<MultipartFile> files;   //업로드된 파일 저장 //여러개 동시에 올릴때
	
	/*
	 *   <input type=file name="files[0]">   => bad request (400) 매개변수 틀렸을때
	 */
}
