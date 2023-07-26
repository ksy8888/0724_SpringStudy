package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//app.xml을 자바소스코딩으로만 대체


@Configuration  //환경설정

//xml파일의 <context:component-scan base-package="com.sist.*"/>를 대체
@ComponentScan(basePackages = "com.sist.*")	//모든 클래스 다 가져옴  

//<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages = "com.sist.mapper")

public class EmpConfig {
	
	/*
	 <!-- DataBase 셋팅 -->
   <bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
     p:driverClassName="oracle.jdbc.driver.OracleDriver"
     p:url="jdbc:oracle:thin:@localhost:1521:XE"
     p:username="hr"
     p:password="happy"
   />
   */
	@Bean("ds") //bean id="ds"
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
		
	}
	
	/*
   <!-- MyBatis -->
   <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
     p:dataSource-ref="ds"
   />
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		
		return ssf.getObject();
	}
	
}
