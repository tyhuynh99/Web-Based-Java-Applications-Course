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
public class Pet implements Serializable{
    private String id, name, type;
    private int age;

    public Pet() {
    }

    public Pet(String id, String name, String type, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
