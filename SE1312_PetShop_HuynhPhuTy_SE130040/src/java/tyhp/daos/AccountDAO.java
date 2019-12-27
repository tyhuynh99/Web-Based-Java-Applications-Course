/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import tyhp.dbutils.MyConnection;
import tyhp.dtos.Account;

/**
 *
 * @author ACER
 */
public class AccountDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preSt = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preSt != null) {
            preSt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select Role From Account Where Username = ? and Password = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, username);
                preSt.setString(2, password);
                rs = preSt.executeQuery();
                if (rs.next()) {
                    role = rs.getString("Role");
                }
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public Account getAccount(String username, String password) throws Exception {
        Account dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select Fullname, Email, Birthdate, Role From Account Where Username = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, username);
                rs = preSt.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("Fullname");
                    String email = rs.getString("Email");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = rs.getDate("Birthdate");
                    String birthdate = sdf.format(date);
                    String role = rs.getString("Role");
                    dto = new Account(username, password, fullname, email, role, birthdate);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<Account> getAccountByRole(String role) throws Exception {
        List<Account> list = null;
        Account dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select Username, Fullname, Email, Birthdate From Account Where Role = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, role);
                rs = preSt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String fullname = rs.getString("Fullname");
                    String email = rs.getString("Email");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = rs.getDate("Birthdate");
                    String birthdate = sdf.format(date);
                    dto = new Account();
                    dto.setUsername(username);
                    dto.setFullname(fullname);
                    dto.setEmail(email);
                    dto.setBirthdate(birthdate);
                    dto.setRole(role);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insertAccount(Account dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into Account(Username, Password, Fullname, Email, Birthdate, Role) values(?,?,?,?,?,?)";
                preSt = conn.prepareCall(sql);
                preSt.setString(1, dto.getUsername());
                preSt.setString(2, dto.getPassword());
                preSt.setString(3, dto.getFullname());
                preSt.setString(4, dto.getEmail());
                String[] d = dto.getBirthdate().split("/");
                Date date = new Date(Integer.parseInt(d[2]) - 1900, Integer.parseInt(d[1]) - 1, Integer.parseInt(d[0]));
                preSt.setDate(5, date);
                preSt.setString(6, dto.getRole());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkDuplicate(String username) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select Role From Account Where Username = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, username);
                rs = preSt.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean update(Account dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update Account set  Fullname = ?, Email = ?, Birthdate = ? where Username = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getFullname());
                preSt.setString(2, dto.getEmail());
                String[] d = dto.getBirthdate().split("/");
                Date date = new Date(Integer.parseInt(d[2]) - 1900, Integer.parseInt(d[1]) - 1, Integer.parseInt(d[0]));
                preSt.setDate(3, date);
                preSt.setString(4, dto.getUsername());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(String username) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Delete from Account where Username = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, username);
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean changePassword(String username, String password) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update Account set Password = ? where Username = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, password);
                preSt.setString(2, username);
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
