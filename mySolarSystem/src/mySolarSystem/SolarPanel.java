package mySolarSystem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class SolarPanel extends JPanel {
	//initializes all necessary variables
	Graphics2D g2d;
	int determine;
	int num=0;
	int keeptrack=1;
	int panelWidth, panelHeight;
	JFrame frame;
	Calculations calc;
	int numBodies;
	Main m;
	static ArrayList<Double> position= new ArrayList<Double>();
	
	/**
	 * Create the panel.
	 */
	public SolarPanel(Main main, JFrame frame1) 
	{
		//makes background blue
		setBackground(new Color(0, 0, 128));
		setForeground(new Color(0, 0, 128));
		frame=frame1;
		m=main;

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
	
	public void paintOrbits()
	{
		calc.calc();
		position.addAll(calc.getPos());
		numBodies=m.getNumBodies();
		
		Graphics g1=this.getGraphics();
		if(determine==1)
		{
				
				int n;
				g2d=(Graphics2D) g1;
				for(n=0;n<numBodies;n++)
				{
					getColor(n,g2d);
					g2d.drawLine((int)Math.floor(position.get((num+n)*2))+400, (int)Math.floor(position.get((num+n)*2+1))+200, (int)Math.floor(position.get((num+n)*2+numBodies*2))+400, (int)Math.floor(position.get((num+n)*2+numBodies*2+1))+200);
				}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			num+=numBodies;
			m.checkNum();
		}
	}
	
	private void getColor(int i, Graphics2D g2d)
	{
		if(i==0)
		{
			g2d.setColor(Color.YELLOW);
		}
		if(i==1)
		{
			g2d.setColor(Color.MAGENTA);
		}
		if(i==2)
		{
			g2d.setColor(Color.CYAN);
		}
		if(i==3)
		{
			g2d.setColor(Color.GREEN);
		}
	}
	
	public void getNum(int num)
	{
		determine=num;
	}
	
	public void getCalc(Calculations calculations)
	{
		calc=calculations;
		position.addAll(calc.getInit());
	}
	

}
