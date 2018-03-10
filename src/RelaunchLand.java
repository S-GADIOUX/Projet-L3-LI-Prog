public class RelaunchLand implements LandStyle{

	public int land(Player p, Dice d){
		return d.roll(false);
	}
}
