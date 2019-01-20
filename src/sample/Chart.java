package sample;

import javafx.scene.chart.XYChart;
import java.util.List;

public class Chart {
    XYChart.Series potentialSeries = new XYChart.Series();
    XYChart.Series uSeries = new XYChart.Series();
    XYChart.Series currentSeries = new XYChart.Series();

    public XYChart.Series uSeriesChart (List uVariableList, List<Double> time){
        for (int i = 0; i < uVariableList.size() - 1; i++) {
            uSeries.getData().add(new XYChart.Data(time.get(i),
                    uVariableList.get(i)));
        }
        return uSeries;
    }

    public XYChart.Series potentialSeriesChart (List<Double> potentialList, List<Double> time){
        for (int i = 0; i < potentialList.size() - 1; i++) {
            potentialSeries.getData().add(new XYChart.Data(time.get(i),
                    potentialList.get(i)));
        }
        return potentialSeries;
    }

    public XYChart.Series currentSeriesChart (List<Double> time, Double timeStop, Double current){
        for (int i = 0; i < time.size() - 1; i++) {
            double t = time.get(i);
            double iCurr = 0.0;
            if (t > 0.15 * timeStop) {
                iCurr = current;
            }
            currentSeries.getData().add(new XYChart.Data(time.get(i),
                    iCurr));
        }
        return currentSeries;
    }

}