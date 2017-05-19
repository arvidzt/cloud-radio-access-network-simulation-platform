/*
 * This source code is part of TWaver 4.5.0
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2013 Serva Software. All rights reserved.
 */

package map.network;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import twaver.TWaverConst;
import twaver.network.TNetwork;

public class PropertyChangeProcessor implements PropertyChangeListener{
	TNetwork network;
	
	public PropertyChangeProcessor(TNetwork network){
		this.network = network;
	}
	
	public void propertyChange(PropertyChangeEvent evt) {		
		if (!(evt.getSource() instanceof StateNode)) {
			return;
		}
		StateNode node = (StateNode)evt.getSource();
		String name = evt.getPropertyName();
		// add or remove attachment
		if(name.equals(TWaverConst.PROPERTYNAME_SELECTED)){
			if(node.getType() == 1){ 
				if(node.containsAttachment("BbuPool")){
						node.removeAttachment("BbuPool");
						}
				else{
					node.addAttachment("BbuPool");	
				}
			}
			else if(node.getType() == 2){ 
				if(node.containsAttachment("Bbu")){
						node.removeAttachment("Bbu");
						}
				else{
					node.addAttachment("Bbu");	
				}
			}
			else if(node.getType() == 3){ 
				if(node.containsAttachment("Rru")){
						node.removeAttachment("Rru");
						}
				else{
					node.addAttachment("Rru");	
				}
			}
			
			
		}
		// adjust attachment direction
		if(name.equals(TWaverConst.PROPERTYNAME_SELECTED) ||
				name.equals(TWaverConst.PROPERTYNAME_LOCATION)){
			Point location = node.getCenterLocation();
			Point center = network.getLogicalCenterPoint();
			if(location.x > center.x && location.y < center.y){
				node.setAttachmentDirection(TWaverConst.ATTACHMENT_DIRECTION_BOTTOM_LEFT);
			}
			else if(location.x > center.x && location.y > center.y){
				node.setAttachmentDirection(TWaverConst.ATTACHMENT_DIRECTION_TOP_LEFT);
			}
			else if(location.x < center.x && location.y < center.y){
				node.setAttachmentDirection(TWaverConst.ATTACHMENT_DIRECTION_BOTTOM_RIGHT);
			}
			else{
				node.setAttachmentDirection(TWaverConst.ATTACHMENT_DIRECTION_TOP_RIGHT);
			}
		}
	}
}