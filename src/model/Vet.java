package model;

import java.io.Serializable;
import java.util.List;

public class Vet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7039234306391176173L;
	private Integer id;
	private String name, pic;
	private List<Specialty> specialties;
	
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
	public List<Specialty> getSpecialties() {
		return specialties;
	}
	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}
