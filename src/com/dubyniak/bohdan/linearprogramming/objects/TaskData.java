package com.dubyniak.bohdan.linearprogramming.objects;

import java.util.ArrayList;
import java.util.List;

public class TaskData {
    private MathematicalExpression purposeFunction;
    private MathematicalExpression y;
    private List<Inequality> limits;

    public TaskData() {
        limits = new ArrayList<>();
    }

    public MathematicalExpression getPurposeFunction() {
        return purposeFunction;
    }

    public void setPurposeFunction(MathematicalExpression purposeFunction) {
        this.purposeFunction = purposeFunction;
    }

    public MathematicalExpression getY() {
        return y;
    }

    public void setY(double k1, double k2) {
        y = new MathematicalExpression(k1, k2, 0);
    }

    public List<Inequality> getLimits() {
        return limits;
    }

    public void setLimits(ArrayList<Inequality> limits) {
        this.limits = limits;
    }

}
