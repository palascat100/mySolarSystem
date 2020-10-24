package test;

import java.awt.Dimension;  

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Image;
 
public class Charges extends JButton implements MouseListener,MouseMotionListener
{
	PointerInfo info;
	Point location;
	private JFrame frame1;
	private JPanel panel1;
	Dimension actualSize;
	int screenWidth,screenHeight;
	double num1;
	int click;
	double[] data;
	double[] dataloc;
	double keepTrack;
	ElectricField elec;
	InfoEx infoex;
	ImageIcon icon= new ImageIcon(this.getClass().getResource("/negativeFixed.png"));
	Color color=getBackground();
	Charges charge=this;
	
	
	
	/**
	 * Create the application.
	 */
	public Charges(JFrame frame, JPanel panel,double num, ElectricField electric,InfoEx ex) 
	{
		setForeground(new Color(204, 255, 255));
		setBackground(new Color(204, 255, 255));
		frame1=frame;
		panel1=panel;
		this.setBounds(245, 231, 24, 24);
		addMouseListener(this);
		addMouseMotionListener(this);
		num1=num;
		data= new double[4];
		data[0]=1.0;
		data[2]=num1;
		data[1]=1.0;
		data[3]=2.0;
		dataloc= new double[3];
		dataloc[0]=245+(this.getWidth()>>1);
		dataloc[1]=231+(this.getHeight()>>1);
		dataloc[2]=num1;
		elec=electric;
		infoex=ex;
		infoex.getCRN(data);
		infoex.getLoc(dataloc);
		this.setBackground(color);
		this.setIcon(new ImageIcon(this.getClass().getResource("/positiveFixed.png")));
	}


	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		RealDialog dialog= new RealDialog(frame1,num1,this,data);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		  if(this.getX()<0 && this.getX()>screenWidth-this.getWidth()-188&&this.getY()<0&&this.getY()>screenHeight-this.getHeight()-39)
		  {
			  infoex.remove(data);
			  infoex.removeLoc(dataloc);
			  elec.setnum(true);
			  panel1.remove(this);
			  panel1.repaint();
		  }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		
		final PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		if ( pointerInfo != null ) // Mouse not present?
		{
		  screenWidth=678;
		  screenHeight=502;
		  location = MouseInfo.getPointerInfo().getLocation();
		  if(this.getX()>=0 && this.getX()<=screenWidth-this.getWidth()-188&&this.getY()>=0&&this.getY()<=screenHeight-this.getHeight()-39)
		  {
			  this.setBounds((int)(-frame1.getX()-panel1.getX()+location.getX()-(this.getWidth()>>1)), (int)(-frame1.getY()-panel1.getY()+location.getY()-23-(this.getHeight()>>1)),(int)(24*data[1]),(int)(24*data[1]));
			  dataloc[0]=this.getX()+(this.getWidth()>>1);
			  dataloc[1]=this.getY()+(this.getHeight()>>1);
			  infoex.modifyloc(dataloc);
		  }
		  else
		  {
			  infoex.remove(data);
			  infoex.removeLoc(dataloc);
			  elec.setnum(true);
			  panel1.remove(this);
			  panel1.repaint();
		  }	
		  panel1.repaint();
		  
		}
	}

	



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void getStats(double[] array)
	{
		data=array;
		
		
		if(data[0]==-1000 && data[1]==-1000)
		{
			infoex.remove(data);
			infoex.removeLoc(dataloc);
			elec.setnum(true);
			panel1.remove(this);
			panel1.repaint();
		}
		else
		{
			
			
		
			if(data[0]>0)
			{
				this.setIcon(new ImageIcon(this.getClass().getResource("/positiveFixed.png")));
				icon=new ImageIcon(this.getClass().getResource("/positiveFixed.png"));
			
			}
			else
			{
				this.setIcon(new ImageIcon(this.getClass().getResource("/negativeFixed.png")));
				icon=new ImageIcon(this.getClass().getResource("/negativeFixed.png"));
			
			}
		
			Image newImage = icon.getImage().getScaledInstance((int)(24*data[1]),(int)(24*data[1]), Image.SCALE_DEFAULT);
			icon.setImage(newImage);
			this.setIcon(icon);
			this.setBounds(this.getX(),this.getY(),(int)(24*data[1]),(int)(24*data[1]));
			infoex.modify(data);
			dataloc[0]=this.getX()+this.getWidth()/2;
			dataloc[1]=this.getY()+this.getHeight()/2;
			infoex.modifyloc(dataloc);	
			panel1.repaint();
		}
		
	}
}
	