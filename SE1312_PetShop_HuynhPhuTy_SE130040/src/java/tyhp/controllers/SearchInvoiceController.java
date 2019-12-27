/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyhp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tyhp.dtos.AccCart;
import tyhp.dtos.Invoice;
import tyhp.dtos.ServiceCart;
import tyhp.models.InvoiceBean;

/**
 *
 * @author ACER
 */
public class SearchInvoiceController extends HttpServlet {

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
            String username = request.getParameter("username");
            String d = request.getParameter("date_search");
            InvoiceBean invoiceBean = new InvoiceBean();
            if (username != null && !username.equals("")) {
                invoiceBean.setUsername(username);
                List<Invoice> invoiceDto = invoiceBean.getInvoice();
                if (invoiceDto != null) {
                    request.setAttribute("INVOICE", invoiceDto);
                } else {
                    request.setAttribute("MESS", "CANNOT FIND THIS USERNAME");
                }
            } else if (d != null && !d.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(d);
                System.out.println(sdf.format(date));
                System.out.println("a");
                if (sdf.format(date).equals(d)) {
                    System.out.println("b");
                    invoiceBean.setDate(date);
                    List<Invoice> invoiceDto = invoiceBean.getInvoiceByDate();
                    if (invoiceDto != null) {
                        System.out.println(invoiceDto.size());
                        request.setAttribute("INVOICE", invoiceDto);
                    } else {
                        request.setAttribute("MESS", "CANNOT FIND THIS USERNAME");
                    }
                } else {
                    request.setAttribute("MESS", "INVALID DATE");
                }
            } else {
                request.setAttribute("MESS", "Please input username or date");
            }

        } catch (Exception e) {
            request.setAttribute("MESS", "INVALID DATE OR USERNAME");
            log("ERROR at SearchInvoiceController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("view_invoice.jsp").forward(request, response);
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
