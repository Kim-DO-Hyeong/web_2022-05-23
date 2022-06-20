package dto;

public class NoticeInfo {
	private int noticeId;
	private String title;
	private String contents;
	
	public NoticeInfo(String title, String contents){
		this.title=title;
		this.contents=contents;
	}
	
	public NoticeInfo(int noticeId,String title, String contents){
		this.noticeId=noticeId;
		this.title=title;
		this.contents=contents;
	}
	
	public int getnoticeId() {
			return noticeId;
		}
	
	public void setIdx(int noticeId) {
		this.noticeId = noticeId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
