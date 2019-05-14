package model;

import java.io.Serializable;

public class Vet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7039234306391176173L;
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
