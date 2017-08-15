import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Gnome_Gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	static JFrame frame;

	public Gnome_Gui(JFrame frame) {
		Gnome_Gui.frame = frame;
		setLayout(new GridLayout(2, 3));

		// Creates TextFields, TextAreas, and the button
		name = new JTextField();
		VIP = new JTextField();
		color = new JTextField();
		home = new JTextField();
		JButton jbtGenerate = new JButton("Create Gnome");
		echoGnome = new JTextArea();
		// add(layeredPane);
		// Adds TextFields, TextAreas, and the button
		add(new JLabel("Name:"));
		add(name);
		add(new JLabel("VIP:"));
		add(VIP);
		add(new JLabel("Color:"));
		add(color);
		add(new JLabel("ID of Home Village:")); 
		add(home);
		add(jbtGenerate);
		jbtGenerate.addActionListener(this);
		add(echoGnome);
		echoGnome.setEditable(false);
	}

	// Top TextFields
	private JTextField name;
	private JTextField color;
	private JTextField VIP;
	private JTextField home; 

	// Bottom(echoed) TextArea
	private JTextArea echoGnome;
	private static JLabel gnomeLabel;
	Gui_storage gs = new Gui_storage();

	public void actionPerformed(ActionEvent a) {
		Point p = new Point(2, 3);
		Gnome g = new Gnome(name.getText(), Integer.parseInt(VIP.getText()), color.getText());
		echoGnome.setText(g.toString());
		Gnomenwald.getGraph().addGnome(g);
	
		//System.out.println(Gnomenwald.getGraph().searchGnome(g.getGnomeName()));

		g.setCurrentVillage(Gnomenwald.getVillageID().get(home.getText()));
		//Gnomenwald.getGraph().searchGnome(name.getText()).setCurrentVillage(Gnomenwald.getVillageID().get(home.getText()));
		//Gnomenwald.getGnomes().put(g, Gnomenwald.getVillageID().get(home) );

		// Create and add the gnome label to the layered pane.
		// create gnome icon
		final ImageIcon iconyellow = createImageIcon("/images/gnomeyellow.png");
		final ImageIcon iconblue = createImageIcon("/images/gnomeblue.png");
		final ImageIcon iconred = createImageIcon("/images/gnomered.png");
		final ImageIcon icongreen = createImageIcon("/images/gnomegreen.png");

		if (color.getText().equals("Yellow"))
			gnomeLabel = new JLabel(name.getText(), iconyellow, JLabel.CENTER);
		     Gnomenwald.getGnomeLabels().put(name.getText(), gnomeLabel);
		if (color.getText().equals("Blue"))
			gnomeLabel = new JLabel(name.getText(), iconblue, JLabel.CENTER);
		Gnomenwald.getGnomeLabels().put(name.getText(), gnomeLabel);
		if (color.getText().equals("Green"))
			gnomeLabel = new JLabel(name.getText(), icongreen, JLabel.CENTER);
		Gnomenwald.getGnomeLabels().put(name.getText(), gnomeLabel);
		if (color.getText().equals("Red"))
			gnomeLabel = new JLabel(name.getText(), iconred, JLabel.CENTER);
		Gnomenwald.getGnomeLabels().put(name.getText(), gnomeLabel);
		
     int x = (int) gs.arrayPoints[Integer.parseInt(home.getText())-1].getX();
     int y = (int) gs.arrayPoints[Integer.parseInt(home.getText())-1].getY();
		Gnomenwald.getPane().add(gnomeLabel, p);
		gnomeLabel.setVerticalTextPosition(JLabel.BOTTOM);
		gnomeLabel.setHorizontalTextPosition(JLabel.CENTER);
		gnomeLabel.setBounds(510+x, 200+y, 50, 50);
		// add(gnomeLabel, p);
		int v = Integer.parseInt(home.getText());
	int pop = Gnomenwald.getVillage().get(v)+1;
	Gnomenwald.getJLabel()[v-1].setText(home.getText() + ", Population: "+pop);
	Gnomenwald.getVillage().put(v, pop);
	//Gnomenwald.getVillageID().put(home.getText(), value)
	
	
	revalidate(); 

//		repaint();
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

	public static void createAndShowGUI() {
		Gnome_Gui f = new Gnome_Gui(frame);
		f.setTitle("Gnome Creator");
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
}