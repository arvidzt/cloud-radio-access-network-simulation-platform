/*
 * 每个RRU吞吐量
 * 单个BBU的频谱效率
 * 单个BBU的能耗
 */
package ui.Draft;

import java.awt.Dimension;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class pic_column{
	ChartPanel frame1;
	String TitleName;//图标标题
	public  pic_column(){  
		CategoryDataset dataset = getDataSet(); 
        JFreeChart chart = ChartFactory.createBarChart3D( 
                           "水果产量图", // 图表标题
                           "水果", // 目录轴的显示标签
                           "产量", // 数值轴的显示标签
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,  // 是否显示图例(对于简单的柱状图必须是 false)
                            false, // 是否生成工具
                            false  // 是否生成 URL 链接
                            ); 
        //中文乱码
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));      
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame  
        frame1.setPreferredSize(new Dimension(440,300));
	}
	/** 
	 * 获取一个演示用的简单数据集对象
	 * @return 
	 */ 
	 private static CategoryDataset getDataSet() { 
	     DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	                dataset.addValue(100, "北京", "苹果");  
	                dataset.addValue(100, "上海", "苹果");  
	                dataset.addValue(100, "广州", "苹果");  
	                dataset.addValue(200, "北京", "梨子");  
	                dataset.addValue(200, "上海", "梨子");  
	                dataset.addValue(200, "广州", "梨子");  
	                dataset.addValue(300, "北京", "葡萄");  
	                dataset.addValue(300, "上海", "葡萄");  
	                dataset.addValue(300, "广州", "葡萄");  
	                dataset.addValue(400, "北京", "香蕉");  
	                dataset.addValue(400, "上海", "香蕉");  
	                dataset.addValue(400, "广州", "香蕉");  
	                dataset.addValue(500, "北京", "荔枝");  
	                dataset.addValue(500, "上海", "荔枝");  
	               dataset.addValue(500, "广州", "荔枝");  
	               //
	               dataset.addValue(100, "北京", "苹果1");  
	                dataset.addValue(100, "上海", "苹果1");  
	                dataset.addValue(100, "广州", "苹果1");  
	                dataset.addValue(200, "北京", "梨子1");  
	                dataset.addValue(200, "上海", "梨子1");  
	                dataset.addValue(200, "广州", "梨子1");  
	                dataset.addValue(300, "北京", "葡萄1");  
	                dataset.addValue(300, "上海", "葡萄1");  
	                dataset.addValue(300, "广州", "葡萄1");  
	                dataset.addValue(400, "北京", "香蕉1");  
	                dataset.addValue(400, "上海", "香蕉1");  
	                dataset.addValue(400, "广州", "香蕉1");  
	                dataset.addValue(500, "北京", "荔枝1");  
	                dataset.addValue(500, "上海", "荔枝1");  
	               dataset.addValue(500, "广州", "荔枝1");  
	               return dataset;  

	 } 
	 public ChartPanel getChartPanel(){  
		     return frame1;  
		 }  

}
