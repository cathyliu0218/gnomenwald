import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Village_Delete extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Village_Delete() {
		setLayout(new GridLayout(2, 3));

		// Creates TextFields, TextAreas, and the button
		name = new JTextField();
		JButton jbtGenerate2 = new JButton("Delete Village");
		echoVillaged = new JTextArea();

		// Adds TextFields, TextAreas, and the button
		add(new JLabel("ID of Village to Remove:"));
		add(name);
		add(jbtGenerate2);
		jbtGenerate2.addActionListener(this);
		add(echoVillaged);
		echoVillaged.setEditable(false);

	}

	// Top TextFields
	private JTextField name;

	// Bottom(echoed) TextArea
	private JTextArea echoVillaged;
	private static JLabel villageLabel;
	Gui_storage gs = new Gui_storage();

	public void actionPerformed(ActionEvent a) {
//		Point p = new Point(2,3);
//		final ImageIcon icon = createImageIcon("/images/red.png");
//		villageLabel = new JLabel(icon); 
//		
		int f= Integer.parseInt(name.getText()); 
//		Gnomenwald.getPane().add(villageLabel, p);
//        int x = (int) gs.arrayPoints[f].getX();
//        int y = (int) gs.arrayPoints[f].getY();
//        
//        villageLabel.setBounds(520+x,200+y, 200, 220);
//		System.out.println(Village_Gui.getVillageLabel()[0]);
		Gnomenwald.getJLabel()[f-1].setVisible(false);

			for(int i=0;i<Gnomenwald.getVillageID().get(name.getText()).getAllRoads().getLength();i++) {
				String vill =Gnomenwald.getVillageID().get(name.getText()).getOut_AdjVillages().get(i).getData().getName();
				Road_Delete.deleteRoads(f, Integer.parseInt(vill));
			}
			
			for(int i=0;i<Gnomenwald.getVillageID().get(name.getText()).getAllRoads().getLength();i++) {
				String vill =Gnomenwald.getVillageID().get(name.getText()).getIn_AdjVillages().get(i).getData().getName();
				Road_Delete.deleteRoads(Integer.parseInt(vill),f);
			}

		try {
			Gnomenwald.getGraph().deleteVillage(Gnomenwald.getVillageID().get(name.getText()),true);
		} catch (NotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Gnomenwald.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	public static void createAndShowGUI2() {
		Village_Delete frame = new Village_Delete();
		frame.setTitle("Village Deletor");
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}