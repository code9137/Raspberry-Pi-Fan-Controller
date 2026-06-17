package org.example.demo_javafx;

import javafx.application.Application;
import org.example.demo_javafx.control.Config;
import org.example.demo_javafx.thread.Timer;

import java.util.ArrayList;

public class  Launcher {
    public static Timer myRunnable;
    public static Thread thread;
    //参数
    public static int time_ = 3;    //刷新时间(秒)
    public static Integer temperature = 40;  //目标温度(摄氏度*1000)
    public static String file_path = "/sys/class/thermal/thermal_zone0/temp";
    public static String py_path = "python/GPIO_Controller.py";

    public static void main(String[] args) {

        //先读取内部配置文件,再读取外部配置文件
        //读取默认配置文件
        ArrayList<Object> default_param = Config.default_load();
        if(!default_param.isEmpty()) {
            temperature = (int) default_param.get(0);
            time_ = (int) default_param.get(1);
            file_path = (String) default_param.get(2);
            py_path = (String) default_param.get(3);
        }
        //读取用户配置文件
        ArrayList<Object> param = Config.load("./conf.properties");
        temperature = (int)param.get(0);
        time_ = (int)param.get(1);
        file_path = (String) param.get(2);


        myRunnable = new Timer();
        thread = myRunnable.start_timer();

        //注意：因为Application是阻塞线程(所有准备必须在启动前做完)
        Application.launch(FXApplication.class, args);
    }
}
