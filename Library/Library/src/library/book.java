
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author Kerlos
 */
public class book {

    public String name, author,releasedate,posb;
    
  static   Connection conect=null;
 static   Statement sta=null;
 static   PreparedStatement  per=null;
 static   ResultSet rs=null;
    public book(String n,String a,String rd,String pb){
        name=n;
        author=a;
        releasedate=rd;
        posb=pb;
        
    }

 public static void loadk(LinkedList<book> b){
            try {
   conect=DriverManager.getConnection("jdbc:mysql://localhost:3306/kids","root","01289882588");
        sta=conect.createStatement();  
     if (conect!=null) System.out.println("connect");
     rs=sta.executeQuery("SELECT * FROM book");
        while(rs.next()){
                String name = rs.getString("Bname");
                String author = rs.getString("author");
                String releasedate = rs.getString("releasedate");
                String posb = rs.getString("posb");            
       book use=new book(name, author, releasedate,posb);
              b.add(use);
        }
        rs.close();   
        sta.close(); conect.close();
    }catch (SQLException ex) {
        System.err.println("error in database"); 
    }
     
   }
  public  static void deletek()
    {           
         try {
               conect=DriverManager.getConnection("jdbc:mysql://localhost:3306/kids","root","01289882588");
        sta=conect.createStatement();  
       sta.executeUpdate("DELETE FROM book" );
    
       sta.close(); conect.close();
         } catch (SQLException ex) {
             System.out.println("error in delete");
            
         }
    }
public static void savek (LinkedList<user> k){
   
   try {
        conect=DriverManager.getConnection("jdbc:mysql://localhost:3306/kids","root","01289882588");
        sta=conect.createStatement();  
   for (int i=0; i<k.size();i++){
       for(int j=0;j<k.get(i).books.size();j++){
        sta.executeUpdate("insert into book(Bname,author,releasedate,posb) values('"+k.get(i).books.get(j).name+"','"+k.get(i).books.get(j).author+"','"+k.get(i).books.get(j).releasedate+"','"+k.get(i).books.get(j). posb+"')");
       }
       }
       
        sta.close(); conect.close();
    }catch (SQLException ex) {
        System.err.println("error in data base");
    }
   }

}

