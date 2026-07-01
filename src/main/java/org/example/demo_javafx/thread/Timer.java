package org.example.demo_javafx.thread;


import org.example.demo_javafx.Launcher;

import java.io.*;



public class Timer implements Runnable{
    final Object clock = new Object();//线程锁
    boolean stop_boo = true;
    File temp_file;
    BufferedReader input;

    public Thread start_timer(){
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
        return thread;
    }

    /**
     *  重启多线程
     *  将当前的Timer对象抛弃,重新创建新的Timer
     *
     * */
    public Timer restart_timer(Thread thread){

        this.stop_boo = false; // 不再循环
        thread.interrupt(); //关闭该多线程

        return new Timer();
    }

    public Timer(){
        try {
            //开启IO流
            temp_file  = new File(Launcher.file_path);
            input = new BufferedReader(new InputStreamReader(new FileInputStream(temp_file)));
            System.out.println("多线程初始化成功!");
        } catch (Exception e) {
            System.out.println("多线程初始化错误:"+e.getMessage());
        }
    }

    @Override
    public void run() {
        while (stop_boo){
            synchronized (clock){
                try {
                    //延时
                    Thread.sleep( (Launcher.time_* 1000L));

                    //读取指定文件的温度
                    int i = Integer.parseInt(input.readLine().trim());
                    System.out.println("temperature == "+i/1000);

                    //关闭IO流
                    input.close();
                    //关闭IO后重新创建IO流
                    input = new BufferedReader(new InputStreamReader(new FileInputStream(temp_file)));


                    //当温度达到指定温度时,调用python文件
                    if(i > Launcher.temperature*1000){
                        if(!new File(Launcher.file_path).exists())
                            System.out.println("python文件不存在!");

                        //执行Python文件，以调用树莓派的GPIO
                        ProcessBuilder process = new ProcessBuilder("python",Launcher.py_path,"27","True","30");
                        process.redirectErrorStream(true);
                        Process start = process.start();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
                        String word;
                        while((word = bufferedReader.readLine()) != null){
                            System.out.println(word);
                        }
                        System.out.println("python exit code: "+start.exitValue());
                    }


                }catch (InterruptedException e){
                    System.out.println("已中断");
                } catch (Exception e) {
                    System.out.println("多线程错误:"+e.getMessage());
                }
            }
        }
    }
    public void stop(){
        stop_boo = false;
    }
}
