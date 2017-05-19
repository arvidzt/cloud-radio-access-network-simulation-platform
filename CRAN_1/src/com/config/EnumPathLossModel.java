package com.config;

/**
 * 
 * @author yaoyao 1. 自由空间模型 2. 自由+衰落 3. Hata231 4. Hata231修正
 * 
 */
public enum EnumPathLossModel {
	vFreedomLoss(0),//自由损耗
	vFreedomVsFading(1),//自由空间损耗和快衰
	vHata231(2),//cost231
	vHata231A(3),//cost231a
	;
	
	private int vIndex;

	EnumPathLossModel(int vIndex) {

		this.vIndex=vIndex;
	}
	int get_value(){
		return vIndex;
	}
	public static EnumPathLossModel get_model(int vIndex){
		switch (vIndex) {
		case 0:
			return vFreedomLoss;
		case 1:
			return vFreedomVsFading;
		case 2:
			return vFreedomLoss;
		case 4:
			return vHata231;
		default:
			return vFreedomLoss;
		}			
	}
}
