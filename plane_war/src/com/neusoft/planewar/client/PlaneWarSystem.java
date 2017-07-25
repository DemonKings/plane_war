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
 * �ͻ�����
 * 
 * @author κͨ
 *
 */
public class PlaneWarSystem extends MyFrame {
	//ը������
	public List<Zhadan> zhadans = new ArrayList<>();
	// ����ͼƬ
	backGround bg = new backGround();
	// �洢�з��ɻ�������
	public List<Plane> enemyplanes = new ArrayList<>();
	// �洢�ӵ�������
	public List<Bullet> bullets = new ArrayList<>();
	// ������ը������
	public List<Explode> explodes = new ArrayList<>();
	// �����ϰ��������
	public List<Obstraction> obs = new ArrayList<>();
	// ����boss������
	public List<Boss> bosses = new ArrayList<>();
	// �������ߵ�����
	public List<Item> items = new ArrayList<>();
	// ��ʼ����
	Image k = GameUtil.getImage(Constent.GAME_BASIC_IMG + "kaishi1.png");
	int kx = 0;// ��ʼ����������
	int ky = 0;
	// game over
	Over o = new Over();
	// game over������
	public List<Over> overs = new ArrayList<>();
	// ����ʱ������
	public List<Time> times = new ArrayList<>();
	// �����־
	public Warning warning = new Warning(this);
	// ����
	public Score score1 = new Score(340, 100, this, 1);
	public Score score2 = new Score(380, 100, this, 2);
	public Score score3 = new Score(420, 100, this, 3);
	public Score score4 = new Score(460, 100, this, 4);
	public int kilobit = 0;// ǧλ
	public int hundreds = 0;// ��λ
	/**
	 * �����з��ɻ��ӷֵķ���
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
	 * ����boss�ӷֵķ���
	 */
	public void bossScore() {
		if (kilobit == 9) {
			kilobit = 9;
			return;
		}
		kilobit++;
	}
	/**
	 * �����ϰ���ӷֵķ���
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
	// ���Ƿɻ�
	public Plane plane = new Plane(Constent.GAME_WIDTH / 2, Constent.GAME_HEIGET, true, this, 1);
	/**
	 * ��дlaunchFrame���� ��Ӽ��̼����� ���ش����Ǽ������з��ɻ����ϰ���
	 */
	@Override
	public void launchFrame() {
		super.launchFrame();
		// ��Ӽ��̼�������дKeyAdapeter
		this.addKeyListener(new KeyAdapter() {
			// ��дkeyPressed�����£�����
			@Override
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}

			// ��дkeyReleased���ɿ�������
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
		// forѭ����������ӽ�����y
		// ��һ�ֵз��ɻ�
		for (int i = 0; i < 5; i++) {
			Plane p = new Plane(0, i * 40, false, this, -1);
			enemyplanes.add(p);
		}
		// �ڶ��ֵз��ɻ�
		for (int i = 0; i < 5; i++) {
			Plane p = new Plane(472, i * 40, false, this, -2);
			enemyplanes.add(p);
		}
		// �����ַɻ�
		for (int i = 0; i < 6; i++) {
			Plane p = new Plane(i * 85, -500, false, this, -3);
			enemyplanes.add(p);
		}
		// ���ϰ�����ӽ�����
		obs.add(new Obstraction(0, -200,this));
		obs.add(new Obstraction(50, -400,this));
		// ��boss�ӽ�����
		Boss boss = new Boss(this);
		bosses.add(boss);
	}
	/**
	 * ��дpaint����
	 */
	@Override
	public void paint(Graphics g) {
		// ����ʼ����
		g.drawImage(k, kx, ky, null);
		ky -= 10;// ��ʼ����������
		// ����ʼ����y<-500ʱ����ʼ��
		if (ky < -500) {
			// ������
			bg.draw(g);
			// ѭ�����������ϰ���
			for (int i = 0; i < obs.size(); i++) {
				Obstraction ob = obs.get(i);
				ob.draw(g);
			}
			plane.draw(g);// �����Ƿɻ�
			plane.concatWithObstractions(obs);// ���Ƿɻ����ϰ���
			plane.eatItems(items);// ���Ƿɻ��Ե���
			plane.concatWithEnemyplanes(enemyplanes);// ���Ƿɻ����з��ɻ��ķ���
			plane.concatWithBosses(bosses);// ���Ƿɻ���boss�ķ���
			// ��warning����ʱ����boss
			if (!warning.isLive()) {
				for (int i = 0; i < bosses.size(); i++) {
					Boss b = bosses.get(i);
					b.draw(g);
				}
			}
			// ���ӵ�
			for (int i = 0; i < bullets.size(); i++) {
				// ȡ�������е��ӵ�
				Bullet b = bullets.get(i);
				b.draw(g);
				b.hitPlanes(enemyplanes);// ����з��ɻ���
				b.hitPlane(plane);// �������Ƿɻ�
				b.hitobstractions(obs);// �����ϰ���
				b.hitBosses(bosses);// ����boss
			}
			// ���з��ɻ�
			for (int i = 0; i < enemyplanes.size(); i++) {
				Plane enemyPlane = enemyplanes.get(i);
				// ����з��ɻ��ɳ��߽���������Ȼ���Ƴ�
				if (enemyPlane.y > Constent.GAME_HEIGET) {
					enemyPlane.setLive(false);
					enemyplanes.remove(enemyPlane);
				}
				enemyPlane.draw(g);
				enemyPlane.concatWithObstractions(obs);// ���ϰ�����ײ�ķ���
			}
			// ����ըЧ��
			for (int i = 0; i < explodes.size(); i++) {
				Explode e = explodes.get(i);
				e.draw(g);
			}
			// ����з��ɻ�ȫ����������warning
			if (enemyplanes.size() <= 0) {
				warning.draw(g);
			}
			// ����������
			for (int i = 0; i < plane.getLifeCount(); i++) {
				g.drawImage(Images.imgs.get("my_small"), 30 + i * 30, Constent.GAME_HEIGET - 50, null);
			}
			// ������
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				if (item.x > Constent.GAME_WIDTH || item.y > Constent.GAME_HEIGET
						|| item.x < -item.img.getWidth(null)) {
					item.setLive(false);
					items.remove(item);
				}
				item.draw(g);
			}
			// ������Ϊ0��ʱ���game over
			if (plane.getLifeCount() <= 0) {
				o.draw(g);

			}
			// ������ʱ�������game over
			for (int i = 0; i < overs.size(); i++) {
				Over o = overs.get(i);
				o.draw(g);
			}
			// ������ʱ
			for (int i = 0; i < times.size(); i++) {
				Time t = times.get(i);
				t.draw(g);
			}
			// ������
			score1.draw(g);// ǧλ
			score2.draw(g);// ��λ
			score3.draw(g);// ʮλ
			score4.draw(g);// ��λ
			//��ը��
			for(int i=0;i<zhadans.size();i++){
				Zhadan z = zhadans.get(i);
				z.draw(g);
				z.bigBang(300);
			}
			g.drawString("�ɻ�������"+enemyplanes.size(), 300, 300);
		}
	}

	// ������
	public static void main(String[] args) {
		new PlaneWarSystem().launchFrame();
	}
}
