/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class user extends pf{

    public String name;
    public String pass;
    public String qa;
    public LinkedList<book> books = new LinkedList<>();

static    Connection conect=null;
static    Statement sta=null;
static    PreparedStatement  per=null;
static    ResultSet rs=null;
   
public user(String n, String p, String q) {
        name = n;
        pass = p;
        qa = q;

    }
   public static void loadu(LinkedList<user> u){
            try {
   
                conect=DriverManager.getConnection("jdbc:mysql://localhost:3306/kids","root","01289882588");
        sta=conect.createStatement();  
     if (conect!=null) System.out.println("con   nem   ct");
      //  sta.executeUpdate( "SELECT * FROM users");
     rs=sta.executeQuery("SELECT * FROM kuser");
        while(rs.next()){
                String name = rs.getString("kname");
                String pass = rs.getString("kpass");
                String date = rs.getString("kdata");
              user use=new user(name, pass, date);
              u.add(use);
        }
     
        rs.close();   
        sta.close(); conect.close();
    }catch (SQLException ex) {
        System.err.println("error in database"); 
    }
     
   } 
    
    public static void deleteu()
    {           
         try {
         //("DELETE FROM user WHERE pass ='"+pass+"'");
               conect=DriverManager.getConnection("jdbc:mysql://localhost:3306/kids","root","01289882588");
        sta=conect.createStatement();  
       sta.executeUpdate("DELETE FROM kuser" );
       // rs=sta.executeQuery(qu);
        //rs.close();   
        sta.close(); conect.close();
         } catch (SQLException ex) {
             System.out.println("error in delete");
            
         }
    }
   
   
   public static void saveu (LinkedList<user> k){
   try {
        conect=DriverManager.getConnection("jdbc:mysql://localhost:3306/kids","root","01289882588");
        sta=conect.createStatement();  
     
       for (int i=0;i<k.size();i++){   
      sta.executeUpdate("insert into kuser(kname,kpass,kdata) values('"+k.get(i).name+"','"+k.get(i).pass+"','"+k.get(i).qa+"')");
       }
        sta.close(); conect.close();
    }catch (SQLException ex) {
        System.err.println("error in data base");
    }
   }
}
