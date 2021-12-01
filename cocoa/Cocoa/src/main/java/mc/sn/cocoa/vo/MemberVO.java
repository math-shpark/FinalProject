package mc.sn.cocoa.vo;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String pImg;
	private String pContents;
	private int doLike;
	
	public MemberVO() {
		
	}
	
	public MemberVO(String id, String pwd, String name, String phone, String pImg, String pContents, int doLike) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.pImg = pImg;
		this.pContents = pContents;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getpImg() {
		return pImg;
	}

	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

	public String getpContents() {
		return pContents;
	}

	public void setpContents(String pContents) {
		this.pContents = pContents;
	}

	public int getDoLike() {
		return doLike;
	}

	public void setDoLike(int doLike) {
		this.doLike = doLike;
	}
	
	
	
}