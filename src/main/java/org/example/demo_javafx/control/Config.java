package org.example.demo_javafx.control;

import org.example.demo_javafx.FXApplication;
import org.example.demo_javafx.Launcher;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Config {
    /**
     * 读取配置参数
     * @param filePath 文件路径
     * @return [温度,时间,路径]
     * */
    public static ArrayList<Object> load(String filePath){
        File file = new File(filePath);
        ArrayList<Object> param = new ArrayList<>();
        //判断文件是否存在
        if(file.exists()) {
            try {
                //数组

                Properties properties = new Properties();
                BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
                properties.load(stream);

                param.add(Integer.parseInt(properties.getProperty("temperature")));
                param.add(Integer.parseInt(properties.getProperty("time")));
                param.add(properties.getProperty("filePath"));
                param.add(properties.getProperty("pythonPath"));

                System.out.println("读取成功: \n\t启动温度: "+param.get(0)+"\n\t刷新时间: "+param.get(1)+"\n\t读取的文件路径: "+param.get(2)+"\n\tPython路径: "+param.get(3)+"\n");
            } catch (Exception e) {
                System.out.println("错误: 读取外部配置失败" + e.getMessage());
            }
        }else System.out.println("警告: 外部配置文件不存在");
        return param;
    }

    /**
     *      getClassLoader()    获取类加载器
     *          用于 1. 将当前类路径(Class-Path)的.class文件 给 JVM加载
     *              2. 读取当前类路径(Class-Path)的图片,以及其他文件
     *          - 方法:
     *              getResourceAsStream()       获取输入流
     *
     *  @return [温度,时间,路径]
     * */
    public static ArrayList<Object> default_load(){
        //获取Jar包内部指定的文件的输入流
        //.getClassLoader()获取 当前Class的ClassLoader对象(类加载器对象) 用于加载类路径（ClassPath）下的.class文件到JVM  以及  读取类路径下的资源文件（如.properties、.txt）
        //返回参数
        ArrayList<Object> param = new ArrayList<>();
        try {
            InputStream stream = Config.class.getClassLoader().getResourceAsStream("conf.properties");
            Properties properties = new Properties();
            properties.load(stream);

            //读取
            param.add(Integer.parseInt(properties.getProperty("temperature")));
            param.add(Integer.parseInt(properties.getProperty("time")));
            param.add(properties.getProperty("filePath"));
            param.add(properties.getProperty("pythonPath"));
        } catch (Exception e) {
            System.out.println("读取默认配置失败:"+e.getMessage());
        }




        return param;
    }

    /**
     *     修改参数时,在jar包目录下生成一份conf.properties配置文件
     *      未使用
     * */
    public static void setConfig(String target,int temp,int time){
        File file = new File("./conf.properties");
        try{
            file.createNewFile();
            Properties properties = new Properties();
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
            properties.load(stream);

            properties.setProperty("temperature", String.valueOf(Launcher.temperature));
            properties.setProperty("time", String.valueOf(Launcher.time_));
            properties.setProperty("filePath", Launcher.file_path);

        } catch (Exception e) {
            System.out.println("创建配置文件失败:"+e.getMessage());
        }
    }
}
