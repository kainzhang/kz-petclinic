package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vet;
import util.Connect;

public class VetDAO {
	public void insertVet(Vet vet) {
		String stmt = 
				" BEGIN;" +
                " INSERT INTO vet" +
                " (NAME)" +
                " VALUES" +
                " (null, '"+vet.getName()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
		}
	}
	
	public void deleteVet(Integer id) {
		String stmt = 
				" BEGIN;" +
                " DELETE FROM vet" +
                " WHERE id="+id+";" +
                " COMMIT;";
        try {
            Connect.exeUpdate(stmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
        }
	}
	
	public void editVet(Vet vet) {
    	String updateStmt =
				" BEGIN;" +
                " UPDATE vet" +
                " SET name = '"+vet.getName()+"' " +
                " WHERE id = '"+vet.getId()+"'; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(updateStmt);
		} catch (SQLException e) {
		    System.out.print("Error occurred while UPDATE Operation: " + e);
		}
	}
	
	public List<Vet> getVets() {
		String stmt = " SELECT * FROM vet; ";
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
}
