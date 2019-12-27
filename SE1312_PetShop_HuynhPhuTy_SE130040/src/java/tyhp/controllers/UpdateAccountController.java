/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tyhp.dtos.Account;
import tyhp.models.AccountBean;

/**
 *
 * @author ACER
 */
public class UpdateAccountController extends HttpServlet {

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
        boolean valid = true;
        try {
            HttpSession session = request.getSession();
            Account dto = (Account) session.getAttribute("ACCOUNT");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            dto.setFullname(fullname);
            dto.setEmail(email);
            String dob = request.getParameter("birthdate");
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(dob);
                if (!sdf.format(date).equalsIgnoreCase(dob)) {
                    throw new Exception("Wrong birthdate format or invalid date");
                }
            } catch (Exception e) {
                valid = false;
                request.setAttribute("INVALID", e.getMessage());
            }
            if (valid == true) {
                AccountBean bean = new AccountBean();
                dto.setBirthdate(dob);
                bean.setDto(dto);
                if (bean.update() == true) {
                    request.setAttribute("DTO", dto);
                    session.setAttribute("ACCOUNT", dto);
                    request.setAttribute("UPDATE_SUCCESS", "Update information successful");
                } else {
                    request.setAttribute("DTO", dto);
                    request.setAttribute("UPDATE_FAILED", "Update information failed");
                }
            } else {
                request.setAttribute("DTO", dto);
            }
        } catch (Exception e) {
            log("ERROR at UpdateAccountController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ViewAccountInfoController").forward(request, response);
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
