package com.sgadioux.style;
public class NormalLaunch implements LaunchStyle {

	public int launch(Dice d){
		return d.roll(true);
	}
}
