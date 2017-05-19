/*
 * 类名：BackgroundTree
 * 功能：为了能使树形列表可以添加背景图片，重写JTree
 */
package ui.Share_bag;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;

import javax.swing.*;
import javax.swing.tree.*;

public class BackgroundTree extends JTree{
	private Image image = null;  
    public BackgroundTree(Image image,TreeNode root) {  
        this.image = image;  
    }  
    // 固定背景图片，允许这个JPanel可以在图片上添加其他组件  
    protected void paintComponent(Graphics g) {
    	 g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
    }  

}
