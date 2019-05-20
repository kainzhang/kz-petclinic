package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Record;
import util.Connect;

public class RecordDAO {
	public boolean insertRecord(Record record) {
		String stmt = 
				" BEGIN;" +
                " INSERT INTO record" +
                " VALUES" +
				" (null, "+record.getPetid()+","
				+record.getVetid()+",datetime('now','localtime'),'"+record.getDescr()+"');" +
                " COMMIT;";
		try {
			Connect.exeUpdate(stmt);
		} catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            return false;
		}
		return true;
	}
	
	public boolean deleteRecord(Integer id) {
		String stmt = 
				" BEGIN;" +
                " DELETE FROM record" +
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
	
	public boolean updateRecord(Record record) {
    	String stmt =
				" BEGIN;" +
                " UPDATE Record" +
				" SET petid = "+record.getPetid()+" ," +
				" vetid = "+record.getVetid()+" ," +
				" date = '"+record.getDate()+"' ," +
				" descr = '"+record.getDescr()+"' " +
                " WHERE id = "+record.getId()+"; " +
                " COMMIT;";
		try {
		    Connect.exeUpdate(stmt);
		} catch (SQLException e) {
		    System.out.print("Error occurred while UPDATE Operation: " + e);
		    return false;
		}
		return true;
	}
	
	private List<Record> getList(String stmt) {
		ResultSet rs;
		List<Record> list = new ArrayList<Record>();
		try {
			rs = Connect.exeQuery(stmt);
			while(rs.next()) {
				Record record = new Record();
				record.setId(rs.getInt("ID"));
				record.setVetid(rs.getInt("vetid"));
				record.setPetid(rs.getInt("petid"));
				record.setDate(rs.getString("date"));
				record.setDescr(rs.getString("descr"));
				record.setPetname(rs.getString("Petname"));
				record.setVetname(rs.getString("Vetname"));
				list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	public List<Record> searchRecord(String keyword) {
		String stmt = "select r.*,p.NAME as petname,v.NAME as vetname from (record as r inner join pet as p on r.petid=p.ID)"
		+" inner join vet as v on r.vetid=v.ID "
		+" where r.descr like '%"+keyword+"%' or p.name like '%"+keyword+"%';";
		return getList(stmt);
	}
	
	public List<Record> getRecord() {
		String stmt = " select r.*,p.NAME as petname,v.NAME as vetname from (record as r inner join pet as p on r.petid=p.ID) inner join vet as v on r.vetid=v.ID; ";
		return getList(stmt);
	}
}
