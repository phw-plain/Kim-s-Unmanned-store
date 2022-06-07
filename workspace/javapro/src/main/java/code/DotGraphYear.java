package code;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class DotGraphYear {
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
			str1 = "이번 주";
			str2 = "지난 주";
			str3 = "WeekSales";
		}
			break;
		case 2: {
			str1 = "이번 연도";
			str2 = "지난 연도";
			str3 = "YearSales";
		}
			break;
		}

		TimeSeries s1 = new TimeSeries(str1); // (초,분,시,일,월,년),마지막이 데이터
		TimeSeries s2 = new TimeSeries(str2);
		
		for (int i = 0; i < data1.length; i++) {
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
				"원", // y-axis label
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
		axis.setDateFormatOverride(new SimpleDateFormat("  MM월  "));

		ChartUtilities.applyCurrentTheme(chart);

		// 그래프 디자인
		// Title
		chart.getTitle().setFont(new Font("돋움체", Font.BOLD, 20));
		chart.getLegend().setItemFont(new Font("돋움체", Font.PLAIN, 10));
		
		// x축
		Font font = plot.getDomainAxis().getLabelFont();
		plot.getDomainAxis().setLabelFont(new Font("돋움체", font.getStyle(), font.getSize()));
		plot.getDomainAxis().setTickLabelFont(new Font("돋움체", font.getStyle(), 10));
		
		// y축
		font = plot.getRangeAxis().getLabelFont();
		plot.getRangeAxis().setLabelFont(new Font("돋움체", font.getStyle(), font.getSize()));
		plot.getRangeAxis().setTickLabelFont(new Font("돋움체", font.getStyle(), 10));	
		
		plot.setBackgroundPaint(java.awt.Color.green);
		chart.setBackgroundPaint(java.awt.Color.white);
		chart.getPlot().setBackgroundPaint(java.awt.Color.white);

		r.setSeriesPaint(0, new Color(179, 110, 232));
		r.setSeriesPaint(1, new Color(211, 211, 211));

		return chart;
	}
}