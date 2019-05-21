package com.invoice;

import com.invoice.data.Product;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet(name = "InvoiceServlet", urlPatterns = {"invoice"}, loadOnStartup = 1)
public class InvoiceServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String nettoPrice = request.getParameter("netto-price");
        String vat = request.getParameter("vat");

        Product product = new Product(name, nettoPrice, vat);

        ByteArrayOutputStream byteArrayOutputStream = new InvoiceCreator().create(product);
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=" + "Invoice-" + name + ".pdf");
        response.setContentLength(byteArrayOutputStream.size());

        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            byteArrayOutputStream.writeTo(servletOutputStream);
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //if (name == null) name = "World";
        //request.setAttribute("user", name);
        //request.getRequestDispatcher("response.jsp").forward(request, response);
    }

}
