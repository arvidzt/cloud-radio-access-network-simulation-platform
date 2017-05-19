/*
 * 类名：pic_column1
 * 功能：run3界面中第一个柱形图模型类
 * 每个RRU吞吐量
 */
package ui.running;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class pic_column1{
	ChartPanel frame1;
	String TitleName;//图标标题
	String Xname;//X轴名称
	String Yname;//Y轴名称
	JFreeChart chart;
	DefaultCategoryDataset dataset;
	//获取一个数据集对象
	public  pic_column1(String TitleNames,String Xnames,String Ynames,DefaultCategoryDataset datasets){ 
		TitleName=TitleNames;
		Xname=Xnames;
		Yname=Ynames;
		dataset=datasets;
        chart = ChartFactory.createBarChart3D( 
        					TitleName, // 图表标题
        					Xname, // 目录轴的显示标签X轴
                            Yname, // 数值轴的显示标签Y轴
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,  // 是否显示图例(对于简单的柱状图必须是 false)
                            false, // 是否生成工具
                            false  // 是否生成 URL 链接
                            ); //new Color(33,176,218)
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        BarRenderer3D customBarRenderer = (BarRenderer3D) categoryplot.getRenderer();//为了更改柱状图颜色
        customBarRenderer.setSeriesPaint(0, Color.decode("#7979FF")); //格式为：COLOR+“BBGGRR”：BB、GG、RR表示蓝色、绿色和红色的分量，每种颜色的取值范围是00-FF，采用了16进制。
        categoryplot.setBackgroundPaint(SystemColor.white);//设置背景颜色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确） 
        customBarRenderer.setMaximumBarWidth(0.05);//设置柱子宽度
        categoryplot.setBackgroundAlpha(0.8f);// 背景色透明度    
        categoryplot.setForegroundAlpha(0.8f);// 设置柱的透明度    
        
        categoryplot.setRangeGridlinesVisible(true);//设置横虚线可见    
        categoryplot.setRangeGridlinePaint(Color.gray);//虚线色彩 

        categoryplot.setNoDataMessage("无数据显示");
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        CategoryAxis domainAxis = categoryplot.getDomainAxis(); 
        
     //解决中文乱码问题(关键)  
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));      
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
        domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("黑体", Font.PLAIN, 12));
        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame  
        frame1.setPreferredSize(new Dimension(440,300));
	} 
	public ChartPanel getChartPanel(){  
	    return frame1;  
	}  


}
