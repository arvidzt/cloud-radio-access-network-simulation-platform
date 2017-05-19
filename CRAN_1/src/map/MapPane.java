/*
 * This source code is part of TWaver 4.5.0
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2013 Serva Software. All rights reserved.
 */

package map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import twaver.TWaverUtil;
import twaver.swing.FullScreenSupport;
import twaver.swing.TableLayout;

public class MapPane extends JPanel{
	
	private FullScreenSupport fullScreenSupport;
    private Date createTime = new Date();
    
	public MapPane(){
		super(new BorderLayout());
		this.fullScreenSupport = new FullScreenSupport(this, JSplitPane.RIGHT, KeyStroke.getKeyStroke("F5")){
		    protected JPanel createExpandPane(){
		    	double[] rows = new double[] { 
					TableLayout.PREFERRED, 
				};
		    	double[] columns = new double[] { 
						TableLayout.FILL,
						TableLayout.PREFERRED, 
						TableLayout.PREFERRED, 
				}; 
				JPanel panel = new JPanel(new TableLayout(columns, rows));
				JButton button = new JButton(TWaverUtil.getIcon("/demo/resource/images/exportImage.png"));
				button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						TWaverUtil.exportImage(MapPane.this);
					}
				});
				panel.add(button, "1,0");
				panel.add(this.getRestoreButton(), "2,0");
				return panel;
		    }
		};
		this.fullScreenSupport.setExpand(true);
	}
	
	public FullScreenSupport getFullScreenSupport() {
		return fullScreenSupport;
	}

	public void setFullScreenSupport(FullScreenSupport fullScreenSupport) {
		this.fullScreenSupport = fullScreenSupport;
	}

	public Date getCreateTime() {
		return createTime;
	}

}