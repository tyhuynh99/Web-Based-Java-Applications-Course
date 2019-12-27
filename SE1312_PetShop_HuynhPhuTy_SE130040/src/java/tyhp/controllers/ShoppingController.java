/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tyhp.dtos.Accessory;
import tyhp.dtos.Account;
import tyhp.dtos.Pet;
import tyhp.models.AccessoryBean;
import tyhp.models.PetBean;

/**
 *
 * @author ACER
 */
public class ShoppingController extends HttpServlet {

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
            String search = request.getParameter("search");
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("ACCOUNT");
            AccessoryBean bean = new AccessoryBean();
            List<Accessory> list = null;
            if (search == null) {
                list = bean.getAccessory();
            } else {
                bean.setSearch(search);
                
                list = bean.getAccessoryByName();
            }
            request.setAttribute("LIST", list);
            PetBean petBean = new PetBean();
            petBean.setUsername(account.getUsername());
            List<Pet> listPet = petBean.getPetByUsername();
            request.setAttribute("LIST_PET", listPet);

        } catch (Exception e) {
            log("ERROR at ShoppingController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("shopping.jsp").forward(request, response);
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
