package com.invoice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class InvoiceCreator {

    public ByteArrayOutputStream create(String name, String value) {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        Chunk nameChunk = new Chunk("Name: " + name , font);
        Chunk valueChunk = new Chunk("Value: " + value, font);

        Paragraph firstParagraph = new Paragraph();
        Paragraph secondParagraph = new Paragraph();

        firstParagraph.add(nameChunk);
        secondParagraph.add(valueChunk);


        try {
            document.add(firstParagraph);
            document.add(secondParagraph);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();

        return byteArrayOutputStream;
    }

}
