package org.example.demo_javafx;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.demo_javafx.control.Config;
import org.example.demo_javafx.thread.Timer;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class FXController {
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?\\d*$");
    @FXML
    public Spinner<Integer> Time_Field;

    @FXML
    public Label message;
    @FXML
    public Spinner<Integer> Temperature;
    public TextField FilePath;
    public TextField py_file;

    // 初始化方法:需要@FXML注解，而且方法名必须为initialize
    @FXML
    public void initialize() {
        // ========== 通用数字过滤器 ==========
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            // 只允许输入符合整数正则的内容
            if (INTEGER_PATTERN.matcher(newText).matches()) {
                return change; // 允许输入
            }
            return null; // 拒绝输入
        };

        // 时间标签---------------------------------------------------------------------------
        // 参数说明：min(最小值), max(最大值), initialValue(初始值), step(步长)
        IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 60, Launcher.time_, 1);

        // 绑定数值工厂到Spinner
        Time_Field.setValueFactory(valueFactory);

        // 可选：允许手动输入数字（默认允许，增强用户体验）
        Time_Field.setEditable(true);

        Time_Field.getEditor().setTextFormatter(new TextFormatter<>(integerFilter));

        //温度标签--------------------------------------------------------------------------------
        IntegerSpinnerValueFactory valueFactory_1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, Launcher.temperature, 1);
        Temperature.setValueFactory(valueFactory_1);
        Temperature.setEditable(true);



        // 路径参数-------------------------------------------------------------------------------
        FilePath.setText(Launcher.file_path);
        //py路径参数------------------------------------------------------
        py_file.setText(Launcher.py_path);

        System.out.println("Controller initialize method over !");
    }

    @FXML
    protected void onSave(){
        System.out.println("save");
        message.setVisible(true);
        message.setTextFill(new Color(0,0.7,0 ,1));
        //刷新时间
        try {
            //将参数赋值到变量中
            Launcher.time_ = Time_Field.getValue();
            System.out.println(Launcher.time_);
        }catch (Exception e){
            message.setText("设置时间错误："+e.getMessage());
            message.setTextFill(new Color(1,0,0 ,1));
        }

        //温度
        try{
            System.out.println(Temperature.getValue());
            Launcher.temperature = Temperature.getValue();
        }catch (Exception e){
            message.setText("设置温度错误："+e.getMessage());
            message.setTextFill(new Color(1,0,0 ,1));
        }

        //文件
        try{
            Launcher.file_path= FilePath.getText();
            System.out.println(FilePath.getText());
        }catch (Exception e){
            message.setText("设置文件错误："+e.getMessage());
            message.setTextFill(new Color(1,0,0 ,1));
        }


        //重启Timer多线程
        Launcher.myRunnable = Launcher.myRunnable.restart_timer(Launcher.thread);
        Launcher.thread = new Thread(Launcher.myRunnable);
        Launcher.thread.setName(""+System.currentTimeMillis());

        Launcher.thread.setDaemon(true);//设置为守护线程
        Launcher.thread.start();


        //用于显示是否保存成功
        //创建两秒的延迟任务
        // PauseTransition： 创建JavaFX延迟操作
        // Duration是一个时间对象：指定时间单位与长度
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));

        pauseTransition.setOnFinished(event -> message.setVisible(false));

        // 4. 启动延时任务
        pauseTransition.play();
    }


}
