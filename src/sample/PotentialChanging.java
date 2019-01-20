package sample;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

import java.util.ArrayList;
import java.util.List;

public class PotentialChanging implements StepHandler {
    private List<Double> potential;
    private List<Double> uVariable;
    private List<Double> time;

    public PotentialChanging() {
        this.potential = new ArrayList<Double>();
        this.uVariable = new ArrayList<Double>();
        this.time = new ArrayList<Double>();
    }

    public void init(double v, double[] doubles, double v1) {
    }

    public void handleStep(StepInterpolator stepInterpolator, boolean b)
            throws MaxCountExceededException {
        double t = stepInterpolator.getCurrentTime();
        double[] x = stepInterpolator.getInterpolatedState();
        time.add(t);
        potential.add(x[0]);
        uVariable.add(x[1]);
    }

    public List<Double> getPotential() {
        return potential;
    }

    public List<Double> getuVariable() {
        return uVariable;
    }

    public List<Double> getTime() {
        return time;
    }
}
