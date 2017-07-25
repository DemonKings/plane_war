package com.neusoft.planewar.core;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constent.Constent;
/**
 * boss类
 * @author 魏通
 *
 */
public class Boss extends PlaneObject{
	public PlaneWarSystem pws;
	private int speed = 5;//boss 出场时的移速
	private boolean right;//通过布尔变量控制boss左右移动
	public Random r = new Random();//随机数
	Direction[] dirs = Direction.values();//枚举类型转换为数组
	private int life = 10000;//boss初始生命为10000
	private boolean live=true;
	private boolean good;
	int step = 0;
	//静态数组，boss变身组图
	public static Image[] imgs ={
			Images.imgs.get("b1"),
			Images.imgs.get("b2"),
			Images.imgs.get("b3"),
			Images.imgs.get("b4"),
			Images.imgs.get("b5"),
			Images.imgs.get("b6"),
			Images.imgs.get("b7"),
			Images.imgs.get("b8"),
			Images.imgs.get("b9"),
		};
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public Boss() {
		
	}
	public Boss(PlaneWarSystem pws) {
		this.pws = pws;
		this.img = Images.imgs.get("boss");
		this.x = Constent.GAME_WIDTH/2 - imgs[0].getWidth(null)/2;
		this.y = -imgs[0].getHeight(null);
	}
	public boolean isGood() {
		return good;
	}
	public void setGood(boolean good) {
		this.good = good;
	}
	@Override
	public void draw(Graphics g) {
		bb.draw(g);//画血条
		if(this.getLife()<=0){
			this.setLive(false);
		}
		if(!this.isLive()){
			Explode e = new Explode(this.x, this.y,pws,1);
			pws.explodes.add(e);
			pws.bosses.remove(this);
			pws.bossScore();
			return;
		}
		//当血量小于300时换图片
		if(life<=3000){
			if(step>imgs.length-1){
				step = 0;
			}
			g.drawImage(imgs[step], x, y, null);
			step++;
			if(r.nextInt(1000)>900){
				fire1();
			}
		}else{
			g.drawImage(img, x, y, null);
			if(r.nextInt(1000)>980){
				fire();
			}
		}
		move();
	}
	@Override
	public void move() {
		//当y等于50后左右移动
		if(y>50){
			y=50;
			if(right){
				x += speed;
			}else{
				x -= speed;
			}
			if(x<5){
				right = true;
			}
			if(x>Constent.GAME_WIDTH-imgs[0].getWidth(null)-5){
				right = false;
			}
		}
		y += speed;
	}
	/**
	 * boss发子弹的方法
	 */
	public void fire(){
		Bullet b = new Bullet(x+imgs[0].getWidth(null)/2-60, y+60,Direction.DOWN,pws,false,Images.imgs.get("bossbullet"));
		pws.bullets.add(b);
	}
	/**
	 * boss变身后的子弹
	 */
	public void fire1(){
		for(int i=0;i<8;i++){
			Bullet b = new Bullet(x+imgs[0].getWidth(null)/2-60, y+60,dirs[i],pws,false,Images.imgs.get("bossbullet"));
			pws.bullets.add(b);
		}
	}
	public BloodBar bb= new BloodBar();
	/**
	 * 显示boss血条的方法 
	 */
	class BloodBar{
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawImage(Images.imgs.get("boss_head"), 10, 50, null);
			g.drawRect(70, 50,430 , 50);
			g.fillRect(70, 50, 430*life/10000, 50);
			g.setColor(c);
		}
	}
	/**
	 * 获得矩形的方法
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(x, y, imgs[0].getWidth(null), imgs[0].getHeight(null));
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
}
