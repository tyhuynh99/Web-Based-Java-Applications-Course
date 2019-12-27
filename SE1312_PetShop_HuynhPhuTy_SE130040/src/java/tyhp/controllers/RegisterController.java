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
import tyhp.validation.RegisterErrorObject;

/**
 *
 * @author ACER
 */
public class RegisterController extends HttpServlet {

    private static final String INVALID = "register.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "admin.jsp";

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
        boolean valid = true;
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("ACCOUNT");
        try {
            RegisterErrorObject errObj = new RegisterErrorObject();
            AccountBean bean = new AccountBean();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String fullname = request.getParameter("fullname");
            String dob = request.getParameter("dob");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            if (role.equals("")) {
                if (acc == null) {
                    role = "user";
                } else {
                    role = "admin";
                }
            }
            //validation password and confirm password
            if (!confirm.equals(password)) {
                valid = false;
                errObj.setErrConfirm("Password and Confirm Password should be correct");
            }
            //validation DOB
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(dob);
                if (!sdf.format(date).equalsIgnoreCase(dob)) {
                    throw new Exception();
                }
                Date currDate = new Date();
                if (currDate.getYear() - date.getYear() <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                valid = false;
                errObj.setErrDOB("Wrong birthdate format or invalid date");
            }
            //validation duplicate username
            bean.setUsername(username);
            if (bean.checkDuplicate() == true) {
                valid = false;
                errObj.setErrUsername("This username has been in list already");
            }
            if (valid == true) {
                Account dto = new Account(username, password, fullname, email, role, dob);
                bean.setDto(dto);
                if (bean.insertAccount() == true) {
                    request.setAttribute("RegisterSuccess", "Register successful");
                    if (role.equalsIgnoreCase("admin")) {
                        url = ADMIN;
                    } else {
                        url = USER;
                    }
                } else {
                    request.setAttribute("RegisterFailed", "Register failed");
                }
            } else {
                request.setAttribute("INVALID", errObj);
            }
        } catch (Exception e) {
            log("ERROR at RegisterController: " + e.getMessage());
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
