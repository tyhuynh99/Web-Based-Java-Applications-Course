/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import tyhp.daos.ServiceCartDAO;
import tyhp.dtos.ServiceCart;

/**
 *
 * @author ACER
 */
public class ServiceCartBean implements Serializable {

    private Date date;
    private ServiceCart dto;
    private String petId;

    public ServiceCartBean() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ServiceCart getDto() {
        return dto;
    }

    public void setDto(ServiceCart dto) {
        this.dto = dto;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public List<ServiceCart> getServiceCartByDate() throws Exception {
        ServiceCartDAO dao = new ServiceCartDAO();
        return dao.getServiceCartByDate(date);
    }

    public List<ServiceCart> getServiceCartByPetId() throws Exception {
        ServiceCartDAO dao = new ServiceCartDAO();
        return dao.getServiceCartByPet(petId);
    }

    public boolean insert() throws Exception {
        ServiceCartDAO dao = new ServiceCartDAO();
        return dao.insert(dto);
    }

    public boolean update() throws Exception {
        ServiceCartDAO dao = new ServiceCartDAO();
        return dao.update(dto);
    }

    public boolean delete() throws Exception {
        ServiceCartDAO dao = new ServiceCartDAO();
        return dao.delete(dto);
    }
    
    public boolean updateStatus() throws Exception{
        ServiceCartDAO dao = new ServiceCartDAO();
        return dao.updateStatus(dto);
    }
    
    public boolean isAlready() throws Exception{
        ServiceCartDAO dao = new ServiceCartDAO();
        return dao.isAlready(dto);
    }
}
