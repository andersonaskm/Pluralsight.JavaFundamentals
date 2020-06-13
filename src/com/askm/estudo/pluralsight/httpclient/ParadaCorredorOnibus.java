package com.askm.estudo.pluralsight.httpclient;

public class ParadaCorredorOnibus {

    public int cp;
    public String np;
    public String ed;
    public Double py;
    public Double px;

    public String getDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Id: %s", this.cp));
        sb.append(String.format(" Nome: %s", this.np));
        sb.append(String.format(" Endereco: %s", this.ed));
        sb.append(String.format(" Lat: %s", this.py));
        sb.append(String.format(" Long: %s", this.px));
        return sb.toString();
    }

}
