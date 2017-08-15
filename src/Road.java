
public class Road {
	private Village from, to;
	private int toll;
	private Llist<Gnome> allGnomes;
	private int limitGnomes;

	public Road(Village f, Village t, int toll, int limit) {
		this.from = f;
		this.to = t;
		this.limitGnomes = limit;
		this.toll = toll;
		this.allGnomes = new Llist<Gnome>();
	}

	public Road(Village f, Village t, int toll) {

		this(f,t,toll,10);

	}

	public Llist<Gnome> getAllGnomes() {
		return this.allGnomes;
	}

	public int getLimitGnomes() {
		return this.limitGnomes;
	}

	public int getToll() {
		return this.toll;
	}

	public Village getFrom() {
		return this.from;
	}

	public Village getTo() {
		return this.to;
	}
	
	public boolean trafficJam(){
		return (this.getLimitGnomes()==this.getAllGnomes().getLength()
		        || this.getLimitGnomes()==(this.getAllGnomes().getLength()-1));
	}
	 

	public void addGnome(Gnome g) {

		if (g.getVIP() >= 3 || this.allGnomes.getLength() <= this.limitGnomes) {
			this.allGnomes.append(g);
			g.payToll(this.toll);
		}
	}

	public void removeGnome(Gnome g) {
		this.allGnomes.remove(g);
	}

}