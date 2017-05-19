/*
 * This source code is part of TWaver 4.5.0
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2013 Serva Software. All rights reserved.
 */

package map.network;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.chart.BarChart;
import twaver.chart.Item;
import twaver.network.ui.ComponentAttachment;
import twaver.network.ui.ElementUI;

public class RruAttachment extends ComponentAttachment{
	
	private final static Color bodyColor = new Color(0, 0, 0, 168);
	private final static Color gradientColor = new Color(25, 255, 255, 108);
			
	private static BarChart bar;
	private static Item vUeNum;
	private static Item vRruTransPower;
	private static Item vEquipPower;
	private static Item vCarrierFrequent;
	private static Item vRruBandwidth;
	private static Item xItem;
	private static Item yItem;
	final JLabel e1 = new JLabel("X:");
	final JLabel e2 = new JLabel("Y:");
	final JTextField j1 = new JTextField();
	final JTextField j2 = new JTextField();
	
	static{
		Vector items = new Vector();
		//items.add(xItem = new Item("x",Color.RED));
		//items.add(yItem = new Item("y",Color.white));
	//	items.add(vUeNum = new Item("UeNum", new Color(255, 0, 255)));
		items.add(vRruTransPower = new Item("RruTransPower", new Color(220, 0, 255)));
		items.add(vEquipPower = new Item("EquipPower", new Color(170, 0, 255)));
		items.add(vCarrierFrequent = new Item("CarrierFrequent", new Color(130, 0, 255)));
		items.add(vRruBandwidth = new Item("RruBandwidth",new Color(150, 0, 255)));
		bar = new BarChart(items, null, Color.BLACK);
		bar.setLegendLayout(TWaverConst.LEGEND_LAYOUT_VERTICAL);
		bar.setValueTextVisible(false);
		bar.setForeground(Color.WHITE);
		bar.setLegendFont(TWaverUtil.getFont(Font.ITALIC, 9));
		bar.setYAxisOutlineColor(Color.WHITE);
		bar.setXAxisOutlineColor(Color.WHITE);
		bar.setOpaque(false);
	}
	
	public RruAttachment(String name, ElementUI ui) {
		super(name, ui);
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		j1.setEditable(false);
		j2.setEditable(false);
		j1.setOpaque(false);
	    j2.setOpaque(false);
		
		panel2.setLayout(new GridLayout(1, 4, 1, 1));
		panel2.add(e1);
		panel2.add(j1);
		panel2.add(e2);
		panel2.add(j2);
		panel.setLayout(new BorderLayout());
	    panel.add(panel2, BorderLayout.NORTH);
	    panel.setOpaque(false);
	    panel2.setOpaque(false);
		panel.add(bar);
        this.setStyle(TWaverConst.ATTACHMENT_STYLE_BUBBLE);
        this.setPosition(TWaverConst.POSITION_CENTER);
        this.setBorderVisible(true);
        this.setBorderColor(Color.BLACK);
        this.setBodyVisible(true);
        this.setBodyGradient(true);
        this.setBodyColor(bodyColor);
        this.setBodyGradientColor(gradientColor);
        this.setBodyGradientType(TWaverConst.GRADIENT_LINE_W);
        this.setBorderColor(Color.RED);
        this.setBorderVisible(false);
		this.setComponent(panel);
		this.setWidth(200);
		this.setHeight(200);
	}
	
	protected void update() {
		this.updateAttachment();
        this.invalidate();
    	super.update();
    }

	private void updateAttachment(){
		StateNode node = (StateNode)element;
		bar.setTitle("Rru"+node.getId());
		this.setDirection(node.getAttachmentDirection());
		Point point =new Point();
		point = element.getCenterLocation();
		j1.setText(Double.toString(point.getX()));
		j2.setText(Double.toString(point.getY()));
		//vUeNum.setValue(node.getUeNum());
		vRruTransPower.setValue(node.getRruTransPower());
		vEquipPower.setValue(node.getEquipPower());
		vCarrierFrequent.setValue(node.getCarrierFrequent());
		vRruBandwidth.setValue(node.getRruBandwidth());
	//	vUeNum.setName( "关联Ue " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vUeNum.getValue()));
		vRruTransPower.setName("传输攻略 " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vRruTransPower.getValue()));
		vEquipPower.setName("设备功率 " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vEquipPower.getValue()));
		vCarrierFrequent.setName("频率 " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vCarrierFrequent.getValue()));
		vRruBandwidth.setName("带宽 " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vRruBandwidth.getValue()));
	}
}