package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Image;
import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constent.Constent;
/**
 * 警告类
 * @author 魏通
 *
 */
public class Warning extends PlaneObject{
	public PlaneWarSystem pws;
	public int step = 0;
	private boolean live = true;
	private int count = 0; //计数
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public static Image[] imgs={
		Images.imgs.get("w1"),
		Images.imgs.get("w2"),
		Images.imgs.get("w3"),
		Images.imgs.get("w4"),
		Images.imgs.get("w5"),
		Images.imgs.get("w6"),
		Images.imgs.get("w7"),
		Images.imgs.get("w8"),
		Images.imgs.get("w9"),
		Images.imgs.get("w10"),
	};
	public Warning() {
		
	}
	public Warning(PlaneWarSystem pws){
		this.pws = pws;
		this.x = Constent.GAME_WIDTH/2-imgs[0].getWidth(null)/2;
		this.y = Constent.GAME_HEIGET/2-imgs[0].getHeight(null)/2-100;
	}
	@Override
	public void draw(Graphics g) {
		//如果死掉则不再画
		if(!live){
			return;
		}
		if(step>imgs.length-1){
			step = 0;
			count++;
			//当警告执行5遍后移除
			if(count==5){
				this.live = false;
			}
		}
		g.drawImage(imgs[step], x, y, null);
		step++;
	}
}
