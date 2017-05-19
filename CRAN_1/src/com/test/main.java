package com.test;

import ui.PrintRedirect.Entrance;

import com.database.ReadConfig;

public class main extends Thread {
	/***/
	public static ThreadGroup vMyGroup=new ThreadGroup("main procedure group");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.setOut(new Entrance().work());//需要在ConfigJavaControler中增添一个文件路径的属性
		System.out.println("读取配置参数");
		//ReadConfig vTest=new ReadConfig("C:/Users/tao/Desktop/input.xls");
		//ConfigJavaControler.setSamplingCustom(EnumSamplingInterval.vSecond, 3,vInput ,5);
		System.loadLibrary("NativeTestJni");//加载库1
		System.loadLibrary("NativeProCase");//加载库2
		//创建任务线程，建议做成按钮之前要判断线程是否是已存在，否则多次点击会创建多次线程
		ThreadTaskMain main_thread=new ThreadTaskMain(vMyGroup,"main procedure");
		main_thread.start();
		
		//界面线程启动
//		ThreadUi vUiThread=new ThreadUi(vMyGroup,"Ui");
//		vUiThread.start();
		System.out.println("--线程1----end-----");	
	}
}
