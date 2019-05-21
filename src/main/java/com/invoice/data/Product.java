package com.invoice.data;

import java.math.BigDecimal;

public class Product {

    private String name;
    private String nettoPrice;
    private String VAT;

    public Product() {
    }

    public Product(String name, String nettoPrice, String VAT) {
        this.name = name;
        this.nettoPrice = nettoPrice;
        this.VAT = VAT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNettoPrice() {
        return nettoPrice;
    }

    public void setNettoPrice(String nettoPrice) {
        this.nettoPrice = nettoPrice;
    }

    public String getBruttoPrice() {
        BigDecimal nettoPriceBD = new BigDecimal(nettoPrice);
        BigDecimal vatBD = new BigDecimal(VAT).multiply(BigDecimal.valueOf(0.01));
        BigDecimal bruttoPrice = nettoPriceBD.multiply(vatBD).add(nettoPriceBD);
        return bruttoPrice.toString();
    }

    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }
}
