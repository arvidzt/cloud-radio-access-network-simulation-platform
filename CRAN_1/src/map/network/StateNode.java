/*
 * This source code is part of TWaver 4.5.0
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2013 Serva Software. All rights reserved.
 * 此文档所描述StateNode类用于封装node的一些属性
 */

package map.network;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Text;

import org.apache.poi.hssf.record.formula.functions.Int;

import twaver.ResizableNode;
import twaver.TWaverConst;
import twaver.TWaverUtil;

public class StateNode extends ResizableNode{
	private int vType;//1 = bbupool;2 = bbu;
	private int vId;
	private double vAllRes;
	private double vResLeft;
	private double vDynamicEnengy; 
	private double vStaticEnengy; 
	private String vBbuPoolInfo;
	private int vRruNum;
	private double vTransPower;
	private double vEquipPower;
	private double vRes;
	private int vBbuPoolId;
	private int vRruTransPower;
	private int vRruBandwidth;
	private int vUeNum;
	private double vCarrierFrequent;
	private double vRadius;
	private int vUeTransPower;
	private int vRbNum;
	private int vUeType;
	private int attachmentDirection = TWaverConst.ATTACHMENT_DIRECTION_TOP_RIGHT;
	private int vCircleId;
	public void initBbuPool(int vType,int vId,
    		double vAllRes, double vResLeft, double vDynamicEnengy, 
    		double vStaticEnengy,String vBbuPoolInfo) {
		this.vType = vType;
		this.vId = vId;
		this.vAllRes = vAllRes;
		this.vResLeft = vResLeft;
		this.vDynamicEnengy = vDynamicEnengy;
		this.vStaticEnengy = vStaticEnengy;
		this.vBbuPoolInfo = vBbuPoolInfo;

		this.putCustomDrawFillColor(Color.ORANGE);
		this.putBorderColor(Color.YELLOW);
		this.putBorderStroke(TWaverConst.STROKE_SOLID_MIDDLE);
		this.putCustomDrawOutline(false);
		this.putLabelFont(TWaverUtil.getFont(Font.BOLD, 10));
		this.putLabelColor(Color.blue);
		this.putLabelHighlightForeground(Color.GREEN);

	//	int size = (int) (8 + Math.sqrt(vAllRes / 300000000) * 150);
	//	Point point = this.getCenterLocation();
		//this.setSize(size, size);
		//this.setCenterLocation(point.x, point.y);
	}
	public void initBbu(int vType,int vId,
    		int vRruNum, double vTransPower, double vEquipPower, 
    		double vRes,int vBbuPoolId) {
		this.vType = vType;
		this.vId = vId;
		this.vRruNum = vRruNum;
		this.vTransPower = vTransPower;
		this.vEquipPower = vEquipPower;
		this.vEquipPower = vEquipPower;
		this.vRes = vRes;
		this.vBbuPoolId = vBbuPoolId;
		
		this.putCustomDrawFillColor(Color.ORANGE);
		this.putBorderColor(Color.YELLOW);
		this.putBorderStroke(TWaverConst.STROKE_SOLID_MIDDLE);
		this.putCustomDrawOutline(false);
		this.putLabelFont(TWaverUtil.getFont(Font.BOLD, 10));
		this.putLabelColor(Color.blue);
		this.putLabelHighlightForeground(Color.GREEN);
//
		//Point point = this.getCenterLocation();
		//this.setSize(size, size);
		//this.setCenterLocation(point.x, point.y);
	}

