/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserHelper {
    private Connection con;
    private PreparedStatement prestmt;
    private ResultSet result;
    private String sql="";
    
    
    public void AddUser(User u){
        try {
            con=DB.OpenConnection();
            sql = "insert into user(firstname,lastname,email,password,pet) values(?,?,?,?,?)";
            prestmt= con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(u.getFirstname());
            params.add(u.getLastname());
            params.add(u.getEmail());
            params.add(u.getPassword());
            params.add(u.getPet());
            
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void DeleteUser(int id){
        try {
            con = DB.OpenConnection();
            sql = "delete form user where id=?";
            prestmt = con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(id);
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<User> SelectAll(){
        ArrayList<User> users = new ArrayList<User>();
        con = DB.OpenConnection();
        sql = "select * from user";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = DB.ExecuteQuery(prestmt, null);
        User user = new User();
        try {
            while(result.next()){
                user.setId(result.getInt("id"));
                user.setFirstname(result.getString("firstname"));
                user.setLastname(result.getString("lastname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setPet(result.getString("pet"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public void UpdateUser(User u){
        try {
            con = DB.OpenConnection();
            sql="update user set firstname=?, lastname=?,email=?,password=? where id=?";
            prestmt = con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(u.getFirstname());
            params.add(u.getLastname());
            params.add(u.getEmail());
            params.add(u.getPassword());
            params.add(u.getPet());
            params.add(u.getId());
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User GetUser(int id){
        User u = new User();
        con = DB.OpenConnection();
        sql = "select * from user where id=?";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(id);
        result = DB.ExecuteQuery(prestmt, params);
        try {
            if(result.next()){
                u.setId(result.getInt("id"));
                u.setFirstname(result.getString("firstname"));
                u.setLastname(result.getString("lastname"));
                u.setEmail(result.getString("email"));
                u.setPassword(result.getString("password"));
                u.setPet(result.getString("pet"));
            }else {
                u = null;
            }
        } catch (SQLException ex) {
            u=null;
        }
            
        return u;
    }
    
    public User IsExist (String email, String password){
        User user = new User();
        con = DB.OpenConnection();
        sql= "select * from user where email=? and password=?";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(email);
        params.add(password);
        result = DB.ExecuteQuery(prestmt, params);
        try {
            if(result.next()){
                user.setId(result.getInt("id"));
                user.setFirstname(result.getString("firstname"));
                user.setLastname(result.getString("lastname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setPet(result.getString("pet"));
            } else{
                user = null;
            }
        } catch (SQLException ex) {
            //user=null;
        }
        return user;
    }
    
    public User IsExist (String email){
        User user = new User();
        con = DB.OpenConnection();
        sql= "select * from user where email=?";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(email);
        result = DB.ExecuteQuery(prestmt, params);
        try {
            if(result.next()){
                user.setId(result.getInt("id"));
                user.setFirstname(result.getString("firstname"));
                user.setLastname(result.getString("lastname"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setPet(result.getString("pet"));
            } else{
                user = null;
            }
        } catch (SQLException ex) {
            //user=null;
        }
        return user;
    }
    public User getUser_sub(int subId)
    {
        User u = new User();
        int auserid=-1;
        con = DB.OpenConnection();
        sql = "select userid from subject where id=?";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(subId);
        result = DB.ExecuteQuery(prestmt, params);
        try{
            if(result.next()){
                auserid=result.getInt("userid");
            }
        }catch(SQLException ex){
            System.out.println("7aga 8alat");
            auserid=-1;
        }
        u=this.GetUser(auserid);
        return u;
    }
    public Boolean check_mail_pet(String mail,String pet)
    {
        User user = new User();
        con = DB.OpenConnection();
        sql= "select * from user where email=? and pet=?";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(mail);
        params.add(pet);
        result = DB.ExecuteQuery(prestmt, params);
        try {
            if(result.next()){
               return true;
            } else{
                return false;
            }
        } catch (SQLException ex) {
        }
       return false;
    }
    public void update_pass(String mail,String pass)
    {
        try {
            con = DB.OpenConnection();
            sql="update user set password=? where email=?";
            prestmt = con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(pass);
            params.add(mail);
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
