package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Sqlite
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:E:\\eclipse-workspace\\lokka-petclinic\\db\\data.db");
      
      stmt = c.createStatement();
      String drop = "DROP TABLE vet";

      String sql5 = " CREATE TABLE USER" +
    		  		 " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		         " USERNAME       TEXT     NOT NULL," +
    		  		 " PASSWORD       TEXT     NOT NULL," +
    		         " POSITION       INT      NOT NULL," +
    		         " CHECK(POSITION>=0 AND POSITION<=2));";

      String sql6= " CREATE TABLE VET " +
    		  	   " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		  	   " NAME          TEXT   NOT NULL);";
      
      
      String sql9= " CREATE TABLE vet_spec "+
    		  	   " (vetid   INTEGER       NOT NULL," +
    		  	   " specid   INTEGER       NOT NULL," +
    		  	   " FOREIGN KEY(vetid)  REFERENCES VET(id)," +
    		  	   " FOREIGN KEY(specid) REFERENCES SPECIALTY(id));";
      
      String sql8="INSERT INTO USER VALUES(null,'kun','pass','2');";
      
      String sql82="UPDATE VET SET name='lokka' WHERE id = 1;";
      
      String sql20 = " CREATE TABLE SPECIALTY" +
		  		 " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
		         " NAME       TEXT     NOT NULL );";
      
      String sql21="insert into SPECIALTY values(null,'小型动物');";
      
      String sql22 = " CREATE TABLE species" +
		  		 " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
		         " NAME       TEXT     NOT NULL );";
      
      String sql23 = "CREATE TABLE owner"+
              " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
              " NAME         TEXT            NOT NULL," +
              " TEL          TEXT            NOT NULL," +
              " ADDR         TEXT           NOT NULL);";

      
      String sql24 = "CREATE TABLE pet"+
              " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
              " NAME         TEXT            NOT NULL," +
              " BDAY         TEXT            NOT NULL," +
              " SPECIESID    INTEGER         NOT NULL," +
              " OWNERID      INTEGER         NOT NULL," +
              " PIC          TEXT            NOT NULL,"
              + "FOREIGN KEY(SPECIESID) REFERENCES SPECIES(ID),"
              + "FOREIGN KEY(OWNERID) REFERENCES OWNER(ID) );";

      String sql55 = "CREATE TABLE record " +
              "(id  INTEGER  PRIMARY KEY AUTOINCREMENT," +
              " petid          INTEGER    NOT NULL," +
              " vetid          INTEGER    NOT NULL," +
              " date            TEXT    NOT NULL," +
              " descr           TEXT    NOT NULL," 
              + "FOREIGN KEY(petID) REFERENCES pet(ID),"
 			  + " FOREIGN KEY(vetID) REFERENCES vet(ID));";
      
      stmt.executeUpdate(sql55);
  
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("success");
  }
}