	public void initRru(int vType,int vId,
    		int vRruTransPower, int vRruBandwidth,int vUeNum,  
    		double vCarrierFrequent,double vEquipPower,double vRadius) {
		this.vType = vType;
		this.vId = vId;
		this.vRruTransPower = vRruTransPower;
		this.vRruBandwidth = vRruBandwidth;
		this.vUeNum = vUeNum;
		this.vEquipPower = vEquipPower;
		this.vCarrierFrequent = vCarrierFrequent;
		this.vRadius = vRadius;
		
		this.putCustomDrawFillColor(Color.ORANGE);
		this.putBorderColor(Color.YELLOW);
		this.putBorderStroke(TWaverConst.STROKE_SOLID_MIDDLE);
		this.putCustomDrawOutline(false);
		this.putLabelFont(TWaverUtil.getFont(Font.BOLD, 10));
		this.putLabelColor(Color.green);
		this.putLabelHighlightForeground(Color.GREEN);

		//Point point = this.getCenterLocation();
		//this.setSize(size, size);
		//this.setCenterLocation(point.x, point.y);
	}
	
	public void initUe(int vType,int vId,int vUeType,int vRbNum,
    		int vUeTransPower) {
		this.vType = vType;
		this.vId = vId;
		this.vUeTransPower = vUeTransPower;
		this.vRbNum = vRbNum;
		this.vUeType = vUeType;
		
		this.putCustomDrawFillColor(Color.ORANGE);
		this.putBorderColor(Color.YELLOW);
		this.putBorderStroke(TWaverConst.STROKE_SOLID_MIDDLE);
		this.putCustomDrawOutline(false);
		this.putLabelFont(TWaverUtil.getFont(Font.BOLD, 5));
		this.putLabelColor(Color.black);
		this.putLabelHighlightForeground(Color.GREEN);

	}
	public void initAccessPoint( int vType,int vCId,int vId){
		this.vType = vType;
		this.vId = vId;
		this.vCircleId = vCId;
		this.putCustomDrawFillColor(Color.ORANGE);
		this.putBorderColor(Color.YELLOW);
		this.putBorderStroke(TWaverConst.STROKE_SOLID_MIDDLE);
		this.putCustomDrawOutline(false);
		this.putLabelFont(TWaverUtil.getFont(Font.BOLD, 2));
		this.putLabelColor(Color.green);
		this.putLabelHighlightForeground(Color.GREEN);
	}
	public int getCircleId(){
		return vCircleId;
	}
	public double getCarrierFrequent(){
		return vCarrierFrequent;
	}
	
	public double getRadius(){
		return vRadius;
	}
	
	public int getType(){
		return vType;
	}
	
	public int getId(){
		return vId;
	}
	
	public double getRes() {
		return vRes;
	}
	
	public double getTransPower(){
		return vTransPower;
	}
	
	public double getEquipPower(){
		return vEquipPower;
	}
	
	public int getRruNum(){
		return vRruNum;
	}
	public int getUeNum(){
		return vUeNum;
	}
	
	public int getRruTransPower(){
		return vRruTransPower;
	}
	
	public int getUeTransPower(){
		return vUeTransPower;
	}
	
	public int getUeType(){
		return vUeType;
	}
	
	public int getRbNum(){
		return vRbNum;
	}
	
	public int getRruBandwidth(){
		return vRruBandwidth;
	}
	
	public int getBbuPoolId(){
		return vBbuPoolId;
	}
	
	public double getAllRes() {
		return vAllRes;
	}

	public double getResLeft() {
		return vResLeft;
	}

	public double getDynamicEnengy() {
		return vDynamicEnengy;
	}

	public double getStaticEnengy() {
		return vStaticEnengy;
	}

	public int getAttachmentDirection() {
		return attachmentDirection;
	}
	public String getBbuPoolInfo() {
		return vBbuPoolInfo;
	}

	public void setAttachmentDirection(int attachmentDirection) {
		if(this.attachmentDirection != attachmentDirection){
			int oldValue = this.attachmentDirection;
			this.attachmentDirection = attachmentDirection;
			this.firePropertyChange("attachmentDirection", oldValue, attachmentDirection);
		}
	}
	public void initLinkBus(int vId,int vType)	{
		this.vType = vType;
		this.vId = vId;
		this.putLabelFont(TWaverUtil.getFont(Font.BOLD, 0));
	}

}