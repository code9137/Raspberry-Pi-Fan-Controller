package org.example.demo_javafx;

import javafx.application.Application;
import org.example.demo_javafx.control.Config;
import org.example.demo_javafx.thread.Timer;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class  Launcher {
    public static Timer myRunnable;
    public static Thread thread;
    //参数
    public static int time_ = 3;    //刷新时间(秒)
    public static Integer temperature = 40;  //目标温度(摄氏度*1000)
    public static String file_path = "/sys/class/thermal/thermal_zone0/temp";
    public static String py_path = "python/GPIO_Controller.py";

    public static String src_path;

    static {
        try {
            /*
                类.class.getProtectionDomain().getCodeSource().getLocation().toURI()   获取指定类的系统路径
                Paths.get(URI)      将 URI对象 转换为 Paths对象   由于URI对象默认会在 路径前面添加一个\,所以需要Paths.get()将其转为当前系统的路径格式
                String.valueOf(object)  将Path对象转换为 String
            */
            src_path = String.valueOf(Paths.get(Launcher.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {



        //先查看外部是否有conf.properties配置文件
        if(new File(src_path+"\\conf.properties").exists()){
            System.out.println("读取外部配置文件中....");
            //读取用户配置文件
            ArrayList<Object> param = Config.load(src_path+"\\conf.properties");
            temperature = (int)param.get(0);
            time_ = (int)param.get(1);
            file_path = (String) param.get(2);
        }else{
            //如果不存在,则读取默认配置文件
            System.out.println("不存在外部配置文件,正在读取默认配置...");
            ArrayList<Object> default_param = Config.default_load();
            if(!default_param.isEmpty()) {
                temperature = (int) default_param.get(0);
                time_ = (int) default_param.get(1);
                file_path = (String) default_param.get(2);
                py_path = (String) default_param.get(3);
            }
        }
        System.out.println("src_path ==" + src_path);





        //开启多线程
        myRunnable = new Timer();
        thread = myRunnable.start_timer();



        //注意：因为Application是阻塞线程(所有准备必须在启动前做完)
        Application.launch(FXApplication.class, args);
    }
}
