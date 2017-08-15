
import java.util.HashMap;

public class GnomeGraph {

	private String name;// name of Graph
	private Llist<Gnome> allGnomes;// linked lists to hold people and friends

	public GnomeGraph() {// default constructor
		this("NewGraph");
	}// end

	public GnomeGraph(String name) {// constructor
		this.name = name;
		this.allGnomes = new Llist<Gnome>();

	}// end

	public String getName() {// getter
		return this.name;
	}// end

	public Llist<Gnome> getAllGnomes() {// getter
		return this.allGnomes;
	}// end
	
	public String getAnonymisedGraph() {
		
		HashMap<Gnome, Integer> table = new HashMap<Gnome, Integer>(this.getAllGnomes().getLength());

		for (int i = 0; i < this.getAllGnomes().getLength(); i++) {
			int random = 1000 + (int) (Math.random() * ((2000 - 1000) + 1));
			table.put(this.getAllGnomes().get(i).getData(), random);
		}

		String graph = "";
		for (int i = 0; i < this.getAllGnomes().getLength(); i++) {
			Gnome temp = this.getAllGnomes().get(i).getData();
			graph += "\n"+"Gnome " + Integer.toString(table.get(temp)) + " has friends: ";
			for (int j = 0; j < temp.getAllFriends().getLength(); j++) {
				Gnome g = temp.getAllFriends().get(j).getData();
				graph += Integer.toString(table.get(g)) + ", ";
			}

		}
		return graph;
	}
	
	public void addConnection(Gnome employee, Gnome connection, Gnome friend) {
    
		if(this.checkFriendship(connection, employee)&&this.checkFriendship(connection, friend))
		this.addFriendship(employee, friend);
		else{
			Message_Centre mc = new Message_Centre();
			Message m = new Message(mc, employee, connection, "Alert!!!", "The sender may be a hacker");
			m.send();
		}
	}
	
	/**
	 * adds this Person as a Node to the graph (populate the graph)
	 */
	public void addNode(Gnome p) {

		this.allGnomes.append(p);// add to Llist of all people

	}// end

	/**
	 * makes Person t a friend(edge) of Person f
	 */
	public void addFriendship(Gnome f, Gnome t) {

		Edge e = new Edge(f, t);

	}// end

	/**
	 * returns true if
	 * Person f is friends with Person t,
	 * false otherwise
	 */
	public boolean checkFriendship(Gnome f, Gnome t) {

		for (int i = 0; i <f.getAllFriends().getLength(); i++) {

			if (f.getAllFriends().get(i).equals(t)) {
				return true;
			}
		}
		return false;
	}


}// end class Graph