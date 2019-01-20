package sample;

import org.apache.commons.math3.ode.events.EventHandler;

public class MaxPoint implements EventHandler {
    private static final int THRESHOLD = 30;
    private int sign;
    private double c;
    private double d;

    public MaxPoint(double c, double d) {
        this.c = c;
        this.d = d;
    }

    public void init(double t, double[] x, double dxdt) {
        sign = 1;
    }

    public double g(double t, double[] x) {
        return THRESHOLD - x[0];
    }

    public Action eventOccurred(double t, double[] x, boolean b) {
        sign = -sign;
        return Action.RESET_STATE;
    }

    public void resetState(double t, double[] x) {
        x[0] = c;
        x[1] = x[1] + d;
    }
}