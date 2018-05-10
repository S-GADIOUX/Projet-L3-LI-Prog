package com.sgadioux.board;

import java.util.HashSet;
import com.sgadioux.style.*;

/**
 * Un objet Tile représente un case du plateau de jeu.
 * Son fonctionnement lui est donné par des interfaces lors de la création de l'objet.
 * @author sebga
 */
public class Tile {
	
	/**
	 * La liste de tous les joueurs présents sur la case.
	 */
	private HashSet<Player> players;
	
	/**
	 * La façon dont le joueur quitte la case.
	 */
	private final LaunchStyle launcher;
	
	/**
	 * L'effet de la case lorsqu'un joueur arrive dessus.
	 */
	private final LandStyle lander;
	
	/**
	 * Lenuméro de la case.
	 */
	private final int number;

	/**
	 * Crée une instance de case dont le comportement est régie par les interfaces passées en argument.
	 * 
	 * @param lau
	 *	Ce qui ce passe quand un joueur quitte la case
	 * @param lan
	 *  Ce qui ce passe quand un joueur arrive sur la case
	 * @param nb
	 *  Le numéro de la case
	 */
	public Tile(LaunchStyle lau, LandStyle lan, int nb){
		this.launcher = lau;
		this.lander = lan;
		this.players = new HashSet<Player>();
		this.number = nb;
	}

	/**
	 * Cette méthode est utilisé pour savoir si la case est vide ou non. 
	 * @return True si la case est vide, False sinon.
	 */
	public boolean isEmpty(){
		return this.players.isEmpty();
	}

	/**
	 * Ordonne à la case de lancer le joueur.
	 * @param p
	 *	Le joueur qui doit se déplacer.
	 * @param d
	 *	Le dé qu'il doit utiliser pour quitter la case.
	 * @return Le nombre de case dont le joueur est déplacé.
	 */
	public int launch(Player p, Dice d){
		this.players.remove(p);
		return this.launcher.launch(d);
	}

	/**
	 * Ordonne à la case de recevoir le joueur et d'y appliquer son effet.
	 * @param p
	 *	Le joueur qui va être reçu sur la case.
	 * @param d
	 *	Le dé devant être utilisé dans le cas d'un comportement l'impliquant.
	 * @return Le nombre de case dont est déplacé le joueur.
	 */
	public int land(Player p, Dice d){
		p.moveTo(number);
		return this.lander.land(p,d);
	}

	/**
	 * Permet à un joueur d'atterir sur une case sans en subir les effets.
	 * @param p
	 *	Le joueur qui atterit sur la case
	 */
	public void fall(Player p){
		p.moveTo(number);
		this.players.add(p);
	}

}
