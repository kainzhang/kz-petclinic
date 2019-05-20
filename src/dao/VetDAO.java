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
                " (ID, NAME, PIC)" +
                " VALUES" +
                " (null, '"+vet.getName()+"', '"+vet.getPic()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
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
        	e.printStackTrace();
            return false;
        }
        return true;
	}
	
	public boolean updateVet(Vet vet) {
    	String stmt =
				" BEGIN;" +
                " UPDATE vet" +
                " SET name = '"+vet.getName()+"', " +
                " pic= '"+vet.getPic()+"' " +
                " WHERE id = "+vet.getId()+"; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
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
				vet.setPic(rs.getString("PIC"));
				list.add(vet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	public List<Vet> searchVets(String keyword, Integer start, Integer end) {
		String stmt = " SELECT * FROM vet WHERE name LIKE '%"+keyword+"%' " +
					  " ORDER BY ID LIMIT "+start+", "+end+" ;";
		return getList(stmt);
	}
	
	public Integer getResultAmount(String keyword) {
		String stmt = 
				" SELECT COUNT(*) totalcount FROM vet WHERE name LIKE '%"+keyword+"%';";
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) return rs.getInt("totalcount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Vet getVet(Integer id) {
		String stmt = " SELECT * FROM vet WHERE id="+id+"; ";
		Vet vet = new Vet();
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) {
				vet.setId(rs.getInt("ID"));
				vet.setName(rs.getString("NAME"));
				vet.setPic(rs.getString("PIC"));
			}
			return vet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Vet> getVets(Integer start, Integer end) {
		String stmt = " SELECT * FROM vet " +
					  " ORDER BY id LIMIT "+start+", "+end+" ;";
		return getList(stmt);
	}
	
	public Integer getAmount() {
		String stmt = " SELECT COUNT(*) totalcount FROM vet;";
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) return rs.getInt("totalcount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public List<Vet> getAllVets(){
		String stmt="select *  from vet;";
		return getList(stmt);
	}
}
