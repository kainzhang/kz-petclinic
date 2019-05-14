package model;

import java.io.Serializable;

public class Pet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2250448552572002245L;
	private String name, bday;
	private Integer id, speciesid, ownerid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSpeciesid() {
		return speciesid;
	}
	public void setSpeciesid(Integer speciesid) {
		this.speciesid = speciesid;
	}
	public Integer getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(Integer ownerid) {
		this.ownerid = ownerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}
}
