/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thrift;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.thrift.TException;

/**
 *
 * @author cpu11251-local
 */
public class UserServiceHandler implements UserService.Iface{

    @Override
    public String getUserById(int id, String time) throws TException {
        int theStudentId = id;
        String result = "";
        DateTimeFormatter current_time = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        time = current_time.format(now);
        
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con= DriverManager.getConnection(  
                            "jdbc:mysql://localhost:3306/hocsinh","root","");  
            PreparedStatement stmt= null;  
            
            String sql = "select * from hocsinh where id=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, theStudentId);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String email = rs.getString("email");
                    result = name + "_" + age + "_" + email + "_" + time;
            }
            con.close();  
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e);}  
        return result;
    }
    
    @Override
    public Student getFullInfo(int id) throws TException {
        int theStudentId = id;
        Student student = new Student();
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con= DriverManager.getConnection(  
                            "jdbc:mysql://localhost:3306/hocsinh","root","");  
            PreparedStatement stmt= null;  
            
            String sql = "select * from hocsinh where id=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, theStudentId);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                student.setName(name);
                student.setAge(age);
                student.setEmail(email);
            }
            con.close();  
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e);}  
        
        return student;
    }

    @Override
    public String getUser2(int id, String time) throws TException {
        int theStudentId = id;
        String result = "";
        DateTimeFormatter current_time = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        time = current_time.format(now);
        
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con= DriverManager.getConnection(  
                            "jdbc:mysql://localhost:3306/hocsinh","root","");  
            PreparedStatement stmt= null;  
            
            String sql = "select * from hocsinh where id=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, theStudentId);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String email = rs.getString("email");
                    result = name + "_" + age + "_" + email + "_" + time;
            }
            con.close();  
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e);}  
        return result;
    }


}
