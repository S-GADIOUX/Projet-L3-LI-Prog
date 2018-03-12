package com.sgadioux.style;
public class NormalLaunch implements LaunchStyle {

	public int launch(Dice d){
		System.out.println("Let's roll the dice.");
		return d.roll(true);
	}
}
