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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import twaver.ElementAttribute;
import twaver.Node;
import twaver.TUIManager;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.chart.BarChart;
import twaver.chart.Item;
import twaver.network.ui.ComponentAttachment;
import twaver.network.ui.ElementUI;
import twaver.table.TPropertySheet;

public class BbuAttachment extends ComponentAttachment{
	
	private final static Color bodyColor = new Color(0, 0, 0, 168);
	private final static Color gradientColor = new Color(25, 255, 255, 108);
	
	private static BarChart bar;
	private static Item vRruNum;
	private static Item vTransPower;
	private static Item vEquipPower;
	private static Item vRes;
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
	//	items.add(vRruNum = new Item("RruNum", new Color(255, 0, 255)));
		items.add(vTransPower = new Item("TransPower", new Color(220, 0, 255)));
		items.add(vEquipPower = new Item("EquipPower", new Color(170, 0, 255)));
		items.add(vRes = new Item("Res", new Color(130, 0, 255)));
		bar = new BarChart(items, null, Color.BLACK);
		bar.setLegendLayout(TWaverConst.LEGEND_LAYOUT_VERTICAL);
		bar.setValueTextVisible(false);
		bar.setForeground(Color.WHITE);
		bar.setLegendFont(TWaverUtil.getFont(Font.ITALIC, 9));
		bar.setYAxisOutlineColor(Color.WHITE);
		bar.setXAxisOutlineColor(Color.WHITE);
		bar.setOpaque(false);
	}

	public BbuAttachment(String name, ElementUI ui) {
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
		
		this.setBodyGradient(false);
		this.setBodyColor(new Color(230, 230, 230, 160));
		this.setBorderColor(Color.DARK_GRAY);
		this.setBorderVisible(true);
		this.setStyle(TWaverConst.ATTACHMENT_STYLE_BUBBLE);
		this.setTail(30);
		this.setArc(10);
		this.setWidth(190);
		this.setHeight(380);	
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
		bar.setTitle("Bbu"+node.getId());
		this.setDirection(node.getAttachmentDirection());
		Point point =new Point();
		point = element.getCenterLocation();
		j1.setText(Double.toString(point.getX()));
		j2.setText(Double.toString(point.getY()));
	//	vRruNum.setValue(node.getRruNum());
		vTransPower.setValue(node.getTransPower());
		vEquipPower.setValue(node.getEquipPower());
		vRes.setValue(node.getRes());
		//xItem.setName("X坐标  "+ TWaverConst.DEFAULT_DOUBLE_FORMATER.format(xItem.getValue()));
		//yItem.setName("Y坐标  "+ TWaverConst.DEFAULT_DOUBLE_FORMATER.format(yItem.getValue()));
		//vRruNum.setName( "关联Rru " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vRruNum.getValue()));
		vTransPower.setName("传输攻略 " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vTransPower.getValue()));
		vEquipPower.setName("设备功率 " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vEquipPower.getValue()));
		vRes.setName("资源数  " + TWaverConst.DEFAULT_DOUBLE_FORMATER.format(vRes.getValue()));
	}
}