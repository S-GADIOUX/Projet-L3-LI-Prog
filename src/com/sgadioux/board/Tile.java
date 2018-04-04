package com.sgadioux.board;

import java.util.HashSet;
import com.sgadioux.style.*;

/**
 *
 * @author sebga
 */
public class Tile {
	
	private HashSet<Player> players;
	private LaunchStyle launcher;
	private LandStyle lander;
	private int number;

	/**
	 *
	 * @param lau
	 * @param lan
	 * @param nb
	 */
	public Tile(LaunchStyle lau, LandStyle lan, int nb){
		this.launcher = lau;
		this.lander = lan;
		this.players = new HashSet<Player>();
		this.number = nb;
	}

	/**
	 *
	 * @return
	 */
	public boolean isEmpty(){
		return this.players.isEmpty();
	}

	/**
	 *
	 * @param p
	 * @param d
	 * @return
	 */
	public int launch(Player p, Dice d){
		this.players.remove(p);
		return this.launcher.launch(d) + p.applyMod();
	}

	/**
	 *
	 * @param p
	 * @param d
	 * @return
	 */
	public int land(Player p, Dice d){
		p.moveTo(number);
		return this.lander.land(p,d);
	}

	/**
	 *
	 * @param p
	 */
	public void fall(Player p){
		p.moveTo(number);
		this.players.add(p);
	}

}
