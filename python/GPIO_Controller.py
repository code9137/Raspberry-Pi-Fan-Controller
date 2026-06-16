from gpiozero import LED
import time
import sys

"""
    注意:
        gpiozero库默认引脚类型为BCM

    argv 1: BCM型号引脚
    argv 2: 输入true/True,将引脚设置为高电平
    argv 3: 开启时间,每秒
"""
print("python gpio")
num = sys.argv[1]
boo = sys.argv[2]
time_seconds = int(sys.argv[3])
tt = LED(int(num))

if boo == "True" or boo == "true":
    boo = True      # 高电平
else:
    boo = False     # 低电平

def on_gpio(boo):
    if boo : tt.on()
    else: tt.off()
    print("运行中...")
    time.sleep(time_seconds)

    return True
on_gpio(boo)
print(num)
print(boo)
