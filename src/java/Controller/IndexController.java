/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import Model.UserHelper;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class IndexController { //login controller
    User user = new User();
    UserHelper userhelper;
    
    public String login(){
        User user1 = new User();
        userhelper = new UserHelper();
        user1 = userhelper.IsExist(user.getEmail(), user.getPassword());
        if(user1 !=null){
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("usersession", user1);
            return "Welcome.xhtml?faces-redirect=true";
        }
        return "index.xhtml?faces-redirect=true";
    }
    public String regisiter(){
        return "Regisiter.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
