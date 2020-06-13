package com.askm.estudo.pluralsight.httpclient;

public class CorredorOnibus {

    private int cc;
    private String nc;

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Id: %s", this.cc));
        sb.append(String.format(" Nome: %s", this.nc));
        return sb.toString();
    }

    public void setCc(int cc){
        this.cc = cc;
    }

    public void setNc(String nc){
        this.nc = nc;
    }

}
