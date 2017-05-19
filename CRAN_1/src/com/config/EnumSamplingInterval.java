package com.config;

public enum EnumSamplingInterval{
	vTTI("TTI",0),vSecond("Second",1),vMinute("Minute",2),vHour("Hour",3),vDay("Day",4);
	private String vName;
	private int vIndex;

	EnumSamplingInterval(String a,int b){
		this.vName=a;
		this.vIndex=b;
	}
	
	int get_value(){
		return vIndex;
	}
	public static EnumSamplingInterval get_model(int vIndex){
		switch (vIndex) {
		case 0:
			return vTTI;
		case 1:
			return vSecond;
		case 2:
			return vMinute;
		case 3:
			return vHour;
		case 4:
			return vDay;
		default:
			return vTTI;
		}			
	}
}