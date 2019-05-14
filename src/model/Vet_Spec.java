package model;

import java.io.Serializable;

public class Vet_Spec implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1565421810053077691L;
	private Integer vetId, specId;
	public Integer getVetId() {
		return vetId;
	}
	public void setVetId(Integer vetId) {
		this.vetId = vetId;
	}
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
}
