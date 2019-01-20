package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;

import java.util.List;

public class Controller {
    private double a = 0.2;
    private double b = 0.25;
    private double c = -65;
    private double d = 0.05;
    private double current = 40;
    private double timeStop = 10;
    @FXML
    private LineChart<Number, Number> potentialChart;
    @FXML
    private LineChart<Number, Number> uChart;
    @FXML
    private LineChart<Number, Number> intensityChart;
    @FXML
    private TextField aText;
    @FXML
    private TextField bText;
    @FXML
    private TextField cText;
    @FXML
    private TextField dText;
    @FXML
    private TextField currentText;
    @FXML
    private TextField timeText;

    @FXML
    void onClickCalculate(ActionEvent event) {
        Chart chart = new Chart();

        a = Double.parseDouble(aText.getText());
        b = Double.parseDouble(bText.getText());
        c = Double.parseDouble(cText.getText());
        d = Double.parseDouble(dText.getText());

        timeStop = Double.parseDouble(timeText.getText());
        current = Double.parseDouble(currentText.getText());

        FirstOrderDifferentialEquations potentialEquation = new PotentialEquation(a, b);
        FirstOrderIntegrator integrator = new ClassicalRungeKuttaIntegrator(0.01);
        PotentialChanging potentialChanging = new PotentialChanging();

        integrator.addStepHandler(potentialChanging);
        MaxPoint maxPoint = new MaxPoint(c, d);

        integrator.addEventHandler(maxPoint, 0.1, 0.001, 2000);
        CurrentChange currentChange = new CurrentChange((PotentialEquation) potentialEquation);
        integrator.addEventHandler(currentChange, 0.1, 0.001, 2000);

        double[] xStart = {c, c * b};
        double[] xStop = {0, 0};

        integrator.integrate(potentialEquation, 0, xStart, timeStop, xStop);

        potentialChart.getData().clear();
        intensityChart.getData().clear();
        uChart.getData().clear();

        List<Double> potentialList = potentialChanging.getPotential();
        List<Double> time = potentialChanging.getTime();
        List<Double> uVariableList = potentialChanging.getuVariable();

        intensityChart.getData().addAll(chart.currentSeriesChart(time,timeStop,current));
        potentialChart.getData().addAll(chart.potentialSeriesChart(potentialList,time));
        uChart.getData().addAll(chart.uSeriesChart(uVariableList,time));
    }
}