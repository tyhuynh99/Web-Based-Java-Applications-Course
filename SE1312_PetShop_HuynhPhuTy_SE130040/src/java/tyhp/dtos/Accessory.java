/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.dtos;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class Accessory implements Serializable {

    private String id, name, des;
    private float price;
    private boolean status;
    private int quantity;

    public Accessory() {
    }

    public Accessory(String id, String name, String des, float price, boolean status, int quantity) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

   

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
