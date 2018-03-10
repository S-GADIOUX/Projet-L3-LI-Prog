import java.util.HashSet;
public class Tile {
	
	private HashSet<Player> players;
	private LaunchStyle launcher;
	private LandStyle lander;

	public Tile(LaunchStyle lau, LandStyle lan){
		this.launcher = lau;
		this.lander = lan;
		this.players = new HashSet<Player>();
	}

	public boolean isEmpty(){
		return this.players.size() == 0;
	}

	public int launch(Player p, Dice d){
		this.players.remove(p);
		return this.launcher.launch(d) + p.applyMod();
	}

	public int land(Player p, Dice d){
		return this.lander.land(d);
	}

	public void fall(Player p){
		this.players.add(p);
	}

}
