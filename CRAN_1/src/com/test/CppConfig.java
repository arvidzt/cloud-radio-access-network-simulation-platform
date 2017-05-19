package com.test;

import com.config.ConfigTti;


public class CppConfig {

	/**
	 * 本地方法声明
	 * @param args
	 */
	public native void setConfiguration();
	public native void setConfigCase0(ConfigTti vSample);//默认配置一 传入对象，调用对象中OBJECT[]参数
	public native void setConfigCase1(ConfigTti vSample,int vMode);//默认配置二 传入对象 及其他参数
	public native void setConfigCase2(ConfigTti vSample,String name);//默认配置三,cpp中基础类型没有string 只能转为char	
}
