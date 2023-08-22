package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
import com.sist.vo.ReplyVO;

public interface FoodMapper {
	@Select("SELECT cno,title,poster,subject "
			+ "FROM food_category "
			+ "ORDER BY cno ASC")
	public List<CategoryVO> foodCategoryListData();
	
	@Select("SELECT title,subject FROM food_category WHERE cno=#{cno}")
	public CategoryVO foodCategoryInfoData(int cno);
	
	@Select("SELECT fno,name,address,phone,type,poster,score,"
			+ "(SELECT name FROM springReply "
			+ "WHERE rownum=1 AND fno=food_house.fno) as userName,"
			+ "(SELECT msg FROM springReply "
			+ "WHERE rownum=1 AND fno=food_house.fno) as rdata "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	////////////////////////////////////////////////////////////////////////////
	
	//servlet    (mapper.xml > mapper.java > DAO)
	//<select id="foodFindData" resultType="FoodVO" parameterType="hashmap">
	public List<FoodVO> foodFindData(Map map);
	
	//<select id="foodFindTotalPage" resultType="int" parameterType="hashmap">
	public int foodFindTotalPage(Map map);
	
	//상세
	@Select("SELECT fno,name,tel as phone,address,type,time,parking,price,score,poster,menu "
			+ "FROM food_location "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Select("SELECT fno,cno,name,phone,address,type,time,parking,price,score,poster "
			+ "FROM food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailHouseData(int fno);
	
	
	
}
