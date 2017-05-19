package com.config;
/**
 * 
*1.	RR  RoundRobin
*2.	Fairness
*3.	PF
*4.	预留
 *
 */
public enum EnumResourceSheldual {
	vRR("RoundRobin",0),//轮询
	vPF("MC",1),//最大载干比
	vFairness("Proportional fairness",2);//公平调度
	private String vName;
	private int vIndex;
	
	EnumResourceSheldual(String a,int b){
		this.vName=a;
		this.vIndex=b;
	}
	
	int get_value(){
		return vIndex;
	}
	public static EnumResourceSheldual get_model(int vIndex){
		switch (vIndex) {
		case 0:
			return vRR;
		case 1:
			return vPF;
		case 2:
			return vFairness;
		default:
			return vRR;
		}			
	}
}
