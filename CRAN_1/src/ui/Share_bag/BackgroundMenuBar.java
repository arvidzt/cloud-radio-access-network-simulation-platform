/*
 * 类名：BackgroundMenuBar jmb;//主干菜单  
 * 功能：重写菜单栏背景颜色属性
 */
package ui.Share_bag;
import java.awt.*;

import javax.swing.*;
public class BackgroundMenuBar extends JMenuBar{
	Color bgColor=new Color(65,169,225); 
    public void setColor(Color color) 
    { 
        bgColor=color; 
    } 
    protected void paintComponent(Graphics g) 
    { 
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g; 
        g2d.setColor(bgColor); 
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1); 
    }
}
