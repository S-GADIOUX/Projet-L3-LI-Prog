public class Player {
	
	String name;
	int tileNumber;
	int mod;

	public Player(String name){
		this.name = name;
		this.tileNumber = 0;
		this.mod = 0;
	}

	public int applyMod(){
		int r = this.mod;
		this.mod = 0;
		return r;
	}


}
