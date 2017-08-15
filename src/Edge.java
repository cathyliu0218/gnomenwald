
public class Edge {
	private Gnome from, to;
	
	public Edge(Gnome f, Gnome t) {
		this.from = f;
		this.to = t;
		f.getAllFriends().append(t);// add t to the adjacency list of f
	
	}



	public Gnome getFrom() {
		return this.from;
	}

	public Gnome getTo() {
		return this.to;
	}

}
