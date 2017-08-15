
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

class Village_Gui extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    

    
	public Village_Gui() {
        setLayout(new GridLayout(2, 2));

        // Creates TextFields, TextAreas, and the button
        name = new JTextField();
        limit = new JTextField();
        JButton jbtGenerate = new JButton("Create Village");
        echoVillage = new JTextArea();

        // Adds TextFields, TextAreas, and the button
        add(new JLabel("ID:"));
        add(name);
        add(new JLabel("Limit:"));
        add(limit);
        add(jbtGenerate);
        jbtGenerate.addActionListener(this);
        add(echoVillage);
        echoVillage.setEditable(false);

    }

    // Top TextFields
    private JTextField name;
    private JTextField limit;

    // Bottom(echoed) TextArea
    private JTextArea echoVillage;
    private static JLabel villageLabel;
    Gui_storage gs = new Gui_storage();
    public static JLabel getVillageLabel() {
    		return villageLabel;
    }
    
    
    public void actionPerformed(ActionEvent a) {
    	    
        Village v= new Village(name.getText(), Integer.parseInt(limit.getText()));
        Gnomenwald.getGraph().addVillage(v);
        Gnomenwald.getVillageID().put(name.getText(), v);
        echoVillage.setText(v.toString());
        Point p = new Point(2,3);
		Gnomenwald.getVillage().put(Integer.parseInt(name.getText()), 0) ; 
//		Gnomenwald.getVillageID().put(name.getText(), v);
//		final ImageIcon icon = createImageIcon("/images/village.png");
//	    villageLabel = new JLabel(name.getText() +  ", Population:" + v.getAllGnomes().getLength(), icon, JLabel.CENTER);
//	    
//	    villageLabel.setVerticalTextPosition(JLabel.BOTTOM);
//        villageLabel.setHorizontalTextPosition(JLabel.CENTER);
//       
////        villageLabel.setLocation(0,0);
//        int x = (int) gs.arrayPoints[Gnomenwald.getCount2()].getX();
//        int y = (int) gs.arrayPoints[Gnomenwald.getCount2()].getY();
//        villageLabel.setBounds(520+x,200+y, 200, 220);
//        Gnomenwald.getJLabel()[Gnomenwald.getCount2()] = villageLabel;
//        
//        System.out.println( Gnomenwald.getJLabel()[Gnomenwald.getCount2()]);
		//villageLabel = Gnomenwald.getJLabel()[Integer.parseInt(name.getText())-1];
        
        Gnomenwald.getPane().add( Gnomenwald.getJLabel()[Integer.parseInt(name.getText())-1], p);
//        repaint();

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

    public static void createAndShowGUI() {
    	Village_Gui frame = new Village_Gui();
        frame.setTitle("Village Creator");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    } 
}
