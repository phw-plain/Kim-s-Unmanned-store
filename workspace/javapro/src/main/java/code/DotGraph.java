package code;

import java.awt.Color;
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
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class DotGraph {
	public static JPanel createDemoPanel(int idx, int data1[][], int data2[][]) {
		JFreeChart chart = createChart(createDataset(idx, data1, data2));
		return new ChartPanel(chart);
	}

	private static String str1 = "";
	private static String str2 = "";
	private static String str3 = "";

	private static XYDataset createDataset(int idx, int data1[][], int data2[][]) {
		switch (idx) {
		case 1: {
			str1 = "This week";
			str2 = "Last week";
			str3 = "TodaySales";
		}
			break;
		case 2: {
			str1 = "This month";
			str2 = "Last month";
			str3 = "MonthSales";
		}
			break;
		case 3: {
			str1 = "This week";
			str2 = "Last week";
			str3 = "WeekSales";
		}
			break;
		case 4: {
			str1 = "This month";
			str2 = "Last month";
			str3 = "MonthSales";
		}
			break;
		}

		TimeSeries s1 = new TimeSeries(str1); // (초,분,시,일,월,년),마지막이 데이터
		TimeSeries s2 = new TimeSeries(str2);

		for (int i = 0; i < 7; i++) {
			s1.add(new Day(data1[i][0], data1[i][1], data1[i][2]), data1[i][3]);
			s2.add(new Day(data2[i][0], data2[i][1], data2[i][2]), data2[i][3]);
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		dataset.addSeries(s2);

		return dataset;
	}

	private static JFreeChart createChart(XYDataset dataset) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(str3, // title
				"", // x-axis label
				"", // y-axis label
				dataset, // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
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
		Minute base = new Minute(0, 0, 24, 3, 2022); // 현재 날짜
		RelativeDateFormat rdf = new RelativeDateFormat(base.getFirstMillisecond());
		rdf.setSecondFormatter(new DecimalFormat(""));
		axis.setDateFormatOverride(rdf);

		ChartUtilities.applyCurrentTheme(chart);

		// 그래프 디자인
		plot.setBackgroundPaint(java.awt.Color.green);
		chart.setBackgroundPaint(java.awt.Color.white);
		chart.getPlot().setBackgroundPaint(java.awt.Color.white);

		r.setSeriesPaint(0, new Color(179, 110, 232));
		r.setSeriesPaint(1, new Color(211, 211, 211));

		return chart;
	}
}