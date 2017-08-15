import java.io.*;
import java.util.HashMap;
public class Graph {

	private String name;// name of Graph
	private Llist<Village> allVillages;// linked lists to hold people and friends
	private Llist<Gnome> allGnomes;
	private BST_Name tree; 
	
	public BST_Name getBST() {
		return tree; 
	}

	public Graph() {// default constructor
		this("NewGraph");
	}// end

	public Graph(String name) {// constructor
		this.name = name;
		this.tree = new BST_Name();
		this.allVillages = new Llist<Village>();
		this.allGnomes = new Llist<Gnome>();

	}// end

	public String getName() {// getter
		return this.name;
	}// end

	public Llist<Village> getAllVillages() {// getter
		return this.allVillages;
	}// end
	
	public Llist<Gnome> getAllGnomes() {// getter
		return this.allGnomes;
	}// end
	
	public void addGnome(Gnome p){
		this.allGnomes.append(p);
		tree.insert(p);
	}
	
	public Gnome searchGnome(String name) {
		return this.tree.findName(name).get(0).getData().getData();
	}


	/**
	 * adds this Village as a Node to the graph 
	 */

	public void addVillage(Village v) {
		this.allVillages.append(v);// add to Llist of all Villages
	}// end

	public void removeVillage(Village v){
		this.allVillages.remove(v);
	}

	/**
	 * connects Village f to Village t
	 * through a road
	 */

	public void addRoad(Village f, Village t,int toll,  int limit) {
		Road e = new Road(f, t, toll, limit);
		f. getOut_AdjVillages().append(t);
		t.getIn_AdjVillages().append(f);
		f.getAllRoads().append(e);
		t.getInRoads().append(e);
		
	}

	public void removeRoad(Village f, Village t, int toll){
		Road v = null;
		//System.out.println("from1 "+t.getName());
		for(int i=0; i<f.getAllRoads().getLength(); i++){
			if (f.getAllRoads().get(i).getData().getTo()==t){
				v=f.getAllRoads().get(i).getData();
				//System.out.println("from "+v.getFrom());
			}
		}
		//
		f.getAllRoads().remove(v);
	}
	public void deleteVillage(Village v, boolean option) throws NotFoundException,IOException{
		Llist.Node<Village> x =allVillages.graphFind(v);// find the node

		if (option){
			int i=0;
			//System.out.println(x.getData().getIn_AdjVillages().getLength());
			while(x.getData().getIn_AdjVillages().getLength()>i){//loop through 
				// all incoming villages of x
				for (int j=0;j<x.getData().getOut_AdjVillages().getLength();j++){
					//System.out.println(x.getData().getIn_AdjVillages().get(i).getData());d
					addRoad(x.getData().getIn_AdjVillages().get(i).getData(),
							x.getData().getOut_AdjVillages().get(j).getData(),3,x.getData().getLimitGnomes());
				}// connect the in villages to the out villages
				i++;
			}
			//System.out.println("remove " + x.getData());
			removeVillage(x.getData());// delete the node
			//System.out.println(x.getData());
			//x.getData().setAllRoads(); //delete all roads through village v
		}
		else {
			int i=0;
			while(x.getData().getIn_AdjVillages().getLength()>i){//loop through 
				// all incoming villages of x
				// remove incoming roads from adjacent villages
				//System.out.println(x.getData().getIn_AdjVillages().get(i).getData());
				//System.out.println(x.getData());
				//System.out.println(x.getData().getAllRoads().get(i).getData().getToll());
				removeRoad(x.getData().getIn_AdjVillages().get(i).getData(),
						x.getData(),12);
				i++;
			}
			int j=0;
			//System.out.println(x.getData().getOut_AdjVillages());
			while(x.getData().getOut_AdjVillages().getLength()>j){
				// remove outgoing roads from adjacent villages
				removeRoad(x.getData().getOut_AdjVillages().get(j).
						getData(),x.getData(),6);

				j++;
			}
			removeVillage(x.getData());
			x.getData().setAllRoads(); // set allroads of a village to null
		}	
		
	}
	

