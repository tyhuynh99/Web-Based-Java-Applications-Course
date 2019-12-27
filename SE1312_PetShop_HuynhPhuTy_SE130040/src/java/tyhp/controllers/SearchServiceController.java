/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tyhp.dtos.Account;
import tyhp.dtos.Pet;
import tyhp.dtos.Service;
import tyhp.dtos.ServiceCart;
import tyhp.dtos.Slot;
import tyhp.models.PetBean;
import tyhp.models.ServiceBean;
import tyhp.models.ServiceCartBean;

/**
 *
 * @author ACER
 */
public class SearchServiceController extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("ACCOUNT");
        try {
            String d = request.getParameter("date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(d);
            Date currDate = new Date();
            if (currDate.getTime() - date.getTime() < 0) {
                if (sdf.format(date).equals(d)) {
//                Lấy danh sách service 
                    ServiceBean serBean = new ServiceBean();
                    List<Service> serList = serBean.getService();
                    Map<String, Service> serMap = new HashMap<>();
                    for (Service dto : serList) {
                        serMap.put(dto.getId(), dto);
                    }
//                lấy danh sách service đã được book
                    ServiceCartBean serCartBean = new ServiceCartBean();
                    serCartBean.setDate(date);
                    List<ServiceCart> serCartList = serCartBean.getServiceCartByDate();
//                Cập nhật slot còn trống trong ngày
                    for (ServiceCart dto : serCartList) {
                        if (serMap.containsKey(dto.getServiceId())) {
                            int bookSlot = dto.getSlot();
                            Service tmpService = serMap.get(dto.getServiceId());
                            List<Slot> slotList = tmpService.getListSlot();
                            Slot slot = slotList.get(bookSlot - 1);
                            slot.setStatus(false);
                        }
                    }
                    Collection<Service> serCollection = serMap.values();
                    for (Service dto : serCollection) {
                        List<Slot> slotList = dto.getListSlot();
                        boolean checkStock = false;
                        for (Slot slot : slotList) {
                            if (slot.isStatus() == true) {
                                checkStock = true;
                            }
                        }
                        dto.setStatus(checkStock);
                    }
                    PetBean petBean = new PetBean();
                    petBean.setUsername(account.getUsername());
                    List<Pet> listPet = petBean.getPetByUsername();
                    request.setAttribute("LIST_PET", listPet);
                    request.setAttribute("SERVICE_LIST", serCollection);
//                request.setAttribute("INVALID_DATE", "OK");
                } else {
                    request.setAttribute("INVALID_DATE", "WRONG DATE FORMAT");
                }
            } else {
                request.setAttribute("INVALID_DATE", "Date must be after current day");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at SearchServiceController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("service_book.jsp").forward(request, response);
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
