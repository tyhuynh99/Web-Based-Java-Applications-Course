/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.models;

import java.io.Serializable;
import java.util.List;
import tyhp.daos.AccountDAO;
import tyhp.dtos.Account;

/**
 *
 * @author ACER
 */
public class AccountBean implements Serializable {

    private String username, password;
    private String role;
    private Account dto;

    public AccountBean() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getDto() {
        return dto;
    }

    public void setDto(Account dto) {
        this.dto = dto;
    }

    public String checkLogin() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.checkLogin(username, password);
    }

    public Account getAccount() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.getAccount(username, password);
    }

    public List<Account> getAccountByRole() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.getAccountByRole(role);
    }

    public boolean insertAccount() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.insertAccount(dto);
    }

    public boolean checkDuplicate() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.checkDuplicate(username);
    }

    public boolean update() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.update(dto);
    }

    public boolean delete() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.delete(username);
    }

    public boolean changePassword() throws Exception {
        AccountDAO dao = new AccountDAO();
        return dao.changePassword(username, password);
    }
}
