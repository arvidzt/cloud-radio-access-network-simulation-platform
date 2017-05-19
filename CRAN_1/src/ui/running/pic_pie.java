/*
 * 类名：pic_pie
 * 功能：run3界面中饼状图模型类
 */
package ui.running;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class pic_pie {
	ChartPanel frame1;
	PieDataset data;
	String TitleName;//图标标题
	JFreeChart chart;
	public  pic_pie(String TitleNames,PieDataset datasets){   
		data=datasets;
		TitleName=TitleNames;
//        chart = ChartFactory.createPieChart3D(TitleName,data,true,true,false);
		chart = ChartFactory.createPieChart(TitleName,data,true,true,false);
      //设置百分比  
        		  PiePlot pieplot = (PiePlot) chart.getPlot();  
        	      pieplot.setBackgroundPaint(SystemColor.WHITE); //设置背景颜色
//        		  pieplot.setLabelFont(new Font("黑体", Font.PLAIN, 20));
//    	         TextTitle textTitle = chart.getTitle();
//    	         textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
                  DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题  
                  NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象  
                  StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);//获得StandardPieSectionLabelGenerator对象  
                  pieplot.setLabelGenerator(sp1);//设置饼图显示百分比                  
              //没有数据的时候显示的内容  
                  pieplot.setNoDataMessage("无数据显示");  
                  pieplot.setCircular(true);  
                  pieplot.setLabelGap(0.02D);  
//                  pieplot.setIgnoreNullValues(true);//设置不显示空值  
//                  pieplot.setIgnoreZeroValues(true);//设置不显示负值  
                 chart.getTitle().setFont(new Font("黑体",Font.BOLD,20));//设置标题字体  
                 PiePlot piePlot= (PiePlot) chart.getPlot();//获取图表区域对象  
                 piePlot.setLabelFont(new Font("黑体",Font.BOLD,10));//解决乱码  
                 chart.getLegend().setItemFont(new Font("黑体",Font.BOLD,10));
                 frame1=new ChartPanel (chart,true);  
                 frame1.setPreferredSize(new Dimension(440,300));
        
	}
	 public ChartPanel getChartPanel(){  
		     return frame1;  
		 }  
}
