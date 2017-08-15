import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Road_Delete extends JFrame implements ActionListener {

	public Road_Delete() {
		setLayout(new GridLayout(2, 3));

		// Creates TextFields, TextAreas, and the button
		villagefrom = new JTextField();
		villageto = new JTextField();
		JButton jbtGenerate = new JButton("Delete Road");
		echoRoad = new JTextArea();

		// Adds TextFields, TextAreas, and the button
		add(new JLabel("ID of Village From:"));
		add(villagefrom);
		add(new JLabel("ID of Village To:"));
		add(villageto);
		add(jbtGenerate);
		jbtGenerate.addActionListener(this);
		add(echoRoad);
		echoRoad.setEditable(false);

	}

	// Top TextFields
	private JTextField villageto;
	private JTextField villagefrom;
	// Bottom(echoed) TextArea
	private JTextArea echoRoad;
	

	public void actionPerformed(ActionEvent a) {
		int f= Integer.parseInt(villagefrom.getText()); 
		int t = Integer.parseInt(villageto.getText()); 
		this.deleteRoads(f, t);
//		int x1 = (int) gs.arrayPoints[f-1].getX();
//		int y1 = (int) gs.arrayPoints[f-1].getY();
//		int x2 = (int) gs.arrayPoints[t-1].getX();
//		int y2 = (int) gs.arrayPoints[t-1].getY();
//		Graphics graphic = Gnomenwald.getPane().getGraphics(); 
//		graphic.setColor(Color.WHITE);
//		graphic.drawLine(580+x1,300+y1,580+x2,300+y2);
//		graphic.setColor(Color.WHITE);
//		graphic.drawOval(580+x2,290+y2, 20, 20);
	}

	public static void createAndShowGUI() {
		Road_Delete frame = new Road_Delete();
		frame.setTitle("Road Deleter");
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void deleteRoads(int f, int t) {
		Gui_storage gs = new Gui_storage();
		//int f= Integer.parseInt(villagefrom.getText()); 
		//int t = Integer.parseInt(villageto.getText()); 
		int x1 = (int) gs.arrayPoints[f-1].getX();
		int y1 = (int) gs.arrayPoints[f-1].getY();
		int x2 = (int) gs.arrayPoints[t-1].getX();
		int y2 = (int) gs.arrayPoints[t-1].getY();
		Graphics graphic = Gnomenwald.getPane().getGraphics(); 
		graphic.setColor(Color.WHITE);
		graphic.drawLine(580+x1,300+y1,580+x2,300+y2);
		graphic.setColor(Color.WHITE);
		graphic.drawOval(580+x2,300+y2, 10, 10);
		
	}
}
