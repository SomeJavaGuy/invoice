package com.invoice;

import com.invoice.data.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class InvoiceCreator {

    public ByteArrayOutputStream create(List<Product> productList) {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.open();
        for (Product product: productList) {

            Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
            Chunk nameChunk = new Chunk("Name: " + product.getName(), font);
            Chunk nettoPriceChunk = new Chunk("Netto price: " + product.getNettoPrice(), font);
            Chunk vatChunk = new Chunk("  Vat: " + product.getVAT() + "%", font);
            Chunk bruttoPriceChunk = new Chunk("  Brutto price: " + product.getBruttoPrice(), font);

            Paragraph firstParagraph = new Paragraph();
            Paragraph secondParagraph = new Paragraph();


            firstParagraph.add(nameChunk);
            secondParagraph.add(nettoPriceChunk);
            secondParagraph.add(vatChunk);
            secondParagraph.add(bruttoPriceChunk);


            try {
                document.add(firstParagraph);
                document.add(secondParagraph);

            } catch (DocumentException e) {
                e.printStackTrace();
            }

        }
        document.close();

        return byteArrayOutputStream;
    }

}
