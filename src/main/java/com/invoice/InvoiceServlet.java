package com.invoice;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "InvoiceServlet", urlPatterns = {"invoice"}, loadOnStartup = 1)
public class InvoiceServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String value = request.getParameter("value");

        ByteArrayOutputStream byteArrayOutputStream = new InvoiceCreator().create(name, value);
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=" + "Invoice.pdf");
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
