//package util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class Sqlite
//{
//  public static void main( String args[] )
//  {
//    Connection c = null;
//    Statement stmt = null;
//    try {
//      Class.forName("org.sqlite.JDBC");
//      c = DriverManager.getConnection("jdbc:sqlite::resource:data.db");
//      
//      stmt = c.createStatement();
//      String drop = "DROP TABLE user";
//      String sql = "CREATE TABLE BOOK " +
//                   "(ISBN  TEXT  PRIMARY KEY NOT NULL," +
//                   " TITLE           TEXT    NOT NULL," +
//                   " AUTHOR          TEXT    NOT NULL," +
//                   " PRESS           TEXT    NOT NULL," +
//                   " CID             TEXT    NOT NULL," +
//                   " PDATE           TEXT    NOT NULL," +
//                   " QUANTITY        INT     NOT NULL," +
//      			   " PRICING         REAL    NOT NULL," +
//                   " LOWERLIMIT      INT             ," +
//      			   " FOREIGN KEY(CID) REFERENCES CATEGORY(CID));";
//      
//      String sql2 = "CREATE TABLE SNP"+
//                   " (SNPID INTEGER PRIMARY KEY AUTOINCREMENT," +
//                   " NAME      TEXT            NOT NULL," +
//                   " OWNER     TEXT            NOT NULL," +
//                   " OTITLE    TEXT            NOT NULL," +
//                   " TEL       TEXT            NOT NULL," +
//                   " ADDR      TEXT            NOT NULL," +
//                   " BANK      TEXT                    ," +
//                   " BANKID    TEXT                    ," +
//                   " SIGN      INT             NOT NULL," +
//                   " CHECK(SIGN=1 OR SIGN=2));";
//      
//      String sql3 = "CREATE TABLE ORDERINFO"+
//    		  		"(ORDERID INTEGER PRIMARY KEY AUTOINCREMENT," +
//    		  		"ISBN     TEXT           NOT NULL," +
//    		  		"QUANTITY INT            NOT NULL," +
//    		  		"unitPRICE    REAL           NOT NULL," +
//    		  		"AMOUNT   REAL           NOT NULL," +
//    		  		"DATE     TEXT           NOT NULL," +
//    		  		"SNPID    INTEGER        NOT NULL," +
//    		  		"USERNAME TEXT           NOT NULL," +
//    		  		"SIGN     INT            NOT NULL," +
//    		  		"FOREIGN KEY(ISBN) REFERENCES BOOK(ISBN) ," +
//    		  		"FOREIGN KEY(SNPID) REFERENCES SNP(SNPID)," +
//    		  		"CHECK(SIGN=1 OR SIGN=2));";
//      
//      String sql5 = " CREATE TABLE USER" +
//    		  		 " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
//    		         " USERNAME       TEXT     NOT NULL," +
//    		  		 " PASSWORD       TEXT     NOT NULL," +
//    		         " POSITION       INT      NOT NULL," +
//    		         " CHECK(POSITION>=0 AND POSITION<=2));";
//      
////      String sql5="create table user "+
////	    		  "(username text not null,"+
////	    		  "password text not null,"+
////	    		  "position int not null,"+
////	    		  "check(position>=0 and position<=2));";
//
//      String sql6= " CREATE TABLE ALERTINFO " +
//    		  	   " (AID INTEGER PRIMARY KEY AUTOINCREMENT," +
//    		  	   " ISBN          TEXT   NOT NULL," +
//    		  	   " DETAIL        TEXT   NOT NULL," +
//    		  	   " DATE          TEXT   NOT NULL," +
//    		  	   " ISVALID       INT    NOT NULL," +
//    		  	   " FOREIGN KEY(ISBN) REFERENCES BOOK(ISBN),"+
//    		  	   " CHECK(ISVALID = 1 OR ISVALID=0));";
//      
//      
//      String sql9= " CREATE TABLE CATEGORY "+
//    		  	   " (CID   INTEGER PRIMARY KEY AUTOINCREMENT, "+
//    		  	   " CNAME       TEXT          NOT NULL      );";
////      
//      String sql8="INSERT INTO USER VALUES(null,'user','pass','2')";
//      
//      stmt.executeUpdate(sql8);
//      stmt.close();
//      c.close();
//    } catch ( Exception e ) {
//      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//      System.exit(0);
//    }
//    System.out.println("successfully");
//  }
//}
