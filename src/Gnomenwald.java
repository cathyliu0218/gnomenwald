import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gnomenwald extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static HashMap<Gnome, Village> allGnomes;
	private static HashMap<Integer, Gnome> gnomeids;
	private static HashMap<Integer, Integer> allVillages;
	private static HashMap<String, Village> villagenames;
	private static HashMap<String, JLabel> gnomelabels;
	private JLabel gnomeLabel, villageLabel, roadLabel;
	public static int count, count2, count3 = 0;
	private JButton addgnomes = new JButton("Add Gnomes");
	private JButton movegnomes = new JButton("Move Gnomes");
	private JButton movespecific = new JButton("Move Specific");
	private JButton addvillages = new JButton("Add Villages");
	private JButton deletevillages = new JButton("Delete Villages");
	private JButton addroads = new JButton("Add Roads");
	private JButton deleteroads = new JButton("Delete Roads");
	private JButton historygnomes = new JButton("Gnome History");
	private static JFrame frame;
	private static Graph graph;
	private static JLayeredPane layeredPane;
	public static JPanel drawPane; 
    private static JLabel[] labelList; 	
    
    public static JLabel[] getJLabel() {
    		return labelList; 
    }
    
//	
    public static JLabel[] createLabel() {
    	    JLabel[] labList = new JLabel[10];
        //Point p = new Point(2,3);
        Gui_storage gs = new Gui_storage();
		final ImageIcon icon = createImageIcon("/images/village.png");
		for(int i=0; i<labList.length;i++) {
		int j = i+1;
	    JLabel vLabel = new JLabel(j +  ", Gnomes:" + 0, icon, JLabel.CENTER);
	    vLabel.setVerticalTextPosition(JLabel.CENTER);
        vLabel.setHorizontalTextPosition(JLabel.CENTER);
       
        int x = (int) gs.arrayPoints[i].getX();
        int y = (int) gs.arrayPoints[i].getY();
        vLabel.setBounds(520+x,220+y, 100, 100);
        labList[i]=vLabel;
		}
		return labList;
    }
	public static int getCount3() {
		return count3;
	}
	public static int getCount2() {
		return count2;
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static JLayeredPane getPane() {
		return layeredPane;
	}

	public static Graph getGraph() {
		return graph;
	}
	
	public static HashMap<String, JLabel> getGnomeLabels() {
		return gnomelabels;
	}

	public static HashMap<Gnome, Village> getGnomes() {
		return allGnomes;
	}

	public static HashMap<Integer, Integer> getVillage() {
		return allVillages;
	}

	public static HashMap<Integer, Gnome> getGnomeID() {
		return gnomeids;
	}

	public static HashMap<String, Village> getVillageID() {
		return villagenames;
	}
	

	// Action commands
	private static String LAYER_COMMAND = "layer";

	public Gnomenwald() {
        labelList = this.createLabel();
		graph = new Graph();
		allGnomes = new HashMap<Gnome, Village>();
		allVillages = new HashMap<Integer, Integer>();
		gnomelabels = new HashMap<String, JLabel>();
		gnomeids = new HashMap<Integer, Gnome>();
		villagenames = new HashMap<String, Village>();
		frame = new JFrame("Gnomenwald");

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Create and load the gnome icon.
		final ImageIcon icon = createImageIcon("/images/gnomeyellow.png");

		// Create and set up the layered pane.
		layeredPane = new JLayeredPane();
		drawPane = new JPanel();
		layeredPane.add(drawPane); 
		layeredPane.setBackground(Color.white);
		layeredPane.setOpaque(true);
		layeredPane.setPreferredSize(new Dimension(500, 700));
		layeredPane.setBorder(BorderFactory.createTitledBorder("GnomenWald Village"));

		// Add several labels to the layered pane.
		layeredPane.setLayout(null);

		// Add control pane and layered pane to this JPanel.
		add(Box.createRigidArea(new Dimension(5, 5)));
		add(createControlPanel());
		add(layeredPane);

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


	// Create the control pane for the top of the frame.
	private JPanel createControlPanel() {

		add(addgnomes);
		add(movegnomes);
//		add(labelgnomes);
		add(addvillages);
		add(deletevillages);
		add(movespecific);
		add(addroads);
		add(deleteroads);
//		add(labelroads);
		add(historygnomes);
		addgnomes.addActionListener(new GnomeCreate());
		movegnomes.addActionListener(new GnomeMove());
		movespecific.addActionListener(new MoveSpecific());
		addvillages.addActionListener(new VillageCreate());
		deletevillages.addActionListener(new VillageDelete());
		addroads.addActionListener(new RoadCreate());
		deleteroads.addActionListener(new RoadDelete());
		historygnomes.addActionListener(new GnomeHistory()); 

		JPanel controls = new JPanel();
		controls.add(addgnomes);
		controls.add(movegnomes);
		controls.add(movespecific);
		controls.add(addvillages);
		controls.add(deletevillages);
		controls.add(addroads);
		controls.add(deleteroads);
//		controls.add(labelvillages);
//		controls.add(labelroads);
		controls.add(historygnomes);
		controls.setBorder(BorderFactory.createTitledBorder("Buttons"));
		return controls;

	}
	//
	// implementing a listener for adding gnomes
	class GnomeCreate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			count++;
			Gnome_Gui.createAndShowGUI();
			TrainCanvas s = new TrainCanvas();
//			labelgnomes.setText(generateLabel());
		}
	}

	// implementing a listener for deleting gnomes
	class GnomeMove implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Gnome_Move.createAndShowGUI2();
		}
	}
	
	// implementing a listener formoving gnomes to specific village
	class MoveSpecific implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Move_Specific.createAndShowGUI2();
		}
	}

	// implementing a listener for adding villages
	class VillageCreate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			count2++;
			Village_Gui.createAndShowGUI();
//			labelvillages.setText(generateLabel2());
			allVillages.put(count2, 0);
		}
	}

	// implementing a listener for deleting villages
	class VillageDelete implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Village_Delete.createAndShowGUI2();
			count2--;
		}
	}

	// implementing a listener for adding roads
	class RoadCreate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Road_Gui.createAndShowGUI();
			count3++;
//			labelroads.setText(generateLabel3());
		}
	}

	// implementing a listener for deleting roads
	class RoadDelete implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Road_Delete.createAndShowGUI();
			count3--;
//			labelroads.setText(generateLabel3());
		}
	}
	
	// implementing a listener for history of gnomes
	class GnomeHistory implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Gnome_History.createAndShowGUI();
		}
	}
	

	/**
	 * Create the GUI and show it
	 */
	private void createAndShowGUI() {
		// Create and set up the window.

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
		// Create and set up the content pane.
		JComponent newContentPane = new Gnomenwald();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);
		// Display the window.

		frame.pack();
		frame.setVisible(true);
		frame.repaint();
	}

	public void runGui() {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();

			}
		});
	}

	// @Override
	// public void mousePressed(MouseEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void mouseReleased(MouseEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void mouseEntered(MouseEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void mouseExited(MouseEvent e) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
