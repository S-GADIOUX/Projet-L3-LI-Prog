import com.sgadioux.style.*;
public class Main {
	
	public static void main(String[] args){
		boolean b = true;
		int dSize = 6;
		boolean trickedDice = false;
		Dice d = new Dice(dSize, trickedDice);
		int bLength = 100;
		if (bLength<1) {
			System.out.println("Board Length must be positive");
			b = false;
		}
		int clueNb = 3;
		int tryNb = 3;
		int errorZone = 10;
		double relaunchTile = 0.2;
		double backTile = 0.1;
		String path = "vec0.txt";
		WordVector datas = null;
		try {
			datas = new WordVector(path);
		}
		catch(Exception e){
			b = false;
			System.out.println("Error in file");	
		}
		String[] players = {"Alpha","Delta","Gamma","Echo","Blade","Fire"};
		if(datas != null){
			Game g = new Game(players, bLength, d, clueNb, tryNb, errorZone, relaunchTile, backTile, datas);
			g.play();
		}
	
	}

}
