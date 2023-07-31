package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

//메모리 할당
@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
			+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			+ "FROM (SELECT /*+INDEX_DESC(springBoard sb_no_pk)no,subject,name,regdate,hit "
			+ "FROM springBoard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
						   map.get("start")   map.get("end") */

	public List<BoardVO> boardListData(Map map) {
	//start,end는 VO에 없으니 Map 사용
	return mapper.boardListData(map);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springBoard")
	public int boardTotalPage();
	 */
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
	
/*	@Insert("INSERT INTO springBoard(no,name,subject,content,pwd) "
			+ "VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")	//vo.getName, vo.getSubject....
*/
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 @Select("SELECT * FROM springBoard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);	
 */
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////	
/*
 * @Select("SELECT no,name,subject,content "
			+ "FROM springBoard "
			+ "WHERE no=?")
	public BoardVO boardUpdateData(int no);	
 */
	public BoardVO boardUpdateData(int no) {
		return mapper.boardUpdateData(no);
	}
	
/*
 * @Select("SELECT pwd FROM springBoard WHERE no=#{no}")
	public String boardGetPassword(int no);
	//실제 수정
	@Update("UPDATE springBoard SET name=#{name},subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);	
 */
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck = true;
			mapper.boardUpdate(vo);
		}
		return bCheck;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	 * @Delete("DELETE FROM springBoard "
			+ "WHERE no=#{no}")
	public void boardDelete(int no);
	 */
	public boolean boardDelete(int no,String pwd) {
		boolean bCheck=false;
		String db_pwd = mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck = true;
			mapper.boardDelete(no);
		}
		return bCheck;
	}
}
