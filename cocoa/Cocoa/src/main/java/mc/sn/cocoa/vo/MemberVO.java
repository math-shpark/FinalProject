package mc.sn.cocoa.vo;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String proImg;
	private String proContents;
	
	public MemberVO() {
		
	}
	
	public MemberVO(String id, String pwd, String name, String phone, String proImg, String proContents) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.proImg = proImg;
		this.proContents = proContents;
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

	public String getproImg() {
		return proImg;
	}

	public void setproImg(String proImg) {
		this.proImg = proImg;
	}

	public String getproContents() {
		return proContents;
	}

	public void setproContents(String proContents) {
		this.proContents = proContents;
	}

	
	
	
	
}