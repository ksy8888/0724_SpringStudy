package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
/*
 *  요청 : .do
 *    DispatcherServlet
 *      => HandlerAdapter : Model 클래스 찾기
 *      => HandlerMapping : @GetMapping, @PosterMapping, @RequestMapping
 *                          => 메소드 찾기 => 호출
 *    DispatcherServlet
 *      => request => ViewResolver
 *                         |
 *                        JSP                      
 */
// 스프링에서 클래스 등록
@Configuration
//<context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages = {"com.sist.*"})
//<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages = {"com.sist.mapper"})
public class SpringConfig implements WebMvcConfigurer {

	
	/* <servlet>
	<servlet-name>dispatcher</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/config/application-*.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
  </servlet>
  
   HandlerMapping / HandlerAdater => 메모리 할당
 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();  //HandlerMapping / HandlerAdater 메모리 할당
	}
	
	

	/*
	 * <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver" 
	   p:prefix="/"
	   p:suffix=".jsp"
	/>
	 */
	@Bean("viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver iv = new InternalResourceViewResolver();
		iv.setPrefix("/");
		iv.setSuffix(".jsp");
		return iv;
	}
	
	
	/*
	 *  <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"
	 */
	@Bean("multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver cr = new CommonsMultipartResolver();
		return cr;
	}
	
	/*
	 * <bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
	   p:driverClassName="#{db['driver']}"
	   p:url="#{db['url']}"
	   p:username="#{db['username']}"
	   p:password="#{db['password']}"
	   p:maxActive="#{db['maxActive']}"
	   p:maxIdle="#{db['maxIdle']}"
	   p:maxWait="#{db['maxWait']}"
	 */
	@Bean("ds")
	public DataSource datasource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		ds.setMaxActive(10);
		ds.setMaxIdle(10);
		ds.setMaxWait(-1);
		return ds;
		
	}
	
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(datasource());
		return ssf.getObject();
	}
	
}
