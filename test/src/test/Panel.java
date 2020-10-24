package test;

import java.awt.*;  

import javax.swing.*;


public class Panel extends JPanel
{
	int x,y,xx,yy;
	int xcoord1,xcoord2,ycoord1,ycoord2,i,keepTrack;
	public Panel()
	{
		x=19;
		xx=419;
		y=150;
		yy=150;
	}
	Graphics2D g2d;
	@Override
	public void paintComponent(Graphics g)
	{
	
		super.paintComponent(g);
		g.setColor(Color.black);
		drawLines(g);
		

	}
	void drawLines(Graphics g)
	{
		xcoord1=x;
		xcoord2=xx;
		ycoord1=y;
		ycoord2=yy;

		for(i=0;i<keepTrack;i++)
		{
			
			g2d = (Graphics2D) g;
			g2d.drawLine(xcoord1,ycoord1,xcoord2,ycoord2);
			ycoord1-=30;
			ycoord2-=30;
		}
	}
	
	public void getMax(int max)
	{
		keepTrack=max;
	}
	
	public void getCoord(int x1, int x2, int y1, int y2)
	{
		x=x1;
		xx=x2;
		y=y1;
		yy=y2;
	}

}