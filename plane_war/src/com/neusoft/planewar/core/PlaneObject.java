package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Image;
/**
 * �ɻ��й�����ĸ���
 * @author κͨ
 *	ʵ�ֻ����ƶ������ӿ�
 */
public class PlaneObject implements Drawable, Moveable {
	//����Ϊpublic��Ĭ��Ϊdefault
	public int x;
	public int y;
	public Image img;
	//��дdraw����
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	//��дmove����
	@Override
	public void move() {
		
	}
}
