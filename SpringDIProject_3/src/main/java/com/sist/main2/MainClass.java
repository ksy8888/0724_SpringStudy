package com.sist.main2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		StudentSystem ss=(StudentSystem)app.getBean("ss");
		ss.print();
	}
	/* list.add
	 * <bean id="ss" class="com.sist.main2.StudentSystem">
		<property name="list">
		 <list>
		   <ref bean="sa1"/>
		   <ref bean="sa2"/>
		   <ref bean="sa3"/>
		 </list>
		</property>
	 </bean>
	 */

}
