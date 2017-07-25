package com.neusoft.planewar.core;

import java.awt.Graphics;

public class Over extends PlaneObject{
	public Over() {
		this.x = 0;
		this.y = -768;
		this.img = Images.imgs.get("gameover");
	}
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		move();
	}
	
	@Override
	public void move() {
		if(y<0){
			y+=50;
			if(y>768){
				y=768;
			}
		}
	}
}
