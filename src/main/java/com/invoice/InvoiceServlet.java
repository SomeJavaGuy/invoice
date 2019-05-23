package com.invoice;

import com.invoice.data.DBController;
import com.invoice.data.Product;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InvoiceServlet", urlPatterns = {"invoice"}, loadOnStartup = 1)
public class InvoiceServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String nettoPrice = request.getParameter("netto-price");
        String vat = request.getParameter("vat");
        Product product = new Product(name, nettoPrice, vat); // Creating new product with sent specification.

        DBController databaseController = new DBController();

        databaseController.addProductToDatabase(product); // Adding new product to database.

        List<Product> productList = new ArrayList<>(); // Creating empty list of products.
        productList.addAll(databaseController.getProductsList()); // Adding all products from database to created list.

        ByteArrayOutputStream byteArrayOutputStream = new InvoiceCreator().create(productList); // Creating PDF with list of products and saving it as output.

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

    }

}
