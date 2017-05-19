/*
 * 类名：BackgroundPanel
 * 功能：为了加载背景图片，重写JPanel
 */
package ui.Share_bag;

import java.awt.*;

import javax.swing.*;

public class BackgroundPanel extends JPanel {
	    private Image image = null;  
	    public BackgroundPanel(Image image) {  
	        this.image = image;  
	    }  
	  
	    // 固定背景图片，允许这个JPanel可以在图片上添加其他组件  
	    protected void paintComponent(Graphics g) {  
	        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
	    }  

}
