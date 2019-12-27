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
import tyhp.dtos.Payment;

/**
 *
 * @author ACER
 */
public class PaymentDAO implements Serializable {

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

    public List<Payment> getPaymentList() throws Exception {
        List<Payment> list = null;
        Payment dto = null;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                String sql = "Select PaymentId, Method from PaymentDetails";
                preSt = conn.prepareStatement(sql);
                rs = preSt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String id = rs.getString("PaymentId");
                    String method = rs.getString("Method");
                    dto = new Payment(id, method);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
