/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Subject;
import Model.SubjectHelper;
import Model.Task;
import Model.TaskHelper;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class WelcomeController {
    
    User user = new User();
    Subject subject = new Subject();
    int subjectID;
    
    SubjectHelper subjecthelper = new SubjectHelper();

    
    public void addsubject(){
        subjecthelper.AddSubject(subject, user.getId());
        clearme();
    }
    
   
    
    public ArrayList<Subject> showallsub(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        user =(User) sessionMap.get("usersession");
        return subjecthelper.SelectAll(user.getId());
        
    }
    
    
    public String showtasksevent(int s){
        return "Tasks.xhtml?faces-redirect=true subID="+s;
    }
    
    public void clearme(){
        subject = new Subject();
    }
    
    public void sendID(int id){
        subjectID = id;
        clearme();
    }
    public void updateSub(){
        subjecthelper.UpdateSubject(subject.getSubject(), subjectID);
        clearme();
    }
    public void deleteSub(int id){
        subjecthelper.DeleteSubject(id);
    }
    
    //****************************** GET & SET******************************
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
}
