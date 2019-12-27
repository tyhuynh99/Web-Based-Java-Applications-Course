/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tyhp.dbutils.MyConnection;
import tyhp.dtos.AccCart;
import tyhp.dtos.Invoice;
import tyhp.dtos.ServiceCart;

/**
 *
 * @author ACER
 */
public class InvoiceDAO {

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

    public boolean insertInvoice(Invoice dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into Invoice(OrderId,Date,Username,PaymentId,PhoneNumber,Address) "
                        + "values(?,?,?,?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, dto.getId());
                java.util.Date d = dto.getDate();
                Date date = new Date(d.getTime());
                preSt.setDate(2, date);
                preSt.setString(3, dto.getUsername());
                preSt.setString(4, dto.getPaymentId());
                preSt.setString(5, dto.getPhoneNo());
                preSt.setString(6, dto.getAddr());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertAccInvoice(AccCart dto, String orderId) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into InvoiceDetails(OrderId,PetId,AccId,Quantity)"
                        + " values(?,?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, orderId);
                preSt.setString(2, dto.getPetId());
                preSt.setString(3, dto.getAccId());
                preSt.setInt(4, dto.getQuantity());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertAccForPet(AccCart dto) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into HaveAccessories(AccId,PetId,Quantity)"
                        + " values(?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(2, dto.getPetId());
                preSt.setString(1, dto.getAccId());
                preSt.setInt(3, dto.getQuantity());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    //Insert Service Invoice
    public boolean insertSerInvoice(ServiceCart dto, String orderId) throws Exception {
        boolean result = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Insert into InvoiceServiceDetails(OrderId,PetId,SerId,Date,Slot)"
                        + " values(?,?,?,?,?)";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, orderId);
                preSt.setString(2, dto.getPetId());
                preSt.setString(3, dto.getServiceId());
                Date date = new Date(dto.getDate().getTime());
                preSt.setDate(4, date);
                preSt.setInt(5, dto.getSlot());
                result = preSt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<Invoice> getInvoice(String username) throws Exception {
        List<Invoice> list = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select OrderId, Date, PaymentID, PhoneNumber, Address from Invoice where Username = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, username);
                rs = preSt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String orderId = rs.getString("OrderId");
                    Date d = rs.getDate("Date");
                    java.util.Date date = new java.util.Date(d.getTime());
                    String payment = rs.getString("PaymentId");
                    String phone = rs.getString("PhoneNumber");
                    String addr = rs.getString("Address");
                    Invoice dto = new Invoice(orderId, username, payment, phone, addr, date);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<Invoice> getInvoiceByDate(java.util.Date date) throws Exception {
        List<Invoice> list = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select OrderId, Username, PaymentID, PhoneNumber, Address from Invoice where Date = ?";
                preSt = conn.prepareStatement(sql);
                Date d = new Date(date.getTime());
                preSt.setDate(1, d);
                list = new ArrayList<>();
                rs = preSt.executeQuery();
                while (rs.next()) {
                    Invoice dto;
                    String orderId = rs.getString("OrderId");
                    String username = rs.getString("Username");
                    String payment = rs.getString("PaymentId");
                    String phone = rs.getString("PhoneNumber");
                    String addr = rs.getString("Address");
                    dto = new Invoice(orderId, username, payment, phone, addr, date);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<ServiceCart> getSerCart(String orderId) throws Exception {
        List<ServiceCart> list = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select PetId, SerId, Date, Slot from InvoiceServiceDetails where OrderId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, orderId);
                rs = preSt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String petId = rs.getString("PetId");
                    String serId = rs.getString("SerId");
                    Date d = rs.getDate("Date");
                    java.util.Date date = new java.util.Date(d.getTime());
                    int slot = rs.getInt("Slot");
                    ServiceCart dto = new ServiceCart(serId, petId, date, slot);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<AccCart> getAccCart(String orderId) throws Exception {
        List<AccCart> list = null;
        AccCart dto;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select AccId, PetId, Quantity from InvoiceDetails where OrderId = ?";
                preSt = conn.prepareStatement(sql);
                preSt.setString(1, orderId);
                rs = preSt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String accId = rs.getString("AccId");
                    int quantity = rs.getInt("Quantity");
                    String petId = rs.getString("PetId");
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
