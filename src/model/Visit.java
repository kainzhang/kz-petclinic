package model;

import java.io.Serializable;

public class Visit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3030476924562243435L;
	private Integer id, userid, petid;
	private String date, descr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getPetid() {
		return petid;
	}
	public void setPetid(Integer petid) {
		this.petid = petid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
}
