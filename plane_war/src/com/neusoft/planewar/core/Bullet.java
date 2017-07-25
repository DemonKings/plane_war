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
 * �ӵ���
 * @author κͨ
 *
 */
public class Bullet extends PlaneObject{
	public Direction dir;//�ӵ��ķ���
	public int speed = 50;//�ӵ����ٶ�
	public PlaneWarSystem pws;
	private boolean live = true;
	private boolean good;
	public Random r = new Random();//�����
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
	//���췽��
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
		//����ӵ�����������������Ƴ�
		if(!live){
			pws.bullets.remove(this);
			return;
		}
		g.drawImage(img, x, y, null);
		move();
	}
	
	@Override
	public void move() {
		//����ǵз��ӵ�������ٶ�
		if(!good){
			speed = 20;
		}
		//ͨ����������ӵ����ƶ�
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
		//�жϳ��߾����Ƴ����ͷ��ڴ�
		if(x<0||x>Constent.GAME_WIDTH+100||y<0||y>Constent.GAME_HEIGET+100){
			this.live = false;
			this.pws.bullets.remove(this);
		}
	}
	/**
	 * ��þ��εķ���
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}
	/**
	 * �ӵ���boss�ķ���
	 */
	public boolean hitBoss(Boss b){
		//����ӵ���boss�ཻ�Ҳ����Լ����ӵ�������y<40֮ǰ�޵�
		if(this.getRect().intersects(b.getRect())&&this.good!=b.isGood()&&y>40){
			b.setLife(b.getLife()-Constent.LOST_BLOOD);//��Ѫ
			this.setLive(false);
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * �ɻ�����boss�ķ���
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
	 * �ӵ���һ�ܷɻ��ķ���
	 */
	public boolean hitPlane(Plane p){
		//��ײ�������
		if(this.live&&p.isLive()&&this.getRect().intersects(p.getRect())&&this.good!=p.isGood()){
			if(p.isGood()){
				//������
				//�����boss���ӵ������30Ѫ
				if(this.img.equals(Images.imgs.get("bossbullet"))){
					p.setLife(p.getLife()-Constent.BOSS_BULLET);
				}else{
					p.setLife(p.getLife()-Constent.LOST_BLOOD);
				}
				if(p.getLife()<=0){
					p.setLive(false);
					//��ը
					Explode e = new Explode(p.x, p.y,pws,1);
					pws.explodes.add(e);
					GameUtil.getAudio1("sounds/die.wav");
					//����������һ
					p.setLifeCount(p.getLifeCount()-1);
					if(p.getLifeCount()>0){
						Time t = new Time(pws);
						pws.times.add(t);
					}
				}
				this.setLive(false);
			}else{
				//��з��ɻ�
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
	 * �ӵ���һ�ѷɻ��ķ���
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
	 * �ӵ����ϰ��������ķ���
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
	 * �ӵ���������ϰ���ķ���
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