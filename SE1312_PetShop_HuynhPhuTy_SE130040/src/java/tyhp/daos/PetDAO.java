/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tyhp.dbutils.MyConnection;
import tyhp.dtos.Pet;

/**
 *
 * @author ACER
 */
public class PetDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preSt;
    private ResultSet rs;

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

    public List<Pet> getPetByUsername(String username) throws Exception {
        List<Pet> list = null;
        Pet dto;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select PetId, PetName, Age, Type from Pet where Owner = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, username);
                rs = preSt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String id = rs.getString("PetId");
                    String name = rs.getString("PetName");
                    String type = rs.getString("Type");
                    int age = rs.getInt("Age");
                    dto = new Pet(id, name, type, age);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public Pet getPetById(String id) throws Exception {
        Pet dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select PetName, Age, Type from Pet where PetId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, id);
                rs = preSt.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("PetName");
                    String type = rs.getString("Type");
                    int age = rs.getInt("Age");
                    dto = new Pet(id, name, type, age);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean update(Pet dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update Pet set PetName = ?, Age = ?, Type = ? where PetId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getName());
                preSt.setInt(2, dto.getAge());
                preSt.setString(3, dto.getType());
                preSt.setString(4, dto.getId());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insert(Pet dto, String username) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into Pet(PetId,PetName,Age,Type,Owner) values(?,?,?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getId());
                preSt.setString(2, dto.getName());
                preSt.setInt(3, dto.getAge());
                preSt.setString(4, dto.getType());
                preSt.setString(5, username);
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(String id) throws Exception {
        boolean result = false;
        String sql;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                sql = "Delete from InvoiceServiceDetails where PetId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, id);
                result = preSt.executeUpdate() > -1;
                if (result == true) {
                    sql = "Delete from InvoiceDetails where PetId = ?";
                    preSt = conn.prepareStatement(sql);
                    preSt.setString(1, id);
                    result = preSt.executeUpdate() > -1;
                    if (result == true) {
                        sql = "Delete from AccCart where PetId = ?";
                        preSt = conn.prepareStatement(sql);
                        preSt.setString(1, id);
                        result = preSt.executeUpdate() > -1;
                        if (result == true) {
                            sql = "Delete from BookService where PetId = ?";
                            preSt = conn.prepareStatement(sql);
                            preSt.setString(1, id);
                            result = preSt.executeUpdate() > -1;
                            if (result == true) {
                                sql = "Delete from HaveAccessories where PetId = ?";
                                preSt = conn.prepareStatement(sql);
                                preSt.setString(1, id);
                                result = preSt.executeUpdate() > -1;
                                if (result == true) {
                                    sql = "Delete from Pet where PetId = ?";
                                    preSt = conn.prepareStatement(sql);
                                    preSt.setString(1, id);
                                    result = preSt.executeUpdate() > 0;
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
