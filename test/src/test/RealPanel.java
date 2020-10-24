package test;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class RealPanel extends JPanel {
	
	Graphics2D g2d;
	InfoEx infoex;
	/**
	 * Create the panel.
	 */
	public RealPanel(InfoEx info) 
	{
			infoex=info;
	}
	
	 @Override
	 public void paintComponent(Graphics g) 
	 {
		 super.paintComponent(g);
		 int i;
		 g2d=(Graphics2D) g;
		 g.setColor(Color.LIGHT_GRAY);
		 for(i=24;i<489;i+=24)
		 {
			
			 g2d.drawLine(i, 0, i, 462);	
		 }
		 for(i=24;i<462;i+=24)
		 {
			
			 g2d.drawLine(0, i, 489, i);	
		 }
		 g.setColor(Color.black);
		 
		 g2d.drawLine(20, 20, 44, 20);	
		 drawLines(g);
	
		 
		 
	 }

	 
	 public void drawLines(Graphics g)
	 {
		 double[] loc= new double[2];
		 double theta,thetastep;
		 ArrayList<Double> list= new ArrayList<Double>();
		 ArrayList<Double> mustline= new ArrayList<Double>();
		 ArrayList<Double> line= new ArrayList<Double>();
		 ArrayList<Double> charge= new ArrayList<Double>();
		 ArrayList<Double> location= new ArrayList<Double>();
		 ArrayList<Double> numline= new ArrayList<Double>();
		 infoex.resetlines();
		 g2d=(Graphics2D) g;
		 int i,f,e;
		 double h,j,k,l;
		 int num;
		 f=0;
		 j=1;
		 l=0;
		 num=0;
		 list=infoex.getInfo2();
		 mustline=infoex.getInfo();
		 location=infoex.getInfo3();
		 charge.clear();
		 if(list.size()!=0)
		 {
			
		 for(i=0;i<mustline.size();i++)
		 {
			 if(list.get(2*(i))>0)
			 {
				 charge.add(1.6);
			 }
			 else
			 {
				 charge.add(-1.6);
			 }
		 }
		 num=mustline.size();
		 for(f=0;f<num;f++)
		 {
			 numline=infoex.getInfo4();
			 theta=0.0;
			 h=mustline.get(f)-numline.get(f);
			 l=0.0;
			 j=1;
			 for(e=0;e<h;e++)
			 {
				 thetastep=360.0/mustline.get(f);
				 loc[0]=(location.get(2*f)+list.get(1+(2*f))*(Math.cos(Math.toRadians(theta)))/24);
				 loc[1]=(location.get(2*f+1)+list.get(1+(2*f))*(Math.sin(Math.toRadians(theta)))/24);
				 line=infoex.getLines(loc,charge.get(f),f);
				 if(line.size()==0)
				 {
					
					 e--;
					
				 }
				 else
				 {
					
					 for(k=0;k<line.size()/4-1;k++)
					 {
						 Line2D line1 = new Line2D.Double((line.get((int)(4*k))*24),(line.get((int)(4*k+1)))*24,line.get((int)(4*k+2))*24,line.get((int)(4*k+3))*24);
						 g2d.draw(line1);
					 }
				 }
				 l++;
			
				 if(l>mustline.get(f)*j-1)
				 {
					 j++;
					 theta+=thetastep/(j);
				 }
				
				 theta+=thetastep;
			 }
		 }
	 }
	 }
	 
	

}
