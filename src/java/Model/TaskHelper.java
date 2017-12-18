/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TaskHelper {
    private Connection con;
    private PreparedStatement prestmt;
    private ResultSet result;
    String sql="";
    
    public void AddTask(Task t,int subid){
        try {
            con=DB.OpenConnection();
            sql = "insert into task (task,subjectid) values(?,?)";
            prestmt= con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(t.getTask());
            params.add(subid);
            
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void UpdateTask(Task t){
        try {
            con = DB.OpenConnection();
            sql="update task set task=? where id=?";
            prestmt = con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(t.getTask());
            params.add(t.getId());
            
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DeleteTask(int id){
        try {
            con = DB.OpenConnection();
            sql = "delete from task where id=?";
            prestmt = con.prepareStatement(sql);
            ArrayList<Object> params = new ArrayList<Object>();
            params.add(id);
            DB.ExecuteNonQuery(prestmt, params);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ArrayList<Task> SelectAll(int subid){
        ArrayList<Task> task = new ArrayList<Task>();
        con = DB.OpenConnection();
        sql = "select * from task where subjectid=?";
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(subid);
        result = DB.ExecuteQuery(prestmt, params);
        
        try {
            while(result.next()){
                Task t = new Task();
                t.setId(result.getInt("id"));
                t.setTask(result.getString("task"));
                
                task.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return task;
    }
    
}
