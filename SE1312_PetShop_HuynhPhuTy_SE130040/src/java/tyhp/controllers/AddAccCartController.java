/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tyhp.dtos.AccCart;
import tyhp.dtos.ShoppingCart;
import tyhp.models.AccessoryBean;
import tyhp.models.AccessoryCartBean;

/**
 *
 * @author ACER
 */
public class AddAccCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String petId = request.getParameter("petId");
            String accId = request.getParameter("accId");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            AccessoryBean accBean = new AccessoryBean();
            accBean.setId(accId);
            if (accBean.getStatus() == true) {
                HttpSession session = request.getSession();
                ShoppingCart cart = (ShoppingCart) session.getAttribute("CART");
                //tao gio hang cho accessory
                //Kiem tra accessory trong gio hang
                String key = accId + "-" + petId;
                if (cart.isContainAccCart(key) == true) {
                     //Da co accessory trong gio hang
                    AccCart accCart = cart.getAccCart(key);
                    accCart.setQuantity(accCart.getQuantity() + quantity);
                    AccessoryCartBean accCartBean = new AccessoryCartBean();
                    accCartBean.setDto(accCart); // Cap nhat so luong
                    if (accCartBean.update() == true) {
                        cart.addAccCart(accCart);
                        session.setAttribute("CART", cart); // luu vao shopping cart
                        request.setAttribute("ADD_SUCCESSFUL", "Add to cart successful");
                    } else {
                        request.setAttribute("OUT_OF_STOCK", "Sorry, this accessory is not available");
                    }
                } else {
                    //chua co accessory trong gio hang
                    AccCart accCart = new AccCart(accId, petId, quantity); 
                    AccessoryCartBean accCartBean = new AccessoryCartBean();
                    accCartBean.setDto(accCart); // tao gio hang cho accessory
                    if (accCartBean.insert() == true) {
                        cart.addAccCart(accCart);
                        session.setAttribute("CART", cart); // luu vao shopping cart
                        request.setAttribute("ADD_SUCCESSFUL", "ADD TO CART SUCCESSFUL");
                    } else {
                        request.setAttribute("OUT_OF_STOCK", "SORRY, THIS ACCESSORY IS NOT AVAILABLE");
                    }
                }
            } else {
                request.setAttribute("OUT_OF_STOCK", "Sorry, this accessory is out of stock");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AddAccCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ShoppingController").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
