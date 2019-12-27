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
import java.util.Date;
import java.util.List;
import tyhp.dbutils.MyConnection;
import tyhp.dtos.ServiceCart;

/**
 *
 * @author ACER
 */
public class ServiceCartDAO implements Serializable {

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

    public List<ServiceCart> getServiceCartByDate(Date date) throws Exception {
        List<ServiceCart> list = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select SerId, PetId, Date, Slot, Status from BookService where Date = ?";
                java.sql.Date d = new java.sql.Date(date.getTime());
                preSt = conn.prepareStatement(sql);
                preSt.setDate(1, d);
                list = new ArrayList<>();
                rs = preSt.executeQuery();
                while (rs.next()) {
                    String serId = rs.getString("SerId");
                    String petId = rs.getString("PetId");
                    d = rs.getDate("Date");
                    Date in_date = new Date(d.getTime());
                    int slot = rs.getInt("Slot");
                    ServiceCart dto = new ServiceCart(serId, petId, in_date, slot);
                    dto.setStatus(rs.getBoolean("Status"));
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ServiceCart> getServiceCartByPet(String id) throws Exception {
        List<ServiceCart> list = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select SerId, Date, Slot, Status from BookService where PetId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, id);
                list = new ArrayList<>();
                rs = preSt.executeQuery();
                while (rs.next()) {
                    String serId = rs.getString("SerId");
                    java.sql.Date d = rs.getDate("Date");
                    Date in_date = new Date(d.getTime());
                    int slot = rs.getInt("Slot");
                    ServiceCart dto = new ServiceCart(serId, id, in_date, slot);
                    dto.setStatus(rs.getBoolean("Status"));
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insert(ServiceCart dto) throws Exception {
        boolean result = true;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into BookService(SerId,PetId,Date,Slot) "
                        + "values(?,?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getServiceId());
                preSt.setString(2, dto.getPetId());
                java.sql.Date date = new java.sql.Date(dto.getDate().getTime());
                preSt.setDate(3, date);
                preSt.setInt(4, dto.getSlot());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean update(ServiceCart dto) throws Exception {
        boolean result = true;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update BookService set Slot=? where SerId = ? and PetId = ? and Date = ? ";
                preSt = conn.prepareStatement(sql);
                preSt.setString(2, dto.getServiceId());
                preSt.setString(3, dto.getPetId());
                java.sql.Date date = new java.sql.Date(dto.getDate().getTime());
                preSt.setDate(4, date);
                preSt.setInt(1, dto.getSlot());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(ServiceCart dto) throws Exception {
        boolean result = true;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Delete from BookService where SerId = ? and PetId = ? and Date = ? ";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getServiceId());
                preSt.setString(2, dto.getPetId());
                java.sql.Date date = new java.sql.Date(dto.getDate().getTime());
                preSt.setDate(3, date);
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateStatus(ServiceCart dto) throws Exception {
        boolean result = true;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update BookService set Status = ? where SerId = ? and PetId = ? and Date = ? ";
                preSt = conn.prepareStatement(sql);
                preSt.setString(2, dto.getServiceId());
                preSt.setString(3, dto.getPetId());
                java.sql.Date date = new java.sql.Date(dto.getDate().getTime());
                preSt.setDate(4, date);
                preSt.setBoolean(1, dto.isStatus());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean isAlready(ServiceCart dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null){
                String sql = "Select Slot from BookService where SerId = ? and PetId = ? and Date = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getServiceId());
                preSt.setString(2, dto.getPetId());
                java.sql.Date date = new java.sql.Date(dto.getDate().getTime());
                preSt.setDate(3, date);
                rs = preSt.executeQuery();
                if (rs.next()){
                    result = true;
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
