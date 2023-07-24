package com.sist.main4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
/*
 *  ApplicationContext
 *  AnnotationConfigApplicationContext ==> 5 버전
 */
public class MainClass {
	
	private Sawon sa;
	
	public Sawon getSa() {
		return sa;
	}

	public void setSa(Sawon sa) {   //여기에 
		this.sa = sa;
	}

	public static void main(String[] args) {
		
		//ApplicationContext app = new ClassPathXmlApplicationContext("app4.xml");
		GenericXmlApplicationContext app = new GenericXmlApplicationContext("app4.xml");
/*
		Sawon sa = (Sawon)app.getBean("sa1"); //bean id //DL :클래스찾는거(lookup) sa1 클래스를 찾아서 객체 얻어옴
		//sa.init();
		sa.print();
		//sa.destroy() (X)
		app.close();
*/
	}

}
