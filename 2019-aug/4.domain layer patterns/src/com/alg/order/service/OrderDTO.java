package com.alg.order.service;

public class OrderDTO {
	private String pid;
	private String iid;
	private String tel;
	private String email;

	public OrderDTO() {

	}

	public OrderDTO(String pid, String iid) {
		super();
		this.pid = pid;
		this.iid = iid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
