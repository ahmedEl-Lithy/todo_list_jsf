/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Task;
import Model.TaskHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class TaskController {
    
    int SubjectID;
    TaskHelper tskhelper = new TaskHelper();
    Task tsk = new Task();
    int tskID;
    
    @PostConstruct
    public void init() {
        SubjectID =Integer.parseInt( FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("subID"));
    }
    
     public void addtask(){
        tskhelper.AddTask(tsk, SubjectID);
        clearme();
    }
     
     public ArrayList<Task> showalltsk(){
        
        
        return tskhelper.SelectAll(SubjectID);
        
    }
     public void clearme(){
         tsk = new Task();
     }
     
     public void sendid(int id){
         tskID = id;
         clearme();
     }
     
     public void updatetask(){
         tsk.setId(tskID);
         tskhelper.UpdateTask(tsk);
         clearme();
     }
     
     
    public void deletetask(int id){
             tskhelper.DeleteTask(id);
             clearme();
     }
    
//    ------------------------- Get& Set------------------------------
    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public Task getTsk() {
        return tsk;
    }

    public void setTsk(Task tsk) {
        this.tsk = tsk;
    }

    
}
