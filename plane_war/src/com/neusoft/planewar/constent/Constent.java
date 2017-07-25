package com.neusoft.planewar.constent;

import java.io.IOException;
import java.util.Properties;


/**
 *游戏中用到的一些常量数据
 * @author 魏通
 *
 */
public class Constent {
	public static Properties prop = new Properties();
	static{
		try {
			prop.load(Constent.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//窗口宽度
	public static final int GAME_WIDTH = 512;
	//窗口高度
	public static final int GAME_HEIGET = 700;
	//窗口起始x坐标
	public static final int GAME_LOCATION_X = 10;
	//窗口起始y坐标
	public static final int GAME_LOCATION_Y = 10;
	//窗口标题
	public static final String GAME_NAME = "飞机大战";
	//图片根路径
	public static final String GAME_BASIC_IMG = "com/neusoft/planewar/img/";
	//背景图片和障碍物的速度
	public static final int GAME_BG_OB_SPEED = 10;
	//飞机被打到掉血
	public static final int LOST_BLOOD = 10;
	//boss子弹伤害
	public static final int BOSS_BULLET = 30;
	//炸弹的伤害
	public static final int ZHADAN = 1000;
	
}
