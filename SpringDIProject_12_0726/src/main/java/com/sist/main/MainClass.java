package com.sist.main;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;
import com.sist.config.*;

@Component("mc")
public class MainClass {
	
	@Autowired   //자동주입 (자동으로 주소값주입)
	private EmpDAO dao;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(EmpConfig.class);	//class로 넘겨줌
		MainClass mc = (MainClass)app.getBean("mc");
		List<EmpVO> list = mc.dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
								+vo.getEname()+" "
								+vo.getJob()+" "
								+vo.getSal());
		}
		
		Scanner scan = new Scanner(System.in);
		System.out.println("상세 번호 입력:");
		int empno = scan.nextInt();
		EmpVO vo = mc.dao.empDetailData(empno);
		System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob()+" "+vo.getSal());
	}

}
