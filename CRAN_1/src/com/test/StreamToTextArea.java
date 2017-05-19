package com.test;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;






import com.config.ConfigJavaControler;

import java.awt.Dimension;
import java.awt.TextArea;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.awt.BorderLayout;

public class StreamToTextArea extends JPanel {
  //declare PrintStream and JTextArea
   
	private static StreamToTextArea uniqueInstance = null;  
	private static PrintStream ps = null;
    private TextArea textPane = new TextArea();  //constructor
    public StreamToTextArea() {
    setLayout(new BorderLayout(0, 0));
//    textPane.setPreferredSize(new Dimension(300,300));//设置输出文本框大小

      this.add(textPane);
    //  this.add(new JScrollPane(textAreaOutput));
      //this is the trick: overload the println(String)
      //method of the PrintStream
      //and redirect anything sent to this to the text box
    ps = new PrintStream(System.out) {
      public void println(String x) {
    	  //创建日志文件
    	  try {
    		  
    		 FileWriter write=new FileWriter(ConfigJavaControler.vLogCppFile,true);
    		 write.write(x);
    		 write.close();
		} catch (Exception e) {
		System.err.println("error!!!!!"+e.toString());
		}
    	  
        textPane.append(x + "\n");
      }
    };
    }
    
    
    public static StreamToTextArea getInstance() {  
       if (uniqueInstance == null) {  
           uniqueInstance = new StreamToTextArea();  
       }  
       return uniqueInstance;  
    }  
	
    public PrintStream getPs() {
      return ps;
    }

  @SuppressWarnings("deprecation")
public static void main(String args[]) {
    //create object
    StreamToTextArea blah = new StreamToTextArea();
    //show it
    blah.show();
    //redirect the output stream
    System.setOut(blah.getPs());
    //print to the text box
    System.out.println("IT'S ALIVE!!");
    //print to the terminal (not a string)
    System.out.println(1);
    //print the same thing to the text box (now a string)
    System.out.println("" + 1);
  }
}
