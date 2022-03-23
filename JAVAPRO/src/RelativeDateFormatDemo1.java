import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.RelativeDateFormat;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class RelativeDateFormatDemo1 extends ApplicationFrame {

    public RelativeDateFormatDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Exercise Chart",        // title
            "Elapsed Time",             // x-axis label
            "Beats Per Minute",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
        );


        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        Minute base = new Minute(0, 9, 1, 10, 2006);
        RelativeDateFormat rdf = new RelativeDateFormat(base.getFirstMillisecond());
        rdf.setSecondFormatter(new DecimalFormat("00"));
        axis.setDateFormatOverride(rdf);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;

    }

    private static XYDataset createDataset() {

        TimeSeries s1 = new TimeSeries("Heart Rate");
        s1.add(new Second(45, 6, 9, 1, 10, 2006), 143);
        s1.add(new Second(33, 8, 9, 1, 10, 2006), 167);
        s1.add(new Second(10, 10, 9, 1, 10, 2006), 189);
        s1.add(new Second(19, 12, 9, 1, 10, 2006), 156);
        s1.add(new Second(5, 15, 9, 1, 10, 2006), 176);
        s1.add(new Second(12, 16, 9, 1, 10, 2006), 183);
        s1.add(new Second(6, 18, 9, 1, 10, 2006), 138);
        s1.add(new Second(11, 20, 9, 1, 10, 2006), 102);

        TimeSeries s2 = new TimeSeries("Heart Rate2");
        s2.add(new Second(6, 18, 9, 1, 10, 2006), 143);
        s2.add(new Second(11, 20, 9, 1, 10, 2006), 167);
        s2.add(new Second(12, 16, 9, 1, 10, 2006), 189);
        s2.add(new Second(5, 15, 9, 1, 10, 2006), 156);
        s2.add(new Second(19, 12, 9, 1, 10, 2006), 176);
        s2.add(new Second(10, 10, 9, 1, 10, 2006), 183);
        s2.add(new Second(33, 8, 9, 1, 10, 2006), 102);
        s2.add(new Second(45, 6, 9, 1, 10, 2006), 138);
        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        return dataset;

    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {

        RelativeDateFormatDemo1 demo = new RelativeDateFormatDemo1(
                "JFreeChart: RelativeDateFormatDemo1.java");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setSize(1500,800);
        demo.setVisible(true);

    }

}