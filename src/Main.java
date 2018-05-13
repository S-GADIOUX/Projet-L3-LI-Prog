import com.sgadioux.board.Dice;
import com.sgadioux.board.Game;
import com.sgadioux.word.WordVector;
import java.util.Scanner;

/**
 * Classe d'exécution principale.
 * @author sebga
 */
public class Main {
	
	/**
	 * Méthode pricipale
	 * @param args
	 *	Arugments venant de la ligne de commande.
	 */
	public static void main(String[] args){
		boolean correct = true;
		int len = args.length;
		int i = 0;
		
		//Ensemble de valeurs par défault
		int nbPlayer = 1;
		String path = "";
		int clueNb = 3;
		int tryNb = 3;
		int errorZone = 10;
		int boardLength = 100;
		double relaunchTile = 0.2;
		double backTile = 0.1;
		int diceSize = 6;
		boolean trickedDice = false;
		
		// Parsing des arguments
		while (len > i){
			switch (args[i]){
				case("--w2v") :
					i++;
					path = args[i];	
					break;
				case("--board") :
					i++;
					boardLength = Integer.parseInt(args[i]);
					break;
				case("--dice") :
					i++;
					diceSize = Integer.parseInt(args[i]);
					break;
				case("--nbJoueurs"):
					i++;
					nbPlayer = Integer.parseInt(args[i]);
					break;
				case("--nbTry"):
					i++;
					tryNb = Integer.parseInt(args[i]);
					break;
				case("--de_magique"):
					trickedDice = true;
					break;
				case("-k"): 
					i++;
					errorZone = Integer.parseInt(args[i]);
					break;
				case("--nbClue"):
					i++;
					clueNb = Integer.parseInt(args[i]);
					break;
			}
			i++;
		
		}
		
		//Verification des arguments
		Dice dice = new Dice(diceSize, trickedDice);
		if (boardLength<1) {
			System.out.println("Board Length must be positive");
			correct = false;
		}
		WordVector datas = null;
		try {
			datas = new WordVector(path);
		}
		catch(Exception e){
			correct = false;
			System.out.println("Error in file : ");	
			e.printStackTrace();
		}
		String[] players = null;
		if (nbPlayer<1) {
			System.out.println("We must have at least one player.");
			correct = false;
		}else{
			players = new String[nbPlayer];

			Scanner sc;
			for(int j = 0; j<nbPlayer; j++){
				System.out.println("Name of the player "+(j+1)+" : ");
				sc = new Scanner(System.in);
				players[j] = sc.next();
			}
			for( String s : players)
				System.out.println(s);
		}
		
		//Lancement du jeu dans le cas où les arguments sont corrects
		if(correct){
			Game g = new Game(players, boardLength, dice, clueNb, tryNb, errorZone, relaunchTile, backTile, datas);
			g.play();
		} else {
			System.out.println("Sorry, due to bad parameters , the game can not start.");
		}
	
	}

}
