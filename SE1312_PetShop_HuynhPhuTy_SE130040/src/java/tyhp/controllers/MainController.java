/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "index.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String REGISTER = "RegisterController";
    private static final String UPDATE_ACCOUNT = "UpdateAccountController";
    private static final String VIEW_ACCOUNT = "ViewAccountInfoController";
    private static final String MANAGE_ACCOUNT = "ManageAccountController";
    private static final String DELETE_ACCOUNT = "DeleteAccountController";
    private static final String CHANGE_PASSWORD = "ChangePasswordController";
    private static final String PET_DETAILS = "PetDetailController";
    private static final String UPDATE_PET = "UpdatePetController";
    private static final String ADD_PET = "AddPetController";
    private static final String DELETE_PET = "DeletePetController";
    private static final String CHECK_USERNAME = "CheckAccountController";
    private static final String SHOPPING = "ShoppingController";
    private static final String ADD_CART_ACCESSORY = "AddAccCartController";
    private static final String DETAIL_CART = "CartDetailController";
    private static final String UPDATE_CART_ACCESSORY = "UpdateAccCartController";
    private static final String DELETE_CART_ACCESSORY = "DeleteAccCartController";
    private static final String INSERT_INVOICE = "InsertInvoiceController";
    private static final String MANAGE_ACCESSORY = "ManageAccessory";
    private static final String EDIT_ACCESSORY = "EditAccessoryController";
    private static final String UPDATE_ACCESSORY = "UpdateAccessoryController";
    private static final String DELETE_ACCESSORY = "DeleteAccessoryController";
    private static final String INSERT_ACCESSORY = "InsertAccessoryController";
    private static final String MANAGE_SERVICE = "ManageServiceController";
    private static final String INSERT_SERVICE = "InsertServiceController";
    private static final String EDIT_SERVICE = "EditServiceController";
    private static final String DELETE_SERVICE = "DeleteServiceController";
    private static final String UPDATE_SERVICE = "UpdateServiceController";
    private static final String ADD_SERVICE_CART = "AddSerCartController";
    private static final String UPDATE_SERVICE_CART = "UpdateSerCartController";
    private static final String DELETE_SERVICE_CART = "DeleteSerCartController";
    private static final String SEARCH_INVOICE_USERNAME = "SearchInvoiceController";
    private static final String SHOW_INVOICE_USERNAME = "ShowInvoiceController";

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
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("Show Invoice Detail")) {
                url = SHOW_INVOICE_USERNAME;
            }
            if (action.equalsIgnoreCase("Search Invoice by username")) {
                url = SEARCH_INVOICE_USERNAME;
            }
            if (action.equalsIgnoreCase("Login")) {
                url = LOGIN;
            }
            if (action.equalsIgnoreCase("Logout")) {
                url = LOGOUT;
            }
            if (action.equalsIgnoreCase("Register")) {
                url = REGISTER;
            }
            if (action.equalsIgnoreCase("UpdateAccount")) {
                url = UPDATE_ACCOUNT;
            }
            if (action.equalsIgnoreCase("View Account")) {
                url = VIEW_ACCOUNT;
            }
            if (action.equalsIgnoreCase("Manage Account")) {
                url = MANAGE_ACCOUNT;
            }
            if (action.equalsIgnoreCase("Delete")) {
                url = DELETE_ACCOUNT;
            }
            if (action.equalsIgnoreCase("Change password")) {
                url = CHANGE_PASSWORD;
            }
            if (action.equalsIgnoreCase("Pet details")) {
                url = PET_DETAILS;
            }
            if (action.equalsIgnoreCase("Update Pet")) {
                url = UPDATE_PET;
            }
            if (action.equalsIgnoreCase("Add Pet")) {
                url = ADD_PET;
            }
            if (action.equalsIgnoreCase("Check username")) {
                url = CHECK_USERNAME;
            }
            if (action.equalsIgnoreCase("Delete pet")) {
                url = DELETE_PET;
            }
            if (action.equalsIgnoreCase("Shopping")) {
                url = SHOPPING;
            }
            if (action.equalsIgnoreCase("Add accessory to cart")) {
                url = ADD_CART_ACCESSORY;
            }
            if (action.equalsIgnoreCase("View Your Shopping Cart")) {
                url = DETAIL_CART;
            }
            if (action.equalsIgnoreCase("Update Accessory Cart")) {
                url = UPDATE_CART_ACCESSORY;
            }
            if (action.equalsIgnoreCase("Delete Accessory Cart")) {
                url = DELETE_CART_ACCESSORY;
            }
            if (action.equalsIgnoreCase("Add to invoice")) {
                url = INSERT_INVOICE;
            }
            if (action.equalsIgnoreCase("Manage Accessory")) {
                url = MANAGE_ACCESSORY;
            }
            if (action.equalsIgnoreCase("Edit Accessory")) {
                url = EDIT_ACCESSORY;
            }
            if (action.equalsIgnoreCase("Update Accessory")) {
                url = UPDATE_ACCESSORY;
            }
            if (action.equalsIgnoreCase("Delete Accessory")) {
                url = DELETE_ACCESSORY;
            }
            if (action.equalsIgnoreCase("Insert Accessory")) {
                url = INSERT_ACCESSORY;
            }
            if (action.equalsIgnoreCase("Manage Service")) {
                url = MANAGE_SERVICE;
            }
            if (action.equalsIgnoreCase("Edit Service")) {
                url = EDIT_SERVICE;
            }
            if (action.equalsIgnoreCase("Update Service")) {
                url = UPDATE_SERVICE;
            }
            if (action.equalsIgnoreCase("Delete Service")) {
                url = DELETE_SERVICE;
            }
            if (action.equalsIgnoreCase("Insert Service")) {
                url = INSERT_SERVICE;
            }
            if (action.equalsIgnoreCase("Add to service cart")) {
                url = ADD_SERVICE_CART;
            }
            if (action.equalsIgnoreCase("Update Service Cart")) {
                url = UPDATE_SERVICE_CART;
            }
            if (action.equalsIgnoreCase("Delete Service Cart")) {
                url = DELETE_SERVICE_CART;
            } else {
                request.setAttribute("ERROR", "This action is not supported");
            }
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
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
