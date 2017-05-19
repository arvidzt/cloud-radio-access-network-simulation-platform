package com.test;

public class MainSub {

	    public native void sayHello();
		   public static void main(String args[]) {
			   System.loadLibrary("NativeBgPro");
		       CppEntrance nativeCode=new CppEntrance();
		       nativeCode.BackGroundPrograme();
		}
}
