package com.help;

public class HelpClass {
	/**
	 * trans int[] to string
	 * @param vInput int[]
	 * @return string
	 */
	public static String  IntArrayTrans2String(int []vInput) {
		String vResult="";
		for (int i = 0; i < vInput.length; i++) {
			if (i!=vInput.length-1) 
				vResult+=vInput[i]+",";
			else 
				vResult+=vInput[i];
		}
		return vResult;
	}
	/**
	 * trans string to int[]
	 * @param vInput input string
	 * @return
	 */
	public static int[] StringTrans2IntArray(String vInput){
		String[] vStrings=vInput.split(",");
		int []vResult=new int [vStrings.length];
		for (int i = 0; i < vStrings.length; i++) {
			vResult[i]=Integer.parseInt(vStrings[i]);
		}
		return vResult;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
