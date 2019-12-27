/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tyhp.dtos.ServiceCart;
import tyhp.dtos.ShoppingCart;
import tyhp.models.ServiceCartBean;

/**
 *
 * @author ACER
 */
public class DeleteSerCartController extends HttpServlet {

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
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("CART");
        Map<String, ServiceCart> serCartMap = cart.getSerCart();
        try {
            String serId = request.getParameter("serId");
            String petId = request.getParameter("petId");
            int slot = Integer.parseInt(request.getParameter("slot"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(request.getParameter("date"));
            ServiceCart serCartDto = new ServiceCart(serId, petId, date, slot);
            ServiceCartBean serCartBean = new ServiceCartBean();
            serCartBean.setDto(serCartDto);
            if (serCartBean.delete() == true){
                String key = serId+"-"+petId;
                serCartMap.remove(key);
                request.setAttribute("SER_MESS", "DELETE SUCCESSFUL");
            } else {
                request.setAttribute("SER_MESS", "DELETE SUCCESSFUL");
            }
        } catch (Exception e) {
            log("ERROR at DeleteSerCartController: "+e.getMessage());
        } finally{
            request.getRequestDispatcher("CartDetailController").forward(request, response);
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
