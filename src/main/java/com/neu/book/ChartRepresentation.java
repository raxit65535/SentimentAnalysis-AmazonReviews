package com.neu.book;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.sun.tools.javac.util.List;

//chart representation
class AnalysisPOJO {
	String sentiment;
	int count;

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}

class BarChart_AWT extends ApplicationFrame {

	public BarChart_AWT(String applicationTitle, String chartTitle) throws IOException {
		super(applicationTitle);
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Sentiment", "Count", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
		CategoryPlot plot = barChart.getCategoryPlot();
		plot.getBackgroundPaint();

	}

	private CategoryDataset createDataset() throws IOException {

		String path = "/home/raxit/eclipse-workspace/dataset/localdir/part-r-00000";
		File file = new File(path);
		BufferedReader br = new BufferedReader(new java.io.FileReader(file));

		ArrayList<AnalysisPOJO> analysis = new ArrayList<AnalysisPOJO>();

		String line;
		int count = 0;
		while ((line = br.readLine()) != null) {
			AnalysisPOJO ob = new AnalysisPOJO();

			String arr[] = line.split("\t");

			ob.setSentiment(arr[0].toString());
			ob.setCount(Integer.parseInt(arr[1]));

			analysis.add(ob);

		}
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// final String positive, negative, neutral;

		for (AnalysisPOJO a : analysis) {

			dataset.addValue(a.getCount(), "sentiment", a.getSentiment());
		}
		return dataset;
	}

}

public class ChartRepresentation {

	public static void main(String a[]) throws IOException {

		/*try {

			Configuration conf = new Configuration(true);
			conf.addResource(new Path("/home/raxit/hadoop/hadoop-dist/etc/hadoop/core-site.xml"));
			conf.addResource(new Path("/home/raxit/hadoop/hadoop-dist/etc/hadoop/hdfs-site.xml"));
			FileSystem fs = FileSystem.get(conf);
			FileStatus[] status = fs.listStatus(new Path("/op2"));
			for (int i = 0; i < status.length; i++) {
				System.out.println(status[i].getPath());
				fs.copyToLocalFile(false, status[i].getPath(),
						new Path("/home/raxit/eclipse-workspace/dataset/localdir"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		BarChart_AWT chart = new BarChart_AWT("Sentiment Analysis", "Overall Sentiment of Product");
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}

}
