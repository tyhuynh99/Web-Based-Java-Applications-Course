/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tyhp.dtos.Accessory;
import tyhp.models.AccessoryBean;
import tyhp.validation.InsertAccessErr;

/**
 *
 * @author ACER
 */
public class UpdateAccessoryController extends HttpServlet {

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
        InsertAccessErr err = new InsertAccessErr();
        AccessoryBean accBean = new AccessoryBean();
        boolean valid = true;
        String mess = "";
        try {
            String id = request.getParameter("up_id");
            float price = 0;
            int quantity = 0;
            accBean.setId(id);
            if (accBean.getAccessoryById() == null) {
                valid = false;
                err.setId("This id has not been in list");
            }
            String name = request.getParameter("up_name");
            try {
                mess = "Price must be number";
                price = Float.parseFloat(request.getParameter("up_price"));
                if (price < 0) {
                    mess = "Price must be > 0";
                    throw new Exception();
                }
            } catch (Exception e) {
                valid = false;
                err.setPrice(mess);
            }
            String des = request.getParameter("up_des");
            try {
                mess = "Quantity must be number";
                quantity = Integer.parseInt(request.getParameter("up_quantity"));
                if (quantity < 0) {
                    mess = "Quantity must be > 0";
                    throw new Exception();
                }
            } catch (Exception e) {
                valid = false;
                err.setQuantity(mess);
            }
            Accessory dto = new Accessory(id, name, des, price, true, quantity);
            if (valid == true) {
                if (quantity == 0) {
                    dto.setStatus(false);
                }
                accBean.setDto(dto);
                accBean.update();
            } else {
                request.setAttribute("INVALID_UPDATE", err);
            }
            request.setAttribute("DTO", dto);
        } catch (Exception e) {
            log("ERROR at UpdateAccessoryController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ManageAccessory").forward(request, response);
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
