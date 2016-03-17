/*
 * Copyright (C) 2016 Dietmar Steiner (open.source@d-steiner.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.mycompany.caassignment.model;

import com.mycompany.caassignment.SessionBean;
import com.mycompany.caassignment.hibernate.Member;
import com.mycompany.caassignment.hibernate.MemberHelper;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
/**
 *
 * @author Dietmar
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {
 
    private static final long serialVersionUID = 1094801825228386363L;
     
    private String pwd;
    private String msg;
    private String email;
 
    /**
     *
     * @return
     */
    public String getPwd() {
        return pwd;
    }
 
    /**
     *
     * @param pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
 
    /**
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }
 
    /**
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }
 
    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
 
    //validate login

    /**
     *
     * @return
     */
    public String validateUsernamePassword() {
        Member member = null;
        MemberHelper members = new MemberHelper();
        try {
            member = members.auth(email, pwd);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (member != null) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("user", member);
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "login";
        }
    }
 
    //logout event, invalidate session

    /**
     *
     * @return
     */
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
}