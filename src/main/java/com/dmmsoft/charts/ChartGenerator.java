package com.dmmsoft.charts;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.trend.QuotationSeries;
import com.dmmsoft.app.analyzer.analyses.trend.QuotationSeriesCriteria;
import com.dmmsoft.app.analyzer.analyses.trend.QuotationSeriesResult;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.app.model.Quotation;
import com.dmmsoft.container.IModelContainerService;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.DayAndMonthRule;
import org.jfree.date.SerialDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by milo on 10.07.17.
 */

public class ChartGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChartGenerator.class);

    private String chartTitle;
    private IModelContainerService mainContainer;

    public ChartGenerator(String chartTitle, IModelContainerService mainContainer) {
        this.mainContainer = mainContainer;
        this.chartTitle = chartTitle;
    }


    public JFreeChart renderChart() throws NoDataForCriteria {
/*        final XYDataset dataset = createRandomDataset();
        final JFreeChart chart = createChart(dataset);*/

        final XYDataset dataset = createDataset();
        final JFreeChart chart = createDayChart(dataset);

        return chart;
    }

    private XYDataset createRandomDataset() {
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

    private XYDataset createDataset() throws NoDataForCriteria {

        BigDecimal value = new BigDecimal("100");
        String name = "CHF";

        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate startDATE = LocalDate.parse("20090910", formatter);
        LocalDate endDATE = LocalDate.parse("20170404", formatter);


        QuotationSeriesCriteria criteria = new QuotationSeriesCriteria(name, startDATE, endDATE);


        List<Quotation> quotations = (List<Quotation>) new QuotationSeries(mainContainer, criteria).getResult();


        final TimeSeries series = new TimeSeries("Random Data");
        Day current = new Day();

        for (Quotation item : quotations) {
            try {
                value = item.getClose();
                series.add(current, value);
                current = (Day) current.next();

            } catch (SeriesException e) {
                LOGGER.error("Failed to generate series");
            }



        }
        return new TimeSeriesCollection(series);
    }
    private JFreeChart createDayChart(final XYDataset dataset){
        return ChartFactory.createTimeSeriesChart(
                this.chartTitle, "time", "value",
                dataset, false, false, false);

    }

    private JFreeChart createChart(final XYDataset dataset) {
        return ChartFactory.createTimeSeriesChart(
                this.chartTitle, "time", "value",
                dataset, false, false, false);
    }
}


