/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tyhp.daos.AccessoryDAO;

/**
 *
 * @author ACER
 */
public class ShoppingCart implements Serializable {

    private String username;
    private Map<String, AccCart> accCart;
    private Map<String, ServiceCart> serCart;
    private int countAccCart, countSerCart;

    public ShoppingCart() {
        this.username = "Guest";
        accCart = new HashMap<>();
        serCart = new HashMap<>();
    }

    public ShoppingCart(String username) {
        this.username = username;
        accCart = new HashMap<>();
        serCart = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, AccCart> getAccCart() {
        return accCart;
    }

    public Map<String, ServiceCart> getSerCart() {
        return serCart;
    }

    public void addAccCart(AccCart dto) throws Exception {
        accCart.put(dto.getAccId() + "-" + dto.getPetId(), dto);
    }

    public void addSerCart(ServiceCart dto) throws Exception {
        serCart.put(dto.getServiceId() + "-" + dto.getPetId(), dto);
    }

    public int getCountAccCart() {
        countAccCart = accCart.size();
        return countAccCart;
    }

    public int getCountSerCart() {
        countSerCart = serCart.size();
        return countSerCart;
    }

    public boolean isContainAccCart(String key) throws Exception {
        return accCart.containsKey(key);
    }

    public boolean isContainSerCart(String key) throws Exception {
        return serCart.containsKey(key);
    }

    public AccCart getAccCart(String key) throws Exception {
        return accCart.get(key);
    }

    public ServiceCart getSerCart(String key) throws Exception {
        return serCart.get(key);
    }

    public AccCart removeAccCart(String key) throws Exception {
        return accCart.remove(key);
    }

    public Map<String, Accessory> getAccDependsCart() throws Exception {
        Collection<AccCart> accCartCollection = accCart.values();
        List<String> listId = new ArrayList<>();
        for (AccCart accCartDto : accCartCollection) {
            String id = accCartDto.getAccId();
            if (!listId.contains(id)) {
                listId.add(id);
            }
        }
        AccessoryDAO accDao = new AccessoryDAO();
        return accDao.getAccDependsCart(listId);
    }

    public List<String> checkEnoughQuantity() throws Exception {
        List<String> listAccKeyNotEnough = new ArrayList<>();
        Map<String, Accessory> accList = getAccDependsCart();
        Collection<AccCart> accCartCollection = accCart.values();
        for (AccCart accCartDto : accCartCollection) {
            Accessory accDto = accList.get(accCartDto.getAccId());
            if (accCartDto.getQuantity() <= accDto.getQuantity()) {
                accDto.setQuantity(accDto.getQuantity() - accCartDto.getQuantity());
            } else {
                if (!listAccKeyNotEnough.contains(accDto.getId())) {
                    listAccKeyNotEnough.add(accDto.getId());
                }
            }
        }
        return listAccKeyNotEnough;
    }

    public void deleteAccCart(String id) throws Exception {
        Set<String> keys = accCart.keySet();
        System.out.println("size: " + accCart.size());
        ArrayList<String> removeKeys = new ArrayList<>();
        for (String tmpKey : keys) {
            System.out.println(tmpKey);
            if (tmpKey.split("-")[1].equalsIgnoreCase(id)) {
                removeKeys.add(tmpKey);
                System.out.println("aaaaaaaaa" + accCart.size());
            }
        }
        for (String tmp : removeKeys) {
            accCart.remove(tmp);
            System.out.println("bbbbbbbbbbbbbb" + accCart.size());
        }
    }
}
