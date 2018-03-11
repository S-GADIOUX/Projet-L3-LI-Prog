package com.sgadioux.style;
public class RelaunchLand implements LandStyle{

	public int land(Player p, Dice d){
		System.out.println("Let's reroll the dice");
		return d.roll(true);
	}
}
