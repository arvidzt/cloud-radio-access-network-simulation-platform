package com.config;

public enum EnumNetworkTrafficModel {
	vPreTrafficLoadModel(0);//‘§÷√“µŒÒ¡ø
	private String vName;
	private int vIndex;
	
	EnumNetworkTrafficModel(int b){
		this.vIndex=b;
	}
	int get_value(){
		return vIndex;
	}
	public static EnumNetworkTrafficModel get_model(int vIndex){
		switch (vIndex) {
		case 0:
			return vPreTrafficLoadModel;
	
		default:
			return vPreTrafficLoadModel;
		}			
	}
}
