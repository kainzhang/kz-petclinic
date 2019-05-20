package model;

import java.io.Serializable;

public class Record implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3030476924562243435L;
	private Integer id, petid,vetid;
	private String date, descr,petname,vetname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getVetid() {
		return vetid;
	}
	public void setVetid(Integer vetid) {
		this.vetid = vetid;
	}
	public String getVetname() {
		return vetname;
	}
	public void setVetname(String vetname) {
		this.vetname = vetname;
	}
	public String getPetname() {
		return petname;
	}
	public void setPetname(String petname) {
		this.petname = petname;
	}
}
