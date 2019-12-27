/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.dtos;

import java.io.Serializable;
import tyhp.models.AccessoryBean;
import tyhp.models.PetBean;

/**
 *
 * @author ACER
 */
public class AccCart implements Serializable {

    private String accId, petId;
    private int quantity;

    public AccCart() {
    }

    public AccCart(String accId, String petId, int quantity) {
        this.accId = accId;
        this.petId = petId;
        this.quantity = quantity;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAccName() throws Exception {
        AccessoryBean bean = new AccessoryBean();
        bean.setId(accId);
        Accessory dto = bean.getAccessoryById();
        return dto.getName();
    }

    public float getAccPrice() throws Exception {
        AccessoryBean bean = new AccessoryBean();
        bean.setId(accId);
        Accessory dto = bean.getAccessoryById();
        return dto.getPrice();
    }
    
    public int getAccQuantity() throws Exception{
        AccessoryBean bean = new AccessoryBean();
        bean.setId(accId);
        Accessory dto = bean.getAccessoryById();
        return dto.getQuantity();
    }
    
    public boolean getAccStatus() throws Exception{
        AccessoryBean bean = new AccessoryBean();
        bean.setId(accId);
        Accessory dto = bean.getAccessoryById();
        return dto.isStatus();
    }

    public String getPetName() throws Exception {
        PetBean bean = new PetBean();
        bean.setPetId(petId);
        Pet dto = bean.getPetById();
        return dto.getName();
    }

}
