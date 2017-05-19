/*
 * 类名：MyTools
 * 功能：字体封装类
 */
package ui.Share_bag;

import java.awt.*;
import java.io.*;

public class MyTools {
	 private static Font definedFont = null;  
	//创建一款字体
	public static Font f1=new Font("微软雅黑", Font.BOLD, 17);
//	public static Font f25=new Font("@Hiragino Sans GB W3", Font.BOLD, 25);
	public static Font f25=new Font("微软雅黑", Font.BOLD, 25);
	public static Font f15=new Font("微软雅黑", Font.PLAIN, 15) ;
	public static Font f14=new Font("微软雅黑", Font.PLAIN, 14) ;
	public static Font f12=new Font("微软雅黑", Font.PLAIN, 12); 
//	public static Font getDefinedFont() {  
//        if (definedFont == null) {  
//            InputStream is = null;  
//           BufferedInputStream bis = null;  
//           try {  
//                is = MyTools.class.getResourceAsStream("Font"+File.separator+"冬青黑体W3.otf");  
//                 bis = new BufferedInputStream(is);  
//                // createFont返回一个使用指定字体类型和输入数据的新 Font。<br>  
//                // 新 Font磅值为 1，样式为 PLAIN,注意 此方法不会关闭 InputStream  
//                definedFont = Font.createFont(Font.TRUETYPE_FONT, bis);  
//                // 复制此 Font对象并应用新样式，创建一个指定磅值的新 Font对象。  
//                definedFont = definedFont.deriveFont(Font.BOLD, 90);  
//            } catch (FontFormatException e) {  
//                e.printStackTrace();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            } finally {  
//                try {  
//                    if (null != bis) {  
//                        bis.close();  
//                    }  
//                    if (null != is) {  
//                        is.close();  
//                    }  
//                } catch (IOException e) {  
//                    e.printStackTrace();  
//                }  
//            }  
//        }  
//        return definedFont;  
//    }  

}
