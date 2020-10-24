package test;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class SwingPainDemo {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
				
			}
		
		});
	}
	
	 private static void createAndShowGUI() {
	        System.out.println("Created GUI on EDT? "+
	        SwingUtilities.isEventDispatchThread());
	        JFrame f = new JFrame("Swing Paint Demo");
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        test.MyPanel myPanel = new MyPanel();
	        f.getContentPane().add(myPanel, BorderLayout.CENTER);
	        myPanel.setLayout(new GridLayout(1, 0, 0, 0));
	        f.pack();
	        f.setVisible(true);
	    }

}
class MyPanel extends JPanel {

    public MyPanel() 
    {
        setBorder(BorderFactory.createLineBorder(Color.black));
      

      
    }
    
   

    public Dimension getPreferredSize() {
        return new Dimension(400,350);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

    }  
}
