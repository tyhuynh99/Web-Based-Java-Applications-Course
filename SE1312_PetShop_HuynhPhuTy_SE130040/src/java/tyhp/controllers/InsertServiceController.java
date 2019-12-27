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
import tyhp.dtos.Service;
import tyhp.models.ServiceBean;
import tyhp.validation.InsertAccessErr;

/**
 *
 * @author ACER
 */
public class InsertServiceController extends HttpServlet {

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
        ServiceBean serBean = new ServiceBean();
        boolean valid = true;
        String mess = "";
        try {
            String id = request.getParameter("insert_id");
            float price = 0;
            int slot = 0;
            serBean.setId(id);
            if (serBean.getServiceById() != null) {
                valid = false;
                err.setId("This id has been in list");
            }
            String name = request.getParameter("name");
            try {
                mess = "Price must be number";
                price = Float.parseFloat(request.getParameter("price"));
                if (price < 0) {
                    mess = "Price must be > 0";
                    throw new Exception();
                }
            } catch (Exception e) {
                valid = false;
                err.setPrice(mess);
            }
            try {
                mess = "Quantity must be number";
                slot = Integer.parseInt(request.getParameter("quantity"));
                if (slot < 0) {
                    mess = "Quantity must be > 0";
                    throw new Exception();
                }
            } catch (Exception e) {
                valid = false;
                err.setQuantity(mess);
            }
            if (valid == true) {
                Service dto = new Service(id, name, price, slot, true);
                serBean.setDto(dto);
                serBean.insert();
            } else {
                request.setAttribute("INVALID", err);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at InsertServiceController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ManageServiceController").forward(request, response);
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
