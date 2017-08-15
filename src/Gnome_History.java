import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gnome_History extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;

		static JFrame frame;

		public Gnome_History() {
			setLayout(new GridLayout(2, 3));

			// Creates TextFields, TextAreas, and the button
			name = new JTextField();
			JButton jbtGenerate = new JButton("Search History");

			add(new JLabel("Name:"));
			add(name);
			
			add(jbtGenerate);
			jbtGenerate.addActionListener(this);
		}

		// Top TextFields
		private JTextField name;



		public void actionPerformed(ActionEvent a) {
			String history =Gnomenwald.getGraph().searchGnome(name.getText()).getVisitedVillages().toString();
			JLabel label = new JLabel("History");
			label.setText("History of Villages Visited: " + history);
			add(label);
		}
		
		public static void createAndShowGUI() {
			Gnome_History f = new Gnome_History();
			f.setTitle("Search Gnome History");
			f.setSize(500, 500);
			f.setLocationRelativeTo(null);
			f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f.pack();
			f.setVisible(true);
		}
	}