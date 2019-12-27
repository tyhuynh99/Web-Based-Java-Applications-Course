/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.dtos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import tyhp.daos.PetDAO;
import tyhp.daos.ServiceDAO;

/**
 *
 * @author ACER
 */
public class ServiceCart implements Serializable {

    private String serviceId, petId;
    private Date date;
    private int slot;
    private boolean status = true;

    public ServiceCart() {
    }

    public ServiceCart(String serviceId, String petId, Date date, int slot) {
        this.serviceId = serviceId;
        this.petId = petId;
        this.date = date;
        this.slot = slot;
        this.status = true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getSerPrice() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        Service dto = dao.getServiceById(serviceId);
        return dto.getPrice();
    }

    public String getSerName() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        Service dto = dao.getServiceById(serviceId);
        return dto.getName();
    }

    public String getId() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        Service dto = dao.getServiceById(serviceId);
        return dto.getId();
    }

    public String getPetName() throws Exception {
        PetDAO dao = new PetDAO();
        Pet dto = dao.getPetById(petId);
        return dto.getName();
    }
    public String getDateBook() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
