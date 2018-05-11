package com.sgadioux.board;

import com.sgadioux.word.WordVector;
import com.sgadioux.style.*;
import java.util.*;

/**
 *
 * @author sebga
 */
public class Game{
	
	/**
	 * The list of players
	 */
	public Player[] players;

	/**
	 * The representation of the board
	 */
	public Tile[] board;

	/**
	 * The length of the board
	 */
	public int bLength;

	/**
	 * The Dice
	 */
	public Dice dice;

	/**
	 * The number of try
	 */
	public int tryNb;

	/**
	 * The number of clue
	 */
	public int clueNb;

	/**
	 * The number of words kept
	 */
	public int nearestNb;

	/**
	 * The data utility class
	 */
	public WordVector datas;

	/**
	 *Crée un nouveau jeu avec les paramètres passés en arguments.
	 * 
	 * @param playersName
	 *	La liste des nom des joueurs.
	 * @param boardLength
	 *	La taille du plateau.
	 * @param d
	 *	Le de utilisé pour jouer.
	 * @param tNb
	 *	Le nombre d'essai.
	 * @param cNb
	 *	Le nombre d'indice à donner.
	 * @param nNb
	 *	Le nombre de mots listés comme proche.
	 * @param pOfRelaunch
	 *	Le pourcentage de case avec relance, sous la forme d'un double entre 0 et 1.
	 * @param pOfBackpush
	 *	Le pourcentage de case avec recul, sous la forme d'un double entre 0 et 1.
	 * @param data
	 *	L'utilitaire de données.
	 */
	public Game(String[] playersName, int boardLength, Dice d, int tNb, int cNb, int nNb, double pOfRelaunch, double pOfBackpush, WordVector data){
		this.players= new Player[playersName.length];
		this.bLength = boardLength;
		this.dice = d;
		this.tryNb = tNb;
		this.clueNb = cNb;
		this.nearestNb = nNb;
		this.datas = data;
		
		//Création des joueurs.
		for(int i=0; i < playersName.length; i ++){
			this.players[i] = new Player(playersName[i]);
		}
		
		// Créatio du plateau de jeu.
		this.board = new Tile[boardLength];
		board[0] = new Tile(new NormalLaunch(), new NormalLand(), 0);
		for(int i = 1; i < bLength-1 ; i++){
			LandStyle lan = new NormalLand();
			double rand = Math.random();
			if (rand < pOfRelaunch){
				lan = new RelaunchLand();
			}else if(rand < pOfRelaunch + pOfBackpush){
				lan = new BackpushLand(3);
			}
			board[i] = new Tile(new NormalLaunch(), lan, i);
		}
		board[bLength-1] = new Tile(new NormalLaunch(), new NormalLand(), bLength-1);
		
	
	}

	/**
	 *
	 */
	public void play(){
		Tile end = this.board[this.board.length-1];
		int mv, effect, i;
		boolean b;
		while(end.isEmpty()){
			for(Player p : this.players ){
				b = true;
				System.out.println("It's turn of "+p+".");
				while(b){
					mv = this.board[p.tileNumber].launch(p,dice);
					effect = this.board[Math.min(bLength-1, Math.max(p.tileNumber + mv, 0))].land(p,dice);
					this.board[Math.min(this.board.length-1,Math.max(p.tileNumber + effect, 0))].fall(p);
					if (p.tileNumber == bLength-1){
						System.out.println("Incredible "+p+", you finished the game, let's see if someone esle can do it in the same turn.");
						break;
					}
					i = 0;
					b = false;
					String tg = datas.getRandomWord();
					while(i< this.tryNb && !b){
						i++;
						System.out.println("Try " + i + "/"+tryNb+ " : ");
						b = this.guess(tg);
					}
					if(b)
						System.out.println("Well played, let's continue "+p);
					else
						System.out.println(""+p+" played.");
				
				}
			}
			System.out.println("A turn is done.\n");
		}
	
	}

	/**
	 * Fait deviner un mot à un joueur.
	 * Cette fonction demander au joueur d'entrer les mots et elle va afficher les mots les plus proches à cet ensemble de mot.
	 * Cette fonction va aussi vérifier si les mots sont correctement écrits.
	 * Elle renvoie True en cas de succès.
	 * @param toGuess
	 *	Le mot à deviner.
	 * @return
	 *	True si le joueur a réussi , False sinon.
	 */
	public boolean guess(String toGuess){
		System.out.println("Type " + this.clueNb + " word" + (this.clueNb>1 ? "s" : "") + " : ");
		Scanner sc = new Scanner(System.in);
		String[] words = new String[clueNb];
		for(int i = 0; i<clueNb ; i++){
			words[i] = sc.next();
		}
		for(int j = 0; j<clueNb ; j++){
			while(!datas.contains(words[j])){
				System.out.println("\n" + words[j] + " is not a known word. Please enter an other word : ");
				words[j] = sc.next();
			}
		}
		double[][] vectors = new double[clueNb][];
		for(int k = 0; k < clueNb ; k++)
			vectors[k] = datas.getVector(words[k]);
		double[] average = WordVector.average(vectors);
		HashMap<String, Double> nearestMap = datas.nearest( average , nearestNb);
		System.out.println(Arrays.asList(nearestMap));
		return nearestMap.containsKey(toGuess);
	}
}
