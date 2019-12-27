/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tyhp.dbutils.MyConnection;
import tyhp.dtos.AccCart;

/**
 *
 * @author ACER
 */
public class AccCartDAO {

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

    public boolean insert(AccCart dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into AccCart(AccId,PetId,Quantity) values(?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getAccId());
                preSt.setString(2, dto.getPetId());
                preSt.setInt(3, dto.getQuantity());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(String accId, String petId) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Delete from AccCart where AccId = ? and PetId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, accId);
                preSt.setString(2, petId);
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean update(AccCart dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update AccCart set Quantity = ? where AccId = ? and PetId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setInt(1, dto.getQuantity());
                preSt.setString(2, dto.getAccId());
                preSt.setString(3, dto.getPetId());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<AccCart> getAccCartByPetId(String petId) throws Exception {
        List<AccCart> list = null;
        AccCart dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select AccId, Quantity from AccCart where PetId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, petId);
                rs = preSt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String accId = rs.getString("AccId");
                    int quantity = rs.getInt("Quantity");
                    dto = new AccCart(accId, petId, quantity);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
