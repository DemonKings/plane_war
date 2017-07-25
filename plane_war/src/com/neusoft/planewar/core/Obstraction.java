package com.neusoft.planewar.core;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constent.Constent;
/**
 * 障碍物类
 * @author 魏通
 *
 */
public class Obstraction extends PlaneObject{
	public int speed = Constent.GAME_BG_OB_SPEED;
	public int life = 100;
	public PlaneWarSystem pws;
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public Obstraction(int x,int y,PlaneWarSystem pws){
		this.x = x;
		this.y = y;
		this.pws = pws;
		this.img = Images.imgs.get("ob");
	}
	@Override
	public void draw(Graphics g) {
		bb.draw(g);
		if(this.getLife()<=0){
			Explode e = new Explode(this.x, this.y,pws,2);
			pws.explodes.add(e);
			pws.obs.remove(this);
			pws.obScore();
		}
		g.drawImage(img, x, y, null);
		move();
	}
	@Override
	public void move() {
		y+=speed;
	}
	/**
	 * 得到矩形的方法
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(x, y,img.getWidth(null), img.getHeight(null));
	}
	public BloodBar bb = new BloodBar();
	/**
	 * 内部类，通过画两个矩形表示血条
	 */
	class BloodBar{
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.YELLOW);
			g.drawRect(x, y-10,img.getWidth(null) , 5);
			g.fillRect(x, y-10, img.getWidth(null)*life/100, 5);
			g.setColor(c);
		}
	}
}
