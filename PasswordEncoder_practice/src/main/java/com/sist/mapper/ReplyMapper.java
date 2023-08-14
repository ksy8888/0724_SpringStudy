package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface ReplyMapper {
	
	//<select id="replyListData" resultType="ReplyVO" parameterType="int">
	public List<ReplyVO> replyListData(int fno);
	
	
   //<insert id="replyInsert" parameterType="ReplyVO">
   public void replyInsert(ReplyVO vo);
   
   //xml사용하지않고
   @Delete("DELETE FROM springTestReply WHERE no=#{no}")
   public void replyDelete(int no);
   
   @Update("UPDATE springTestReply SET msg=#{msg} WHERE no=#{no}")
   public void replyUpdate(ReplyVO vo);
}
