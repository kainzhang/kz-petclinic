package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Species;
import util.Connect;

public class SpeciesDAO {
	public boolean insertSpecies(Species species) {
		String stmt = 
				" BEGIN;" +
                " INSERT INTO species" +
                " (ID, NAME)" +
                " VALUES" +
                " (null, '"+species.getName()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            return false;
		}
		return true;
	}
	
	public boolean deleteSpecies(Integer id) {
		String stmt = 
				" BEGIN;" +
                " DELETE FROM species" +
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
	
	public boolean updateSpecies(Species species) {
    	String stmt =
				" BEGIN;" +
                " UPDATE Species" +
                " SET name = '"+species.getName()+"' " +
                " WHERE id = "+species.getId()+"; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
		    System.out.print("Error occurred while UPDATE Operation: " + e);
		    return false;
		}
		return true;
	}
	
	private List<Species> getList(String stmt) {
		ResultSet rs;
		List<Species> list = new ArrayList<Species>();
		try {
			rs = Connect.exeQuery(stmt);
			while(rs.next()) {
				Species species = new Species();
				species.setId(rs.getInt("ID"));
				species.setName(rs.getString("NAME"));
				list.add(species);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	public List<Species> searchSpecies(String keyword) {
		String stmt = " SELECT * FROM Species WHERE name LIKE '%"+keyword+"%'; ";
		return getList(stmt);
	}
	
	public List<Species> getSpecies() {
		String stmt = " SELECT * FROM Species; ";
		return getList(stmt);
	}
}
