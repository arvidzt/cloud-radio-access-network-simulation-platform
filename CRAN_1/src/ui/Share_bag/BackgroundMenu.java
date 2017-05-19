/*
 * 类名：BackgroundMenu
 * 功能：代码中没有用到此类
 */
package ui.Share_bag;
import java.awt.*;
import javax.swing.*;
public class BackgroundMenu extends JMenu
{
	Color bgColor=new Color(65,169,225); 
	Font font=MyTools.f15;
    public BackgroundMenu(String s){ 
            super(s); 
    } 
    public void setColor(Color color) 
    { 
        bgColor=color; 
    } 
    public void setFont(Font font) 
    { 
    	this.font=font; 
    } 
    protected void paintComponent(Graphics g) 
    { 
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g; 
        g2d.setColor(bgColor); 
        g2d.setFont(font);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1); 
    }
}
