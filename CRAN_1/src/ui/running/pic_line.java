/*
 * 类名：pic_line
 * 功能：run3界面中折线图模型类
 * 
 */
package ui.running;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class pic_line {
	ChartPanel frame1;  
	String TitleName;//图标标题
	String Xname;//X轴名称
	String Yname;//Y轴名称
	DefaultCategoryDataset dataset;
	JFreeChart jfreechart;
	public pic_line(String TitleNames,String Xnames,String Ynames,DefaultCategoryDataset datasets){ 
	    	TitleName=TitleNames;
			Xname=Xnames;
			Yname=Ynames;
			dataset=datasets;
	        jfreechart =  ChartFactory.createLineChart(TitleName,Xname, Yname, dataset, PlotOrientation.VERTICAL, true, true,true);   
	        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
	        categoryplot.setBackgroundPaint(SystemColor.WHITE); //设置背景颜色
	        categoryplot.setRangeGridlinesVisible(true);//设置横虚线可见    
	        categoryplot.setRangeGridlinePaint(Color.gray);//虚线色彩 
	        categoryplot.setNoDataMessage("无数据显示");
	        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
	        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
	      //解决中文乱码问题(关键) 
	        TextTitle textTitle = jfreechart.getTitle();
	        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));      
	        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
	        domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
	        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
	        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
	        jfreechart.getLegend().setItemFont(new Font("黑体", Font.PLAIN, 12));
//            // 获显示线条对象  
            LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();  
            lineandshaperenderer.setBaseShapesVisible(true);  
            lineandshaperenderer.setDrawOutlines(true);  
            lineandshaperenderer.setUseFillPaint(true);  
            lineandshaperenderer.setBaseFillPaint(Color.white);  
            // 设置折线加粗  
            lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2F));  
            lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(1.0F));  
            // 设置折线拐点  
            lineandshaperenderer.setSeriesShape(0,new java.awt.geom.Ellipse2D.Double(-1D, -1D, 3D, 3D));  

	        frame1=new ChartPanel(jfreechart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame  
	        frame1.setPreferredSize(new Dimension(440,300));
	  
	    }   
	      public ChartPanel getChartPanel(){  
	            return frame1;  
	              
	        }  

}
