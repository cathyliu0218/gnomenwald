

public class BST_TEST {


	public static void main(String[] args) {
		// ma Gnome takes in name and VIP status
		Gnome Alison = new Gnome("Alison", 3, "Blue");
		Gnome Cathy = new Gnome("Cathy", 2, "Yellow");
		Gnome Chris = new Gnome("Chris", 1, "Yellow");
		Gnome Nina = new Gnome("Nina", 5, "Yellow");
		Gnome Cathy2 = new Gnome("Cathy", 5, "Red");
		//Gnome Ninoo = new Gnome("Ninoo", 5, "Yellow");

		BST_ID oak = new BST_ID(); // holds Gnomes inserted by ID
		BST_VIP fir = new BST_VIP(); //holds Gnomes inserted by VIP
		BST_Name evergreen = new BST_Name(); //holds Gnomes inserted by Name
		BST_Color deciduous = new BST_Color(); //holds Gnomes inserted by Color

		// insert Gnomes into the tree
		oak.insert(Alison);
		oak.insert(Cathy);
		oak.insert(Nina);
		oak.insert(Cathy2);
		oak.insert(Chris);
		
		fir.insert(Alison);
		fir.insert(Cathy);
		fir.insert(Cathy2);
		fir.insert(Nina);
		fir.insert(Chris);

		evergreen.insert(Alison);
		evergreen.insert(Cathy);
		evergreen.insert(Nina);
		evergreen.insert(Cathy2);
		evergreen.insert(Chris);
		
		deciduous.insert(Cathy);
		deciduous.insert(Nina);
		deciduous.insert(Cathy2);
		deciduous.insert(Alison);
		deciduous.insert(Chris);
		
		// use tree to find the Gnome with a particular ID
		String y = oak.findID(1000).getData().getGnomeName();
		String f = fir.findVIP(5).get(1).getData().getData().getGnomeName();
		String e = evergreen.findName("Cathy").get(1).getData().getData().getGnomeName();
		String d = deciduous.findColor("Yellow").get(2).getData().getData().getGnomeName();
		System.out.println(f);
		System.out.println(e);
		System.out.println(d);
		System.out.println(y);


	}
;
}
