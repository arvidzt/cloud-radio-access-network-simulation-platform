package com.test;
import com.test.StreamToTextArea;

public class ThreadOptimization extends Thread {

	public ThreadOptimization() {
		// TODO Auto-generated constructor stub
		
	}

	public ThreadOptimization(ThreadGroup group, String name) {
		super(group, name);
		// TODO Auto-generated constructor stub
	}
	public void run() {
		StreamToTextArea blah = StreamToTextArea.getInstance();
		System.setOut(blah.getPs());
		System.out.println("判断是否满足优化");
		System.out.println("系统优化处理,修改数据库相应的处理");
		//配置信息建类，TTI大尺度控制，
	}
}
