package cn.yezhss.seetaface.demo;

import cn.yezhss.seetaface.SeetaFace;
import cn.yezhss.seetaface.api.FaceDetector;
import cn.yezhss.seetaface.api.FaceLandmarker;
import cn.yezhss.seetaface.api.FaceRecognizer;
import cn.yezhss.seetaface.demo.base.SeetafaceTest;
import cn.yezhss.seetaface.po.*;
import cn.yezhss.seetaface.util.SeetafaceUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 人脸识别搜索demo
 * <pre>
 * 如果觉得不错的话
 * 关注公众号: 时匆
 * 给个小小的支持
 * </pre>
 * @author Onion_Ye
 * @time 2020年7月15日 上午11:58:14
 */
public class FaceSearchTest extends SeetafaceTest {
	public static float MIN_similarity=0.62f;


	public static Map<String,float[]> faceDb=new HashMap<>();
	private static FaceRecognizer recognizer;
	private static FaceDetector detector;
	private static FaceLandmarker marker;

	public static void main(String[] args) {
		String searchPersonPath =imgPathRoot+"/test/linzhiyingtest.jpg";
		// 初始化人脸识别器
		recognizer = new FaceRecognizer(CSTA_PATH + "/face_recognizer.csta");
		detector = new FaceDetector(CSTA_PATH + "/face_detector.csta");
		marker = new FaceLandmarker(CSTA_PATH + "/face_landmarker_pts5.csta");

		register();
		String key=search(searchPersonPath);
		System.out.printf("识别为:%s\n", key);
	}

	public static void register(){
		//将XX目录下的jpg、png图片都注册到人脸库中，以文件名为key
		String path =imgPathRoot;
		Collection<File> files = FileUtils.listFiles(new File(path), new String[]{"jpg","jpeg", "png"}, false);
		for (File file : files) {
			String key = file.getName();
			float[] features= extract(file.getAbsolutePath());
			faceDb.put(key,features);
		}
	}
	public static String search(String searchPersonPath){
		long startTime=System.currentTimeMillis();
		float current_similarity=0f;
		String key=null;
		float[] features1 = extract(searchPersonPath);
		for(Map.Entry<String,float[]> face:faceDb.entrySet()){
			float calculateSimilarity = recognizer.calculateSimilarity(features1, face.getValue());
			if(calculateSimilarity>MIN_similarity
				&&calculateSimilarity>current_similarity){
				key=face.getKey();
			}
		}
		System.out.println("耗时为:"+(System.currentTimeMillis()-startTime)+"毫秒");
		return key;
	}






	
	/**
	 * 获取特征数组
	 * @author Onion_Ye
	 * @time 2020年7月15日 下午12:10:56
	 */
	private static float[] extract(String fileName) {
		SeetaImageData image = SeetafaceUtil.toSeetaImageData(fileName);
		SeetaFaceInfoArray infos = detector.detect(image);
		for(SeetaFaceInfo info : infos.data) {
			PointWithMask[] marks = marker.mark(image, info.pos);
			SeetaPointF[] points = new SeetaPointF[marks.length];
			for (int n = 0; n < marks.length; n++) {
				points[n] = marks[n].point;
			}
			return recognizer.extract(image, points);
		}
		return null;
	}
	
}
