package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;

/*
 * CONSTRUCTOR, FIELD, METHOD, PARAMETER
 * 
 * class A {
 * 		@Autowired
 * 	    private B b;
 *      
 *      @Autowired
 *      public A(B b) {}
 *      
 *      @Autowired
 *      public void setB(B b) {}
 * 
 * }
 */
@Repository
public class EmpDAO extends SqlSessionDaoSupport {
	
	@Autowired  //xml사용시 setSqlSessionFactory를 사용하여 값을 주입해야 아래 empGetNameData...메소드 사용 가능
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/*
	 * <select id="empGetNameData" resultType="string">
	    SELECT ename FROM emp
	   </select>
	   <select id="empInfoData" resultType="EmpVO" parameterType="hashmap">
	    SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,comm,deptno
	    FROM emp
	 */
	public List<String> empGetNameData() {
		return getSqlSession().selectList("empGetNameData");
	}
	
	public List<EmpVO> empInfoData(Map map) { //parameterType="hashmap"
		return getSqlSession().selectList("empInfoData",map);  //adapter pattern
	}
}
