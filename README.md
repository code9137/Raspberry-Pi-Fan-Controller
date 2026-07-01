# 安装
### 方法一
1. 手动下载Liberica JDK 21(与OpenJDK21的区别在于: 集成了图形界面库 JavaFX)
    ```
    wget https://download.bell-sw.com/java/21.0.6+10/bellsoft-jdk21.0.6+10-linux-aarch64-full.tar.gz         # 安装拥有JavaFX的jdk21
    tar -xzf bellsoft-jdk21.0.6+10-linux-aarch64-full.tar.gz         #解压
    ```
2. 设置环境变量
    ```
    export JAVA_HOME=[下载并解压的JDK路径]
    export PATH=$JAVA_HOME/bin:$PATH
    ```
3. 安装python以及gpiozero框架
   ```
    apt install python
    pip install gpiozero   # 这一步可以省略,一般树莓派系统会自带
   ```


### 方法二
1. 在树莓派安装Java21 与 Python:

      ```
      apt install default-openjdk
      apt install python
      ```

      <br>
2. 安装apt默认的JavaFX框架依赖:

      ```
      apt install openjfx
      ```

    <br>
3. 安装python依赖gpiozero(这一步可以省略,一般树莓派系统会自带)

      ```
      pip install gpiozero
      ```
## 风扇与树莓派GPIO引脚连接

1. NPN三极管:

   - VCC -> 风扇正极
   - 风扇负极 -> NPN 集电极C
   - 风扇负极 -> 二极管正极
   - 二极管负极 -> 风扇正极
   - NPN 发射极E -> GND
   - GPIO ->1kΩ电阻 -> NPN 基极B



## 使用方法

1. 启动
   ```
   java -jar [项目的Fat_Jar包名]        #运行
   ```
   或者
   ```
   java --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml -jar aa.jar
   ```
   <br>
2. 设置用户配置文件:

## 版本需求

1. Java 21版本
2. Raspberry Pi OS with Desktop (树莓派官方带桌面系统)
3. 一个PNP型三极管,一个二极管, 5V/0.1A的外部风扇, 1K欧电阻, N根杜邦线 (如果有能力,就搞个PCB板)

### 注意事项
- 该项目只是用于练习JavaFX和py的gpiozero, 实际作用并不大


