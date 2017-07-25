package com.neusoft.planewar.core;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import com.neusoft.planewar.constent.Constent;
import com.neusoft.planewar.util.GameUtil;
/**
 * 图片类
 * @author 魏通
 *
 */
public class Images {
	//将图片的路径写到数字中
	public static String[] imgsPath = {
		Constent.GAME_BASIC_IMG+"my/myplane.png",//主角飞机
		Constent.GAME_BASIC_IMG+"my/mybullet.png",//主角子弹
		Constent.GAME_BASIC_IMG+"enemy/enemyplane.png",//敌方飞机
		//爆炸图片
		Constent.GAME_BASIC_IMG+"boom/boom1.png",
		Constent.GAME_BASIC_IMG+"boom/boom2.png",
		Constent.GAME_BASIC_IMG+"boom/boom3.png",
		Constent.GAME_BASIC_IMG+"boom/boom4.png",
		Constent.GAME_BASIC_IMG+"boom/boom5.png",
		Constent.GAME_BASIC_IMG+"boom/boom6.png",
		Constent.GAME_BASIC_IMG+"boom/boom7.png",
		Constent.GAME_BASIC_IMG+"boom/boom8.png",
		Constent.GAME_BASIC_IMG+"boom/boom9.png",
		Constent.GAME_BASIC_IMG+"boom/boom10.png",
		//背景图片
		Constent.GAME_BASIC_IMG+"background.jpg",
		Constent.GAME_BASIC_IMG+"enemy/enemybullet-3.png",//敌方向下子弹
		Constent.GAME_BASIC_IMG+"boss/bossbullet.png",//boss子弹
		Constent.GAME_BASIC_IMG+"ob1.png",//障碍物
		//警告图片
		Constent.GAME_BASIC_IMG+"warning/w1.png",
		Constent.GAME_BASIC_IMG+"warning/w2.png",
		Constent.GAME_BASIC_IMG+"warning/w3.png",
		Constent.GAME_BASIC_IMG+"warning/w4.png",
		Constent.GAME_BASIC_IMG+"warning/w5.png",
		Constent.GAME_BASIC_IMG+"warning/w6.png",
		Constent.GAME_BASIC_IMG+"warning/w7.png",
		Constent.GAME_BASIC_IMG+"warning/w8.png",
		Constent.GAME_BASIC_IMG+"warning/w9.png",
		Constent.GAME_BASIC_IMG+"warning/w10.png",
		Constent.GAME_BASIC_IMG+"boss/boss.png",//boss图片
		Constent.GAME_BASIC_IMG+"boss/boss_head.png",//boss头像
		//boss组图
		Constent.GAME_BASIC_IMG+"boss/b1.png",
		Constent.GAME_BASIC_IMG+"boss/b2.png",
		Constent.GAME_BASIC_IMG+"boss/b3.png",
		Constent.GAME_BASIC_IMG+"boss/b4.png",
		Constent.GAME_BASIC_IMG+"boss/b5.png",
		Constent.GAME_BASIC_IMG+"boss/b6.png",
		Constent.GAME_BASIC_IMG+"boss/b7.png",
		Constent.GAME_BASIC_IMG+"boss/b8.png",
		Constent.GAME_BASIC_IMG+"boss/b9.png",
		//生命图片
		Constent.GAME_BASIC_IMG+"my/my_small.png",
		//道具图片
		Constent.GAME_BASIC_IMG+"item/item1.png",
		Constent.GAME_BASIC_IMG+"enemy/enemybullet-1.png",//敌方向右下子弹
		Constent.GAME_BASIC_IMG+"enemy/enemybullet-2.png",//敌方向左下子弹
		Constent.GAME_BASIC_IMG+"gameover.png",//game over
		//第二种爆炸
		Constent.GAME_BASIC_IMG+"boom/ex1.png",
		Constent.GAME_BASIC_IMG+"boom/ex2.png",
		Constent.GAME_BASIC_IMG+"boom/ex3.png",
		Constent.GAME_BASIC_IMG+"boom/ex4.png",
		Constent.GAME_BASIC_IMG+"boom/ex5.png",
		Constent.GAME_BASIC_IMG+"boom/ex6.png",
		Constent.GAME_BASIC_IMG+"boom/ex7.png",
		Constent.GAME_BASIC_IMG+"boom/ex8.png",
		Constent.GAME_BASIC_IMG+"boom/ex9.png",
		Constent.GAME_BASIC_IMG+"boom/ex10.png",
		Constent.GAME_BASIC_IMG+"boom/ex11.png",
		Constent.GAME_BASIC_IMG+"boom/ex12.png",
		//道具
		Constent.GAME_BASIC_IMG+"item/item2.png",
		Constent.GAME_BASIC_IMG+"item/item3.png",
		Constent.GAME_BASIC_IMG+"item/item4.png",
		//倒计时图片
		Constent.GAME_BASIC_IMG+"time/0.png",
		Constent.GAME_BASIC_IMG+"time/1.png",
		Constent.GAME_BASIC_IMG+"time/2.png",
		Constent.GAME_BASIC_IMG+"time/3.png",
		Constent.GAME_BASIC_IMG+"time/4.png",
		Constent.GAME_BASIC_IMG+"time/5.png",
		Constent.GAME_BASIC_IMG+"time/6.png",
		Constent.GAME_BASIC_IMG+"time/7.png",
		Constent.GAME_BASIC_IMG+"time/8.png",
		Constent.GAME_BASIC_IMG+"time/9.png",
		//分数图片
		Constent.GAME_BASIC_IMG+"score/0.png",
		Constent.GAME_BASIC_IMG+"score/1.png",
		Constent.GAME_BASIC_IMG+"score/2.png",
		Constent.GAME_BASIC_IMG+"score/3.png",
		Constent.GAME_BASIC_IMG+"score/4.png",
		Constent.GAME_BASIC_IMG+"score/5.png",
		Constent.GAME_BASIC_IMG+"score/6.png",
		Constent.GAME_BASIC_IMG+"score/7.png",
		Constent.GAME_BASIC_IMG+"score/8.png",
		Constent.GAME_BASIC_IMG+"score/9.png",
		//炸弹
		Constent.GAME_BASIC_IMG+"zhadan.png",
		//第三种爆炸
		Constent.GAME_BASIC_IMG+"boom/ex1-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex2-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex3-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex4-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex5-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex6-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex7-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex8-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex9-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex10-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex11-1.png",
		Constent.GAME_BASIC_IMG+"boom/ex12-1.png",
	};
	//使用MAP构造图片对象
	public static Map<String, Image> imgs = new HashMap<>();
	//静态初始化
	static {
		imgs.put("myplane", GameUtil.getImage(imgsPath[0]));
		imgs.put("bullet1", GameUtil.getImage(imgsPath[1]));
		imgs.put("enemyplane1", GameUtil.getImage(imgsPath[2]));
		imgs.put("e1", GameUtil.getImage(imgsPath[3]));
		imgs.put("e2", GameUtil.getImage(imgsPath[4]));
		imgs.put("e3", GameUtil.getImage(imgsPath[5]));
		imgs.put("e4", GameUtil.getImage(imgsPath[6]));
		imgs.put("e5", GameUtil.getImage(imgsPath[7]));
		imgs.put("e6", GameUtil.getImage(imgsPath[8]));
		imgs.put("e7", GameUtil.getImage(imgsPath[9]));
		imgs.put("e8", GameUtil.getImage(imgsPath[10]));
		imgs.put("e9", GameUtil.getImage(imgsPath[11]));
		imgs.put("e10", GameUtil.getImage(imgsPath[12]));
		imgs.put("bg", GameUtil.getImage(imgsPath[13]));
		imgs.put("bullet-3", GameUtil.getImage(imgsPath[14]));
		imgs.put("bossbullet", GameUtil.getImage(imgsPath[15]));
		imgs.put("ob", GameUtil.getImage(imgsPath[16]));
		imgs.put("w1", GameUtil.getImage(imgsPath[17]));
		imgs.put("w2", GameUtil.getImage(imgsPath[18]));
		imgs.put("w3", GameUtil.getImage(imgsPath[19]));
		imgs.put("w4", GameUtil.getImage(imgsPath[20]));
		imgs.put("w5", GameUtil.getImage(imgsPath[21]));
		imgs.put("w6", GameUtil.getImage(imgsPath[22]));
		imgs.put("w7", GameUtil.getImage(imgsPath[23]));
		imgs.put("w8", GameUtil.getImage(imgsPath[24]));
		imgs.put("w9", GameUtil.getImage(imgsPath[25]));
		imgs.put("w10", GameUtil.getImage(imgsPath[26]));
		imgs.put("boss", GameUtil.getImage(imgsPath[27]));
		imgs.put("boss_head", GameUtil.getImage(imgsPath[28]));
		imgs.put("b1", GameUtil.getImage(imgsPath[29]));
		imgs.put("b2", GameUtil.getImage(imgsPath[30]));
		imgs.put("b3", GameUtil.getImage(imgsPath[31]));
		imgs.put("b4", GameUtil.getImage(imgsPath[32]));
		imgs.put("b5", GameUtil.getImage(imgsPath[33]));
		imgs.put("b6", GameUtil.getImage(imgsPath[34]));
		imgs.put("b7", GameUtil.getImage(imgsPath[35]));
		imgs.put("b8", GameUtil.getImage(imgsPath[36]));
		imgs.put("b9", GameUtil.getImage(imgsPath[37]));
		imgs.put("my_small", GameUtil.getImage(imgsPath[38]));
		imgs.put("item1", GameUtil.getImage(imgsPath[39]));
		imgs.put("bullet-1", GameUtil.getImage(imgsPath[40]));
		imgs.put("bullet-2", GameUtil.getImage(imgsPath[41]));
		imgs.put("gameover", GameUtil.getImage(imgsPath[42]));
		imgs.put("ex1", GameUtil.getImage(imgsPath[43]));
		imgs.put("ex2", GameUtil.getImage(imgsPath[44]));
		imgs.put("ex3", GameUtil.getImage(imgsPath[45]));
		imgs.put("ex4", GameUtil.getImage(imgsPath[46]));
		imgs.put("ex5", GameUtil.getImage(imgsPath[47]));
		imgs.put("ex6", GameUtil.getImage(imgsPath[48]));
		imgs.put("ex7", GameUtil.getImage(imgsPath[49]));
		imgs.put("ex8", GameUtil.getImage(imgsPath[50]));
		imgs.put("ex9", GameUtil.getImage(imgsPath[51]));
		imgs.put("ex10", GameUtil.getImage(imgsPath[52]));
		imgs.put("ex11", GameUtil.getImage(imgsPath[53]));
		imgs.put("ex12", GameUtil.getImage(imgsPath[54]));
		imgs.put("item2", GameUtil.getImage(imgsPath[55]));
		imgs.put("item3", GameUtil.getImage(imgsPath[56]));
		imgs.put("item4", GameUtil.getImage(imgsPath[57]));
		imgs.put("time0", GameUtil.getImage(imgsPath[58]));
		imgs.put("time1", GameUtil.getImage(imgsPath[59]));
		imgs.put("time2", GameUtil.getImage(imgsPath[60]));
		imgs.put("time3", GameUtil.getImage(imgsPath[61]));
		imgs.put("time4", GameUtil.getImage(imgsPath[62]));
		imgs.put("time5", GameUtil.getImage(imgsPath[63]));
		imgs.put("time6", GameUtil.getImage(imgsPath[64]));
		imgs.put("time7", GameUtil.getImage(imgsPath[65]));
		imgs.put("time8", GameUtil.getImage(imgsPath[66]));
		imgs.put("time9", GameUtil.getImage(imgsPath[67]));
		imgs.put("score0", GameUtil.getImage(imgsPath[68]));
		imgs.put("score1", GameUtil.getImage(imgsPath[69]));
		imgs.put("score2", GameUtil.getImage(imgsPath[70]));
		imgs.put("score3", GameUtil.getImage(imgsPath[71]));
		imgs.put("score4", GameUtil.getImage(imgsPath[72]));
		imgs.put("score5", GameUtil.getImage(imgsPath[73]));
		imgs.put("score6", GameUtil.getImage(imgsPath[74]));
		imgs.put("score7", GameUtil.getImage(imgsPath[75]));
		imgs.put("score8", GameUtil.getImage(imgsPath[76]));
		imgs.put("score9", GameUtil.getImage(imgsPath[77]));
		imgs.put("zhadan", GameUtil.getImage(imgsPath[78]));
		imgs.put("ex1-1", GameUtil.getImage(imgsPath[79]));
		imgs.put("ex2-1", GameUtil.getImage(imgsPath[80]));
		imgs.put("ex3-1", GameUtil.getImage(imgsPath[81]));
		imgs.put("ex4-1", GameUtil.getImage(imgsPath[82]));
		imgs.put("ex5-1", GameUtil.getImage(imgsPath[83]));
		imgs.put("ex6-1", GameUtil.getImage(imgsPath[84]));
		imgs.put("ex7-1", GameUtil.getImage(imgsPath[85]));
		imgs.put("ex8-1", GameUtil.getImage(imgsPath[86]));
		imgs.put("ex9-1", GameUtil.getImage(imgsPath[87]));
		imgs.put("ex10-1", GameUtil.getImage(imgsPath[88]));
		imgs.put("ex11-1", GameUtil.getImage(imgsPath[89]));
		imgs.put("ex12-1", GameUtil.getImage(imgsPath[90]));
		
	}
}
