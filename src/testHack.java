
public class testHack {

	public static void main(String[] args) throws BadMessageException {
		// TODO Auto-generated method stub
		GnomeGraph g = new GnomeGraph();
		Gnome ithaca = new Gnome("ithaca");
		Gnome nyc = new Gnome("nyc");
		Gnome chicago = new Gnome("chicago");
		Gnome delhi = new Gnome("delhi");
		Gnome accra = new Gnome("accra");
		Gnome mumbai = new Gnome("mumbai");
		g.addNode(ithaca);
		g.addNode(nyc);
		g.addNode(chicago);
		g.addNode(delhi);
		g.addNode(mumbai);
		g.addNode(accra);		
		
		g.addFriendship(ithaca, nyc);
		g.addFriendship(ithaca, chicago);
		g.addFriendship(nyc, delhi);
		g.addFriendship(nyc, accra);
		g.addFriendship(delhi, accra);
		g.addFriendship(delhi, mumbai);
		g.addFriendship(mumbai, accra);
		g.addFriendship(chicago, mumbai);
		g.addFriendship(accra, chicago);
		g.addFriendship(accra, ithaca);
		
		Elf e = new Elf();
		String d = e.getAnonymisedGraph(g);
		System.out.println(d);
		Gnome fake1 = e.createFakeGnome("fake");
		Gnome fake2 = e.createFakeGnome("fake");
		Gnome fake3 = e.createFakeGnome("fake");
		Gnome fake4 = e.createFakeGnome("fake");
		
		g.addNode(fake1);
		g.addNode(fake2);
		g.addNode(fake3);
		
		
		g.addFriendship(fake1, fake2);
		g.addFriendship(fake1, fake3);
		g.addFriendship(fake2, fake1);
		g.addFriendship(fake2, fake3);
		g.addFriendship(fake3, fake1);
		g.addFriendship(fake3, fake2);
		
		
		Message_Centre mc = new Message_Centre();
		
		Message msg1 = new Message(mc, fake1, ithaca, "1","bro, I can help with your homework",true );
		Message msg2 = new Message(mc, fake2, nyc, "2","hi",true );
		Message msg3 = new Message(mc, fake3, chicago, "3","hi",true );
		
		msg1.send(g);
		msg2.send(g);
		msg3.send(g);
		
		msg1.open();
		
		
	
		//msg.open();
		String c = e.getAnonymisedGraph(g);
		System.out.println(c);
		

		Message msg4 = new Message(mc, ithaca, fake1, "homework question", "5 1 2 + 4 * + 3 -");
		
		msg4.send();
		msg4.open();
		String req = msg4.getContent();
		Message msg5 = new Message(mc, fake1, ithaca, "add me as your friend", RPNOutput.Output(req));
	    msg5.send();
	    msg5.open();
	    

		}
		
		
	}


