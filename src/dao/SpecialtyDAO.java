package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Specialty;
import util.Connect;

public class SpecialtyDAO {
	public boolean insertSpecialtyt(Specialty specialty) {
		String stmt = 
				" BEGIN;" +
                " INSERT INTO specialty" +
                " (ID, NAME)" +
                " VALUES" +
                " (null, '"+specialty.getName()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            return false;
		}
		return true;
	}
	
	public boolean deleteSpecialty(Integer id) {
		String stmt = 
				" BEGIN;" +
                " DELETE FROM specialty" +
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
	
	public boolean updateSpecialty(Specialty specialty) {
    	String stmt =
				" BEGIN;" +
                " UPDATE specialty" +
                " SET name = '"+specialty.getName()+"' " +
                " WHERE id = '"+specialty.getId()+"'; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
		    System.out.print("Error occurred while UPDATE Operation: " + e);
		    return false;
		}
		return true;
	}
	
	private List<Specialty> getList(String stmt) {
		ResultSet rs;
		List<Specialty> list = new ArrayList<Specialty>();
		try {
			rs = Connect.exeQuery(stmt);
			while(rs.next()) {
				Specialty specialty = new Specialty();
				specialty.setId(rs.getInt("ID"));
				specialty.setName(rs.getString("NAME"));
				list.add(specialty);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	
	public List<Specialty> searchSpecialties(String keyword) {
		String stmt = " SELECT * FROM specialty WHERE name LIKE '%"+keyword+"%'; ";
		return getList(stmt);
	}
	
	public List<Specialty> getSpecialties() {
		String stmt = " SELECT * FROM specialty; ";
		return getList(stmt);
	}
}
