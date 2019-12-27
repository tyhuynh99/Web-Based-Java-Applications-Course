/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.models;

import java.io.Serializable;
import java.util.List;
import tyhp.daos.AccessoryDAO;
import tyhp.dtos.Accessory;

/**
 *
 * @author ACER
 */
public class AccessoryBean implements Serializable {

    private String id;
    private String search;
    private Accessory dto;
    private int quantity;

    public AccessoryBean() {
    }

    public List<Accessory> getAccessory() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.getAccessory();
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public boolean getStatus() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.getStatus(id);
    }

    public Accessory getAccessoryById() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.getAccessoryById(id);
    }

    public List<Accessory> getAccessoryByName() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.getAccessoryByName(search);
    }

    public void setDto(Accessory dto) {
        this.dto = dto;
    }

    public boolean update() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.update(dto);
    }

    public boolean updateQuantityAfterOrder() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.updateQuantityAfterOrder(id, quantity);
    }

    public boolean delete() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.delete(id);
    }

    public boolean insert() throws Exception {
        AccessoryDAO dao = new AccessoryDAO();
        return dao.insert(dto);
    }
}
