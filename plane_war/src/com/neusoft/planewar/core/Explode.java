package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.client.PlaneWarSystem;
/**
 * 爆炸类
 * @author 魏通
 *
 */
public class Explode extends PlaneObject{
	public int step1 = 0;//爆炸时画的第几张图片
	public int step2 = 0;
	public int step3 = 0;
	private boolean live = true;
	public PlaneWarSystem pws;
	public int lv;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	//创建静态Image数组
	//飞机爆炸
	public static Image[] imgs1 ={
		Images.imgs.get("e1"),
		Images.imgs.get("e2"),
		Images.imgs.get("e3"),
		Images.imgs.get("e4"),
		Images.imgs.get("e5"),
		Images.imgs.get("e6"),
		Images.imgs.get("e7"),
		Images.imgs.get("e8"),
		Images.imgs.get("e9"),
		Images.imgs.get("e10"),
	};
	//障碍物爆炸
	public static Image[] imgs2 ={
			Images.imgs.get("ex1"),
			Images.imgs.get("ex2"),
			Images.imgs.get("ex3"),
			Images.imgs.get("ex4"),
			Images.imgs.get("ex5"),
			Images.imgs.get("ex6"),
			Images.imgs.get("ex7"),
			Images.imgs.get("ex8"),
			Images.imgs.get("ex9"),
			Images.imgs.get("ex10"),
			Images.imgs.get("ex11"),
			Images.imgs.get("ex12"),
		};
	public static Image[] imgs3 ={
			Images.imgs.get("ex1-1"),
			Images.imgs.get("ex2-1"),
			Images.imgs.get("ex3-1"),
			Images.imgs.get("ex4-1"),
			Images.imgs.get("ex5-1"),
			Images.imgs.get("ex6-1"),
			Images.imgs.get("ex7-1"),
			Images.imgs.get("ex8-1"),
			Images.imgs.get("ex9-1"),
			Images.imgs.get("ex10-1"),
			Images.imgs.get("ex11-1"),
			Images.imgs.get("ex12-1"),
		};
	
	@Override
	public void draw(Graphics g) {
		//爆炸后移除
		if(!live){
			pws.explodes.remove(this);
			return;
		}
		if(lv==1){
			if(step1 > imgs1.length-1){
				step1 = 0;
				this.live = false;
				return;
			}
			g.drawImage(imgs1[step1], x, y, null);
			step1++;
		}else if(lv==2){
			if(step2 > imgs2.length-1){
				step2 = 0;
				this.live = false;
				return;
			}
			g.drawImage(imgs2[step2], x, y, null);
			step2++;
		}else if(lv==3){
			if(step3 > imgs3.length-1){
				step3 = 0;
				this.live = false;
				return;
			}
			g.drawImage(imgs3[step3], x, y, null);
			step3++;
		}
	}
	public Explode() {
		
	}
	public Explode(int x, int y,PlaneWarSystem pws,int lv) {
		this.x = x;
		this.y = y;
		this.pws = pws;
		this.lv = lv;
	}
}
