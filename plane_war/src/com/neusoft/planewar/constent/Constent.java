package com.neusoft.planewar.constent;

import java.io.IOException;
import java.util.Properties;


/**
 *��Ϸ���õ���һЩ��������
 * @author κͨ
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
	//���ڿ��
	public static final int GAME_WIDTH = 512;
	//���ڸ߶�
	public static final int GAME_HEIGET = 700;
	//������ʼx����
	public static final int GAME_LOCATION_X = 10;
	//������ʼy����
	public static final int GAME_LOCATION_Y = 10;
	//���ڱ���
	public static final String GAME_NAME = "�ɻ���ս";
	//ͼƬ��·��
	public static final String GAME_BASIC_IMG = "com/neusoft/planewar/img/";
	//����ͼƬ���ϰ�����ٶ�
	public static final int GAME_BG_OB_SPEED = 10;
	//�ɻ����򵽵�Ѫ
	public static final int LOST_BLOOD = 10;
	//boss�ӵ��˺�
	public static final int BOSS_BULLET = 30;
	//ը�����˺�
	public static final int ZHADAN = 1000;
	
}
