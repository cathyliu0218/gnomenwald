import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

class Gnome_Move extends JFrame implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer time; 

	public Gnome_Move() {
        setLayout(new GridLayout(2, 3));

        // Creates TextFields, TextAreas, and the button
        name = new JTextField(); 
        idfrom = new JTextField();
        idto = new JTextField();
        JButton jbtGenerate2 = new JButton("Move Gnome");
        echoGnomed = new JTextArea();

        // Adds TextFields, TextAreas, and the button
        add(new JLabel("Name of Gnome to Move")); 
        add(name);
        add(new JLabel("ID of Village To Go From")); 
        add(idfrom);
        add(new JLabel("ID of Village To Go")); 
        add(idto);
        add(jbtGenerate2);
        jbtGenerate2.addActionListener(new GnomeMove());
        jbtGenerate2.addActionListener(new Animate());
//        jbtGenerate2.addActionListener(new GAnimate());
        add(echoGnomed);
        echoGnomed.setEditable(false);

    }

    // Top TextFields
    private JTextField name, idfrom, idto;
    // Bottom(echoed) TextArea
    private JTextArea echoGnomed;
    Gui_storage gs = new Gui_storage();
	private static JLabel gnomeLabel;
    int speed = 10; //milliseconds
    
    
    class GnomeMove implements ActionListener {
    public void actionPerformed(ActionEvent a) {
    	    String gname = name.getText();
    	    String to = idto.getText();
    	   
    		Graphics graphic = Gnomenwald.getPane().getGraphics();
    		graphic.setColor(Color.RED);
    		int id1 = Integer.parseInt(idfrom.getText()); 
    		int id2 = Integer.parseInt(to); 
    		int x1 = (int) gs.arrayPoints[id1-1].getX();
    		int y1 = (int) gs.arrayPoints[id1-1].getY();
    		int x2 = (int) gs.arrayPoints[id2-1].getX();
    		int y2 = (int) gs.arrayPoints[id2-1].getY();

    		graphic.drawLine(580+x1,300+y1,580+x2,300+y2);

    		int pop1 = Gnomenwald.getVillage().get(Integer.parseInt(idfrom.getText()))-1;
    		int pop2 = Gnomenwald.getVillage().get(Integer.parseInt(to))+1;
    		Gnomenwald.getJLabel()[id1-1].setText(idfrom.getText() + ", Gnomes: "+pop1);
    		//JLabel labelDecrease = Gnomenwald.getJLabel()[id2-1];
    		Gnomenwald.getJLabel()[id2-1].setText(to + ", Gnomes: "+pop2);
    		Gnomenwald.getVillage().put(id1, pop1);
    		Gnomenwald.getVillage().put(id2, pop2);
    		//Gnomenwald.getGnomeLabels().get(name.getText()).setBounds(700+x3, 400+y3, 50, 50);
    		//Gnomenwald.getGraph().searchGnome(name.getText()).setCurrentVillage(Gnomenwald.getVillageID().get(idto)); 		
    		 try {
    				Gnomenwald.getGraph().travel(Gnomenwald.getGraph().searchGnome(gname), Gnomenwald.getVillageID().get(idfrom.getText()),Gnomenwald.getVillageID().get(to));
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}  
   }
    }
    
    class Animate implements ActionListener {
        public void actionPerformed(ActionEvent a) {
    		Graphics graphic = Gnomenwald.getPane().getGraphics(); 
    		graphic.setColor(Color.BLACK);
    		int id1 = Integer.parseInt(idfrom.getText()); 
    		int id2 = Integer.parseInt(idto.getText()); 
    		int x1 = (int) gs.arrayPoints[id1-1].getX();
    		int y1 = (int) gs.arrayPoints[id1-1].getY();
    		int x2 = (int) gs.arrayPoints[id2-1].getX();
    		int y2 = (int) gs.arrayPoints[id2-1].getY();
    		
    		graphic.drawLine(580+x1,300+y1,580+x2,300+y2);
    		graphic.setColor(Color.cyan);
    		graphic.drawOval(580+x2,290+y2, 10, 10);
    		
   	     int x = (int) gs.arrayPoints[Integer.parseInt(idto.getText())-1].getX();
   	     int y = (int) gs.arrayPoints[Integer.parseInt(idto.getText())-1].getY();
   	     Gnomenwald.getGnomeLabels().get(name.getText()).setBounds(510+x, 200+y, 50, 50);
    		time = new Timer(speed, this);
    		time.setInitialDelay(1900);
    		time.setRepeats(false);
    		time.start(); 
    		
    	
        }
      }
//    class GAnimate implements ActionListener {
//        public void actionPerformed(ActionEvent a) {
//    		Graphics graphic = Gnomenwald.getPane().getGraphics(); 
//    		graphic.setColor(Color.RED);
//    		int id1 = Integer.parseInt(idfrom.getText()); 
//    		int id2 = Integer.parseInt(idto.getText()); 
//    		int x1 = (int) gs.arrayPoints[id1-1].getX();
//    		int y1 = (int) gs.arrayPoints[id1-1].getY();
//    		int x2 = (int) gs.arrayPoints[id2-1].getX();
//    		int y2 = (int) gs.arrayPoints[id2-1].getY();
//    		
//    		graphic.drawLine(580+x1,300+y1,580+x2,300+y2);
//    		graphic.setColor(Color.cyan);
//    		graphic.drawOval(580+x2,290+y2, 10, 10);
//    		
//   	     int x = (int) gs.arrayPoints[Integer.parseInt(idto.getText())-1].getX();
//   	     int y = (int) gs.arrayPoints[Integer.parseInt(idto.getText())-1].getY();
//
//    		Gnomenwald.getGnomeLabels().get(name.getText()).setBounds(510+x, 200+y, 50, 50);
//    		int count = 100;
//    		count--;
//    		time = new Timer(speed, this);
//    		time.setInitialDelay(100);
//    		time.setRepeats(false);
//    		time.start(); 
//    		if(count<10) {
//    			time.stop();
//    		}
//    		
//        }
//      }
    public static void createAndShowGUI2() {
    	Gnome_Move frame = new Gnome_Move();
        frame.setTitle("Gnome Mover");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Gnomenwald.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
