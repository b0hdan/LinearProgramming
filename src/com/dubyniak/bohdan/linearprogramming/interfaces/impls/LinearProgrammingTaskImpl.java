package com.dubyniak.bohdan.linearprogramming.interfaces.impls;

import com.dubyniak.bohdan.linearprogramming.interfaces.LinearProgrammingTask;
import com.dubyniak.bohdan.linearprogramming.objects.Inequality;
import com.dubyniak.bohdan.linearprogramming.objects.Point;
import com.dubyniak.bohdan.linearprogramming.objects.TaskData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LinearProgrammingTaskImpl implements LinearProgrammingTask {
    private TaskData taskData;
    private List<Point> allPoints;
    private List<Point> areaPoints;
    private boolean isMinPoint, isMaxPoint;
    private double minValue, maxValue;
    private Point minPoint, maxPoint;

    public LinearProgrammingTaskImpl() {
        taskData = new TaskData();
        allPoints = new ArrayList<>();
        areaPoints = new ArrayList<>();
    }

    @Override
    public void solve() {
        taskData.getLimits().add(new Inequality(1, 0, 0, ">=", 0));
        taskData.getLimits().add(new Inequality(0, 1, 0, ">=", 0));
        replaceY();
        changeAllSignsToGreaterEqual();
        findAllPoints();
        findAreaPoints();
        findMinPointAndValue();
        findMaxPointAndValue();
    }

    @Override
    public TaskData getTaskData() {
        return taskData;
    }

    @Override
    public List<Point> getListOfAllPoints() {
        return allPoints;
    }

    @Override
    public List<Point> getListOfAreaPoints() {
        return areaPoints;
    }

    @Override
    public boolean isMinPoint() {
        return isMinPoint;
    }

    @Override
    public boolean isMaxPoint() {
        return isMaxPoint;
    }

    @Override
    public Point getPointOfMinValue() {
        return minPoint;
    }

    @Override
    public Point getPointOfMaxValue() {
        return maxPoint;
    }

    @Override
    public double getMinValue() {
        return minValue;
    }

    @Override
    public double getMaxValue() {
        return maxValue;
    }

    private void replaceY() {
        if (taskData.getPurposeFunction().getYKoef() != 0) {
            double k1 = taskData.getPurposeFunction().getK1();
            double k2 = taskData.getPurposeFunction().getK2();
            double yKoef = taskData.getPurposeFunction().getYKoef();
            taskData.getPurposeFunction().setK1(k1 + yKoef * taskData.getY().getK1());
            taskData.getPurposeFunction().setK2(k2 + yKoef * taskData.getY().getK2());
            taskData.getPurposeFunction().setYKoef(0);
        }
        for (int i = 0; i < taskData.getLimits().size(); i++) {
            if (taskData.getLimits().get(i).getYKoef() != 0) {
                double k1 = taskData.getLimits().get(i).getK1();
                double k2 = taskData.getLimits().get(i).getK2();
                double yKoef = taskData.getLimits().get(i).getYKoef();
                taskData.getLimits().get(i).setK1(k1 + yKoef * taskData.getY().getK1());
                taskData.getLimits().get(i).setK2(k2 + yKoef * taskData.getY().getK2());
                taskData.getLimits().get(i).setYKoef(0);
            }
        }
    }

    private void changeAllSignsToGreaterEqual() {
        for (Inequality limit : taskData.getLimits()) {
            if (limit.getSign().equals("<=")) {
                limit.setSign(">=");
                limit.setK1(-limit.getK1());
                limit.setK2(-limit.getK2());
                limit.setYKoef(-limit.getYKoef());
                limit.setB(-limit.getB());
            }
        }
    }

    private void findAllPoints() {
        for (int i = 0; i < taskData.getLimits().size() - 1; i++)
            for (int j = i + 1; j < taskData.getLimits().size(); j++) {
                Point point = solveByKramer(taskData.getLimits().get(i), taskData.getLimits().get(j));
                if (point != null && !allPoints.contains(point))
                    allPoints.add(point);
            }
    }

    private void findAreaPoints() {
        areaPoints.addAll(allPoints.stream().filter(this::isAreaPoint).collect(Collectors.toList()));
    }

    private Point solveByKramer(Inequality equation1, Inequality equation2) {
        double determinant = equation1.getK1() * equation2.getK2() - equation1.getK2() * equation2.getK1();
        double determinant1 = equation1.getB() * equation2.getK2() - equation1.getK2() * equation2.getB();
        double determinant2 = equation1.getK1() * equation2.getB() - equation1.getB() * equation2.getK1();
        if (determinant == 0)
            return  null;
        Point point = new Point();
        point.setX(determinant1 / determinant);
        point.setY(determinant2 / determinant);
        return point;
    }

    private boolean isAreaPoint(Point point) {
        for (Inequality limit : taskData.getLimits())
            if (limit.getK1() * point.getX() + limit.getK2() * point.getY() < limit.getB())
                return false;
        return true;
    }

    private void findMinPointAndValue() {
        isMinPoint = areaPoints.size() != 0;
        minValue = evaluatePurposeFunctionValue(areaPoints.get(0));
        minPoint = new Point(areaPoints.get(0));
        for (Point temp : areaPoints) {
            double value = evaluatePurposeFunctionValue(temp);
            if (minValue > value) {
                minValue = value;
                minPoint = new Point(temp);
            }
        }
    }

    private void findMaxPointAndValue() {
        isMaxPoint = areaPoints.size() != 0;
        maxValue = evaluatePurposeFunctionValue(areaPoints.get(0));
        maxPoint = new Point(areaPoints.get(0));
        for (Point temp : areaPoints) {
            double value = evaluatePurposeFunctionValue(temp);
            if (maxValue < value) {
                maxValue = value;
                maxPoint = new Point(temp);
            }
        }
    }

    private double evaluatePurposeFunctionValue(Point point) {
        return taskData.getPurposeFunction().getK1() * point.getX() +
                taskData.getPurposeFunction().getK2() * point.getY();
    }

}
