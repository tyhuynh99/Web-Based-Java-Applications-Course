/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
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
public class AddSerCartController extends HttpServlet {

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
            //lấy tham số truyền vào
            String serId = request.getParameter("serId");
            String petId = request.getParameter("petId");
            int slot = Integer.parseInt(request.getParameter("slot"));
            String d = request.getParameter("date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(d);
            ServiceCartBean serCartBean = new ServiceCartBean();
            ServiceCart serviceCart = new ServiceCart(serId, petId, date, slot);
            serCartBean.setDto(serviceCart);
            //kiểm tra đã có trong session chưa
            String serCartKey = serId + "-" + petId;
            if (serCartBean.isAlready() == true) {
                //mỗi người chỉ được book trong ngày 1 slot cho 1 loại service
                request.setAttribute("MESS", "YOU HAVE BOOKED THIS SERVICE ALREADY");
            } else {
                //insert vào db
                serCartBean.setDto(serviceCart);
                if (serCartBean.insert() == true) {
                    //insert vào session
                    serCartMap.put(serCartKey, serviceCart);
                    request.setAttribute("MESS", "ADD SUCCESSFUL");
                } else {
                    request.setAttribute("MESS", "ADD FAIL");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AddSerCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("SearchServiceController").forward(request, response);
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
