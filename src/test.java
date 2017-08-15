import java.io.IOException;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph();
		Village ithaca = new Village("ithaca");
		Village nyc = new Village("nyc");
		Village chicago = new Village("chicago");
		Village delhi = new Village("delhi");
		Village accra = new Village("accra");
		Village mumbai = new Village("mumbai");
		g.addVillage(ithaca);
		g.addVillage(nyc);
		g.addVillage(chicago);
		g.addVillage(delhi);
		g.addVillage(mumbai);
		g.addVillage(accra);	
		
		
		g.addRoad(ithaca, nyc, 10,1);
		g.addRoad(ithaca, chicago, 5,2);
		g.addRoad(nyc, accra, 6,3);
		g.addRoad(nyc,delhi , 3,4);
		g.addRoad(delhi, accra, 7,5);
		g.addRoad(delhi, mumbai, 2,6);
		g.addRoad(mumbai, accra, 12,7);
		g.addRoad(chicago, mumbai, 5,8);
		g.addRoad(accra, chicago, 6,9);
		g.addRoad(accra, ithaca, 8,10);
		
//		/// new villages
				Village virginia = new Village("virginia");
				Village london = new Village("london");
				Village madrid = new Village("madrid");
				Village paris = new Village("paris");
				Village cairo=new Village("cairo");
				g.addVillage(london);
    			g.addVillage(madrid);
				g.addVillage(virginia);
    	        g.addVillage(paris);
    	        g.addVillage(cairo);
				
				g.addRoad(delhi, virginia, 3,1);
				g.addRoad(london, virginia,7,2);
				g.addRoad(virginia, paris, 4,3);
				g.addRoad(paris, london, 6,4);
     			g.addRoad(paris,madrid, 4,1);
				g.addRoad(madrid, london, 5,2);
				g.addRoad(madrid, virginia, 6,3);
				g.addRoad(cairo, virginia, 8,8);
		        g.addRoad(cairo,paris,8,7);
		        g.addRoad(madrid,cairo,9,4);
	
		//String c = g.travel(qwerty, ithaca, accra);
		//System.out.println(c);

		Llist<Road> A=g.MST(accra);
		System.out.println("Propersal: there are "+A.getLength()+" roads linking villages with the minimum costs");
		for (int i=0; i<A.getLength();i++){
			System.out.println(A.get(i).getData().getFrom() +"=>  "+ A.get(i).getData().getTo() + " toll= "+  A.get(i).getData().getToll() );
		}
		try {
			g.deleteVillage(accra,true);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(g.getAllVillages().toString()+ "    =>deleted Accra"); 	
		
		System.out.println(delhi.getAllRoads().getLength());
	}		
	
}