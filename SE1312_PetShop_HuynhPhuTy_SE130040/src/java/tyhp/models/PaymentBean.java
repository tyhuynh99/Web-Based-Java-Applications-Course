/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.models;

import java.io.Serializable;
import java.util.List;
import tyhp.daos.PaymentDAO;
import tyhp.dtos.Payment;

/**
 *
 * @author ACER
 */
public class PaymentBean implements Serializable {

    private String id, method;

    public PaymentBean() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<Payment> getPaymentList() throws Exception {
        PaymentDAO dao = new PaymentDAO();
        return dao.getPaymentList();
    }
}
