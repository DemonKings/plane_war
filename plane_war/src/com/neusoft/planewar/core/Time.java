package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Image;
import com.neusoft.planewar.client.PlaneWarSystem;
/**
 * 倒计时类
 * @author 魏通
 *
 */
public class Time extends PlaneObject {
	int slowMax = 20;//通过slow 和 slowmax 控制step的增加
	int slow = 0;
	public int step = 0;
	public PlaneWarSystem pws;
	private boolean live = true;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public static Image[] imgs ={
			Images.imgs.get("time9"),
			Images.imgs.get("time8"),
			Images.imgs.get("time7"),
			Images.imgs.get("time6"),
			Images.imgs.get("time5"),
			Images.imgs.get("time4"),
			Images.imgs.get("time3"),
			Images.imgs.get("time2"),
			Images.imgs.get("time1"),
			Images.imgs.get("time0"),
		};
	public Time(PlaneWarSystem pws) {
		this.x = 0;
		this.y = 0;
		this.pws = pws;
	}
	@Override
	public void draw(Graphics g) {
		if(!live){
			pws.times.remove(this);
			return;
		}
		if(pws.plane.isLive()){
			this.setLive(false);
			pws.times.remove(this);
		}
		if(step>imgs.length-1){
			//当画完一遍后死亡，画game over
			step = 0;
			this.setLive(false);
			Over o = new Over();
			pws.overs.add(o);
			pws.plane.setLifeCount(0);
			return;
		}
		g.drawImage(imgs[step], x, y, null);
		if (slow++ >= slowMax){
			step++;	
			slow = 0;
		}
	}
	@Override
	public void move() {
		
	}
	
}
