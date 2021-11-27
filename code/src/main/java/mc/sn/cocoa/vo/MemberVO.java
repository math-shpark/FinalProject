package mc.sn.cocoa.vo;

public class MemberVO {

	private String id;
	private String pwd;
	private String nickName;
	private String imgFilePath;
	private String contents;
	private double score;
	private String doLike;

	public MemberVO() {

	}

	public MemberVO(String id, String pwd, String nickName, String imgFilePath, String contents, double score,
			String doLike) {
		this.id = id;
		this.pwd = pwd;
		this.nickName = nickName;
		this.imgFilePath = imgFilePath;
		this.contents = contents;
		this.score = score;
		this.doLike = doLike;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getImgFilePath() {
		return imgFilePath;
	}

	public void setImgFilePath(String imgFilePath) {
		this.imgFilePath = imgFilePath;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getDoLike() {
		return doLike;
	}

	public void setDoLike(String doLike) {
		this.doLike = doLike;
	}
}