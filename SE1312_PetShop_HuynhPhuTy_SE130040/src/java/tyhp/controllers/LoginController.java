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
import tyhp.dtos.AccCart;
import tyhp.dtos.Account;
import tyhp.dtos.Pet;
import tyhp.dtos.ServiceCart;
import tyhp.dtos.ShoppingCart;
import tyhp.models.AccessoryCartBean;
import tyhp.models.AccountBean;
import tyhp.models.PetBean;
import tyhp.models.ServiceCartBean;

/**
 *
 * @author ACER
 */
public class LoginController extends HttpServlet {

    private static final String INVALID = "login.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String CUSTOMER = "index.jsp";
    private static final String ERROR = "error.jsp";

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
            session.removeAttribute("ACCOUNT");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username.isEmpty() || password.isEmpty()) {
                request.setAttribute("INVALID", "Username and Password should not be empty");
            } else {
                AccountBean bean = new AccountBean();
                bean.setPassword(password);
                bean.setUsername(username);
                String role = bean.checkLogin();
                if (role.equalsIgnoreCase("FAILED")) {
                    request.setAttribute("INVALID", "Username or Password is incorrect");
                } else {
                    Account dto;
                    if (role.equalsIgnoreCase("ADMIN")) {
                        dto = bean.getAccount();
                        session.setAttribute("ACCOUNT", dto);
                        url = ADMIN;
                    } else if (role.equalsIgnoreCase("USER")) {
                        //luu account vao session
                        dto = bean.getAccount();
                        session.setAttribute("ACCOUNT", dto);
                        //khoi tao shopping cart
                        ShoppingCart cart = new ShoppingCart(username);
                        PetBean petBean = new PetBean();
                        //lay accessory va service cart tu database
                        AccessoryCartBean accCartBean = new AccessoryCartBean();
                        ServiceCartBean serCartBean = new ServiceCartBean();
                        petBean.setUsername(username);
                        List<Pet> listPet = petBean.getPetByUsername();
                        if (listPet.isEmpty() == false) {
                            for (Pet pet : listPet) {
                                accCartBean.setPetId(pet.getId());
                                List<AccCart> accCartList = accCartBean.getAccCartByPetId();
                                if (accCartList.isEmpty() == false) {
                                    for (AccCart accCart : accCartList) {
                                        cart.addAccCart(accCart);
                                    }
                                }
                                serCartBean.setPetId(pet.getId());
                                List<ServiceCart> serCartList = serCartBean.getServiceCartByPetId();
                                for (ServiceCart serCart : serCartList) {
                                    if (serCart.isStatus() == true) {
                                        cart.addSerCart(serCart);
                                    }
                                }
                            }
                        }
                        //lay service cart tu database

                        //luu shopping cart vao session
                        session.setAttribute("CART", cart);
                        //set url
                        url = CUSTOMER;
                    } else {
                        request.setAttribute("ERROR", "Your role is not supported");
                        url = ERROR;
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
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
