package com.dubyniak.bohdan.linearprogramming.objects;

public class Inequality {
    private double k1;
    private double k2;
    private double yKoef;
    private String sign;
    private double b;

    public Inequality(double k1, double k2, double yKoef, String sign, double b) {
        this.k1 = k1;
        this.k2 = k2;
        this.yKoef = yKoef;
        this.sign = sign;
        this.b = b;
    }

    public double getK1() {
        return k1;
    }

    public void setK1(double k1) {
        this.k1 = k1;
    }

    public double getK2() {
        return k2;
    }

    public void setK2(double k2) {
        this.k2 = k2;
    }

    public double getYKoef() {
        return yKoef;
    }

    public void setYKoef(double yKoef) {
        this.yKoef = yKoef;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return k1 + " * x1 + " +
                k2 + " * x2 + " +
                yKoef + " * y " +
                sign + " " + b;
    }

}
