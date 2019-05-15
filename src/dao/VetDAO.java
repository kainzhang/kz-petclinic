package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vet;
import util.Connect;

public class VetDAO {
	public boolean insertVet(Vet vet) {
		String stmt = 
				" BEGIN;" +
                " INSERT INTO vet" +
                " (ID, NAME)" +
                " VALUES" +
                " (null, '"+vet.getName()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            return false;
		}
		return true;
	}
	
	public boolean deleteVet(Integer id) {
		String stmt = 
				" BEGIN;" +
                " DELETE FROM vet" +
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
	
	public boolean updateVet(Vet vet) {
    	String stmt =
				" BEGIN;" +
                " UPDATE vet" +
                " SET name = '"+vet.getName()+"' " +
                " WHERE id = "+vet.getId()+"; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
		    System.out.print("Error occurred while UPDATE Operation: " + e);
		    return false;
		}
		return true;
	}
	
	private List<Vet> getList(String stmt) {
		ResultSet rs;
		List<Vet> list = new ArrayList<Vet>();
		try {
			rs = Connect.exeQuery(stmt);
			while(rs.next()) {
				Vet vet = new Vet();
				vet.setId(rs.getInt("ID"));
				vet.setName(rs.getString("NAME"));
				list.add(vet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	
	public List<Vet> searchVets(String keyword) {
		String stmt = " SELECT * FROM vet WHERE name LIKE '%"+keyword+"%'; ";
		return getList(stmt);
	}
	
	public List<Vet> getVets() {
		String stmt = " SELECT * FROM vet; ";
		return getList(stmt);
	}
}
