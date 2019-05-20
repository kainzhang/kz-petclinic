package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Specialty;
import util.Connect;

public class SpecialtyDAO {
	public boolean insertSpecialty(Specialty specialty) {
		String stmt = 
				" BEGIN;" +
                " INSERT INTO specialty" +
                " (ID, NAME)" +
                " VALUES (null, '"+specialty.getName()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
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
        	e.printStackTrace();
            return false;
        }
        return true;
	}
	
	public boolean updateSpecialty(Specialty specialty) {
    	String stmt =
				" BEGIN;" +
                " UPDATE specialty" +
                " SET name = '"+specialty.getName()+"' " +
                " WHERE id = "+specialty.getId()+"; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		    return false;
		}
		return true;
	}
	
	public boolean insertVetSpecialty(Integer vetid, Integer specid) {
		String stmt = 
				" BEGIN;" +
				" INSERT INTO vet_spec" +
				" (vetid, specid)" +
				" VALUES ("+vetid+", "+specid+");" +
				" COMMIT; ";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean isExist (String name) {
		String stmt = " SELECT FROM specialty WHERE name = "+name+";";
		try {
			ResultSet rs;
			rs=Connect.exeQuery(stmt);
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteVetSpecialty(Integer vetid, Integer specid) {
		String stmt = 
				" BEGIN;" +
				" DELETE FROM vet_spec" +
				" WHERE vetid="+vetid+" and specid="+specid+";" +
				" COMMIT; ";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Specialty> getVetSpecialties(Integer vetid) {
		String stmt = 
				" SELECT s.id AS id, s.name AS name " +
				" FROM specialty AS s, vet_spec AS vs " +
				" WHERE vs.vetid = "+vetid+" " +
				" AND vs.specid = s.id ;";
		return getList(stmt);
	}
	
	private List<Specialty> getList(String stmt) {
		ResultSet rs;
		List<Specialty> list = new ArrayList<Specialty>();
		try {
			rs = Connect.exeQuery(stmt);
			while(rs.next()) {
				Specialty spec = new Specialty();
				spec.setId(rs.getInt("ID"));
				spec.setName(rs.getString("NAME"));
				list.add(spec);
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
