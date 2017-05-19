package ui.Draft;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartDemo {
	 ChartPanel frame1;  
	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
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
        FileOutputStream fos_jpg = null; 
        try { 
            fos_jpg = new FileOutputStream("D:\\BarChart.jpg"); 
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1.0f,chart,400,300,null); 
        } finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {} 
        } 

	}
	/** 
	 * 获取一个演示用的简单数据集对象
	 * @return 
	 */ 
	 private static CategoryDataset getDataSet() { 
	     DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	     dataset.addValue(100, "", "苹果"); 
	     dataset.addValue(200, "", "梨子"); 
	     dataset.addValue(300, "", "葡萄"); 
	     dataset.addValue(400, "", "香蕉"); 
	     dataset.addValue(500, "", "荔枝"); 
	     return dataset; 
	 } 
}

