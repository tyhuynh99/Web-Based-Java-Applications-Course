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
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tyhp.dbutils.MyConnection;
import tyhp.dtos.Service;
import tyhp.dtos.Slot;

/**
 *
 * @author ACER
 */
public class ServiceDAO implements Serializable {

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

    public boolean insert(Service dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into Service(ServiceId, ServiceName, Price, Slot, Status) "
                        + "values(?,?,?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getId());
                preSt.setString(2, dto.getName());
                preSt.setFloat(3, dto.getPrice());
                preSt.setInt(4, dto.getSlot());
                preSt.setBoolean(5, dto.isStatus());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(String id) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Delete From InvoiceServiceDetails where SerId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, id);
                result = preSt.executeUpdate() > -1;
                if (result == true) {
                    if (conn != null) {
                        sql = "Delete From BookService where SerId = ?";
                        preSt = conn.prepareStatement(sql);
                        preSt.setString(1, id);
                        result = preSt.executeUpdate() > -1;
                        if (result == true) {
                            if (conn != null) {
                                sql = "Delete From Service where ServiceId = ?";
                                preSt = conn.prepareStatement(sql);
                                preSt.setString(1, id);
                                result = preSt.executeUpdate() > 0;
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

    public boolean update(Service dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Update Service set ServiceName=?, Price=?, Slot=?, Status=? where ServiceId=? ";
                preSt = conn.prepareStatement(sql);
                preSt.setString(5, dto.getId());
                preSt.setString(1, dto.getName());
                preSt.setFloat(2, dto.getPrice());
                preSt.setInt(3, dto.getSlot());
                preSt.setBoolean(4, dto.isStatus());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Service> getService() throws Exception {
        List<Service> list = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select ServiceId, ServiceName, Price, Slot, Status from Service";
                preSt = conn.prepareStatement(sql);
                list = new ArrayList<Service>();
                rs = preSt.executeQuery();
                while (rs.next()) {
                    System.out.println("a");
                    String id = rs.getString("ServiceId");
                    String name = rs.getString("ServiceName");
                    float price = rs.getFloat("Price");
                    int slot = rs.getInt("Slot");
                    boolean status = rs.getBoolean("Status");
                    Service dto = new Service(id, name, price, slot, status);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public Service getServiceById(String id) throws Exception {
        Service dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select ServiceName, Price, Slot, Status from Service where ServiceId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, id);
                rs = preSt.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("ServiceName");
                    float price = rs.getFloat("Price");
                    int slot = rs.getInt("Slot");
                    boolean status = rs.getBoolean("Status");
                    dto = new Service(id, name, price, slot, status);
                    List<Slot> listSlot = loadSlot(slot);
                    dto.setListSlot(listSlot);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<Slot> loadSlot(int max) throws Exception {
        List<Slot> list = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select Slot, Status from SlotDetails where Slot <= ?";
                preSt = conn.prepareStatement(sql);
                preSt.setInt(1, max);
                list = new ArrayList<>();
                rs = preSt.executeQuery();
                while (rs.next()) {
                    int slot = rs.getInt("Slot");
                    boolean status = rs.getBoolean("Status");
                    Slot dto = new Slot(slot, status);
                    list.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<Slot> loadSlot() throws Exception {
        List<Slot> list = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select Slot, Time, Status from Slot";
                preSt = conn.prepareStatement(sql);
                rs = preSt.executeQuery();
                while (rs.next()) {
                    int slot = rs.getInt("Slot");
                    Time time = rs.getTime("Time");
                    boolean status = rs.getBoolean("Status");
                    Slot dto = new Slot(slot, status);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
