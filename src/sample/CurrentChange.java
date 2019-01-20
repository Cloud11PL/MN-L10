package sample;

import org.apache.commons.math3.ode.events.EventHandler;

public class CurrentChange implements EventHandler {
    private PotentialEquation potentialEquation;
    private double time;

    public CurrentChange(PotentialEquation potentialEquation) {
        this.potentialEquation = potentialEquation;
    }

    public void init(double start, double[] doubles, double stop) {
        time = stop;
    }

    public double g(double t, double[] doubles) {
        return t - time * 0.15;
    }

    public Action eventOccurred(double t, double[] doubles, boolean b) {
        return Action.RESET_STATE;
    }

    public void resetState(double v, double[] doubles) {
        potentialEquation.setI(10);
    }
}