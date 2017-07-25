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
 * 飞机类继承父类
 * @author 魏通
 *
 */
public class Plane extends PlaneObject{
	public boolean re;
	public int speed = 10;//主角飞机的速度
	public boolean up,down,left,right;//四个移动方向
	private boolean good;//是否为好飞机，默认为false
	public Direction dir = Direction.STOP;//枚举类型
	public PlaneWarSystem pws;//PlaneWarSystem类的pws 
	private boolean live = true;//判断是否活着的变量
	public static Random r = new Random();//随机数
	public int lv;//通过lv控制不同飞机轨迹
	public int preX;//飞机的上一个位置
	public int preY;
	private int life;//飞机初始生命
	private int lifeCount;//飞机生命数量
	public int l1;// 用来获取扔炸弹时飞机y坐标
	public int count = 1;//炸弹的数量
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
	 * 构造方法
	 * @param x	x坐标
	 * @param y	y坐标
	 * @param imgpath	图片路径
	 */
	public Plane(){
		
	}
//	public Plane(int x, int y, String imgpath) {
//		this.x = x;
//		this.y = y;
//		//调用GameUtil类的方法加载图片
//		this.img = GameUtil.getImage(imgpath);
//	}
//	//构造方法，加入good参数
//	public Plane(int x, int y,boolean good) {
//		this.good = good;
//		if(good){
//			this.img = Images.imgs.get("myplane");
//		}else{
//			this.img = Images.imgs.get("enemyplane1");
//		}
//		//判断是否为好飞机，是则出现在x中心
//		if(good){
//			this.x = x-img.getWidth(null)/2;
//		}else{
//			this.x = x;
//		}		
//		this.y = y;
//	}
	//构造方法，将pws传入（主角飞机）
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
		//通过good判断敌我飞机的图片
		if(good){
			this.img = Images.imgs.get("myplane");
			lifeCount = 3;
		}else{
			this.img = Images.imgs.get("enemyplane1");
		}
		//如果是主角飞机，则x减去自身一半，即在x正中央
		if(good){
			this.x = x-img.getWidth(null)/2;
		}else{
			this.x = x;
		}		
		this.y = y;
		//通过lv控制飞机方向
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
	//	//构造方法，加入dir参数（敌人飞机）
//	public Plane(int x, int y,boolean good,Direction dir) {
//		//导入之前构造函数中的相同内容
//		this.x = x;
//		this.y = y;
//		this.good = good;
//		this.dir = dir;
//		this.img = Images.imgs.get("enemyplane1");
//	}
	/**
	 * 重写draw方法
	 */
	@Override
	public void draw(Graphics g) {
		//如果敌方飞机死亡
		if(!good&&!live){
			//生成爆炸
			Explode e = new Explode(this.x, this.y,pws,1);
			pws.explodes.add(e);
			//生成道具
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
			pws.enemyplanes.remove(this);//移除敌方飞机
			pws.enemyScore();//计分
			return;
		}else if(!live){
			return;
		}
		g.drawImage(img, x, y, null);
		//判断是否为好飞机，不是则调整速度
		if(!good){
			speed = 5;
		}
		//不同飞机调用不同移动方法
		if(good){
			bb.draw(g);//画血条
			move();
		}else{
			enemyMove();
		}
	}
	/**
	 * 键盘按下的方法
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		//得到按键的值
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
			fire();//如果活着，发子弹
			break;
		case KeyEvent.VK_O:
			reBirth();//重生
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
	 * 键盘松开的方法
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
	 * 主角飞机移动的方法
	 */
	@Override
	public void move() {
		//记录上一步的位置
		this.preX = x;
		this.preY = y;
		//移动
		if(up) y-=speed;
		if(down) y+=speed;
		if(left) x-=speed;
		if(right) x+=speed;
		//判断是否出边界
		outofBound();
	}
	//敌方飞机的移动方法
	public void enemyMove(){
		//记录上一步的位置
		this.preX = x;
		this.preY = y;
		//移动
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
		//用随机数控制敌方飞机发子弹的频率
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
	//判断飞机是否出边界
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
	 * 确定方向的方法
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
	 * 飞机发子弹的方法
	 * 不同飞机发不同子弹
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
			//将子弹添加到容器
			pws.bullets.add(b1);
			pws.bullets.add(b2);
			pws.bullets.add(b3);
			pws.bullets.add(b4);
			pws.bullets.add(b5);
			
		}
	}
	/**
	 * 敌方飞机的发子弹方法
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
	 * 碰撞检测用到的获取图片矩形的方法
	 * @return
	 */
	public Rectangle getRect(){
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}
	/**
	 * 飞机与障碍物接触的方法
	 */
	public boolean concatWithObstraction(Obstraction ob){
		//如果飞机与障碍物相交，则回到上一个位置
		if(live&&this.getRect().intersects(ob.getRect())){
//			this.dir = Direction.STOP;
			rollBack();//回到上一个位置的方法
			//如果是主角飞机，则会被障碍物推走
			if(good){
				y+=ob.speed;
			}
			//如果被障碍物推出屏幕则死亡
			if(y>Constent.GAME_HEIGET-img.getHeight(null)){
				this.setLive(false);
				//爆炸
				Explode e = new Explode(this.x, this.y,pws,1);
				pws.explodes.add(e);
				GameUtil.getAudio1("sounds/die.wav");
				//生命数量减一
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
	 * 飞机与多个障碍物碰撞的方法
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
	 * 回滚到上一个位置的方法
	 */
	private void rollBack(){
		this.x = preX;
		this.y = preY;
	}
	//通过bb来调用内部类中显示血条的方法
	public BloodBar bb = new BloodBar();
	/**
	 * 内部类，通过画两个矩形表示血条
	 */
	class BloodBar{
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.GREEN);
			//当血量小于30时变为红色
			if(life<30){
				g.setColor(Color.RED);
			}
			g.drawRect(x, y-10,img.getWidth(null) , 5);
			g.fillRect(x, y-10, img.getWidth(null)*life/100, 5);
			g.setColor(c);
		}
	}
	/**
	 * 重生的方法
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
	 * 飞机吃道具的方法
	 * @param item
	 * @return
	 */
	public boolean eatItem(Item item){
		if(item.isLive()&&good&&live&&this.getRect().intersects(item.getRect())){
			if(life<100&&item.lv==1){
				this.setLife(this.getLife()+20);
				item.setLive(false);//吃掉后移除道具
				pws.items.remove(item);
				if(this.life>100){
					life=100;
				}
			}else if(item.lv==2){
				this.lv+=1;
				item.setLive(false);//吃掉后移除道具
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
	 * 飞机吃多个道具的方法
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
	 * 主角飞机碰到敌方飞机的方法
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
	 * 主角碰到多个飞机的方法
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
	 * 主角碰到boss的方法
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
	 * 主角碰到多个boss的方法
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