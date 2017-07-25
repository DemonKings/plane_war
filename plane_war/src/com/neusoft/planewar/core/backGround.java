package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Image;
import com.neusoft.planewar.constent.Constent;
import com.neusoft.planewar.util.GameUtil;
/**
 * 背景类
 * @author 魏通
 *
 */
public class backGround extends PlaneObject{
	public int speed = Constent.GAME_BG_OB_SPEED;
	int y1 =0;
	int y2 = -(bgs[0].getHeight(null));
	public backGround(){
		
	}
	public static Image[]  bgs={
		Images.imgs.get("bg"),
		Images.imgs.get("bg"),
	};
	@Override
	public void draw(Graphics g) {
		g.drawImage(bgs[0], 0, y1, null);
		g.drawImage(bgs[1], x, y2, null);
		move();
	}
	@Override
	public void move() {
		//将两个图片拼接在一起
		if(y1>Constent.GAME_HEIGET){
			y1 = -(2*bgs[0].getHeight(null)- Constent.GAME_HEIGET-20);
		}
		if(y2>Constent.GAME_HEIGET){
			y2 = -(2*bgs[1].getHeight(null)- Constent.GAME_HEIGET-20);
		}
		y1+=speed;
		y2+=speed;
	}
}
