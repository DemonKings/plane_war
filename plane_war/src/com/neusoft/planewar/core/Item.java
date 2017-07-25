package com.neusoft.planewar.core;
/**
 * 道具类
 * @author 魏通
 *
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import com.neusoft.planewar.client.PlaneWarSystem;
public class Item extends PlaneObject{
	public PlaneWarSystem pws;
	private boolean live = true;
	public int lv;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public Item(int x, int y,PlaneWarSystem pws,int lv){
		this.x = x;
		this.y = y;
		this.pws = pws;
		this.lv = lv;
		if(lv==1){
			this.img = Images.imgs.get("item1");
		}else if(lv==2){
			this.img = Images.imgs.get("item2");
		}else if(lv==3){
			this.img = Images.imgs.get("item3");
		}else if(lv==4){
			this.img = Images.imgs.get("item4");
		}else if(lv==5){
			this.img = Images.imgs.get("zhadan");
		}
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}
	@Override
	public void move() {
		y+=3;
	}
	/**
	 * 得到矩形的方法
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}
}
