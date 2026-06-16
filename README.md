# 安装

1. 在树莓派安装Java17 与 Python:

```
apt install openjdk-17-jdk
apt install python
```

<br>
2. 安装JavaFX框架依赖:

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
   - 风扇负极 -> NPN 集电极
   - 风扇负极 -> 二极管正极
   - 二极管负极 -> 风扇正极
   - NPN 发射极 -> GND
   - GPIO ->1kΩ电阻 -> NPN 基极


<br/>
2. 使用L9110H驱动电机模块:

   - VCC ->

## 使用方法

1. 在终端输入
   ```
   cd /home/ngo
   wget https://download.bell-sw.com/java/21.0.6+10/bellsoft-jdk21.0.6+10-linux-aarch64-full.tar.gz
   tar -xzf bellsoft-jdk21.0.6+10-linux-aarch64-full.tar.gz
   ```
   或者
   ```
   java --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml -jar aa.jar
   ```
<br>
2. 设置用户配置文件:

## 版本需求

1. Java 17版本
2. Raspberry Pi OS with Desktop (树莓派官方带桌面系统)
3. 一个PNP型三极管,一个二极管, 5V/0.1A的外部风扇, 1K欧电阻, N根杜邦线 (如果有能力用搞个PCB板就搞个PCB板)

### 注意事项
- 该项目只是用于练习JavaFX和py的gpiozero, 实际作用并不大
```

