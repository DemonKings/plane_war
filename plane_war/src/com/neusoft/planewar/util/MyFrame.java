package com.neusoft.planewar.util;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.neusoft.planewar.constent.Constent;
/**
 * ���ش��ڵĹ�����
 * @author κͨ
 *
 */
public class MyFrame extends Frame{
	public void launchFrame(){
		//���ô��ڵĴ�С
		this.setSize(Constent.GAME_WIDTH, Constent.GAME_HEIGET);
		//���ô��ڵĳ�ʼλ��
		this.setLocation(Constent.GAME_LOCATION_X, Constent.GAME_LOCATION_Y);
		//���ô��ڵĿɼ���
		this.setVisible(true);
		//���ô��ڵĲ��ɱ���
		this.setResizable(false);
		//���ñ���
		this.setTitle(Constent.GAME_NAME);
		//���ô��ڿɹر�
		this.addWindowListener(new WindowAdapter() {
			//��дWindowClosing����
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//�����߳�
		new MyThread().start();
		new MyThread1().start();
	}
	/**
	 * �߳���
	 * @author κͨ
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
	
	// ���ͼƬ��˸�����⣬��˫���巽�������˸����
	Image backImg = null;

	// ��дupdate()�������ڴ��ڵ�������һ�������ͼƬ
	@Override
	public void update(Graphics g) {
		if (backImg == null) {
			// �������ͼƬ�����ڣ�����һ���ʹ���һ����С��ͼƬ
			backImg = createImage(Constent.GAME_WIDTH, Constent.GAME_HEIGET);
		}
		// ��ȡ������ͼƬ�Ļ���
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constent.GAME_WIDTH, Constent.GAME_HEIGET);
		backg.setColor(c);
		// ��������ͼƬ��paint()������ÿ50msˢ��һ��
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}
}
