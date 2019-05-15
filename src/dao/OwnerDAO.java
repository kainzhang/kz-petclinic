package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Owner;
import util.Connect;

public class OwnerDAO {
	public boolean insertOwner(Owner owner) {
		String stmt = 
				" BEGIN;" +
                " INSERT INTO owner" +
                " VALUES" +
                " (null, '"+owner.getName()+
                "','"+owner.getTel()+
                "','"+owner.getAddr()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            return false;
		}
		return true;
	}
	
	public boolean deleteOwner(Integer id) {
		String stmt = 
				" BEGIN;" +
                " DELETE FROM owner" +
                " WHERE id="+id+";" +
                " COMMIT;";
        try {
            Connect.exeUpdate(stmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            return false;
        }
        return true;
	}
	
	public boolean updateOwner(Owner owner) {
    	String stmt =
				" BEGIN;" +
                " UPDATE owner" +
                " SET name = '"+owner.getName()+"' ," +
                " tel = '"+owner.getTel()+"' ," +
                " addr = '"+owner.getAddr()+"'," +
                " WHERE id = "+owner.getId()+"; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
		    System.out.print("Error occurred while UPDATE Operation: " + e);
		    return false;
		}
		return true;
	}
	
	private List<Owner> getList(String stmt) {
		ResultSet rs;
		List<Owner> list = new ArrayList<Owner>();
		try {
			rs = Connect.exeQuery(stmt);
			while(rs.next()) {
				Owner owner = new Owner();
				owner.setId(rs.getInt("ID"));
				owner.setName(rs.getString("NAME"));
				owner.setAddr(rs.getString("addr"));
				owner.setTel(rs.getString("tel"));
				list.add(owner);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	
	public List<Owner> searchOwners(String keyword) {
		String stmt = " SELECT * FROM owner WHERE name LIKE '%"+keyword+"%'; ";
		return getList(stmt);
	}
	
	public List<Owner> getOwners() {
		String stmt = " SELECT * FROM owner ; ";
		return getList(stmt);
	}
}