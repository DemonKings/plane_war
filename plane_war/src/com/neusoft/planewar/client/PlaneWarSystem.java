package com.neusoft.planewar.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import com.neusoft.planewar.constent.Constent;
import com.neusoft.planewar.core.Boss;
import com.neusoft.planewar.core.Bullet;
import com.neusoft.planewar.core.Explode;
import com.neusoft.planewar.core.Images;
import com.neusoft.planewar.core.Item;
import com.neusoft.planewar.core.Obstraction;
import com.neusoft.planewar.core.Over;
import com.neusoft.planewar.core.Plane;
import com.neusoft.planewar.core.Score;
import com.neusoft.planewar.core.Time;
import com.neusoft.planewar.core.Warning;
import com.neusoft.planewar.core.Zhadan;
import com.neusoft.planewar.core.backGround;
import com.neusoft.planewar.util.GameUtil;
import com.neusoft.planewar.util.MyFrame;

/**
 * 客户端类
 * 
 * @author 魏通
 *
 */
public class PlaneWarSystem extends MyFrame {
	//炸弹容器
	public List<Zhadan> zhadans = new ArrayList<>();
	// 背景图片
	backGround bg = new backGround();
	// 存储敌方飞机的容器
	public List<Plane> enemyplanes = new ArrayList<>();
	// 存储子弹的容器
	public List<Bullet> bullets = new ArrayList<>();
	// 创建爆炸的容器
	public List<Explode> explodes = new ArrayList<>();
	// 创建障碍物的容器
	public List<Obstraction> obs = new ArrayList<>();
	// 创建boss的容器
	public List<Boss> bosses = new ArrayList<>();
	// 创建道具的容器
	public List<Item> items = new ArrayList<>();
	// 开始动画
	Image k = GameUtil.getImage(Constent.GAME_BASIC_IMG + "kaishi1.png");
	int kx = 0;// 开始动画的坐标
	int ky = 0;
	// game over
	Over o = new Over();
	// game over的容器
	public List<Over> overs = new ArrayList<>();
	// 倒计时的容器
	public List<Time> times = new ArrayList<>();
	// 警告标志
	public Warning warning = new Warning(this);
	// 分数
	public Score score1 = new Score(340, 100, this, 1);
	public Score score2 = new Score(380, 100, this, 2);
	public Score score3 = new Score(420, 100, this, 3);
	public Score score4 = new Score(460, 100, this, 4);
	public int kilobit = 0;// 千位
	public int hundreds = 0;// 百位
	/**
	 * 打死敌方飞机加分的方法
	 */
	public void enemyScore() {
		if (kilobit <= 9) {
			if (kilobit == 9 && hundreds == 9) {
				kilobit = 9;
				hundreds = 9;
				return;
			}
			hundreds++;
			if (hundreds > 9) {
				hundreds = 0;
				kilobit++;
			}
		}
	}

