package com.money.models;

public class Exchange {
    private String data;
    private Double cena;

    public Exchange(String d, Double c)
    {
      data=d;
      cena=c;
    }

    public String getData() {
        return data;
    }

    public double getCena() {
        return cena;
    }
}
