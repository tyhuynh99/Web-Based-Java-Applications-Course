/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.models;

import java.io.Serializable;
import java.util.List;
import tyhp.daos.PetDAO;
import tyhp.dtos.Pet;

/**
 *
 * @author ACER
 */
public class PetBean implements Serializable {

    private String username, petId;
    private Pet dto;

    public PetBean() {
    }

    public Pet getDto() {
        return dto;
    }

    public void setDto(Pet dto) {
        this.dto = dto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public List<Pet> getPetByUsername() throws Exception {
        PetDAO dao = new PetDAO();
        return dao.getPetByUsername(username);
    }

    public Pet getPetById() throws Exception {
        PetDAO dao = new PetDAO();
        return dao.getPetById(petId);
    }

    public boolean update() throws Exception {
        PetDAO dao = new PetDAO();
        return dao.update(dto);
    }

    public boolean insert() throws Exception {
        PetDAO dao = new PetDAO();
        return dao.insert(dto, username);
    }

    public boolean delete() throws Exception {
        PetDAO dao = new PetDAO();
        return dao.delete(petId);
    }
}
