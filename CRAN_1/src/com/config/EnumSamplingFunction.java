package com.config;

public enum EnumSamplingFunction{
	vEqualInterval("Equal",0),//等间隔
	vCustomInterval("Custom",1);//用户自定义
	private String vName;
	private int vIndex;

	EnumSamplingFunction(String a,int b){
		this.vName=a;
		this.vIndex=b;
	}
	int get_value(){
		return vIndex;
	}
	public static EnumSamplingFunction get_model(int vIndex){
		switch (vIndex) {
		case 0:
			return vEqualInterval;
		case 1:
			return vCustomInterval;
		default:
			return vEqualInterval;
		}			
	}
}