import com.sgadioux.board.Dice;
import com.sgadioux.board.Game;
import com.sgadioux.word.WordVector;

/**
 *
 * @author sebga
 */
public class Main {
	
	/**
	 *
	 * @param args
	 */
	public static void main(String[] args){
		boolean b = true;
		int len = args.length;
		int i = 0;
		int nbPlayer = 0;
		String path = "";
		int clueNb = 3;
		int tryNb = 3;
		int errorZone = 10;
		int bLength = 100;
		double relaunchTile = 0.2; //To unHard
		double backTile = 0.1; //To unHard
		int dSize = 6;
		boolean trickedDice = false;
		while (len > i){
			switch (args[i]){
				case("--w2v") :
					i++;
					path = args[i];	
					break;
				case("--board") :
					i++;
					bLength = Integer.parseInt(args[i]);
					break;
				case("--dice") :
					i++;
					dSize = Integer.parseInt(args[i]);
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
		Dice d = new Dice(dSize, trickedDice);
		if (bLength<1) {
			System.out.println("Board Length must be positive");
			b = false;
		}
		WordVector datas = null;
		try {
			datas = new WordVector(path);
		}
		catch(Exception e){
			b = false;
			System.out.println("Error in file : ");	
			e.printStackTrace();
		}
		String[] players = {"Alpha","Delta","Gamma","Echo","Blade","Fire"};
		if(b){
			Game g = new Game(players, bLength, d, clueNb, tryNb, errorZone, relaunchTile, backTile, datas);
			g.play();
		} else {
		}
	
	}

}
