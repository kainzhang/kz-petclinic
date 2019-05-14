package model;

import java.io.Serializable;

public class Owner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6985451998934066131L;
	private Integer id;
	private String name, tel, addr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
