import java.util.HashMap;

public class Elf {
	private static final int STARTING_ID = 1000;
	private int id;
	public static int number_made = 0;
	private String name;
	
	public int getID(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Gnome createFakeGnome(String name){
		return new Gnome(name);
	}
	
	public String getAnonymisedGraph(GnomeGraph g) {
		
		HashMap<Gnome, Integer> table = new HashMap<Gnome, Integer>(g.getAllGnomes().getLength());

		for (int i = 0; i < g.getAllGnomes().getLength(); i++) {
			int random = 1000 + (int) (Math.random() * ((2000 - 1000) + 1));
			table.put(g.getAllGnomes().get(i).getData(), random);
		}

		String graph = "";
		for (int i = 0; i < g.getAllGnomes().getLength(); i++) {
			Gnome temp = g.getAllGnomes().get(i).getData();
			graph += "\n"+"Gnome " + Integer.toString(table.get(temp)) + " has " +g.getAllGnomes().get(i).getData().getAllFriends().getLength()+ " friends: ";
			for (int j = 0; j < temp.getAllFriends().getLength(); j++) {
				Gnome gnome = temp.getAllFriends().get(j).getData();
				graph += Integer.toString(table.get(gnome)) + ", ";
			}

		}
		return graph;
	}
	
	
	public Elf(){
		this("Spy");
	}
	
	public Elf(String name){
		this.name = name;
		this.id = STARTING_ID + number_made;
		number_made++; // thus incrementing this static class variable
	}

}
