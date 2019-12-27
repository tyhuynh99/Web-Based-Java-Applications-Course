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
import javax.servlet.http.HttpSession;
import tyhp.dtos.Account;
import tyhp.models.AccountBean;

/**
 *
 * @author ACER
 */
public class ChangePasswordController extends HttpServlet {

    private static final String INVALID = "change_password.jsp";
    private static final String SUCCESS = "ViewAccountInfoController";

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
        String url = INVALID;
        try {
            HttpSession session = request.getSession();
            Account dto = (Account) session.getAttribute("ACCOUNT");
            String password = request.getParameter("password");
            String newPassword = request.getParameter("newPassword");
            String confirm = request.getParameter("confirm");
            if (!dto.getPassword().equals(password)) {
                request.setAttribute("INVALID", "Your password is incorrect");
            } else {
                if (!newPassword.equals(confirm)) {
                    request.setAttribute("INVALID", "New password and confirm password should be matched");
                } else {
                    AccountBean bean = new AccountBean();
                    bean.setUsername(dto.getUsername());
                    bean.setPassword(newPassword);
                    if (bean.changePassword() == true) {
                        url = SUCCESS;
                        dto.setPassword(newPassword);
                        session.setAttribute("ACCOUNT", dto);
                        request.setAttribute("UPDATE_SUCCESS", "Change password successful");
                    } else {
                        request.setAttribute("INVALID", "Cannot change your password, please try again");
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at ChangePasswordController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
