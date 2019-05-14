package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.Connect;

public class UserDAO {

    public void updateUserPassword (String username, String password) throws ClassNotFoundException {
        String updateStmt =
        				" BEGIN;"+
                        " UPDATE user" +
                        " SET PASSWORD ='"+password+"' " +
                        " WHERE USERNAME = '"+username+"'; " +
                        " COMMIT;";
        try {
            Connect.exeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
        }
    }

    public void deleteUser (Integer id) throws ClassNotFoundException {
        String updateStmt =
        				" BEGIN;" +
                        " DELETE FROM user" +
                        " WHERE id="+id+";" +
                        " COMMIT;";
        try {
            Connect.exeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
        }
    }

    public void insertUser (User user) throws ClassNotFoundException {
    	String updateStmt =
        				" BEGIN;" +
                        " INSERT INTO user" +
                        " (ID, USERNAME, PASSWORD, POSITION)" +
                        " VALUES" +
                        " (null, '"+user.getUsername()+"', '"+user.getPassword()+"', "+user.getPosition()+");" +
                        " COMMIT;";
        try {
            Connect.exeUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
        }
    }
    
	public boolean userExist(String username){
		ResultSet rs;
		try {
			rs = Connect.exeQuery("SELECT * FROM user WHERE username='"+username+"';");
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int confirmUser(String username, String password) {
		ResultSet rs;
		try {
			rs = Connect.exeQuery("SELECT * FROM USER WHERE username='"+username+"' AND password='"+password+"';");
			if(rs.next()){
				//User user = new User();
				//user.setId(rs.getInt("ID"));
		   		//user.setUsername(rs.getString("USERNAME"));
		   		//user.setPassword(rs.getString("PASSWORD"));
		   		//user.setPosition(rs.getInt("POSITION"));
				return rs.getInt("ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
		
	}
}