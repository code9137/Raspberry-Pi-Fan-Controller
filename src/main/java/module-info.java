// 定义模块名：必须和项目的根包名（或自定义名称）一致，是模块的唯一标识
module org.example.demo_javafx {
    // 1. 声明依赖：当前模块需要用到 JavaFX 的控件模块（按钮、标签、布局等）
    requires javafx.controls;
    // 2. 声明依赖：当前模块需要用到 JavaFX 的 FXML 解析模块（加载FXML、绑定控制器）
    requires javafx.fxml;


    // 3. 开放权限：让 javafx.fxml 模块能反射访问 org.example.demo_javafx 包下的类
    opens org.example.demo_javafx to javafx.fxml;
    // 4. 导出包：让外部模块（比如JVM、其他依赖模块）能访问 org.example.demo_javafx 包下的公共类
    exports org.example.demo_javafx;
}