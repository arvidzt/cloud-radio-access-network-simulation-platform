package ui.Draft;
//获取本地字体
import java.awt.GraphicsEnvironment;

public class test3 {
	public static void main(String[] args) {
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();//取得全部可用的字体
        String[] fornames=ge.getAvailableFontFamilyNames();
        for (int i = 0; i < fornames.length; i++) {
            System.out.println(fornames[i]);    
        }
    }

}
