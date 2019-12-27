/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tyhp.dtos.AccCart;
import tyhp.dtos.Account;
import tyhp.dtos.Payment;
import tyhp.dtos.Service;
import tyhp.dtos.ServiceCart;
import tyhp.dtos.ShoppingCart;
import tyhp.dtos.Slot;
import tyhp.models.PaymentBean;
import tyhp.models.ServiceBean;
import tyhp.models.ServiceCartBean;

/**
 *
 * @author ACER
 */
public class CartDetailController extends HttpServlet {

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
        HttpSession session;
        ShoppingCart cart;
        Map<String, AccCart> accCartMap;
        Map<String, ServiceCart> serCartMap;
        Collection<AccCart> listAccCart;
        Collection<ServiceCart> listSerCart;
        Account account;
        PaymentBean payBean;
        List<Payment> payList;
        try {
            session = request.getSession();
            //Lay thong tin gio hang
            cart = (ShoppingCart) session.getAttribute("CART");
            accCartMap = cart.getAccCart();
            serCartMap = cart.getSerCart();
            listAccCart = accCartMap.values();
            listSerCart = serCartMap.values();
            request.setAttribute("ACC_CART_LIST", listAccCart);
            request.setAttribute("SER_CART_LIST", listSerCart);
            //Lay total cua accessory
            if (!listAccCart.isEmpty()) {
                float total = 0;
                for (AccCart dto : listAccCart) {
                    total += dto.getQuantity() * dto.getAccPrice();
                }
                request.setAttribute("TOTAL_ACC", total);
            }
            //lấy total của service
            if (!listSerCart.isEmpty()) {
                float total = 0;
                for (ServiceCart dto : listSerCart) {
                    total += dto.getSerPrice();
                }
                request.setAttribute("TOTAL_SER", total);
            }
            //Xử lý slot service
            //                lấy danh sách service đã được book
            Map<String, List<Slot>> slotAvailable = new HashMap<>();
            for (ServiceCart dto : listSerCart) {
                ServiceCartBean serCartBean = new ServiceCartBean();
                serCartBean.setDate(dto.getDate());
                List<ServiceCart> serBookCartList = serCartBean.getServiceCartByDate();
                ServiceBean serBean = new ServiceBean();
                serBean.setId(dto.getServiceId());
                Service serDto = serBean.getServiceById();
                List<Slot> slot = serDto.getListSlot();
//                System.out.println("slot: " + slot.size());
                for (ServiceCart tmpDto : serBookCartList) {
                    if (!tmpDto.getPetId().equals(dto.getPetId()) && tmpDto.getServiceId().equals(dto.getServiceId())) {
                        for (Slot slotDto : slot) {
                            if (slotDto.getSlot() == tmpDto.getSlot()) {
                                slotDto.setStatus(false);
                            }
                        }
                    }
                }
                slotAvailable.put(dto.getServiceId(), slot);
            }
            request.setAttribute("AVAILABLE_SLOT_LIST", slotAvailable);

            //Lấy thông tin user
            account = (Account) session.getAttribute("ACCOUNT");
            request.setAttribute("ACCOUNT", account);
            //Lấy danh sách payment
            payBean = new PaymentBean();
            payList = payBean.getPaymentList();
            request.setAttribute("PAY_LIST", payList);
        } catch (Exception e) {
            log("ERROR at CartDetailController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("cart_detail.jsp").forward(request, response);
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
