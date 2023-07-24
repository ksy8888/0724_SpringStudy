package com.sist.main;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MainClass {
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
//		EmpDAO dao=new EmpDAO("oracle.jdbc.driver.OracleDriver"); 세팅이 안됨
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
	}
}
