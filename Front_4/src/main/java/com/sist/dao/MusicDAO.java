package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class MusicDAO {
	@Autowired
	private MusicMapper mapper;
	
	
	/*@Select("SELECT * FROM genie_music "
			+ "WHERE cno=#{cno}")
*/	public List<MusicVO> musicListData(int cno) {
		return mapper.musicListData(cno);
	}

	//@Select("SELECT * FROM genie_music "
		//	+ "WHERE mno=#{mno}")
	public MusicVO musicDetailData(int mno) {
		return mapper.musicDetailData(mno);
	}
}
