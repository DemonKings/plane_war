package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Image;
import com.neusoft.planewar.client.PlaneWarSystem;
/**
 * 分数类
 * @author 魏通
 *
 */
public class Score extends PlaneObject{
	public int s1,s2;//s1控制千位图片的下标，s2控制百位图片的下标
	public int s;//s表示传过来的分数位置，1代表千位，2代表百位
	public PlaneWarSystem pws;
	public static Image[] imgs={
			Images.imgs.get("score0"),
			Images.imgs.get("score1"),
			Images.imgs.get("score2"),
			Images.imgs.get("score3"),
			Images.imgs.get("score4"),
			Images.imgs.get("score5"),
			Images.imgs.get("score6"),
			Images.imgs.get("score7"),
			Images.imgs.get("score8"),
			Images.imgs.get("score9"),
		};
	public Score(int x,int y,PlaneWarSystem pws,int s) {
		this.x = x;
		this.y = y;
		this.pws = pws;
		this.s = s;
	}
	@Override
	public void draw(Graphics g) {
		s1 = pws.kilobit;
		s2 = pws.hundreds;
		if(this.s==1){
			g.drawImage(imgs[s1], x, y, null);
		}else if(this.s==2){
			g.drawImage(imgs[s2], x, y, null);
		}else{
			g.drawImage(imgs[0], x, y, null);
		}
	}
}
