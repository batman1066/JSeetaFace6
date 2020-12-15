package cn.yezhss.seetaface;

import java.util.Properties;

public class SeetaFace {
    /**
     * dll基础路径
     * <pre>
     * 1. 下载路径:https://github.com/seetafaceengine/SeetaFace6
     * 2. 拖到下面介绍有一个模块叫<strong>下载地址</strong>
     * 3. 找到windows下载开发包
     * 4. 下载完成后解压 将下面地址指向 %解压的路径%/sf6.0_windows/lib/x64
     * </pre>
     */
    public static String DLL_PATH = "/opt/sf6.0_centos7/lib64";
    /**
     * CSTA基础路径
     * <pre>
     * 1. 下载路径:https://github.com/seetafaceengine/SeetaFace6
     * 2. 拖到下面介绍有一个模块叫<strong>下载地址</strong>
     * 3. 找到模型文件 下载Part1
     * 4. 下载完成后解压 将下面地址指向 %解压的路径%/sf3.0_models
     * </pre>
     */
    public static String CSTA_PATH = "/opt/sf3.0_models";



    public static String[] DLL_SO_ARRAY= {"tennis","SeetaAuthorize","SeetaFaceDetector600","SeetaFaceLandmarker600",
                                        "SeetaMaskDetector200","SeetaGenderPredictor600","SeetaAgePredictor600","SeetaEyeStateDetector200",
                                        "SeetaFaceAntiSpoofingX600","SeetaPoseEstimation600","SeetaQualityAssessor300","SeetaFaceRecognizer610",
                                        "SeetaFaceTracking600"};
    public static String QT5_SO_PATH = "/opt/workspace/idea/JSeetaFace6/lib";
    public static String[] QT5_SO_ARRAY= {"libQt5Core.so.5","libicuuc.so.56","libicui18n.so.56","libicudata.so.56"};



    public static void initEnv(){
        Properties pro = System.getProperties();
        // 仅支持
        String os = pro.getProperty("os.name").toUpperCase();
		/*if (!os.contains("WINDOWS")) {
			throw new RuntimeException("暂不支持非windows系统");
		}*/
        String arch = pro.getProperty("os.arch");
		if (!arch.contains("64")) {
			throw new RuntimeException("暂不支持非64位系统");
		}
        // 加载dll so
        String start="";
        String end="";
        if(os.contains("WINDOWS")){
            end=".dll";
        }else{
            start="lib";
            end=".so";
        }
        //load dll so
        for(String DLL_SO:DLL_SO_ARRAY){
            String path=DLL_PATH + "/"+start+DLL_SO+end;
            System.out.println("loadPath:"+path);
            System.load(path);
        }
        //load jni dll so
        if(os.contains("WINDOWS")){
            String path = SeetaFace.class.getResource("/x64/windows/JSeetaFace6.dll").getPath();
            System.out.println("loadPath:"+path);
            System.load(path);
        }else {
            loadQt5So();
            String path = SeetaFace.class.getResource("/x64/centos/libJSeetaface6.so").getPath();
            System.out.println("loadPath:"+path);
            System.load(path);
        }
    }
    public static void loadQt5So(){
        for(String DLL_SO:QT5_SO_ARRAY){
            String path = QT5_SO_PATH + "/"+DLL_SO;
            System.out.println("loadPath:"+path);
            System.load(path);
        }
    }

}
