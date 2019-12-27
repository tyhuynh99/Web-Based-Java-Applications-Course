/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tyhp.dtos.AccCart;
import tyhp.dtos.Accessory;
import tyhp.dtos.Account;
import tyhp.dtos.Invoice;
import tyhp.dtos.ServiceCart;
import tyhp.dtos.ShoppingCart;
import tyhp.models.AccessoryBean;
import tyhp.models.AccessoryCartBean;
import tyhp.models.InvoiceBean;
import tyhp.models.ServiceCartBean;

/**
 *
 * @author ACER
 */
public class InsertInvoiceController extends HttpServlet {

    private static final String SUCCESS = "ShoppingController";
    private static final String FAIL = "CartDetailController";

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
        String url = SUCCESS;
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("CART");
        List<String> listAccKeyNotEnough = null;
        Map<String, AccCart> accCartMap = cart.getAccCart();
        Collection<AccCart> accCartCollection = accCartMap.values();
        Account account = (Account) session.getAttribute("ACCOUNT");
        String payId, address, orderId, username;
        Date currentDate = new Date();
        Invoice invoice;
        InvoiceBean invoiceBean;
        AccessoryCartBean accCartBean;
        AccessoryBean accBean;
        try {
            if (cart.getCountAccCart() + cart.getCountSerCart() == 0) {
                request.setAttribute("INSERT_INVOICE_SUCCESS", "PLEASE ADD ACCESSORY BEFORE CHECK OUT !!");
            } else {
                //Kiểm tra số lượng toàn bộ cart
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaacc: " + cart.getCountAccCart());
                listAccKeyNotEnough = cart.checkEnoughQuantity();
                if (listAccKeyNotEnough.isEmpty()) {
                    String phone = request.getParameter("phone");
                    if (phone.matches("\\d{10}")) {
//            Lấy thông tin invoice
                        payId = request.getParameter("payment");
                        address = request.getParameter("addr");
                        //orderId = username-ORDER_DATE
                        orderId = account.getUsername() + "-" + currentDate.toString() + "-" + java.time.LocalTime.now().toString();
                        username = account.getUsername();
                        invoice = new Invoice(orderId, username, payId, phone, address, currentDate);
                        //Lưu invoice vào database
                        invoiceBean = new InvoiceBean();
                        invoiceBean.setInvoiceDto(invoice);
                        if (invoiceBean.insertInvoice() == true) {
                            //Lấy Accessory Cart
                            accCartBean = new AccessoryCartBean();
                            //Insert Accessory Cart vào database
                            ArrayList<String> accCartKeys = new ArrayList<>();
                            for (AccCart accCartDto : accCartCollection) {
                                //kiểm trả số lượng hàng trong database
                                int newQuantity = accCartDto.getAccQuantity() - accCartDto.getQuantity();
                                //Nếu hàng còn dủ thì mới cho order
                                invoiceBean.setAccCartDto(accCartDto);
                                invoiceBean.setOrderId(orderId);
                                if (invoiceBean.insertAccInvoice() == true) {
                                    //insert vào HaveAccessories cho Pet
                                    if (invoiceBean.insertAccForPet() == true) {
                                        //insert thành công thì cập nhật quantity
                                        accBean = new AccessoryBean();
                                        accBean.setId(accCartDto.getAccId());
                                        accBean.setQuantity(newQuantity);
                                        if (accBean.updateQuantityAfterOrder() == true) {
                                            //xóa trong database
                                            accCartBean.setAccId(accCartDto.getAccId());
                                            accCartBean.setPetId(accCartDto.getPetId());
                                            if (accCartBean.delete() == true) {
                                                //Xóa trong db thành công thì lưu lại key để xóa trong session
                                                String key = accCartDto.getAccId() + "-" + accCartDto.getPetId();
                                                accCartKeys.add(key);
                                            }
                                        }
                                    }
                                }
                            }
                            //Xóa trong session những key đã lưu vào db
                            if (!accCartKeys.isEmpty()) {
                                for (String keys : accCartKeys) {
                                    accCartMap.remove(keys);
                                }
                            }
                            //Insert Service Cart vào database
                            Map<String, ServiceCart> serCartMap = cart.getSerCart();
                            Collection<ServiceCart> serCartCollection = serCartMap.values();
                            ArrayList<String> serCartKeys = new ArrayList<>();
                            for (ServiceCart serCartDto : serCartCollection) {
                                invoiceBean.setSerCartDto(serCartDto);
                                if (invoiceBean.insertSerInvoice() == true) {
                                    //cập nhật status trong db
                                    serCartDto.setStatus(false);
                                    ServiceCartBean serCartBean = new ServiceCartBean();
                                    serCartBean.setDto(serCartDto);
                                    if (serCartBean.updateStatus() == true) {
                                        //lưu lại key
                                        String key = serCartDto.getServiceId() + "-" + serCartDto.getPetId();
                                        serCartKeys.add(key);
                                    }
                                }
                            }
                            //xóa trong session
                            if (!serCartKeys.isEmpty()) {
                                for (String keys : serCartKeys) {
                                    serCartMap.remove(keys);
                                }
                            }
                            //Xuất thông báo
                            request.setAttribute("INSERT_INVOICE_SUCCESS", "ORDER SUCCESSFUL");
                        } else {
                            url = FAIL;
                            request.setAttribute("INSERT_INVOICE_INVALID", "CANNOT ORDER. PLEASE TRY AGAIN !!!");
                        }
                    } else {
                        url = FAIL;
                        request.setAttribute("INSERT_INVOICE_INVALID", "PHONE NUMBER MUST BE 10 DIGITS");
                    }
                } else {
                    url = FAIL;
                    request.setAttribute("OUT_OF_STOCK", "SORRY, THIS ACCESSORY IS OUT OF STOCK");
                    request.setAttribute("LIST_KEY_NOT_ENOUGH", listAccKeyNotEnough);
                }
            }
        } catch (Exception e) {
            url = FAIL;
            request.setAttribute("INSERT_INVOICE_INVALID", "CANNOT ORDER. PLEASE TRY AGAIN !!!");
            e.printStackTrace();
            log("ERROR at InsertInvoiceController: " + e.getMessage());
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
