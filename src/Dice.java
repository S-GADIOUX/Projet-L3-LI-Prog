package com.sgadioux.style;
public class Dice {
	
	private int side;
	private boolean tricked;

	public Dice(int s, boolean t){
		this.side = s;
		this.tricked = t;
	}

	public int roll(boolean b){
		int r = (int)(Math.random()*side + (tricked ? 0 : 1));
		return r;
	}	
}
