package com.neusoft.planewar.core;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constent.Constent;
/**
 * ը����
 * @author κͨ
 *
 */
public class Zhadan extends PlaneObject{
	public PlaneWarSystem pws;
	private boolean live = true;
	Random r = new Random();
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public Zhadan(int x,int y,PlaneWarSystem pws) {
		this.x = x;
		this.y = y;
		this.pws = pws;
		this.img = Images.imgs.get("zhadan");
	}
	@Override
	public void draw(Graphics g) {
		if(!live){
			pws.zhadans.remove(this);
			return;
		}
		g.drawImage(img, x, y, null);
		move();
	}
	@Override
	public void move() {
//		if(y<=0){
//			y = 0;
//		}
		y-=10;
	}
	/**
	 * ��þ��εķ���
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}
	/**
	 * ը����ը�ķ���
	 * @param l��ը�� �ӳ�ȥ��ը�ľ���
	 * @return
	 */
	public void bigBang(int l){
		int l2 = pws.plane.l1- l; 
		if(this.y==l2||this.y<=0){
			//���ɴ�ը
			Explode e1 = new Explode(x-200, y-200, pws, 3);
			pws.explodes.add(e1);
			//ը���ɻ�
			for(int i=0;i<pws.enemyplanes.size();i++){
				Plane plane = pws.enemyplanes.get(i);
				if(this.isLive()&&plane.isLive()&&plane.getRect().intersects(new Rectangle(x-200, y-200, 456, 465))){
					plane.setLife(plane.getLife()-Constent.ZHADAN);
					plane.setLive(false);
				}
			}
			//ը��boss
			for(int i=0;i<pws.bosses.size();i++){
				Boss b = pws.bosses.get(i);
				if(this.isLive()&&b.isLive()&&b.getRect().intersects(new Rectangle(x-200, y-200, 456, 465))){
					b.setLife(b.getLife()-Constent.ZHADAN);
				}
			}
			//ը���ϰ���
			for(int i=0;i<pws.obs.size();i++){
				Obstraction o = pws.obs.get(i);
				if(this.isLive()&&o.getRect().intersects(new Rectangle(x-200, y-200, 456, 465))){
					o.setLife(o.getLife()-Constent.ZHADAN);
				}
			}
			//�Ƴ�ը��
			this.setLive(false);
		}
	}
}