	public Llist<Road> MST(Village v){
		Llist<Road> A= new Llist<Road>();
		Road g = null;
		for (int i = 0; i < this.getAllVillages().getLength(); i++) {
			this.getAllVillages().get(i).getData().setMinDistance(Integer.MAX_VALUE);
			this.getAllVillages().get(i).getData().setPrevious(null);	
		}
		
		for (int i = 0; i < this.getAllVillages().getLength(); i++) {
			if(this.getAllVillages().get(i).getData().equals(v)) {
				this.getAllVillages().get(i).getData().setMinDistance(0); 
		         //System.out.println("v's min dist=  "+ v.getMinDistance());
			}
		}
		PriorityQueueUsingHeap pr = new PriorityQueueUsingHeap();

		for (int i = 0; i < this.getAllVillages().getLength(); i++) {
			pr.insert(this.getAllVillages().get(i).getData());
		}
		//System.out.println("village in prioty list "+ pr.toString());
	
		while (!pr.isEmpty()) {
		Village a=pr.poll();
			//System.out.println("village poped "+ a);
			if (a.getPrevious()!=null){
				for (int i = 0; i < a.getIn_AdjVillages().getLength(); i++) {
					if(a.getInRoads().get(i).getData().getFrom().equals(a.getPrevious())){	
				g=a.getInRoads().get(i).getData();
				}
				}
				
			boolean success=true;
			if(A.isEmpty()){
				A.append(g);
			}
			for (int j=0; j<A.getLength();j++){
				if (A.get(j).getData().getTo().equals(g.getTo())){
				//	System.out.println("you should be false; "+ A.get(j).getData().getFrom()); 
					success=false;
				}
			}
			if (success==true){
				A.append(g);
			}
			}
			for (int i = 0; i < a.getAllRoads().getLength(); i++) {
				int newDistance=a.getAllRoads().get(i).getData().getToll();
			if(newDistance<a.getOut_AdjVillages().get(i).getData().getMinDistance()){
					//System.out.println("toll "+newDistance);
					//System.out.println("village "+a.getAllRoads().get(i).getData().getTo());
					pr.decreaseKey(a.getAllRoads().get(i).getData().getTo(), newDistance);
			    a.getOut_AdjVillages().get(i).getData().setPrevious(a);// pretty similar the one up there
			   // System.out.println("village in prioty list1 "+ pr.toString());
			}
			}
		}
		return A;
		}
	
	public synchronized Llist<Village> travel(Gnome p, Village v, Village to) throws InterruptedException{
		if(p.getDesperare()){
			return this.cheapestPath(p, v, to);
		}else{
		 return this.bfsPath(p, v, to);
		}
		
	}
	
	public 	synchronized Llist<Village> cheapestPath(Gnome g, Village v, Village to) throws InterruptedException {

		for (int i = 0; i < this.getAllVillages().getLength(); i++) {
			this.getAllVillages().get(i).getData().setMinDistance(Integer.MAX_VALUE);
		}
		//g.leaveVillage(v);
		v.setMinDistance(0);
		PriorityQueueUsingHeap pq = new PriorityQueueUsingHeap();

		for (int i = 0; i < this.getAllVillages().getLength(); i++) {
			pq.insert(this.getAllVillages().get(i).getData());
		}

		//String result = v.getName()+" ";
		Llist <Village> path = new Llist<Village>();
		HashMap<Village, Road> table = new HashMap<Village, Road>(50);

		while (!pq.isEmpty()) {
			Village a = pq.poll();
			if (g.getVIP()>3 || !a.trafficJam()) {
				if (a.equals(to)) {
					while (!a.equals(v)) {
						// update result
						path.append(a);
						a = a.getPrevious();// update u
					}
					for(int i=path.getLength()-1; i>-1;i--){
						
						g.joinRoad(table.get(path.get(i).getData()));
						g.leaveRoad(table.get(path.get(i).getData()));
						g.joinVillage(path.get(i).getData());
						if(i!=0){
						
						g.leaveVillage(path.get(i).getData());
						}
									
					}

					
//					for(int i=path.getLength()-1; i>-1;i--){
//						result+=path.get(i).getData().getName()+" ";
//					}
//					return result;

				}

				for (int i = 0; i < a.getAllRoads().getLength(); i++) {
					if (g.getVIP()>3 || !a.getAllRoads().get(i).getData().trafficJam()) {
						Village b = a.getAllRoads().get(i).getData().getTo();
						
						int newDistance = a.getAllRoads().get(i).getData().getToll() + a.getMinDistance();
						if (newDistance < b.getMinDistance()) {
							pq.decreaseKey(b, newDistance);
							b.setPrevious(a);
							table.put(b, a.getAllRoads().get(i).getData());

						}
					}
				}

			}
		}
		return path;
		//return "n";

	}
	
