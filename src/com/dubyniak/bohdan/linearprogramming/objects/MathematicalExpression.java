package com.dubyniak.bohdan.linearprogramming.objects;

public class MathematicalExpression {
    private double k1;
    private double k2;
    private double yKoef;

    public MathematicalExpression(double k1, double k2, double yKoef) {
        this.k1 = k1;
        this.k2 = k2;
        this.yKoef = yKoef;
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

}
