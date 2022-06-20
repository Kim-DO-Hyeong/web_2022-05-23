package service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.NoticeTblDao;
import dto.NoticeInfo;

public class NoticeService {
	
	public JSONObject selectNoticeInfoByIdx (int idx){
		NoticeTblDao dao = new NoticeTblDao();
		
		NoticeInfo selectedNoticeInfo = dao.selectedNoticeInfoByIdx(idx);
		
		JSONObject json = new JSONObject();
		
		json.put("title",selectedNoticeInfo.getTitle());
		json.put("contents", selectedNoticeInfo.getContents());
		
		return json;
	}
	
	
	public JSONArray getNoticeInfoList (int pageNumber) {
		
		pageNumber = (pageNumber-1)*5;
		
		NoticeTblDao dao = new NoticeTblDao();
		
		List<NoticeInfo> noticeInfoList = dao.getNoticeInfoList(pageNumber);
		
		JSONArray jsonArray = new JSONArray();
		
		for (NoticeInfo nth : noticeInfoList) {
			JSONObject noticeInfo = new JSONObject();

			noticeInfo.put("noticeID", nth.getnoticeId());
			noticeInfo.put("title", nth.getTitle());
			noticeInfo.put("contents", nth.getContents());
			// 공지사항 정보 목록에 배열의 형태로 저장한다
			jsonArray.put(noticeInfo);

		}
		
		return jsonArray;

	
	}

	public int getNoticeAmount() {
		NoticeTblDao dao = new NoticeTblDao();
		
		return dao.getNoticeAmount();
	}

}
