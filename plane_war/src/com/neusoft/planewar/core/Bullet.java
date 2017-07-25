package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;
import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constent.Constent;
import com.neusoft.planewar.util.GameUtil;
/**
 * 子弹类
 * @author 魏通
 *
 */
public class Bullet extends PlaneObject{
	public Direction dir;//子弹的方向
	public int speed = 50;//子弹的速度
	public PlaneWarSystem pws;
	private boolean live = true;
	private boolean good;
	public Random r = new Random();//随机数
	public Bullet(){
		
	}
//	public Bullet(int x, int y,String imgpath) {
//		this.x = x;
//		this.y = y;
//		this.img = GameUtil.getImage(imgpath);
//	}
//	public Bullet(int x, int y,String imgpath,Direction dir) {
//		this(x, y, imgpath);
//		this.dir = dir;
//	}

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	//构造方法
	public Bullet(int x, int y,Direction dir,PlaneWarSystem pws,boolean good,Image img) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.pws = pws;
		this.good = good;
		this.img = img;
	}
	@Override
	public void draw(Graphics g) {
		//如果子弹死亡，则从容器中移除
		if(!live){
			pws.bullets.remove(this);
			return;
		}
		g.drawImage(img, x, y, null);
		move();
	}
	
	@Override
	public void move() {
		//如果是敌方子弹则调整速度
		if(!good){
			speed = 20;
		}
		//通过方向控制子弹的移动
		switch (dir) {
		case LEFT:
			x-=speed;
			break;
		case LEFT_UP:
			x-=speed;
			y-=speed;
			break;
		case UP:
			y-=speed;
			break;
		case RIGHT_UP:
			x+=speed;
			y-=speed;
			break;
		case RIGHT:
			x+=speed;
			break;
		case RIGHT_DOWN:
			x+=speed;
			y+=speed;
			break;
		case DOWN:
			y+=speed;
			break;
		case LEFT_DOWN:
			x-=speed;
			y+=speed;
			break;
		default:
			break;
		}
		//判断出边境后移除，释放内存
		if(x<0||x>Constent.GAME_WIDTH+100||y<0||y>Constent.GAME_HEIGET+100){
			this.live = false;
			this.pws.bullets.remove(this);
		}
	}
	/**
	 * 获得矩形的方法
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}
	/**
	 * 子弹打boss的方法
	 */
	public boolean hitBoss(Boss b){
		//如果子弹和boss相交且不是自己的子弹并且在y<40之前无敌
		if(this.getRect().intersects(b.getRect())&&this.good!=b.isGood()&&y>40){
			b.setLife(b.getLife()-Constent.LOST_BLOOD);//减血
			this.setLive(false);
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * 飞机打多个boss的方法
	 * @param bosses
	 * @return
	 */
	public boolean hitBosses(List<Boss> bosses){
		for(int i=0;i<bosses.size();i++){
			Boss b = bosses.get(i);
			if(hitBoss(b)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 子弹打一架飞机的方法
	 */
	public boolean hitPlane(Plane p){
		//碰撞检测问题
		if(this.live&&p.isLive()&&this.getRect().intersects(p.getRect())&&this.good!=p.isGood()){
			if(p.isGood()){
				//打主角
				//如果是boss的子弹，则掉30血
				if(this.img.equals(Images.imgs.get("bossbullet"))){
					p.setLife(p.getLife()-Constent.BOSS_BULLET);
				}else{
					p.setLife(p.getLife()-Constent.LOST_BLOOD);
				}
				if(p.getLife()<=0){
					p.setLive(false);
					//爆炸
					Explode e = new Explode(p.x, p.y,pws,1);
					pws.explodes.add(e);
					GameUtil.getAudio1("sounds/die.wav");
					//生命数量减一
					p.setLifeCount(p.getLifeCount()-1);
					if(p.getLifeCount()>0){
						Time t = new Time(pws);
						pws.times.add(t);
					}
				}
				this.setLive(false);
			}else{
				//打敌方飞机
				p.setLife(p.getLife()-Constent.LOST_BLOOD);
				if(p.getLife()<=0){
					p.setLive(false);
				}
				this.live = false;
			}
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 子弹打一堆飞机的方法
	 */
	public boolean hitPlanes(List<Plane> planes){
		for(int i=0;i<planes.size();i++){
			Plane plane = planes.get(i);
			if(hitPlane(plane)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 子弹打到障碍物死亡的方法
	 */
	public boolean hitobstraction(Obstraction ob){
		if(this.getRect().intersects(ob.getRect())){
			if(good){
				ob.setLife(ob.getLife()-20);
			}
			this.live = false;
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 子弹碰到多个障碍物的方法
	 * @param obs
	 * @return
	 */
	public boolean hitobstractions(List<Obstraction> obs){
		for(int i=0;i<obs.size();i++){
			Obstraction ob = obs.get(i);
			if(this.hitobstraction(ob)){
				return true;
			}
		}
		return false;
	}
}	