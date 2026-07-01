package org.example.demo_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX框架中:
 *      会先执行Controller的initialize()方法,再执行Application
 *
 */
public class FXApplication extends Application {

    @Override
    public void start(Stage stage){
        int height = 240;
        int width = 320;


        try {
            //读取FXML的样式
            FXMLLoader fxmlLoader = new FXMLLoader(FXApplication.class.getResource("view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle("Setting");
            stage.setScene(scene);

            //设置大小,setResizable(boolean)在树莓派系统中无效
            stage.setMinHeight(height);
            stage.setMaxHeight(height);
            stage.setMinWidth(width);
            stage.setMaxWidth(width);

            stage.setResizable(false);      //不允许调整窗口大小

//            stage.getIcons().add(new Image("")); //设置窗口图标

            stage.show();//显示窗口
            System.out.println("Application start method running over !");
        } catch (Exception e) {
            System.out.println("初始化FXApplication错误: "+e.getMessage());
            e.printStackTrace();  // 打印完整堆栈
            System.exit(7);
        }
    }

    //关闭时调用
    @Override
    public void stop() throws Exception {

    }
}
