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
      String sql = "CREATE TABLE BOOK " +
                   "(ISBN  TEXT  PRIMARY KEY NOT NULL," +
                   " TITLE           TEXT    NOT NULL," +
                   " AUTHOR          TEXT    NOT NULL," +
                   " PRESS           TEXT    NOT NULL," +
                   " CID             TEXT    NOT NULL," +
                   " PDATE           TEXT    NOT NULL," +
                   " QUANTITY        INT     NOT NULL," +
      			   " PRICING         REAL    NOT NULL," +
                   " LOWERLIMIT      INT             ," +
      			   " FOREIGN KEY(CID) REFERENCES CATEGORY(CID));";
      
      String sql2 = "CREATE TABLE SNP"+
                   " (SNPID INTEGER PRIMARY KEY AUTOINCREMENT," +
                   " NAME      TEXT            NOT NULL," +
                   " OWNER     TEXT            NOT NULL," +
                   " OTITLE    TEXT            NOT NULL," +
                   " TEL       TEXT            NOT NULL," +
                   " ADDR      TEXT            NOT NULL," +
                   " BANK      TEXT                    ," +
                   " BANKID    TEXT                    ," +
                   " SIGN      INT             NOT NULL," +
                   " CHECK(SIGN=1 OR SIGN=2));";
      
      String sql3 = "CREATE TABLE ORDERINFO"+
    		  		"(ORDERID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		  		"ISBN     TEXT           NOT NULL," +
    		  		"QUANTITY INT            NOT NULL," +
    		  		"unitPRICE    REAL       NOT NULL," +
    		  		"AMOUNT   REAL           NOT NULL," +
    		  		"DATE     TEXT           NOT NULL," +
    		  		"SNPID    INTEGER        NOT NULL," +
    		  		"USERNAME TEXT           NOT NULL," +
    		  		"SIGN     INT            NOT NULL," +
    		  		"FOREIGN KEY(ISBN) REFERENCES BOOK(ISBN) ," +
    		  		"FOREIGN KEY(SNPID) REFERENCES SNP(SNPID)," +
    		  		"CHECK(SIGN=1 OR SIGN=2));";
      
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
//      
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

      
      String name ="name";
      stmt.executeUpdate(sql24);
//      for(int i=0;i<10;i++) {
//    	  String sql81="INSERT INTO vet VALUES(null,"+i+" );";
//    	  stmt.executeUpdate(sql81);
//      }
      
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("success");
  }
}
