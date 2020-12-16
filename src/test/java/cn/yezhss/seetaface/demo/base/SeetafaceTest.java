package cn.yezhss.seetaface.demo.base;

import cn.yezhss.seetaface.SeetaFace;
import cn.yezhss.seetaface.demo.AgePredictorTest;

import java.util.Properties;

/**
 * @author Onion_Ye
 * @time 2020年6月23日 下午4:14:12
 */
public class SeetafaceTest extends SeetaFace {
	/**
	 * 测试的图片(更换到你自己的图片路径)
	 */
	//   /opt/workspace/idea/JSeetaFace6/src/main/resources/static/imgs
	public static final String TEST_PICT = "C:\\dev\\workspace\\JSeetaFace6\\src\\main\\resources\\static\\imgs\\fanbingbing.jpg";
	public static String imgPathRoot ="C:\\dev\\workspace\\JSeetaFace6\\src\\main\\resources\\static\\imgs";
	static {
		SeetaFace.initEnv();
	}
}
