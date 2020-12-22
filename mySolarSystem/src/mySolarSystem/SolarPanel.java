package mySolarSystem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class SolarPanel extends JPanel {
	//initializes all necessary variables
	Graphics2D g2d;
	int determine;
	int i=0;
	int keeptrack=1;
	int panelWidth, panelHeight;
	JFrame frame;
	/**
	 * Create the panel.
	 */
	public SolarPanel(JFrame frame1) 
	{
		//makes background blue
		setBackground(new Color(0, 0, 128));
		setForeground(new Color(0, 0, 128));
		frame=frame1;
	}
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		g2d=(Graphics2D) g;
		int i;
		 g.setColor(Color.DARK_GRAY);
		 //I want to make sure that the screen is always 800 km by 600 km (each box is 100 km by 100 km)
		 panelWidth=this.getWidth();
		 panelHeight=this.getHeight();
		 for(i=panelWidth/8;i<frame.getWidth();i+=panelWidth/8)
		 {
			
			 g2d.drawLine(i, 0, i, frame.getHeight());	
		 }
		 for(i=panelHeight/6;i<frame.getHeight();i+=panelHeight/6)
		 {
			
			 g2d.drawLine(0, i, frame.getWidth(), i);	
		 }
		 
	}

}
