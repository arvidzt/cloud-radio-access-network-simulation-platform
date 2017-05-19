package com.test;

import map.network.GraphDemo;
import ui.CRAN1.tuoputu;

import com.config.ConfigJavaControler;
import com.config.ConfigTti;

public class ThreadBackGround extends Thread {
	
	
	public ThreadBackGround() {
		// TODO Auto-generated constructor stub
	}

	public ThreadBackGround(Runnable target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	public ThreadBackGround(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public ThreadBackGround(ThreadGroup group, Runnable target) {
		super(group, target);
		// TODO Auto-generated constructor stub
	}

	public ThreadBackGround(ThreadGroup group, String name) {
		super(group, name);
		// TODO Auto-generated constructor stub
	}

	public ThreadBackGround(Runnable target, String name) {
		super(target, name);
		// TODO Auto-generated constructor stub
	}

	public ThreadBackGround(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		// TODO Auto-generated constructor stub
	}

	public ThreadBackGround(ThreadGroup group, Runnable target, String name,
			long stackSize) {
		super(group, target, name, stackSize);
	}
	public void run() {
		StreamToTextArea blah = StreamToTextArea.getInstance();
		System.setOut(blah.getPs());
		System.out.println("准备进入后台C++程序运行");

		/**cpp 模块已经开始运行*/
		try {
			System.out.println("网络流量模型"+ConfigTti.vEnumNetworkTrafficModel.name());
			System.out.println("资源调度模型"+ConfigTti.vEnumResourceSheldual.name());
			System.out.println("用户业务量配置模型"+ConfigTti.vEnumUeTrafficModel.name());
			System.out.println("用户移动性模型"+ConfigTti.vMov.name());
			System.out.println("路损模型"+ConfigTti.vPathLossModel.name());
			System.out.println("频带模型"+ConfigTti.vBandwidthResourceModel.name());
			System.out.println("有线链路模型"+ConfigTti.vWirePathModel.name());
			Thread.sleep(2000);

	        CppEntrance nativeCode=new CppEntrance();
//	        nativeCode.BackGroundPrograme();
//			System.out.println(System.getProperty("java.library.path"));  

			CppConfig aConfig=new CppConfig();
			/** test only**/
			int a[]={2,3,4};//传值到静态对象中
			int speed[]={2,33};
			double angle[]={12.3};
			Object vSample[]=new Object[3];
			String vTstring="Random";
			vSample[0]=vTstring;
			vSample[1]=speed;//均匀分布
			vSample[2]=angle;//移动的方向角度
			ConfigTti.vConfigMovM=vSample;
			System.out.println(vSample[0]);
			
			switch (ConfigJavaControler.vCaseVersion) {
			/**调用默认装配的case*/
			case vDefaultDemo:
//				System.out.println("调用默认装配的case");
				//nativeCode.BackGroundPrograme();

				aConfig.setConfiguration();     //暂时注释，不考虑C++模块,调用C++
				System.out.println("C++调用成功！");
//				tuoputu.fpaint.clear();
//				ConfigTti tttConfigTti=new ConfigTti(a);
//				aConfig.setConfigCase0(tttConfigTti);
				break;
//			case vDefaultDemo1:
//				aConfig.setConfigCase1(new ConfigTti(ConfigJavaControler.vSamplingTime),1);
//				ConfigTti tttConfigTti=new ConfigTti(a);
//				aConfig.setConfigCase0(tttConfigTti);
//			case vDefaultDemo2:
//				aConfig.setConfigCase2(new ConfigTti(ConfigJavaControler.vSamplingTime),ConfigJavaControler.vSoftName);
			default:
				System.out.println("该功能目前未实现");
				break;
			}
		} catch (Exception e) {
			System.out.println("look here"+e.toString());
			// TODO: handle exception
		}

        /**cpp 模块已经结束*/
		System.out.println("cpp_单个TTi线程 结束。数据结果返回数据库");
	}

}
