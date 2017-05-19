package com.config;

public enum EnumDemoCase {
	vDefaultDemo("default",0);//设置不同的版本
	private String vName;
	private int vIndex;
	EnumDemoCase(String vName,int vIndex){
		this.vName=vName;
		this.vIndex=vIndex;
	}
	int get_value(){
		return vIndex;
	}
	
	
	static public EnumDemoCase value(int vIndex){
		switch (vIndex) {
		case 0:
			return vDefaultDemo;
		default:
			return vDefaultDemo;
		}			
	}
}
