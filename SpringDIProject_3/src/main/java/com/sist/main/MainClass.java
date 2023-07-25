package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Setter;
public class MainClass {
	@Setter
	private GoodsDAO dao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mc"); // Spring에서 등록된 MainClass 갖고와야함 DAO객체를 갖고 있음 
		// new를 사용하면 DAO객체가 없는 세팅이 안된  
		
		List<String> list=mc.dao.goodsNameList();
		for(String name:list)
		{
			System.out.println(name);
		}
	}

}
