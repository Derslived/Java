/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.entity;

import java.util.Date;

/**
 *
 * @author Derslived
 */
public class User extends MasterEntity {

    private String email, password;
    private Date createdDate, modifiedDate;
    private boolean status;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String email, String password, boolean status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", password=" + password + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", status=" + status + '}';
    }

}
