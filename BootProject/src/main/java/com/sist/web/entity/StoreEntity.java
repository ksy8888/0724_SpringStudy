package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
/*
PDNO int 
TITLE text 
POSTER text 
SUBJECT text 
SALE text 
PRICED_SALE text 
ORIGINAL_PRI text 
FIRST_PRI text 
SCORE double 
DELIVERY_PRI text 
GOODS_COUNT int
 */
@Entity
@Table(name="product_detail")   //insert,update... 자동설정
@Getter
@Setter
public class StoreEntity {
	@Id    //auto increment >> 번호 자동증가 설정
	private int pdno;
	private int goods_count;
	private double score;
	private String title,poster,subject,sale,priced_sale,original_pri,first_pri,delivery_pri;
}
