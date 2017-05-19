package ui.PrintRedirect;
import java.io.PrintStream;
import java.util.Timer;


public class Entrance {
	public Entrance(){}
	/**
	 * 实现一秒钟的自动刷新功能
	 */
	public PrintStream work(){
		PrintRedirection a1=new PrintRedirection();
		PrintRedirection.call_back c1=a1.new call_back();//创建回调函数
		Timer aTimer=new Timer();//创建定时器
		aTimer.schedule(new TimeKeeper(c1),1000,1000);//创建task，并调度1000ms调度一次
		return a1.vNewPrintSteam;
	}
}
