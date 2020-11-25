# 介绍

使用Android Uiautomator2脱机运行Monkey测试

# 安装方式

## 安装AndroidTest
```
gradle assembleAndroidTest

在/app/build/outputs/apk/androidTest/debug生成
aut
adb install app-debug-androidTest.apk
```

## 安装MonkeyTest
```
gradle assembleRelease

在/app/build/outputs/apk/release生成

adb install app-release-unsigned.apk
```

# 启动命令

```
adb shell am instrument -w -r -e debug false -e class 'android.example.xinxi.monkeytest.ExampleInstrumentedTestIgetcool' android.example.autodismiss.test/android.support.test.runner.AndroidJUnitRunner
```

# 适配

## 小米手机

```
弹出【继续安装】
```

## 华为手机

```
弹出【继续安装】- 弹出【继续安装】
```


# vivo手机

参考: https://www.cnblogs.com/lwbqqyumidi/p/10217076.html