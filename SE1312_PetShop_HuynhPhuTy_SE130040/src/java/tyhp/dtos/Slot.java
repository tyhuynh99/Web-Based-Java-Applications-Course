/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.dtos;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author ACER
 */
public class Slot implements Serializable {

    private int slot;
    private Time time;
    private boolean status;

    public Slot() {
    }

    public Slot(int slot,  boolean status) {
        this.slot = slot;
        this.status = status;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
