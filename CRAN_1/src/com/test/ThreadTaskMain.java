package com.test;
//控制TTI
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import ui.running.run3;

import com.config.*;
import com.test.StreamToTextArea;
import ui.CRAN1.tuoputu;

public class ThreadTaskMain extends Thread {
	private volatile boolean vIsSuspend=false;
	public static int vUnitCounter=0;//抽样时刻计数器
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS"); 
	TimeZone t = sdf.getTimeZone(); 
	public double fProgress = 0;
	long pauseTime = 0;
	long endTime = 0;
	public ThreadTaskMain() {
		// TODO Auto-generated constructor stub
		super();
	}

	public ThreadTaskMain(ThreadGroup tag, String aim) {
		super(tag, aim);
	}
	
	public synchronized void stopThread() {
		vIsSuspend=true;
	}

	@Override
	public void run() {
		t.setRawOffset(0); 
		sdf.setTimeZone(t); 
		StreamToTextArea blah = StreamToTextArea.getInstance();
		System.setOut(blah.getPs());
		System.out.println("进入主程序");
		boolean vLargeScale=false;

		/**判断大尺度*/
		if (ConfigJavaControler.vIntervalUnit!=EnumSamplingInterval.vTTI) {
			vLargeScale=true;	
			System.out.println("大尺度运行中");
		}
		
		
		/**第一层循环，控制抽样时刻，中断*/
		try{	
			System.out.print("------------------------"+ConfigJavaControler.vSamplingTime.length);
		}catch(Exception e){
			System.out.print(e.toString());
		}
		run3.bar.setValue(0);
		ConfigJavaControler.vFlagPause = true;//在每次线程开始时，将暂停变量还原。
		vUnitCounter = 0;
		fProgress = 0;
		BreakLabel: while (vUnitCounter<ConfigJavaControler.vSamplingTime.length && !vIsSuspend) {
			run3.textField_2.setText(Integer.toString(ConfigJavaControler.vSamplingTime[ThreadTaskMain.vUnitCounter]));
			int vSubUniteCounter=0;//抽样时刻内计数器
			
			boolean vOptimizationStart=false;//每一个抽样时刻的优化结束标志
			/**第二层循环，控制抽样时刻内部的n个抽样，并进行数据存储*/
			Optimization:while (vSubUniteCounter<ConfigJavaControler.vSamplingNum && !vIsSuspend) {
				if (vIsSuspend) 
					continue BreakLabel;
				System.out.println(vIsSuspend+"1111111111111111111111111111");
				/**跳出循环监测点*/
				fProgress = (double)((vUnitCounter)*ConfigJavaControler.vSamplingNum +vSubUniteCounter+1)/(double)(ConfigJavaControler.vSamplingTime.length*(ConfigJavaControler.vSamplingNum));
				run3.bar.setValue((int)(fProgress*100));
				run3.textField_3.setText(Double.toString(fProgress));
				run3.textField_4.setText(Integer.toString(vSubUniteCounter));
				
				if(!ConfigJavaControler.vFlagPause)
				{
					pauseTime += System.currentTimeMillis()-endTime;
				}
				endTime = System.currentTimeMillis();
				
				
				
				System.out.println("系统运行中");
				
				if (ConfigJavaControler.vFlagPause) {
					run3.textField_3.setText(sdf.format(new Date(endTime - run3.startTime-pauseTime))); 
					try {
						/**跳出循环监测点*/
						if (vIsSuspend) 
							continue BreakLabel;
							
						/**表示进入新的一轮循环*/
						if (vOptimizationStart) {
							System.out.println("--优化后的结果--，当前时段信息-------"+ConfigJavaControler.vSamplingTime[vUnitCounter]+"----当前的时刻-----" + vSubUniteCounter);
						}else {
							System.out.println("--未优化的结果--，当前时段信息-------"+ConfigJavaControler.vSamplingTime[vUnitCounter]+"----当前的时刻-----" + vSubUniteCounter
							);
						}
						/** 进行相关配置 */
						System.out.println("每个TTI配置信息加载中");
//						/** 是否数据库数据更新标志 */
//						System.out.println("加载数据信息");
						if (vSubUniteCounter==ConfigJavaControler.vSamplingNum-1 && ConfigJavaControler.vFlagOptiTriggle) {
							/**此处要运行进行判断是否满足优化*/
							ConfigJavaControler.vMeetOptimization=true;//确定优化，由判决条件决定(判决方法在这里)
							System.out.println("判断是否满足优化条件："+ConfigJavaControler.vMeetOptimization);
							/**如果满足*/
							if (ConfigJavaControler.vMeetOptimization && !vOptimizationStart) {
								new ThreadOptimization(main.vMyGroup, "Optimization");
								ConfigJavaControler.vMeetOptimization=false;//
								vSubUniteCounter=0;//重新置0
								vOptimizationStart=true;//更新第二层循环内的标志防止进入死循环。
								continue Optimization;
							}									
						}
						/**如果满足优化加载的信息，否则读取未优化信息，这一段应该在cpp中实现*/
						if (ConfigJavaControler.vMeetOptimization) {
							System.out.println("读取优化后的用户信息");
						}else {
							System.out.println("读取优化前的用户信息");
						}
						
						/**配置运行case加载*/
							ThreadBackGround vBackGroundThread=new ThreadBackGround(main.vMyGroup,"back groud procedure");
							vBackGroundThread.start();
							
							/**主控制线程的保持等待，保持每次只有一个TTI线程在运行*/
							while (vBackGroundThread.isAlive()) {
								//Thread.sleep(3000);//如果想突然暂停只用 用break +lable就可以
								//tuoputu.fpaint.clear();	
							}
							
						/**判断是否满足优化要求*/
						/** 优化指标检查，满足则运行前优化启动 */
						//Thread.sleep(300);
					

						/**计数器**/
						vSubUniteCounter++;
					} catch (Exception e) {
						// TODO: handle exception
					}
				} else {
					System.out.println("线程TTI暂停中");
				}				
			}
			ConfigJavaControler.vMeetOptimization=false;//每一个时刻重新刷新满足优化标志信息。
			
			vUnitCounter++;
		}
		System.out.println("运行结束");
		
		//run3.bar.setValue(0);
		
	}

}
