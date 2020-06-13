package com.askm.estudo.pluralsight.jdbc;

public class Product {

    public String ProductCode;
    public String ProductName;
    public String ProductLine;
    public Double BuyPrice;

    public String getDetails() {
        return String.format("Code: %s Name: %s (%s) Price: %s"
                , this.ProductCode
                , this.ProductName
                , this.ProductLine
                , this.BuyPrice);
    }

}
