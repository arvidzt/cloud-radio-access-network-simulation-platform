/*
 * This source code is part of TWaver 4.5.0
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2013 Serva Software. All rights reserved.
 */

package map;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import twaver.DataBoxSelectionAdapter;
import twaver.DataBoxSelectionEvent;
import twaver.Element;
import twaver.Node;
import twaver.TWaverUtil;
import twaver.ZoomListener;
import twaver.list.TList;
import twaver.network.TNetwork;
import twaver.swing.TDropDownSelector;

public class MapUtil {
	
	public static JInternalFrame createInternalFrame(String title, final TNetwork network, JComponent mainPane){
      	final JInternalFrame frame = new JInternalFrame();
      	frame.getContentPane().add(mainPane);
      	frame.setTitle(title);     	
      	frame.pack();
      	frame.setLocation(30, 30);
		frame.setVisible(false);
		network.getLayeredPane().add(frame, 0);
		
		frame.addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
                network.adjustComponentPosition(frame);
            }
        });		
		
		return frame;
	}
	
	public static JTextArea createColorTextArea(){
		JTextArea textArea = new JTextArea(){
			 public void append(String text) {
				 super.append(text + "\n");
				 this.setCaretPosition(getDocument().getLength());
			 }
		};
		textArea.setEditable(false);
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.GREEN);
		return textArea;
	}
	
	public static JTextField createColorTextField(){
		JTextField textField = new JTextField("");
		textField.setOpaque(true);
		textField.setBackground(new Color(100, 100, 180));
		textField.setForeground(Color.YELLOW);
		textField.setCaretColor(Color.WHITE);
		return textField;
	}
	
    public static JLabel createStatusLabel(final TNetwork network) {
        final JLabel label = new JLabel();
        network.getCanvas().addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
            	updateStatus(label, network, null);
            }
        });
        network.getCanvas().addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
            	updateStatus(label, network, e.getPoint());
            }

            public void mouseMoved(MouseEvent e) {
            	updateStatus(label, network, e.getPoint());
            }
        });
        network.getZoomer().addZoomListener(new ZoomListener(){
			public void zoomChanged(double oldZoom, double newZoom) {
				updateStatus(label, network, null);
			}
        });
        return label;
    }

    public static void updateStatus(JLabel label, TNetwork network, Point point){
    	NumberFormat format = NumberFormat.getInstance();
    	format.setMaximumFractionDigits(2);
    	double zoom = network.getZoom();
    	StringBuffer sb = new StringBuffer();
    	sb.append("  zoom=" + format.format(zoom));
    	if(point != null){
            int x = (int) (point.getX() / zoom);
            int y = (int) (point.getY() / zoom);
            sb.append("  x=" + x + "  y=" + y);
    	}
    	label.setText(sb.toString());
    }
    
	
	public static boolean isUpperCase(char c){
		return c >= 'A' && c <= 'Z';
	}
}