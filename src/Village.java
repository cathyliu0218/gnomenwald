public class Village {

	private Llist<Village> Out_adjVillages, In_adjVillages;
	private int id, num_Made = 1;
	private String name;
	private Village next;
	private Llist<Road> allRoads, In_Roads;
	private Llist<Gnome> allGnomes;
	private int limitGnomes;
	private int minDistance;
	private Village previous;
	public boolean visited;

	public String getName() {
		return this.name;
	}

	public int getID() {
		return this.id;
	}
	
	public boolean getVisited(){
		return this.visited;
	}
	
	public void setVisited(){
		this.visited=true;
	}
	
	public Village getThis(){
		return this;
	}
	
	public void notVisited(){
		this.visited=false;
	}
	
	public int getLimitGnomes() {
		return this.limitGnomes;
	}
	
	public Village getPrevious(){
		return this.previous;
	}
	
	public void setPrevious(Village v){
		this.previous=v;
	}

	public Llist<Gnome> getAllGnomes() {
		return this.allGnomes;
	}

	public Llist<Road> getAllRoads() {
		return this.allRoads;
	}
	public String toString(){
		return this.name;
	}
	
	public boolean trafficJam(){
		return (this.getLimitGnomes()==this.getAllGnomes().getLength()
		        || this.getLimitGnomes()==(this.getAllGnomes().getLength()-1));
	}
	
	public Llist<Road> getInRoads(){
		return this.In_Roads;
	}
	 
	public int getMinDistance(){
	 return this.minDistance;
	}
	
	public void setMinDistance(int dist){
		this.minDistance=dist;
	}
	public int compareTo(Village v){
	    return Integer.compare(this.getMinDistance(), v.getMinDistance());
	}
	
	public void setAllRoads() {// used in delete method
		this.allRoads=null;
	}

	public Llist<Village> getOut_AdjVillages() {
		return this.Out_adjVillages;
	}
	
	public Llist<Village> getIn_AdjVillages() {
		return this.In_adjVillages;
	}

	public Village getNext() {// getter
		return this.next;
	}// end

	void setNext(Village n) {// setter
		this.next = n;
	}// end


	public void addGnome(Gnome g) {
		if (g.getVIP()>=3 || this.allGnomes.getLength() <= this.limitGnomes) {
			this.allGnomes.append(g);
			g.getVisitedVillages().append(this);
			
			//g.payToll(this.getMinDistance());	
		}
	}

	public void removeGnome(Gnome g) {
		this.allGnomes.remove(g);
	}

	public Village(String n, int limit) {
		this.name = n;
		this.id = num_Made++;
		this.limitGnomes = limit;
		this.Out_adjVillages = new Llist<Village>();
		this.In_adjVillages = new Llist<Village>();
		this.allRoads = new Llist<Road>();
		this.allGnomes = new Llist<Gnome>();
		this.minDistance=Integer.MAX_VALUE;
		this.previous=null;
		this.visited=false;
		this.In_Roads = new Llist<Road>();
		
	}

	public Village() {// default constructor
		this("newVillage");
	}// end

	public Village(String name) {// default constructor
		
		this(name,10);
	}// end

} // end class Node

// end class Llist