package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pet;
import util.Connect;

public class PetDAO {
	public boolean insertPet(Pet pet) {
		String stmt = 
				" BEGIN;" +
                " INSERT INTO pet" +
                " VALUES" +
                " (null, '"+pet.getName()+"', " +
                " '"+pet.getBday()+"'," +
                " "+pet.getSpeciesId()+", " +
                " "+pet.getOwnerId()+", " +
                " '"+pet.getPic()+"' );" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
            return false;
		}
		return true;
	}
	
	public boolean deletePet(Integer id) {
		String stmt = 
				" BEGIN;" +
                " DELETE FROM pet" +
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
	
	public boolean wipePets(Integer speciesid) {
		String stmt =
				" BEGIN;" +
                " DELETE FROM pet" +
                " WHERE speciesid="+speciesid+";" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updatePet(Pet pet) {
    	String stmt =
				" BEGIN;" +
                " UPDATE pet" +
                " SET name = '"+pet.getName()+"' ," +
                " BDAY = '"+pet.getBday()+"' ," +
                " pic = '"+pet.getPic()+"'," +
                " speciesid = "+pet.getSpeciesId()+"," +
                " ownerid = "+pet.getOwnerId()+" " +
                " WHERE id = "+pet.getId()+"; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		    return false;
		}
		return true;
	}
	
	private List<Pet> getList(String stmt) {
		ResultSet rs;
		List<Pet> list = new ArrayList<Pet>();
		try {
			rs = Connect.exeQuery(stmt);
			while(rs.next()) {
				Pet pet = new Pet();
				pet.setId(rs.getInt("ID"));
				pet.setName(rs.getString("NAME"));
				pet.setBday(rs.getString("BDAY"));
				pet.setPic(rs.getString("PIC"));
				pet.setOwnerId(rs.getInt("OWNERID"));
				pet.setOwner(rs.getString("OWNER"));
				pet.setSpeciesId(rs.getInt("SPECIESID"));
				pet.setSpecies(rs.getString("SPECIES"));
				list.add(pet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	
	public List<Pet> searchPets(String keyword, Integer start, Integer end) {
		String stmt = "select p.ID as id,p.NAME as name,p.BDAY as bday,o.ID as ownerid,o.name as owner,s.id as speciesid,s.name as species,p.PIC as pic" + 
				" from (pet as p inner join owner as o on p.OWNERID=o.Id)" + 
				" inner join species as s on p.SPECIESID=s.id" + 
				" where p.name like '%"+keyword+"%' OR s.name LIKE '%"+keyword+"%' " +
				" ORDER BY p.ID LIMIT "+start+", "+end+" ;";
		return getList(stmt);
	}
	
	public List<Pet> getPets(Integer start, Integer end) {
		String stmt = " select p.ID as id,p.NAME as name,p.BDAY as bday,o.ID as ownerid,o.name as owner,s.id as speciesid,s.name as species,p.PIC as pic" + 
				" from (pet as p inner join owner as o on p.OWNERID=o.Id)" + 
				" inner join species as s on p.SPECIESID=s.id " +
				" ORDER BY p.ID LIMIT "+start+", "+end+" ;";
		return getList(stmt);
	}
	
	public Integer getResultAmount(String keyword) {
		String stmt = 
				"select COUNT(*) totalcount" + 
				" from (pet as p inner join owner as o on p.OWNERID=o.Id)" + 
				" inner join species as s on p.SPECIESID=s.id " + 
				" where p.name like '%"+keyword+"%' OR s.name LIKE '%"+keyword+"%' ;";
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) return rs.getInt("totalcount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Pet getPet(Integer id) {
		String stmt =
				"select p.ID as id,p.NAME as name,p.BDAY as bday,o.ID as ownerid, " + 
				" o.name as owner,s.id as speciesid,s.name as species,p.PIC as pic" + 
				" from (pet as p inner join owner as o on p.OWNERID=o.Id)" + 
				" inner join species as s on p.SPECIESID=s.id" + 
				" WHERE p.ID="+id+" ;";
		Pet pet = new Pet();
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) {
				pet.setId(rs.getInt("ID"));
				pet.setName(rs.getString("NAME"));
				pet.setBday(rs.getString("BDAY"));
				pet.setPic(rs.getString("PIC"));
				pet.setOwnerId(rs.getInt("OWNERID"));
				pet.setOwner(rs.getString("OWNER"));
				pet.setSpeciesId(rs.getInt("SPECIESID"));
				pet.setSpecies(rs.getString("SPECIES"));
			}
			return pet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getAmount() {
		String stmt = " SELECT COUNT(*) totalcount FROM PET;";
		try {
			ResultSet rs = Connect.exeQuery(stmt);
			if(rs.next()) return rs.getInt("totalcount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}