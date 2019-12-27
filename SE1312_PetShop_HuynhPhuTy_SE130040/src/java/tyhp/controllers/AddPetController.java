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
import tyhp.dtos.Pet;
import tyhp.models.PetBean;

/**
 *
 * @author ACER
 */
public class AddPetController extends HttpServlet {

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
            request.setAttribute("SHOW", "Show");
            String username = request.getParameter("username");
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String type = request.getParameter("type");
            PetBean bean = new PetBean();
            bean.setPetId(id);
            if (bean.getPetById() == null) {
                Pet dto = new Pet(id, name, type, age);
                bean.setDto(dto);
                bean.setUsername(username);
                if (bean.insert() == true) {
                    request.setAttribute("SUCCESS", "Insert successful");
                } else {
                    request.setAttribute("FAILED", "Insert successful");
                }
            } else {
                request.setAttribute("FAILED", "This ID has been in list already");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at AddPetController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("add_pet.jsp").forward(request, response);
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
