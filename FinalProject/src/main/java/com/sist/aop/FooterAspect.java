package com.sist.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Aspect //공통 모듈
@Component //메모리할당
public class FooterAspect {
	@Autowired
	private AspectDAO dao;
	 
	@After("execution(* com.sist.web.*Controller.*(..))") //finally >> 에러가 나도 footer에 뿌려줌
	public void footerData() {
		// => 매개변수에 값을 채워준다 (DispatcherServlet)
		//=> @Controller, @RestController에서 가능
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		 //                           PageContext (내장 객체)
		List<FoodVO> fList = dao.foodTop7Data();
		List<String> sList = dao.seoulTop7Data();
		List<String> rList = dao.recipeTop7Data();
		
		request.setAttribute("fList", fList);
		request.setAttribute("sList", sList);
		request.setAttribute("rList", rList);
		
	}
	
}
