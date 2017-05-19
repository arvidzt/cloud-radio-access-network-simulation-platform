package com.database;

import java.io.File;

import ui.running.run2;

import com.config.ConfigJavaControler;
import com.config.ConfigTti;
import com.config.EnumDemoCase;
import com.config.EnumMovmentModel;
import com.config.EnumNetworkTrafficModel;
import com.config.EnumPathLossModel;
import com.config.EnumResourceModel;
import com.config.EnumResourceSheldual;
import com.config.EnumSamplingInterval;
import com.config.EnumSamplingFunction;
import com.config.EnumUeTrafficModel;
import com.config.EnumWirePathModel;
import com.help.HelpClass;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.demo.ReadWrite;

public class ReadConfig {
	String vFileNameString="C:/Users/yaoyaodelaogong/Desktop/input2.xls";
	ReadWrite readWrite;
	int j;
	public ReadConfig(String vName) {
		vFileNameString=vName;
		 try    {
	            Workbook book  =  Workbook.getWorkbook( new  File( vFileNameString ));
	             //  获得第一个工作表对象 
	              Sheet sheet  =  book.getSheet( 0 );
	             //  得到第一列第一行的单元格 
                Cell cell1 ;
                
                //cell1 =  sheet.getCell( 1,  0 );	
  	            ConfigJavaControler.vSoftName="CRAN";
  	            //System.out.println("软件名称："+cell1.getContents());
                
                //获取配置的case
            //    cell1 =  sheet.getCell( 0,  1 );	
  	         //   ConfigJavaControler.vCaseVersion=EnumDemoCase.value(Integer.parseInt(cell1.getContents()));
//  	            System.out.println("ConfigJavaControler.vCaseVersion参数"+cell1.getContents());

	           //获取仿真单位
	            cell1=sheet.getCell(4, 1);
//	            System.out.println("获取仿真单位"+cell1.getContents());
	            j =Integer.parseInt(cell1.getContents());
	            run2.jcb1.setSelectedItem(run2.jg1[j]);
	         //   ConfigJavaControler.vIntervalUnit=EnumSamplingInterval.get_model(Integer.parseInt(cell1.getContents()));//抽样方式，是等间隔抽样还是自定义
	            
	            cell1=sheet.getCell(3, 1);
//	            System.out.println("获取仿真单位"+cell1.getContents());
	            j =Integer.parseInt(cell1.getContents());
	           // ConfigJavaControler.vTtiTimeLength = j;
	            run2.textField_2.setText(cell1.getContents());
	            //自定义抽样还是等间隔抽样最后的处理方式。为了数据的一致性，建议double存成数组形式
	            
	            cell1=sheet.getCell(2, 1);
	            j =Integer.parseInt(cell1.getContents());
	            run2.jcb2.setSelectedItem(run2.jg2[j]);
//	            System.out.println("等间隔抽样还是自定义"+cell1.getContents());
	          //  ConfigJavaControler.vSamplingFunction=EnumSamplingFunction.get_model(Integer.parseInt(cell1.getContents()));
	            
	            //仿真总时常
	            cell1  =  sheet.getCell( 5,  1 );
	            run2.textField_4.setText(cell1.getContents());
	           // ConfigJavaControler.vSimuT=Double.parseDouble((cell1.getContents()));//仿真总时长，单位由仿真抽样单位决定
	            
	            
	            
	        	//public static int vTtiLength;//TTI个数
	            
	            //每个抽样单位的连续的抽样数
	            if (run2.jcb2.getSelectedIndex() == 0) {
	            	cell1  =  sheet.getCell( 6, 1);
	            	run2.textField_1.setText(cell1.getContents());
	            	cell1  =  sheet.getCell( 7, 1 );
		            run2.tf.setText(cell1.getContents());
//		            ConfigJavaControler.vSamplingNum=Integer.parseInt(cell1.getContents());//存储抽样的时刻,决定抽样的个数    
//		            ConfigJavaControler.vSamplingTime=new int[(int)ConfigJavaControler.vSimuT/ConfigJavaControler.vSamplingNum];
//		            for (int i = 0; i < (int)ConfigJavaControler.vSimuT/ConfigJavaControler.vSamplingNum; i++) {				
//						ConfigJavaControler.vSamplingTime[i]=ConfigJavaControler.vSamplingNum*i;
//					}
//					System.out.println("抽样的个数"+ConfigJavaControler.vSimuT);
				}else {
					cell1  =  sheet.getCell( 6, 1);
					run2.textField_1.setText(cell1.getContents());
			          
//		            ConfigJavaControler.vSamplingNum=Integer.parseInt(cell1.getContents());//存储抽样的时刻,决定抽样的个数    
		            cell1  =  sheet.getCell( 7, 1 );
		            run2.text.setText(cell1.getContents());
			          
//		            ConfigJavaControler.vSamplingTime=HelpClass.StringTrans2IntArray(cell1.getContents());//存储抽样的时刻,决定抽样的个数          
		            
				}
	          
	            //  用户移动性模型vEnumResourceSheldual vEnumUeTrafficModel vEnumNetworkTrafficMode
//	            cell1  =  sheet.getCell( 1, 7 );
	           ConfigTti.vMov=EnumMovmentModel.get_model(1);
	           System.out.println("移动性模型ConfigTti.vMov");
	           
//	           cell1  =  sheet.getCell( 1, 8 );
	           ConfigTti.vPathLossModel=EnumPathLossModel.get_model(0);
	           System.out.println("路损模型ConfigTti.vPathLossModel");
	           
//	           cell1  =  sheet.getCell( 1, 9 );
	           ConfigTti.vBandwidthResourceModel=EnumResourceModel.get_model(0);
	           System.out.println("带宽模型ConfigTti.vResourceModel");
	           
//	           cell1  =  sheet.getCell( 1, 10 );
	           ConfigTti.vEnumUeTrafficModel=EnumUeTrafficModel.get_model(1);
	           System.out.println("用户业务量模型ConfigTti.vResourceModel");
	           
//	           cell1  =  sheet.getCell( 1, 11 );
	           ConfigTti.vWirePathModel=EnumWirePathModel.get_model(0);
	           System.out.println("有线模型ConfigTti.vWirePathModel");
	            
//	           cell1  =  sheet.getCell( 1, 12 );
	           ConfigTti.vEnumNetworkTrafficModel=EnumNetworkTrafficModel.get_model(0);
	           System.out.println("网络业务量模型ConfigTti.vWirePathModel"); 
	           
//	           cell1  =  sheet.getCell( 1, 13 );
	           ConfigTti.vEnumResourceSheldual=EnumResourceSheldual.get_model(0);
	           System.out.println("网络的调度模型vEnumResourceSheldual"); 
	           
	           book.close();//关闭句柄
	            
	            
	        }    catch  (Exception e)   {
	            System.out.println(e);
	        }  
	}
	
	
}
