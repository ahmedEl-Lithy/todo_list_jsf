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
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class SubjectHelper {
    private Connection con;
    private PreparedStatement prestmt;
    private ResultSet result;
    String sql="";
    
    public void AddSubject(Subject subject,int userid){
        try {
            con = DB.OpenConnection();
            sql = "insert into subject(subject,userid) values(?,?)";
            prestmt = con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(subject.getSubject()); 
            params.add(userid);
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void UpdateSubject(String sub ,int id){
        try {
            con = DB.OpenConnection();
            sql = "update subject set subject=? where id=?";
            prestmt = con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(sub);
            params.add(id);
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Subject IsExist(String sub){
        Subject subject = new Subject();
        con = DB.OpenConnection();
        sql = "select * from subject where subject=?";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(sub);
        result = DB.ExecuteQuery(prestmt, params);
        try {
            if(result.next()){
                subject.setId(result.getInt("id"));
                subject.setSubject(result.getString("subject"));
            }else{
                subject =null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subject;
    }
    
    public void DeleteSubject(int id){
        try {
            con = DB.OpenConnection();
            sql = "delete from subject where id=?";
            prestmt = con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(id);
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "cant delete");
        }
        
    }
    
    public ArrayList<Subject> SelectAll(int userid){
        ArrayList<Subject> sub = new ArrayList<Subject>();
        con = DB.OpenConnection();
        sql = "select * from subject where userid=?";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(userid);
        result = DB.ExecuteQuery(prestmt, params);
        
        try {
            while(result.next()){
                Subject s = new Subject();
                s.setId(result.getInt("id"));
                s.setSubject(result.getString("subject"));
                
                sub.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sub;
    }
    
}
