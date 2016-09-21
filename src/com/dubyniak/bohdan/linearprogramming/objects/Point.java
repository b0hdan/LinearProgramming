package com.dubyniak.bohdan.linearprogramming.objects;

public class Point {
    private double x, y;

    public Point() {
    }

    public Point(Point point) {
        x = point.getX();
        y = point.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%.2f; %.2f)", x, y);
    }
}
