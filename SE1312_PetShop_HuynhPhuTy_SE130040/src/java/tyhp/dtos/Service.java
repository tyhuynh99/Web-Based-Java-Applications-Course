/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ACER
 */
public class Service implements Serializable {

    private String id, name;
    private float price;
    private int slot;
    private boolean status;
    private List<Slot> listSlot;

    public Service() {
    }

    public Service(String id, String name, float price, int slot, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.slot = slot;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public List<Slot> getListSlot() {
        return listSlot;
    }

    public void setListSlot(List<Slot> listSlot) {
        this.listSlot = listSlot;
    }

}
