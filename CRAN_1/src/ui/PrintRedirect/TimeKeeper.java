package ui.PrintRedirect;
import java.util.TimerTask;

import com.config.ConfigJavaControler;

public class TimeKeeper extends TimerTask {
	PrintRedirection.call_back vCallBack;

	public TimeKeeper(PrintRedirection.call_back vCallBack) {
		this.vCallBack = vCallBack;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		run(vCallBack);
	}

	public synchronized void run(PrintRedirection.call_back a1) {

		try {
			String vTemp=a1.getString();
			if(vTemp.length()>0){
				ConfigJavaControler.vLogList=vTemp;
				System.err.println("Timer--callback:"+vTemp);//a1.getString()返回的值就是你想要的值
			}else
				System.err.println("No data flush");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
