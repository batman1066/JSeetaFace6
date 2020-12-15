
# JSeetaface6
本项目主要是完善seetaFace6在centos7的jni,一对多搜索（稍后提供）。

# 
centos7下依赖QT5 lib
链接: https://pan.baidu.com/s/1mG-HthbcJtSMMmTLBCC4Mw 提取码: 8k3i

#### 介绍
1. 基于中科院seetaface6进行封装的JAVA人脸识别算法库。
2. 该封装仅做了java到seetaface6(c++)的连接(jni)，可以让java通过resource下x64/centos/JSeetaFace6.so直接使用seetaface6动态库。
3. windows版本请到大神https://github.com/CongCongBig/JSeetaface6
7. 在此提供[Seetaface6的传送门](https://github.com/seetafaceengine/SeetaFace6)。
8. 在此提供[Seetaface6入门教程的传送门](http://leanote.com/blog/post/5e7d6cecab64412ae60016ef)。

#### 安装教程

1.  拉取本代码
2.  用idea或eclipse导入maven项目
3.  可以看项目中test目录下
```
cn.yezhss.seetaface.demo.base.SeetafaceTest
```
基本上一看就会，或查看下面使用说明，**请按照seetaface6官方说明修改相关路径**
#### 使用说明

1.  以上步骤完成后，并不能立即使用，你还需下载官方提供的动态库以及识别模型。详情见 说明2
2.  需要先进入 **SeetafaceTest** 类修改自定义参数即可以开始测试，下载地址见说明3

参数 | 含义
---|---
**DLL_PATH** | 官方提供的动态库路径
**CSTA_PATH** | 官方提供的模型路径
**TEST_PICT** | 自己的测试图片

3.  官方提供的动态库路径以及官方提供的模型路径下载， 请前往[Seetaface6](https://github.com/seetafaceengine/SeetaFace6)，在他的文档说明中有下载地址的模块，下载并解压后 将自定义参数指向你所放路径.
4.  查看该包下的部分测试代码进行学习使用
```
cn.yezhss.seetaface.demo
```
5.  官方API在本项目中基本都有，方法名雷同，仅对大小写以及下划线进行转换，API可参考你刚刚下载的动态库包里有个docs文件夹，或参考[Seetaface6入门教程](http://leanote.com/blog/post/5e7d6cecab64412ae60016ef)
6.  一般的操作方式参考如下:
```
// 首先创建你关注的类 此处使用FaceDetector做说明
FaceDetector detector = new FaceDetector(官方face_detector.csta在你本地存放的路径);
// 创建完后可直接使用. 但是入参是图片的BGR字节数组，可以使用SeetafaceUtil创建
SeetaImageData image = SeetafaceUtil.toSeetaImageData(你的图片路径);
// 直接使用seetaface提供的方法
SeetaFaceInfoArray infos = detector.detect(image);
// 得到的结果infos可以进行你的自定义操作
...
// 如果使用完后 您的线程需要释放，但项目并不停止则需要调用close方法
// 当然我并不建议你close掉，毕竟创建消耗资源的问题我也不累述，可以重复使用，只需要自行写个存放类进行缓存
detector.close();
```
7.  如有疑问便提,看到后会尽快回复
