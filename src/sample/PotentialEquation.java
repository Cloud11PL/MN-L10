package sample;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class PotentialEquation implements FirstOrderDifferentialEquations {
    private double a;
    private double b;
    private double i = 0.0;

    public PotentialEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public int getDimension() {
        return 2;
    }

    public void computeDerivatives(double t, double[] x, double[] dxdt)
            throws MaxCountExceededException, DimensionMismatchException {
        dxdt[0] = 0.04 * Math.pow(x[0], 2) + 5 * x[0] + 140 - x[1] + i;
        dxdt[1] = a * ((b * x[0]) - x[1]);
    }

    public void setI(double i) {
        this.i = i;
    }
}
