/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import Model.UserHelper;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class GetPassContorller {
    User user = new User();
    UserHelper userhelper;

    public String changepass(){
        userhelper = new UserHelper();
        userhelper.update_pass(user.getEmail(), user.getPassword());
        return "index.xhtml?faces-redirect=true";
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
