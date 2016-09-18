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

    public void setY(MathematicalExpression y) {
        this.y = y;
    }

    public List<Inequality> getLimits() {
        return limits;
    }

    public void setLimits(ArrayList<Inequality> limits) {
        this.limits = limits;
    }

    public void addLimit(Inequality limit) {
        limits.add(limit);
    }
}
