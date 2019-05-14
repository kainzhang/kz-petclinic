package model;

import java.io.Serializable;

public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6303692237258314732L;
	
	private String username, password;
    private Integer id, position;

    public Integer getId() {
    	return id;
    }
    public void setId(Integer id) {
    	this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword () {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public Integer getPosition() {
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }
}

