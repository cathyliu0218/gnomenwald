import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Road_Gui extends JFrame implements ActionListener {

	public Road_Gui() {
		setLayout(new GridLayout(2, 3));

		// Creates TextFields, TextAreas, and the button
		villagefrom = new JTextField();
		villageto = new JTextField();
		toll = new JTextField();
		limit = new JTextField();
		JButton jbtGenerate = new JButton("Create Road");
		echoRoad = new JTextArea();

		// Adds TextFields, TextAreas, and the button
		add(new JLabel("ID of Village From:"));
		add(villagefrom);
		add(new JLabel("ID of Village To:"));
		add(villageto);
		add(new JLabel("Toll:"));
		add(toll);
		add(new JLabel("Limit:"));
		add(limit);
		add(jbtGenerate);
		jbtGenerate.addActionListener(this);
		add(echoRoad);
		echoRoad.setEditable(false);

	}

	// Top TextFields
	private JTextField villageto;
	private JTextField villagefrom;
	private JTextField toll;
	private JTextField limit;
	private JTextArea echoRoad;
	private static JLabel roadLabel;
	
	int x1, x2, y1, y2;

	public void actionPerformed(ActionEvent a) {
		Road g = new Road(Gnomenwald.getVillageID().get(villagefrom.getText()),
				Gnomenwald.getVillageID().get(villageto.getText()), Integer.parseInt(toll.getText()),
				Integer.parseInt(limit.getText()));

		echoRoad.setText(g.toString());
		int f= Integer.parseInt(villagefrom.getText()); 
		int t = Integer.parseInt(villageto.getText()); 
		this.addRoads(f, t);
		Gnomenwald.getGraph().addRoad(Gnomenwald.getVillageID().get(villagefrom.getText()),
				Gnomenwald.getVillageID().get(villageto.getText()), Integer.parseInt(toll.getText()), Integer.parseInt(limit.getText()));
//		repaint();

	}
	public static void addRoads(int f, int t) {
		Gui_storage gs = new Gui_storage();
		int x1 = (int) gs.arrayPoints[f-1].getX();
		int y1 = (int) gs.arrayPoints[f-1].getY();
		int x2 = (int) gs.arrayPoints[t-1].getX();
		int y2 = (int) gs.arrayPoints[t-1].getY();

		Graphics graphic = Gnomenwald.getPane().getGraphics(); 
		graphic.setColor(Color.BLACK);
		graphic.drawLine(580+x1,300+y1,580+x2,300+y2);
		graphic.setColor(Color.cyan);
		graphic.drawOval(580+x2,300+y2, 10, 10);
		
	}

	public static void createAndShowGUI() {
		Road_Gui frame = new Road_Gui();
		frame.setTitle("Road Creator");
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}