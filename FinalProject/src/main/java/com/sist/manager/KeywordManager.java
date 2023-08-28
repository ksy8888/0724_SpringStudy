package com.sist.manager;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

public class KeywordManager {
	public static void main(String[] args) {
		String keyword = "개인적으로 구워 먹는 닭갈비보다 볶아 먹는 닭갈비를 좋아하는데 지금까지 먹어본 구워 먹는 닭갈비 중에 제일 맛있었어요!!!! 반찬도 깔끔하고 셀프리필 가능하구요, 닭갈비는 직원분께서 타지 않게 구워주셔서 편하고 좋아요. 다 구워지면 판을 이중으로 세워서? 따뜻하게 먹을 수 있게 해주세용.";
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList list = ke.extractKeyword(keyword, true); //true => 명사만 추출
				
		for(int i=0;i<list.size();i++) {
			Keyword wrd = list.get(i);
			System.out.println(wrd.getString()+":"+wrd.getCnt());
		}
	}
}
