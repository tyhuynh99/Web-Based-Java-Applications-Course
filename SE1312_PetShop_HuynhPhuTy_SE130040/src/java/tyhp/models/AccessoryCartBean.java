/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.models;

import java.io.Serializable;
import java.util.List;
import tyhp.daos.AccCartDAO;
import tyhp.dtos.AccCart;

/**
 *
 * @author ACER
 */
public class AccessoryCartBean implements Serializable {

    private String petId;
    private String accId;
    private AccCart dto;

    public AccessoryCartBean() {
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public void setDto(AccCart dto) {
        this.dto = dto;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }
    

    public List<AccCart> getAccCartByPetId() throws Exception {
        AccCartDAO dao = new AccCartDAO();
        return dao.getAccCartByPetId(petId);
    }

    public boolean insert() throws Exception {
        AccCartDAO dao = new AccCartDAO();
        return dao.insert(dto);
    }
    
    public boolean update() throws Exception{
        AccCartDAO dao = new AccCartDAO();
        return dao.update(dto);
    }
    
    public boolean delete() throws Exception{
        AccCartDAO dao = new AccCartDAO();
        return dao.delete(accId, petId);
    }
}
