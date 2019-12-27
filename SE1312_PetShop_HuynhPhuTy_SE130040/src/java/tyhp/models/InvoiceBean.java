/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import tyhp.daos.InvoiceDAO;
import tyhp.dtos.AccCart;
import tyhp.dtos.Invoice;
import tyhp.dtos.ServiceCart;

/**
 *
 * @author ACER
 */
public class InvoiceBean implements Serializable {

    private Invoice invoiceDto;
    private AccCart accCartDto;
    private String orderId;
    private ServiceCart serCartDto;
    private String username;
    private Date date;

    public ServiceCart getSerCartDto() {
        return serCartDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSerCartDto(ServiceCart serCartDto) {
        this.serCartDto = serCartDto;
    }

    public InvoiceBean() {
    }

    public Invoice getInvoiceDto() {
        return invoiceDto;
    }

    public void setInvoiceDto(Invoice invoiceDto) {
        this.invoiceDto = invoiceDto;
    }

    public AccCart getAccCartDto() {
        return accCartDto;
    }

    public void setAccCartDto(AccCart accCartDto) {
        this.accCartDto = accCartDto;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean insertInvoice() throws Exception {
        InvoiceDAO dao = new InvoiceDAO();
        return dao.insertInvoice(invoiceDto);
    }

    public boolean insertAccInvoice() throws Exception {
        InvoiceDAO dao = new InvoiceDAO();
        return dao.insertAccInvoice(accCartDto, orderId);
    }

    public boolean insertAccForPet() throws Exception {
        InvoiceDAO dao = new InvoiceDAO();
        return dao.insertAccForPet(accCartDto);
    }

    public boolean insertSerInvoice() throws Exception {
        InvoiceDAO dao = new InvoiceDAO();
        return dao.insertSerInvoice(serCartDto, orderId);
    }

    public List<Invoice> getInvoice() throws Exception {
        InvoiceDAO dao = new InvoiceDAO();
        return dao.getInvoice(username);
    }

    public List<Invoice> getInvoiceByDate() throws Exception {
        InvoiceDAO dao = new InvoiceDAO();
        return dao.getInvoiceByDate(date);
    }

    public List<ServiceCart> getSerCart() throws Exception {
        InvoiceDAO dao = new InvoiceDAO();
        return dao.getSerCart(orderId);
    }

    public List<AccCart> getAccCart() throws Exception {
        InvoiceDAO dao = new InvoiceDAO();
        return dao.getAccCart(orderId);
    }
}
