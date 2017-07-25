package com.neusoft.planewar.util;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
/**
 * 加载图片的工具类
 * @author 魏通
 *
 */
public class GameUtil {
	static File f;
	static URI uri;
	static URL url;
	
	public static Image getImage(String impath){
		URL u = GameUtil.class.getClassLoader().getResource(impath);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static void getAudio(String musicPath){
		try {
			f = new File(musicPath);
			uri = f.toURI();
			url = uri.toURL(); // 解析地址
			AudioClip aau;
			aau = Applet.newAudioClip(url);
			aau.loop(); // 循环播放
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getAudio1(String musicPath){
		try {
			f = new File(musicPath);
			uri = f.toURI();
			url = uri.toURL(); // 解析地址
			AudioClip aau;
			aau = Applet.newAudioClip(url);
			aau.play(); //播放一次
			//aau.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
