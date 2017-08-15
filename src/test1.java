import java.io.IOException;

public class test1 {

	public static synchronized void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Graph g = new Graph();
		Village ithaca = new Village("ithaca");
		Village nyc = new Village("nyc",3);
		Village chicago = new Village("chicago");
		Village delhi = new Village("delhi");
		Village accra = new Village("accra",3);
		Village mumbai = new Village("mumbai");
		g.addVillage(ithaca);
		g.addVillage(nyc);
		g.addVillage(chicago);
		g.addVillage(delhi);
		g.addVillage(mumbai);
		g.addVillage(accra);	
		
		g.addRoad(ithaca, nyc, 10, 3);
		g.addRoad(ithaca, chicago, 5, 1);
		g.addRoad(nyc, delhi, 6, 4);
		g.addRoad(nyc, accra, 3, 4);
		g.addRoad(delhi, accra, 7, 5);
		g.addRoad(delhi, mumbai, 2, 5);
		g.addRoad(mumbai, accra, 12, 7);
		g.addRoad(chicago, mumbai, 5, 1);
		g.addRoad(accra, chicago, 6, 3);
		g.addRoad(accra, ithaca, 8, 5);
		

		Gnome w = new Gnome("w");
		Gnome q = new Gnome("q");
		Gnome e = new Gnome("e");
		Gnome a = new Gnome("a");
		Gnome t = new Gnome("t");
		Gnome x = new Gnome("x");
		Gnome b = new Gnome("b");
		Gnome p = new Gnome("p");
		Gnome o = new Gnome("o");
		
		
		//w.setLazy();
	
		
		q.setLazy();
		
   	    
		
		w.setCurrentVillage(chicago);
		q.setCurrentVillage(nyc);
		e.setCurrentVillage(ithaca);
		a.setCurrentVillage(delhi);
		t.setCurrentVillage(accra);
		x.setCurrentVillage(accra);
	    b.setCurrentVillage(mumbai);
		p.setCurrentVillage(delhi);
		o.setCurrentVillage(nyc);
		
   	    
		g.travel(w, chicago, delhi);
		g.travel(q, nyc, mumbai);
		g.travel(e, ithaca, accra);
		g.travel(a, delhi, nyc);
		g.travel(t, accra, ithaca);
		g.travel(x, accra, delhi);
		g.travel(b, mumbai, ithaca);
		g.travel(p, delhi, accra);
		g.travel(o, nyc, delhi);
		
		
	 		System.out.println(w.getCash());
			System.out.println(w.getCurrentVillage().getName());
     


	 		System.out.println(q.getCash());
			System.out.println(q.getCurrentVillage().getName());
			
  	 		System.out.println(e.getCash());
			System.out.println(e.getCurrentVillage().getName());
 
  	 		System.out.println(a.getCash());
			System.out.println(a.getCurrentVillage().getName());
 
			
  	 		System.out.println(t.getCash());
			System.out.println(t.getCurrentVillage().getName());
			
  	 		System.out.println(x.getCash());
			System.out.println(x.getCurrentVillage().getName());
			
  	 		System.out.println(b.getCash());
			System.out.println(b.getCurrentVillage().getName());
			
  	 		System.out.println(p.getCash());
			System.out.println(p.getCurrentVillage().getName());
			
  	 		System.out.println(o.getCash());
			System.out.println(o.getCurrentVillage().getName());
			
	

				
 
		//g.travel(qwerty, ithaca, mumbai);
		//System.out.println(c);

		
		//String b="";
		//for(int i=0;i<qwerty.getVisitedVillages().getLength();i++){
		//	b=qwerty.getVisitedVillages().get(i).getData().getName()+" "+b;	
		//}
		
		//System.out.println(qwerty.getCurrentVillage().getName());
		
	
	}
}