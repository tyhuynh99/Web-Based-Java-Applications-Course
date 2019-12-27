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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tyhp.dbutils.MyConnection;
import tyhp.dtos.Accessory;

/**
 *
 * @author ACER
 */
public class AccessoryDAO implements Serializable {

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

    public List<Accessory> getAccessory() throws Exception {
        List<Accessory> list = null;
        Accessory dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select AccId, AccName, Price, Quantity, Status, Description from Accessory";
                preSt = conn.prepareStatement(sql);
                list = new ArrayList<>();
                rs = preSt.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("AccId");
                    String name = rs.getString("AccName");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("Status");
                    String des = rs.getString("Description");
                    dto = new Accessory(id, name, des, price, status, quantity);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<Accessory> getAccessoryByName(String search) throws Exception {
        List<Accessory> list = null;
        Accessory dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select AccId, AccName, Price, Quantity, Status, Description from Accessory where AccName Like ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, "%" + search + "%");
                list = new ArrayList<>();
                rs = preSt.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("AccId");
                    String name = rs.getString("AccName");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("Status");
                    String des = rs.getString("Description");
                    dto = new Accessory(id, name, des, price, status, quantity);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public Accessory getAccessoryById(String id) throws Exception {
        Accessory dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select  AccName, Price, Quantity, Status, Description from Accessory where AccId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, id);
                rs = preSt.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("AccName");
                    float price = rs.getFloat("Price");
                    int quantity = rs.getInt("Quantity");
                    boolean status = rs.getBoolean("Status");
                    String des = rs.getString("Description");
                    dto = new Accessory(id, name, des, price, status, quantity);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public Map<String, Accessory> getAccDependsCart(List<String> listId) throws Exception {
        Map<String, Accessory> list = null;
        Accessory dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select  AccName, Price, Quantity, Status, Description from Accessory where AccId = ?";
                preSt = conn.prepareStatement(sql);
                list = new HashMap<>();
                for (String id : listId) {
                    preSt.setString(1, id);
                    rs = preSt.executeQuery();
                    while (rs.next()) {
                        String name = rs.getString("AccName");
                        float price = rs.getFloat("Price");
                        int quantity = rs.getInt("Quantity");
                        boolean status = rs.getBoolean("Status");
                        String des = rs.getString("Description");
                        dto = new Accessory(id, name, des, price, status, quantity);
                        list.put(dto.getId(), dto);
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean update(Accessory dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update Accessory set AccName = ?, Price = ?, Quantity = ?, Status = ?, Description = ? where AccId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getName());
                preSt.setFloat(2, dto.getPrice());
                preSt.setInt(3, dto.getQuantity());
                preSt.setBoolean(4, dto.isStatus());
                preSt.setString(5, dto.getDes());
                preSt.setString(6, dto.getId());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insert(Accessory dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into Accessory(AccId,AccName, Price, Quantity, Status, Description) values(?,?,?,?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getId());
                preSt.setString(2, dto.getName());
                preSt.setFloat(3, dto.getPrice());
                preSt.setInt(4, dto.getQuantity());
                preSt.setBoolean(5, dto.isStatus());
                preSt.setString(6, dto.getDes());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateQuantityAfterOrder(String accId, int quantity) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update Accessory set Quantity = ?, Status = ? where AccId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setInt(1, quantity);
                preSt.setString(3, accId);
                if (quantity == 0) {
                    preSt.setBoolean(2, false);
                } else {
                    preSt.setBoolean(2, true);
                }
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean getStatus(String id) throws Exception {
        boolean status = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select Status from Accessory where AccId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, id);
                rs = preSt.executeQuery();
                if (rs.next()) {
                    status = rs.getBoolean("Status");
                }
            }
        } finally {
            closeConnection();
        }
        return status;
    }

    public boolean delete(String id) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Delete from InvoiceDetails where AccId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, id);
                result = preSt.executeUpdate() >= 0;
                if (result == true) {
                    if (conn != null) {
                        sql = "Delete from AccCart where AccId = ?";
                        preSt = conn.prepareStatement(sql);
                        preSt.setString(1, id);
                        result = preSt.executeUpdate() >= 0;
                        if (result == true) {
                            if (conn != null) {
                                sql = "Delete from HaveAccessories where AccId = ?";
                                preSt = conn.prepareStatement(sql);
                                preSt.setString(1, id);
                                result = preSt.executeUpdate() >= 0;
                                if (result == true) {
                                    if (conn != null) {
                                        sql = "Delete from Accessory where AccId = ?";
                                        preSt = conn.prepareStatement(sql);
                                        preSt.setString(1, id);
                                        result = preSt.executeUpdate() > 0;
                                    }
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
