package com.config;


import java.lang.Object;
public class ConfigTti {
	/**成员变量表*/
	/**java控制程序控制变量*/
	public int vTest[]={1,2,3};
	public static int PP[]={4,5,6,4};
	public static String testString="CranSystem_Simulator";
	//vMov  vPathLossModel vResourceModel  vWirePathModel vEnumResourceSheldual vEnumUeTrafficModel vEnumNetworkTrafficMode
	/**用户移动性模型*/	
	public static EnumMovmentModel vMov=EnumMovmentModel.vAllRandomModel;
	public static Object[] vConfigMovM=new Object[3];//参数对象表\
		
	
	/**无线链路模型*/
	public static EnumPathLossModel vPathLossModel=EnumPathLossModel.vFreedomLoss;
	public static Object[] vConfigPathLossM;
	
	/**系统频带资源*/
	public static EnumResourceModel vBandwidthResourceModel=EnumResourceModel.vResourceConcentation;
	public static Object[] vConfigResourceM;
	
	/**有线链路模型*/
	public static EnumWirePathModel vWirePathModel=EnumWirePathModel.vDefault;
	public static Object[] vConfigWirePathM;//参数对象表	
	
	/**资源调度模型*/
	public static EnumResourceSheldual vEnumResourceSheldual=EnumResourceSheldual.vFairness;
	public static Object[] vConfigResourceSheldual;//参数对象表
	
	/**用户业务量模型*/
	public static EnumUeTrafficModel vEnumUeTrafficModel=EnumUeTrafficModel.vVoIP;	
	public static Object[] vConfigUeTraM;//参数对象表
	
	/**网络业务量模型*/
	public static EnumNetworkTrafficModel vEnumNetworkTrafficModel=EnumNetworkTrafficModel.vPreTrafficLoadModel;	
	public static Object[] vConfigNetworkTraM;//参数对象表
	
	public ConfigTti(){
		
	}
	/**
	 * 调试的构造参数
	 * @param a testset
	 */
	public ConfigTti(int a[] ){
		vTest=a;
	}
	/**用户移动性模型参数配置*/
	public static void configMovmentModel(Object... srgs ) {
		switch (vMov) {
		case vAllRandomModel:
			/**检查参数*/
			break;
		case vPatialRandomModel:	//部分随机模型
			/**检查参数*/
			break;
		case vDirectionRandomModel://定向随机模型
			/**检查参数*/
			break;
		default:
			System.err.println("参数输入有误");
			break;
		}
		/**可变参数赋值给配置表*/
		vConfigMovM=srgs;
	}
	
	/**无线链路模型*/
	public void configPathLossM(Object... srgs ) {
		switch (vMov) {
		case vAllRandomModel:
			/**检查参数*/
			break;

		default:
			break;
		}
		vConfigMovM=srgs;
	}
	/**系统频带资源*/
	public void configResourceM(Object... srgs ) {
		switch (vMov) {
		case vAllRandomModel:
			/**检查参数*/
			break;

		default:
			break;
		}
		vConfigResourceM=srgs;
	}
	/**有线链路模型*/
	public void configWirePathM(Object... srgs ) {
		switch (vMov) {
		case vAllRandomModel:
			/**检查参数*/
			break;

		default:
			break;
		}
		vConfigWirePathM=srgs;
	}
	
	/**资源调度模型*/
	public void configResourceSheldual(Object... srgs ) {
		switch (vMov) {
		case vAllRandomModel:
			/**检查参数*/
			break;

		default:
			break;
		}
		vConfigResourceSheldual=srgs;
	}
	/**用户业务量模型*/
	public void configUeTrafficModel(Object... srgs ) {
		switch (vMov) {
		case vAllRandomModel:
			/**检查参数*/
			break;

		default:
			break;
		}
		vConfigUeTraM=srgs;
	}	
	/**网络业务量模型*/
	public void configNetworkTrafficModel(Object... srgs ) {
		switch (vMov) {
		case vAllRandomModel:
			/**检查参数*/
			break;

		default:
			break;
		}
		vConfigNetworkTraM=srgs;
	}
//	public static void main(String... srgs) {
//		System.out.println(srgs.getClass());
//		Object[] testObjects=new Object[3];
//		double a[]={1,1};
//		testObjects[0]=a;
//		testObjects[1]=1;
//		testObjects[2]="int";	
//		System.out.println(testObjects[0].getClass());
//		System.out.println(testObjects[1].getClass());
//	}
}
