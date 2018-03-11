package com.sgadioux.style;
public class BackpushLand implements LandStyle {

	private int value;

	public BackpushLand(int v){
		this.value = v;
	}

	public int land(Player p, Dice d){
		System.out.println("Let's go back of " + value + " tile" +(value<1?"s":"")+" !");
		return 0-value;
	}
}
