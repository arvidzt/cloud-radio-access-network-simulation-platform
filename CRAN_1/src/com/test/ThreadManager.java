package com.test;
import com.test.StreamToTextArea;
   public class ThreadManager implements Runnable{
       //线程对象引用
       private Thread myThread;
       //休眠间隔
      private long interval=1000l;
      //用volatile保证变量同步
      private volatile boolean threadSuspended;
  
      //开始
      public void start(){
          myThread=new Thread(this,"myThread");
          myThread.start();
      }
      
      /**
 19      * 线程体(执行内容)：
 20      * 如果线程不需要sleep之类的阻塞方法，
 21      * 可以通过Thread.isInterrupted()方法来检测中断
 22      */
      @Override
      public void run() {
    	  StreamToTextArea blah = StreamToTextArea.getInstance();
  		  System.setOut(blah.getPs());
          Thread thisThread=Thread.currentThread();
          while(myThread==thisThread){
              try{
                  Thread.sleep(interval);
                  System.out.println(myThread.getName()+" is running.");
                  //先if一下，避免每次都进入同步块带来的开销
                  if(threadSuspended&&myThread==thisThread){
                      synchronized(this){
                          /* 如果线程在suspend状态被stop，那么myThread==null
                           * 它在下一个循环就快速退出，不再等待了
                           * 不过话说，wait方法本身也会抛中断异常的
                           * 所以我觉得这里去掉"myThread==thisThread"也行
                           * 元方，你怎么看？
 38                          * */
                          while(threadSuspended&&myThread==thisThread){
                              wait();
                          }
                      }
                  }
              }catch(InterruptedException e){
                  System.out.println(thisThread.getName()+
                          " is interrupted by InterruptedException.");
                  //如果这里不打算处理此异常,而又无法抛出去,可以重新断言自己:
                  //Thread.currentThread().interrupt();
              }
          }
          System.out.println(thisThread.getName()+": I'm out, do you copy? ");
      }
      
      /**
       * 停止线程:
 56      * 如果是sleep状态，是interrupt起作用；
 57      * 如果是运行状态，是myThread==null终止了循环
 58      */
     public synchronized void stop(){
          if(myThread==null){
              return;
          }
          Thread moribund=myThread;
          myThread=null;
          moribund.interrupt();
      }
      
      /**
       * 挂起线程：
 70      * 反转条件，并通知其它等待线程
 71      */
      public synchronized void suspend(){
          threadSuspended=!threadSuspended;
          if(!threadSuspended){
              notify();
          }
      }
      
      //test
 }