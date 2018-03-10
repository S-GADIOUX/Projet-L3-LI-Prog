public class BackpushLand implements LandStyle {

	private int value;

	public BackpushLand(int v){
		this.value = v;
	}

	public int land(Player p, Dice d){
		return 0-value;
	}
}