	public synchronized Llist<Village>bfsPath(Gnome g, Village p, Village t) throws InterruptedException {

		// unmark all people
		for (int i = 0; i < this.getAllVillages().getLength(); i++) {
			this.getAllVillages().get(i).getData().notVisited();
			//this.getAllVillages().get(i).getData().notPushed();

		} // end for loop

		//String result = p.getName()+" ";
		//g.leaveVillage(p);
		Llist <Village> path = new Llist<Village>();
		HashMap<Village, Road> table = new HashMap<Village, Road>(50);
		Q3 q = new Q3();
		q.join(p);// make the given Person p join the queue

		while (!q.isEmpty()) {// while there are still more people in queue
			Village u = q.leave();// make the person at front leave the queue
			if (!u.getVisited()) {// if person is still unvisited
		
				u.setVisited();// visit the person
				if (u.equals(t)) {//if required Person
                    //keep adding parent names till we get to Person p
					while (!u.equals(p)) {
					    //update result
						path.append(u);
						u = u.getPrevious();//update u
					}
					for(int i=path.getLength()-1; i>-1;i--){
						g.joinRoad(table.get(path.get(i).getData()));
						g.leaveRoad(table.get(path.get(i).getData()));
						g.joinVillage(path.get(i).getData());
						if(i!=0)
						g.leaveVillage(path.get(i).getData());					
					}
//					for(int i=path.getLength()-1; i>-1;i--){
//						result+=path.get(i).getData().getName()+" ";
//					}

					//return result;
				}

				// make all friends(edges) of that person join the queue

				for (int i = 0; i < u.getAllRoads().getLength(); i++) {
					if (u.getAllRoads().get(i).getData().getTo().equals(t)){ 
							
						u.getAllRoads().get(i).getData().getTo().setPrevious(u);
						q.join(u.getAllRoads().get(i).getData().getTo());
						table.put(u.getAllRoads().get(i).getData().getTo(), u.getAllRoads().get(i).getData());
						
					}
					if (!u.getAllRoads().get(i).getData().getTo().equals(t)) {
							
							u.getAllRoads().get(i).getData().getTo().setPrevious(u);
							q.join(u.getAllRoads().get(i).getData().getTo());	
							table.put(u.getAllRoads().get(i).getData().getTo(), u.getAllRoads().get(i).getData());
							
					}
				}//end for loop
			} // end if
		} // end while loop
		//return result;
		return path;
	}// end optimalPath

	// end

	//	/**
	//	 * returns true if
	//	 * there is a road connecting Village f to Village t,
	//	 * false otherwise
	//	 */
	//	public boolean checkRoad(Village f, Village t) {
	//
	//		for (int i = 0; i < f.getNumFriends(); i++) {
	//
	//			if (f.getAllEdges()[i].getTo().equals(t)) {
	//				return true;
	//			}
	//		}
	//		return false;
	//	}

}// end class Graph