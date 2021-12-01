package mc.sn.cocoa.vo;

import org.springframework.stereotype.Component;

@Component("projectVO")
public class ProjectVO {
	private int projectNO;
	private String leader;
	private String pImg;
	private String pTitle;
	private int memberCount;
	private String level;
	private String pContents;
	private String map;
	private String kakao;

	public ProjectVO() {

	}

	public ProjectVO(int projectNO, String leader, String pImg, String pTitle, int memberCount, String level,
			String pContents, String map, String kakao) {
		super();
		this.projectNO = projectNO;
		this.leader = leader;
		this.pImg = pImg;
		this.pTitle = pTitle;
		this.memberCount = memberCount;
		this.level = level;
		this.pContents = pContents;
		this.map = map;
		this.kakao = kakao;
	}

	public int getProjectNO() {
		return projectNO;
	}

	public void setProjectNO(int projectNO) {
		this.projectNO = projectNO;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getpImg() {
		return pImg;
	}

	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getpContents() {
		return pContents;
	}

	public void setpContents(String pContents) {
		this.pContents = pContents;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getKakao() {
		return kakao;
	}

	public void setKakao(String kakao) {
		this.kakao = kakao;
	}
}