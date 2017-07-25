package com.neusoft.planewar.core;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import com.neusoft.planewar.client.PlaneWarSystem;
import com.neusoft.planewar.constent.Constent;
import com.neusoft.planewar.util.GameUtil;
/**
 * �ɻ���̳и���
 * @author κͨ
 *
 */
public class Plane extends PlaneObject{
	public boolean re;
	public int speed = 10;//���Ƿɻ����ٶ�
	public boolean up,down,left,right;//�ĸ��ƶ�����
	private boolean good;//�Ƿ�Ϊ�÷ɻ���Ĭ��Ϊfalse
	public Direction dir = Direction.STOP;//ö������
	public PlaneWarSystem pws;//PlaneWarSystem���pws 
	private boolean live = true;//�ж��Ƿ���ŵı���
	public static Random r = new Random();//�����
	public int lv;//ͨ��lv���Ʋ�ͬ�ɻ��켣
	public int preX;//�ɻ�����һ��λ��
	public int preY;
	private int life;//�ɻ���ʼ����
	private int lifeCount;//�ɻ���������
	public int l1;// ������ȡ��ը��ʱ�ɻ�y����
	public int count = 1;//ը��������
	public int getLifeCount() {
		return lifeCount;
	}
	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	/**
	 * ���췽��
	 * @param x	x����
	 * @param y	y����
	 * @param imgpath	ͼƬ·��
	 */
	public Plane(){
		
	}
//	public Plane(int x, int y, String imgpath) {
//		this.x = x;
//		this.y = y;
//		//����GameUtil��ķ�������ͼƬ
//		this.img = GameUtil.getImage(imgpath);
//	}
//	//���췽��������good����
//	public Plane(int x, int y,boolean good) {
//		this.good = good;
//		if(good){
//			this.img = Images.imgs.get("myplane");
//		}else{
//			this.img = Images.imgs.get("enemyplane1");
//		}
//		//�ж��Ƿ�Ϊ�÷ɻ������������x����
//		if(good){
//			this.x = x-img.getWidth(null)/2;
//		}else{
//			this.x = x;
//		}		
//		this.y = y;
//	}
	//���췽������pws���루���Ƿɻ���
	public Plane(int x, int y,boolean good,PlaneWarSystem pws,int lv) {
		this.x = x;
		this.y = y;
		this.good = good;		
		this.pws = pws;
		this.lv = lv;
		this.preX = x;
		this.preY = y;
		if(good){
			life = 100;
		}else{
			life = 10;
		}
		//ͨ��good�жϵ��ҷɻ���ͼƬ
		if(good){
			this.img = Images.imgs.get("myplane");
			lifeCount = 3;
		}else{
			this.img = Images.imgs.get("enemyplane1");
		}
		//��������Ƿɻ�����x��ȥ����һ�룬����x������
		if(good){
			this.x = x-img.getWidth(null)/2;
		}else{
			this.x = x;
		}		
		this.y = y;
		//ͨ��lv���Ʒɻ�����
		if(lv==-1){
			this.dir = Direction.RIGHT_DOWN;
		}else if(lv==-2){
			this.dir = Direction.LEFT_DOWN;
		}else if(lv==-3){
			this.dir = Direction.DOWN;
		}
	}
	public boolean isGood() {
		return good;
	}
	public void setGood(boolean good) {
		this.good = good;
	}
	//	//���췽��������dir���������˷ɻ���
//	public Plane(int x, int y,boolean good,Direction dir) {
//		//����֮ǰ���캯���е���ͬ����
//		this.x = x;
//		this.y = y;
//		this.good = good;
//		this.dir = dir;
//		this.img = Images.imgs.get("enemyplane1");
//	}
	/**
	 * ��дdraw����
	 */
	@Override
	public void draw(Graphics g) {
		//����з��ɻ�����
		if(!good&&!live){
			//���ɱ�ը
			Explode e = new Explode(this.x, this.y,pws,1);
			pws.explodes.add(e);
			//���ɵ���
			if(r.nextInt(10)>5){
				Item item = new Item(this.x+this.img.getWidth(null)/2-14,this.y+this.img.getHeight(null)/2-14, pws,1);
				pws.items.add(item);
			}else if(r.nextInt(10)>7){
				Item item = new Item(this.x+this.img.getWidth(null)/2-14,this.y+this.img.getHeight(null)/2-14, pws,2);
				pws.items.add(item);
			}else if(r.nextInt(10)>5){
				Item item = new Item(this.x+this.img.getWidth(null)/2-14,this.y+this.img.getHeight(null)/2-14, pws,5);
				pws.items.add(item);
			}
			pws.enemyplanes.remove(this);//�Ƴ��з��ɻ�
			pws.enemyScore();//�Ʒ�
			return;
		}else if(!live){
			return;
		}
		g.drawImage(img, x, y, null);
		//�ж��Ƿ�Ϊ�÷ɻ�������������ٶ�
		if(!good){
			speed = 5;
		}
		//��ͬ�ɻ����ò�ͬ�ƶ�����
		if(good){
			bb.draw(g);//��Ѫ��
			move();
		}else{
			enemyMove();
		}
	}
	/**
	 * ���̰��µķ���
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		//�õ�������ֵ
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_J:
			if(live)
			fire();//������ţ����ӵ�
			break;
		case KeyEvent.VK_O:
			reBirth();//����
			break;
		case KeyEvent.VK_SPACE:
			if(live&&count>0){
				l1 = this.y;
				Zhadan z = new Zhadan(x+20, y-20, pws);
				pws.zhadans.add(z);
				count-=1;
			}
			break;
		default:
			break;		
		}
	}	
	/**
	 * �����ɿ��ķ���
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		default:
			break;
		}
	}
	/**
	 * ���Ƿɻ��ƶ��ķ���
	 */
	@Override
	public void move() {
		//��¼��һ����λ��
		this.preX = x;
		this.preY = y;
		//�ƶ�
		if(up) y-=speed;
		if(down) y+=speed;
		if(left) x-=speed;
		if(right) x+=speed;
		//�ж��Ƿ���߽�
		outofBound();
	}
	//�з��ɻ����ƶ�����
	public void enemyMove(){
		//��¼��һ����λ��
		this.preX = x;
		this.preY = y;
		//�ƶ�
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
		//����������Ƶз��ɻ����ӵ���Ƶ��
//		if(r.nextInt(100)>95)
//		fire();
		if(this.lv==-1&&this.x==10){
			fire1();
		}else if(this.lv==-2&&this.x==462){
			fire1();
		}else if(this.lv==-3&&this.y==100){
			fire1();
		}
//		fire1();
	}
	//�жϷɻ��Ƿ���߽�
	public void outofBound(){
		if(y<30){
			y=30;
		}
		if(y>Constent.GAME_HEIGET-img.getHeight(null)){
			y=Constent.GAME_HEIGET-img.getHeight(null);
		}
		if(x<0){
			x=0;
		}
		if(x>Constent.GAME_WIDTH-img.getWidth(null)){
			x=Constent.GAME_WIDTH-img.getWidth(null);
		}
	}
	/**
	 * ȷ������ķ���
	 */
	public void confirmDirection(){
		if(left && !up && !right && !down){
			dir = Direction.LEFT;
		}else if(left && up && !right && !down){
			dir = Direction.LEFT_UP;
		}else if(!left && up && !right && !down){
			dir = Direction.UP;
		}else if(!left && up && right && !down){
			dir = Direction.RIGHT_UP;
		}else if(!left && !up && right && !down){
			dir = Direction.RIGHT;
		}else if(!left && !up && right && down){
			dir = Direction.RIGHT_DOWN;
		}else if(!left && !up && !right && down){
			dir = Direction.DOWN;
		}else if(left && !up && !right && down){
			dir = Direction.LEFT_DOWN;
		}else{
			dir = Direction.STOP;
		}
	}
	/**
	 * �ɻ����ӵ��ķ���
	 * ��ͬ�ɻ�����ͬ�ӵ�
	 */
	public void fire(){
		if(this.lv==1){
			Bullet b1 = new Bullet(x+this.img.getWidth(null)/2-10, y,Direction.UP,pws,good,Images.imgs.get("bullet1"));
			pws.bullets.add(b1);

		}else if(lv==2){
			Bullet b1 = new Bullet(x+this.img.getWidth(null)/2-10, y,Direction.UP,pws,good,Images.imgs.get("bullet1"));
			Bullet b2 = new Bullet(x, y,Direction.UP,pws,good,Images.imgs.get("bullet1"));
			Bullet b3 = new Bullet(x+this.img.getWidth(null)-20, y, Direction.UP,pws,good,Images.imgs.get("bullet1"));
			pws.bullets.add(b1);
			pws.bullets.add(b2);
			pws.bullets.add(b3);
		}else if(lv>=3){
			Bullet b1 = new Bullet(x+this.img.getWidth(null)/2-10, y,Direction.UP,pws,good,Images.imgs.get("bullet1"));
			Bullet b2 = new Bullet(x, y,Direction.UP,pws,good,Images.imgs.get("bullet1"));
			Bullet b3 = new Bullet(x+this.img.getWidth(null)-20, y, Direction.UP,pws,good,Images.imgs.get("bullet1"));
			Bullet b4 = new Bullet(x, y,Direction.LEFT_UP,pws,good,Images.imgs.get("bullet1"));
			Bullet b5 = new Bullet(x+this.img.getWidth(null)-20, y, Direction.RIGHT_UP,pws,good,Images.imgs.get("bullet1"));
			//���ӵ���ӵ�����
			pws.bullets.add(b1);
			pws.bullets.add(b2);
			pws.bullets.add(b3);
			pws.bullets.add(b4);
			pws.bullets.add(b5);
			
		}
	}
	/**
	 * �з��ɻ��ķ��ӵ�����
	 */
	public void fire1(){
		if(this.lv==-1){
			Bullet b = new Bullet(x+this.img.getWidth(null)/2, y,Direction.RIGHT_DOWN,pws,good,Images.imgs.get("bullet-1"));
			pws.bullets.add(b);
		}
		if(this.lv==-2){
			Bullet b = new Bullet(x+this.img.getWidth(null)/2, y,Direction.LEFT_DOWN,pws,good,Images.imgs.get("bullet-2"));
			pws.bullets.add(b);
		}
		if(this.lv==-3){
			Bullet b = new Bullet(x+this.img.getWidth(null)/2, y,Direction.DOWN,pws,good,Images.imgs.get("bullet-3"));
			pws.bullets.add(b);
		}
	}
	/**
	 * ��ײ����õ��Ļ�ȡͼƬ���εķ���
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}
	/**
	 * �ɻ����ϰ���Ӵ��ķ���
	 */
	public boolean concatWithObstraction(Obstraction ob){
		//����ɻ����ϰ����ཻ����ص���һ��λ��
		if(live&&this.getRect().intersects(ob.getRect())){
//			this.dir = Direction.STOP;
			rollBack();//�ص���һ��λ�õķ���
			//��������Ƿɻ�����ᱻ�ϰ�������
			if(good){
				y+=ob.speed;
			}
			//������ϰ����Ƴ���Ļ������
			if(y>Constent.GAME_HEIGET-img.getHeight(null)){
				this.setLive(false);
				//��ը
				Explode e = new Explode(this.x, this.y,pws,1);
				pws.explodes.add(e);
				GameUtil.getAudio1("sounds/die.wav");
				//����������һ
				this.setLifeCount(this.getLifeCount()-1);
				if(this.getLifeCount()>0){
					Time t = new Time(pws);
					pws.times.add(t);
				}
			}
			return true;
		}else{
			return false;
		}
	}
	/**
	 * �ɻ������ϰ�����ײ�ķ���
	 * @param obs
	 * @return
	 */
	public boolean concatWithObstractions(List<Obstraction> obs){
		for(int i=0;i<obs.size();i++){
			Obstraction ob = obs.get(i);
			if(this.concatWithObstraction(ob)){
				return true;
			}
		}
		return false;
	}
	/**
	 * �ع�����һ��λ�õķ���
	 */
	private void rollBack(){
		this.x = preX;
		this.y = preY;
	}
	//ͨ��bb�������ڲ�������ʾѪ���ķ���
	public BloodBar bb = new BloodBar();
	/**
	 * �ڲ��࣬ͨ�����������α�ʾѪ��
	 */
	class BloodBar{
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.GREEN);
			//��Ѫ��С��30ʱ��Ϊ��ɫ
			if(life<30){
				g.setColor(Color.RED);
			}
			g.drawRect(x, y-10,img.getWidth(null) , 5);
			g.fillRect(x, y-10, img.getWidth(null)*life/100, 5);
			g.setColor(c);
		}
	}
	/**
	 * �����ķ���
	 */
	public void reBirth(){
		if(!live&&lifeCount>0){
			this.x = Constent.GAME_WIDTH/2;
			this.y = Constent.GAME_HEIGET;
			this.setLive(true);
			this.setLife(100);
			this.lv =1;
		}
	}
	/**
	 * �ɻ��Ե��ߵķ���
	 * @param item
	 * @return
	 */
	public boolean eatItem(Item item){
		if(item.isLive()&&good&&live&&this.getRect().intersects(item.getRect())){
			if(life<100&&item.lv==1){
				this.setLife(this.getLife()+20);
				item.setLive(false);//�Ե����Ƴ�����
				pws.items.remove(item);
				if(this.life>100){
					life=100;
				}
			}else if(item.lv==2){
				this.lv+=1;
				item.setLive(false);//�Ե����Ƴ�����
				pws.items.remove(item);
			}else if(item.lv==5){
				this.count+=1;
				item.setLive(false);
				pws.items.remove(item);
			}
			return true;
		}else{
			return false;
		}
	}
	/**
	 * �ɻ��Զ�����ߵķ���
	 */
	public boolean eatItems(List<Item> items){
		for(int i=0;i<items.size();i++){
			Item item = items.get(i);
			if(eatItem(item)){
				return true;
			}
		}
		return false;
	}
	/**
	 * ���Ƿɻ������з��ɻ��ķ���
	 */
	public boolean concatWithEnemyplane(Plane enemyplane){
		if(this.live&&enemyplane.isLive()&&this.getRect().intersects(enemyplane.getRect())&&this.good!=enemyplane.isGood()){
			pws.enemyplanes.remove(enemyplane);
			Explode e = new Explode(enemyplane.x, enemyplane.y,pws,1);
			pws.explodes.add(e);
			this.setLife(this.getLife()-50);
			if(this.getLife()<=0){
				this.setLive(false);
				this.setLifeCount(this.getLifeCount()-1);
				Explode e1 = new Explode(this.x, this.y,pws,1);
				pws.explodes.add(e1);
				GameUtil.getAudio1("sounds/die.wav");
				if(this.getLifeCount()>0){
					Time t = new Time(pws);
					pws.times.add(t);
				}
			}
			return true;
		}else{
			return false;
		}
	}
	/**
	 * ������������ɻ��ķ���
	 * @param enemyplanes
	 * @return
	 */
	public boolean concatWithEnemyplanes(List<Plane> enemyplanes){
		for(int i=0;i<enemyplanes.size();i++){
			Plane enemyplane = enemyplanes.get(i);
			if(concatWithEnemyplane(enemyplane)){
				return true;
			}
		}
		return false;
	}
	/**
	 * ��������boss�ķ���
	 */
	public boolean concatWithBoss(Boss boss){
		if(this.live&&boss.isLive()&&this.getRect().intersects(boss.getRect())&&this.good!=boss.isGood()){
				this.setLive(false);
				this.setLifeCount(this.getLifeCount()-1);
				Explode e = new Explode(this.x, this.y,pws,1);
				GameUtil.getAudio1("sounds/die.wav");
				pws.explodes.add(e);
				if(this.getLifeCount()>0){
					Time t = new Time(pws);
					pws.times.add(t);
				}
			return true;
		}else{
			return false;
		}
	}
	/**
	 * �����������boss�ķ���
	 */
	public boolean concatWithBosses(List<Boss> bosses){
		for(int i=0;i<bosses.size();i++){
			Boss boss = bosses.get(i);
			if(concatWithBoss(boss)){
				return true;
			}
		}
		return false;
	}
}