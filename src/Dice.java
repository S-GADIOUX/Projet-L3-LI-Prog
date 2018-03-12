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
		if(b){
			if( r != 0)
				System.out.println("Let's move of " +r +" tile" +(r<2 ? "" : "s") +" !" );
			else 
				System.out.println("Bad news, you don't move !");
		}
		return r;
	}	
}
