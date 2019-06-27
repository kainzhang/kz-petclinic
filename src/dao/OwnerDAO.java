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
                "','"+owner.getAddr()+
                "','"+owner.getPic()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
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
        	e.printStackTrace();
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
                " pic = '"+owner.getPic()+"'" +
                " WHERE id = "+owner.getId()+"; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
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
				owner.setPic(rs.getString("pic"));
				list.add(owner);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
    public List<Owner> searchOwners(String keyword, Integer start, Integer size) {
        String stmt = " SELECT * FROM owner WHERE name LIKE '%"+keyword+"%' "+
                    " ORDER BY ID LIMIT "+start+", "+size+" ;";
		return getList(stmt);
	}
    

	
	public List<Owner> getOwners(Integer start, Integer size) {
		String stmt =" SELECT * FROM owner "+
				" ORDER BY id LIMIT "+start+", "+size+" ;";
		return getList(stmt);
	}
	public List<Owner> getAllOwners() {
		String stmt =" SELECT * FROM owner ;";
		return getList(stmt);
	}
	public Integer getResultAmount(String keyword) {
		String stmt = 
                "select COUNT(*) totalcount" + 
                " from owner  WHERE name LIKE '%"+keyword+"%' ;";
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) return rs.getInt("totalcount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Owner getOwner(Integer id) {
        String stmt ="select * from owner where id="+id+" ;";
		Owner owner = new Owner();
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) {
				owner.setId(rs.getInt("ID"));
				owner.setName(rs.getString("NAME"));
				owner.setAddr(rs.getString("addr"));
				owner.setPic(rs.getString("PIC"));
				owner.setTel(rs.getString("tel"));
			}
			return owner;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getAmount() {
		String stmt = " SELECT COUNT(*) totalcount FROM Owner;";
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) return rs.getInt("totalcount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}