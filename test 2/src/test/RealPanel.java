package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class RealPanel extends JPanel {
	
	Graphics2D g2d;
	int size1,size;
	/**
	 * Create the panel.
	 */
	public RealPanel() 
	{
			
	}
	
	 @Override
	 public void paintComponent(Graphics g) 
	 {
		 super.paintComponent(g);
		 size1=this.getWidth();
		 size=this.getHeight();
		 int i;
		 g2d = (Graphics2D) g;
		 g.setColor(Color.LIGHT_GRAY);
		 for(i=24;i<size1;i+=24)
		 {
			
			 g2d.drawLine(i, 0, i, size);	
		 }
		 for(i=24;i<size;i+=24)
		 {
			
			 g2d.drawLine(0, i, size1, i);	
		 }
		 g.setColor(Color.black);
		 
		 g2d.drawLine(20, 20, 44, 20);	
		
	
		 
		 
	 }
	 
	 public void takeIn(int width,int height) 
	 {
		 size1=width;
		 size=height;
	 }

}
