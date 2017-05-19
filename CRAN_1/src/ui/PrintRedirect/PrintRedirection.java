package ui.PrintRedirect;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import com.config.ConfigJavaControler;

public class PrintRedirection {
	static ByteArrayOutputStream vByteOutStream;
	PrintStream vNewPrintSteam;
	FileInputStream vLogIn;
	File vLogFile;
	int vMark=0;
	int vCppLogMark=0;//日志文件的读取标志
	public PrintRedirection() {
		try {
			vLogFile=new File(ConfigJavaControler.vLogCppFile);//创建路径
			if(!vLogFile.exists()) 
				vLogFile.createNewFile();
			else
				vLogFile.delete();
				vLogFile.createNewFile();
				System.err.println("文件已经存在");
			
			vLogIn=new FileInputStream(vLogFile);
			
			PrintStream vOrientPrint = System.out;
			vByteOutStream = new ByteArrayOutputStream();
			vNewPrintSteam = new PrintStream(vByteOutStream);
			System.setOut(vNewPrintSteam);//重定向IO
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}// TODO Auto-generated method stub
	}
	/**
	 * 
	 * @return 输出流的字符串
	 */
	public synchronized String get_buffer() {
		byte vTemp[];
		byte vCppTemp[];//存储Cpp文件中 的字符串
		if (vByteOutStream != null) {
			synchronized (vByteOutStream) {
				if (vByteOutStream.size()>0 &&vByteOutStream.size()>vMark) {
					vTemp=new byte[vByteOutStream.size()-vMark];
					System.arraycopy(vByteOutStream.toByteArray(),vMark>0?vMark-1:0, vTemp, 0,vByteOutStream.size()-vMark);
					String vTempString=new String(vTemp);
					vMark=vByteOutStream.size();
					try{
						if (vLogIn.available()>vCppLogMark){
							vCppTemp=new byte[vLogIn.available()-vCppLogMark];
							vLogIn.skip(vCppLogMark);
							vLogIn.read(vCppTemp);
							vCppLogMark=vLogIn.available();
							vTempString=vTempString+new String(vCppTemp);
						}
					}catch(Exception e){
						
					}
					
					return vTempString;
				} else {
					return null;
				}
			}
		} else {
			return null;
		}

	}

	// 设置一个内部类，回调函数
	public class call_back {
		public call_back() {

		}
		public String getString() {

			String vString=get_buffer();//由于string实现了serloized interface
			
//			try{
//				System.err.println("here");
//				PipedOutputStream cPipeOut=new PipedOutputStream();
//				PipedInputStream vPipeIn=new PipedInputStream(cPipeOut);
//				//cPipeOut.connect(vPipeIn);
//				
//				ObjectOutputStream vOutObj=new ObjectOutputStream(cPipeOut);
//				
//				ObjectInputStream vInObj=new ObjectInputStream(vPipeIn);
//				System.err.println("here2");
//				vOutObj.writeObject(vString);
//				
//				System.err.println("here3");
//				 
//				vString=(String)vInObj.readObject();
//				
//				System.err.println("deepcopy:"+vInObj.readObject());
//				 
//				vPipeIn.close();
//				cPipeOut.close();
//				
//				
//				vOutObj.close();
//				vInObj.close();
//				vInObj.close();
//				vByteOutStream.reset();
//			}catch(Exception  e){
//				System.err.println(e.toString());
//				e.printStackTrace();
//			}
			
			return vString;
		}

	}

	public void Dispose() {

	}

	@Override
	/**
	 * java 类似析构函数，对象被情理后执行
	 */
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		if (vByteOutStream != null) {
			vByteOutStream.close();
		}
		if (vByteOutStream != null) {
			vLogIn.close();
		}
	}

}
