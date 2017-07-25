package com.neusoft.planewar.util;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.neusoft.planewar.constent.Constent;
/**
 * 加载窗口的工具类
 * @author 魏通
 *
 */
public class MyFrame extends Frame{
	public void launchFrame(){
		//设置窗口的大小
		this.setSize(Constent.GAME_WIDTH, Constent.GAME_HEIGET);
		//设置窗口的初始位置
		this.setLocation(Constent.GAME_LOCATION_X, Constent.GAME_LOCATION_Y);
		//设置窗口的可见性
		this.setVisible(true);
		//设置窗口的不可变性
		this.setResizable(false);
		//设置标题
		this.setTitle(Constent.GAME_NAME);
		//设置窗口可关闭
		this.addWindowListener(new WindowAdapter() {
			//重写WindowClosing方法
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//启动线程
		new MyThread().start();
		new MyThread1().start();
	}
	/**
	 * 线程类
	 * @author 魏通
	 *
	 */
	private class MyThread extends Thread{
		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private class MyThread1 extends Thread{
		@Override
		public void run() {
			GameUtil.getAudio("sounds/7790.wav");
			}
	}
	
	// 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
	Image backImg = null;

	// 重写update()方法，在窗口的里层添加一个虚拟的图片
	@Override
	public void update(Graphics g) {
		if (backImg == null) {
			// 如果虚拟图片不存在，创建一个和窗口一样大小的图片
			backImg = createImage(Constent.GAME_WIDTH, Constent.GAME_HEIGET);
		}
		// 获取到虚拟图片的画笔
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constent.GAME_WIDTH, Constent.GAME_HEIGET);
		backg.setColor(c);
		// 调用虚拟图片的paint()方法，每50ms刷新一次
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}
}
