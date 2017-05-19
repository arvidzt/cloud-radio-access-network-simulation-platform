/*
 * This source code is part of TWaver 4.5.0
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2013 Serva Software. All rights reserved.
 */

package map;

import twaver.Element;
import twaver.TWaverUtil;
import twaver.Node;

public class MapNode extends Node {
	
    private Class demoClass = null;
    private MapPane demoPane = null;
    
    public MapNode(Element parent, Class demoClass) {
        this.setParent(parent);
        this.demoClass = demoClass;
        
        String className = TWaverUtil.getClassNameWithoutPackage(demoClass);
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<className.length(); i++){
        	char c = className.charAt(i);
        	if(MapUtil.isUpperCase(c)){
        		if(i == className.length()-1 || !MapUtil.isUpperCase(className.charAt(i+1))){
            		if(sb.length() != 0){
            			sb.append(" ");
            		}
        		}
        	} 
        	sb.append(c);
        }
        this.setName(sb.toString());
        this.setIcon("/demo/resource/images/leaf.gif");

    }

    public Class getDemoClass() {
        return demoClass;
    }

    public MapPane getDemoPane() {
        return demoPane;
    }

    public void setDemoPane(MapPane demoPane) {
        this.demoPane = demoPane;
    }


}