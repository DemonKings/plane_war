package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Image;
/**
 * 飞机有关物体的父类
 * @author 魏通
 *	实现画和移动两个接口
 */
public class PlaneObject implements Drawable, Moveable {
	//定义为public，默认为default
	public int x;
	public int y;
	public Image img;
	//重写draw方法
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	//重写move方法
	@Override
	public void move() {
		
	}
}
