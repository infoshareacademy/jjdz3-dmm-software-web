package com.dmmsoft.charts;

import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by milo on 10.07.17.
 */

public class ChartGenerator  {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChartGenerator.class);

    public JFreeChart renderChart() {
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        return chart;
    }

    private XYDataset createDataset() {
        final TimeSeries series = new TimeSeries("Random Data");
        Second current = new Second();
        double value = 100.0;

        for (int i = 0; i < 4000; i++) {

            try {
                value = value + Math.random() - 0.5;
                series.add(current, new Double(value));
                current = (Second) current.next();
            } catch (SeriesException e) {
              LOGGER.error("Failed to generate series");
            }
        }
        return new TimeSeriesCollection(series);
    }

    private JFreeChart createChart(final XYDataset dataset) {
        return ChartFactory.createTimeSeriesChart(
                "Prototype","time","value",
                dataset,false,false,false);
    }
}


