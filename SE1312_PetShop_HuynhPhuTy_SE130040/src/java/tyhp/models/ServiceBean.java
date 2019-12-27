/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.models;

import java.io.Serializable;
import java.util.List;
import tyhp.daos.ServiceDAO;
import tyhp.dtos.Service;
import tyhp.dtos.Slot;

/**
 *
 * @author ACER
 */
public class ServiceBean implements Serializable {

    private String id;
    private Service dto;

    public ServiceBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Service getDto() {
        return dto;
    }

    public void setDto(Service dto) {
        this.dto = dto;
    }

    public boolean insert() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.insert(dto);
    }

    public boolean update() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.update(dto);
    }

    public boolean delete() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        return dao.delete(id);
    }

    public List<Service> getService() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        List<Service> list = dao.getService();
        for (Service dto : list) {
            List<Slot> slotList = dao.loadSlot(dto.getSlot());
            dto.setListSlot(slotList);
        }
        return list;
    }

    public Service getServiceById() throws Exception {
        ServiceDAO dao = new ServiceDAO();
        Service serDto = dao.getServiceById(id);
        if (serDto != null) {
            List<Slot> slotList = dao.loadSlot(serDto.getSlot());
            serDto.setListSlot(slotList);
        }
        return serDto;
    }
}
