import com.sgadioux.style.*;
public class Main {
	
	public static void main(String[] args){
		int dSize = 6;
		boolean trickedDice = false
		Dice d = new Dice(dSize, trickedDice);
		int bLength = 100;
		int clueNb = 3;
		int tryNb = 3;
		int errorZone = 10;
		double relaunchTile = 0.2;
		double backTile = 0.1;
		String path = "../vec0.txt";
		String[] players = {"Alpha","Delta","Gamma","Echo","Blade","Fire"};
		Game g = new Game(players, bLength, d, clueNb, tryNb, errorZone, relaunchTile, backTile, new WordVector(path));
		g.play();
	
	}

}
