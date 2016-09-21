package com.dubyniak.bohdan.linearprogramming.interfaces;

import com.dubyniak.bohdan.linearprogramming.objects.Point;
import com.dubyniak.bohdan.linearprogramming.objects.TaskData;

import java.util.List;

public interface LinearProgrammingTask {

    void solve();

    TaskData getTaskData();

    List getListOfAllPoints();

    List getListOfAreaPoints();

    boolean isMinPoint();

    boolean isMaxPoint();

    Point getPointOfMinValue();

    Point getPointOfMaxValue();

    double getMinValue();

    double getMaxValue();

}
