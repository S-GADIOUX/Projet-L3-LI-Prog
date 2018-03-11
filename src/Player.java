package com.sgadioux.style;
public class Player {
	
	String name;
	public int tileNumber;
	int mod;

	public Player(String name){
		this.name = name;
		this.tileNumber = 0;
		this.mod = 0;
	}

	public int applyMod(){
		int r = this.mod;
		this.mod = 0;
		return r;
	}

	public void moveTo(int t){
		this.tileNumber = t;
	}

	public String toString(){
		return this.name + " on tile "+this.tileNumber;
	}


}