	/**
	 * 打死boss加分的方法
	 */
	public void bossScore() {
		if (kilobit == 9) {
			kilobit = 9;
			return;
		}
		kilobit++;
	}
	/**
	 * 打死障碍物加分的方法
	 */
	public void obScore() {
		if (kilobit <= 9) {
			if (kilobit == 9 && hundreds == 9) {
				kilobit = 9;
				hundreds = 9;
				return;
			}
			hundreds+=2;
			if (hundreds > 9) {
				hundreds = 0;
				kilobit++;
			}
		}
	}
	// 主角飞机
	public Plane plane = new Plane(Constent.GAME_WIDTH / 2, Constent.GAME_HEIGET, true, this, 1);
	/**
	 * 重写launchFrame方法 添加键盘监听器 加载窗口是即创建敌方飞机和障碍物
	 */
	@Override
	public void launchFrame() {
		super.launchFrame();
		// 添加键盘监听并重写KeyAdapeter
		this.addKeyListener(new KeyAdapter() {
			// 重写keyPressed（按下）方法
			@Override
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}

			// 重写keyReleased（松开）方法
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
		// for循环将对象添加进容器y
		// 第一种敌方飞机
		for (int i = 0; i < 5; i++) {
			Plane p = new Plane(0, i * 40, false, this, -1);
			enemyplanes.add(p);
		}
		// 第二种敌方飞机
		for (int i = 0; i < 5; i++) {
			Plane p = new Plane(472, i * 40, false, this, -2);
			enemyplanes.add(p);
		}
		// 第三种飞机
		for (int i = 0; i < 6; i++) {
			Plane p = new Plane(i * 85, -500, false, this, -3);
			enemyplanes.add(p);
		}
		// 将障碍物添加进容器
		obs.add(new Obstraction(0, -200,this));
		obs.add(new Obstraction(50, -400,this));
		// 将boss加进容器
		Boss boss = new Boss(this);
		bosses.add(boss);
	}
	/**
	 * 重写paint方法
	 */
	@Override
	public void paint(Graphics g) {
		// 画开始动画
		g.drawImage(k, kx, ky, null);
		ky -= 10;// 开始动画的移速
		// 当开始动画y<-500时，开始画
		if (ky < -500) {
			// 画背景
			bg.draw(g);
			// 循环遍历画出障碍物
			for (int i = 0; i < obs.size(); i++) {
				Obstraction ob = obs.get(i);
				ob.draw(g);
			}
			plane.draw(g);// 画主角飞机
			plane.concatWithObstractions(obs);// 主角飞机碰障碍物
			plane.eatItems(items);// 主角飞机吃道具
			plane.concatWithEnemyplanes(enemyplanes);// 主角飞机碰敌方飞机的方法
			plane.concatWithBosses(bosses);// 主角飞机碰boss的方法
			// 当warning死亡时，画boss
			if (!warning.isLive()) {
				for (int i = 0; i < bosses.size(); i++) {
					Boss b = bosses.get(i);
					b.draw(g);
				}
			}
			// 画子弹
			for (int i = 0; i < bullets.size(); i++) {
				// 取出容器中的子弹
				Bullet b = bullets.get(i);
				b.draw(g);
				b.hitPlanes(enemyplanes);// 击打敌方飞机的
				b.hitPlane(plane);// 击打主角飞机
				b.hitobstractions(obs);// 击打障碍物
				b.hitBosses(bosses);// 击打boss
			}
			// 画敌方飞机
			for (int i = 0; i < enemyplanes.size(); i++) {
				Plane enemyPlane = enemyplanes.get(i);
				// 如果敌方飞机飞出边界则死亡，然后移除
				if (enemyPlane.y > Constent.GAME_HEIGET) {
					enemyPlane.setLive(false);
					enemyplanes.remove(enemyPlane);
				}
				enemyPlane.draw(g);
				enemyPlane.concatWithObstractions(obs);// 与障碍物碰撞的方法
			}
			// 画爆炸效果
			for (int i = 0; i < explodes.size(); i++) {
				Explode e = explodes.get(i);
				e.draw(g);
			}
			// 如果敌方飞机全部死亡，则画warning
			if (enemyplanes.size() <= 0) {
				warning.draw(g);
			}
			// 画生命数量
			for (int i = 0; i < plane.getLifeCount(); i++) {
				g.drawImage(Images.imgs.get("my_small"), 30 + i * 30, Constent.GAME_HEIGET - 50, null);
			}
			// 画道具
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				if (item.x > Constent.GAME_WIDTH || item.y > Constent.GAME_HEIGET
						|| item.x < -item.img.getWidth(null)) {
					item.setLive(false);
					items.remove(item);
				}
				item.draw(g);
			}
			// 画生命为0的时候的game over
			if (plane.getLifeCount() <= 0) {
				o.draw(g);

			}
			// 画倒计时结束后的game over
			for (int i = 0; i < overs.size(); i++) {
				Over o = overs.get(i);
				o.draw(g);
			}
			// 画倒计时
			for (int i = 0; i < times.size(); i++) {
				Time t = times.get(i);
				t.draw(g);
			}
			// 画分数
			score1.draw(g);// 千位
			score2.draw(g);// 百位
			score3.draw(g);// 十位
			score4.draw(g);// 个位
			//画炸弹
			for(int i=0;i<zhadans.size();i++){
				Zhadan z = zhadans.get(i);
				z.draw(g);
				z.bigBang(300);
			}
			g.drawString("飞机容器："+enemyplanes.size(), 300, 300);
		}
	}

	// 主函数
	public static void main(String[] args) {
		new PlaneWarSystem().launchFrame();
	}
}
