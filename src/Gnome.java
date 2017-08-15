public class Gnome extends Thread{// a class of gnomes to populate the graph
	private Llist<Village> villagesVisited;
	public static int number_made = 0;
	private static final int DEFAULT_SIZE = 1000;
	public static int DEFAULT_NUMBER = 1000;
	private static final int STARTING_ID = 1000;
	private int id, VIP;// id unique for each gnome
	private int cash; // amount of cash for each gnome to spend on tolls
	private String name;
	private String color; // gnomes' favorite color
	private Village currentVillage, desiredVillage;
	private boolean desperate;
	private Llist<Gnome>allFriends;
	private int[] recd_message, sent_message;// to hold index of all messages
	public boolean alert;// set true when msg recd, set false when read
	private int num_mess_recd;
	private int num_mess_sent;

//	public void setName(String name) {// setter for Name
//		this.name = name;
//	}// end setName

	public String getGnomeName() { // getter for Name
		return this.name;
	}// end getName
	
	public boolean getDesperare(){
		return this.desperate;
	}
	
	public void setLazy(){
		this.desperate=false;
	}
	
	public Llist<Gnome>getAllFriends(){
		return this.allFriends;
	}
	
	public synchronized void joinVillage(Village v) throws InterruptedException{
//		if (this.getVIP()>=3 || v.getAllGnomes().getLength() <= v.getLimitGnomes()){
//		this.setCurrentVillage(v);
//		this.villagesVisited.append(v);
//		v.getAllGnomes().append(this);	
//		}
//		else{
//			this.wait(100);
//		}
		while(!(this.getVIP()>=3 || v.getAllGnomes().getLength() <= v.getLimitGnomes())){
			this.wait();	
		}
		this.setCurrentVillage(v);
		this.villagesVisited.append(v);
		v.getAllGnomes().append(this);	
	}
	
	public synchronized void leaveVillage(Village v){
		try{
		v.getAllGnomes().remove(this);
		this.notify();
		}
		catch (NullPointerException n){
			v.getAllGnomes().append(this);
			
			
		}
	}
	
	public synchronized void joinRoad(Road v) throws InterruptedException{
		//System.out.println(this.getName());
				//+" "+v.getFrom().getName()+v.getTo().getName());
		
//		if (this.getVIP()>=3 || v.getAllGnomes().getLength() <= v.getLimitGnomes()){
//		this.payToll(v.getToll());
//		v.getAllGnomes().append(this);	
//		}
//		else{
//			this.wait(100);
//		}
		while(!(this.getVIP()>=3 || v.getAllGnomes().getLength() <= v.getLimitGnomes())){
			this.wait();	
		}
		this.payToll(v.getToll());
		v.getAllGnomes().append(this);		
	}
	
	public synchronized void leaveRoad(Road v){
		
		v.getAllGnomes().remove(this);
		this.notify();
	}
	
	/**
	 * increments number of msgs sent
	 * gives an id to the sent message accordingly 
	 */
	public void add_sent_id(int id) {
		this.num_mess_sent++;
		this.sent_message[this.num_mess_sent] = id;
		//thus adding the msg id to the sent array																													
	}//end add_sent_id

	/**
	 * increments number of msgs recd
	 * gives an id to the recd message accordingly 
	 * sets alert to true
	 */
	public void add_recd_id(int id) {
		this.num_mess_recd++;
		this.recd_message[this.num_mess_recd] = id;
		//thus adding the msg id to the recd array	
		this.alert = true;// letting me know when I get a message
	}//end add_recd_id

	/**
	 * alerts the receiver of a new msg
	 */
	public void messageAlert() {
		if (this.alert == true)
			System.out.println(this.getGnomeName() + ", You have a new message");
	}//end messageAlert

	public int getNum_mess_recd() {//getter
		return this.num_mess_sent;
	}//end getNum_mess_recd

	public int getNum_mess_sent() {//getter
		return this.num_mess_sent;
	}//end getNum_mess_sent

	
	public void moveGnome(Village v){
		
		this.setCurrentVillage(v);
		this.villagesVisited.append(v);	
	}
	
	public void moveGnomeAdjacent(Village v) {
		for (int i = 0; i < this.getCurrentVillage().getOut_AdjVillages().getLength(); i++) {
			if (this.getCurrentVillage().getOut_AdjVillages().get(i).getData().equals(v)) {
				this.setCurrentVillage(v);
				this.villagesVisited.append(v);
			}
		}
	}
	
	public Village moveGnomeRandom(){
		Village v = this.getCurrentVillage();
		int range = v.getOut_AdjVillages().getLength();
		int index = 0 + (int)(Math.random() * ((range - 0) + 1));
		this.setCurrentVillage(v.getOut_AdjVillages().get(index).getData());
		this.villagesVisited.append(v.getOut_AdjVillages().get(index).getData());
		return v.getOut_AdjVillages().get(index).getData();
	}

	public Llist<Village> getVisitedVillages() {
		return this.villagesVisited;
	}
	
	public void visitVillage(Village v){
		this.villagesVisited.append(v);
	}
	
	public Village getCurrentVillage(){
		return this.currentVillage;
	}
	
	public void setCurrentVillage(Village v){
		this.currentVillage=v;
	}
	
	public Village getDesiredVillage(){
		return this.desiredVillage;
	}
	
	public void setDesiredVillage(Village v){
		this.desiredVillage=v;
	}


	public void setColor(String color) { // setter for Color
		this.color = color;
	}// end setColor

	public String getColor() { // getter for Color
		return this.color;
	}// end getName

	public void setCash(int cash) { // setter for Cash
		this.cash = cash;
	}// end setCash

	public void payToll(int toll) {
		this.cash -= toll;
	}

	public int getCash() { // getter for Cash
		return this.cash;
	}// end getCash

	public int getID() { // get ID
		return this.id;
	}// end for get ID

	public void setVIP(int vip) {// sets VIP
		this.VIP = vip;
	}// end for setVIP

	public int getVIP() {// gets VIP
		return this.VIP;
	}// end for getVIP

	public void increaseVIPStatus() {// increase the vip status of an individual
		//System.out.println("Congrats bro!. your VIP status has increased by 1");
		this.VIP++;
	}// end increaseVIPStatus

	public void decreaseVIPStatus() {// increase the vip status of an individual
		//System.out.println("Sorry bro!. your VIP status has decreased by 1");
		this.VIP--;
	}// end decreaseVIPStatus

	public Gnome() {// default constructor
		this("Anon");// anonymous, 1, black being default
	}// end Gnome
	
	public Gnome(String name){
		this(name, 1, "Black");
		
	}

	public Gnome(String name, int vip, String color) {// constructor
		this.name = name;
		this.color = color;
		this.cash = DEFAULT_NUMBER;
		this.id = STARTING_ID + number_made;
		this.VIP = vip;
		this.villagesVisited = new Llist<Village>();
		this.currentVillage=null;
		this.desiredVillage=null;
		this.desperate=true;
		this.allFriends = new Llist<Gnome>();
		this.recd_message = new int[DEFAULT_SIZE];
		this.sent_message = new int[DEFAULT_SIZE];
		for (int i = 0; i < recd_message.length; i++) {
			this.recd_message[i] = -1;
		}
		for (int i = 0; i < recd_message.length; i++) {
			this.sent_message[i] = -1;
		}
		this.alert = false;
		this.num_mess_recd = 0;
		this.num_mess_sent = 0;
		

		number_made++; // thus incrementing this static class variable
		this.start();

	}// end public gnome (constructor)

}// end class Gnome