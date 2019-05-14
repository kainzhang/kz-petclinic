package model;

import java.io.Serializable;

public class Specialty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2160751975800454214L;
	private Integer id;
	private String name;
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
}
