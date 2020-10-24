package test;

import java.awt.Dimension;   
import java.awt.EventQueue;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;
 
public class Charges extends JButton implements MouseListener, ActionListener,MouseMotionListener,EventListener
{
	PointerInfo info;
	Point location;
	private JFrame frame1;
	private JPanel panel1;
	Dimension actualSize;
	int screenWidth,screenHeight;
	double buttonx,buttony;
	double num1,distx,disty;
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
	public Charges(JFrame frame, JPanel panel,int x,int y, int width,int height,double num, ElectricField electric,InfoEx ex) 
	{
		setForeground(new Color(204, 255, 255));
		setBackground(new Color(204, 255, 255));
		frame1=frame;
		panel1=panel;
		this.setBounds(x,y,width,height);
		addMouseListener(this);
		addMouseMotionListener(this);
		addActionListener(this);
		num1=num;
		data= new double[4];
		data[0]=1.0;
		data[2]=num1;
		data[1]=1.0;
		data[3]=2.0;
		dataloc= new double[3];
		dataloc[0]=x;
		dataloc[1]=y;
		dataloc[2]=num1;
		elec=electric;
		infoex=ex;
		infoex.getCRN(data);
		infoex.getLoc(dataloc);
		distx=this.getX()-panel1.getWidth()/2;
		disty=this.getY()-panel1.getHeight()/2;
		this.setBackground(color);
		this.setIcon(new ImageIcon(this.getClass().getResource("/positiveFixed.png")));
		frame1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				try 
				{
					charge.setBounds((int)(panel1.getWidth()/2+distx),(int)(panel1.getHeight()/2+disty),(int)(24*data[1]),(int)(24*data[1]));
			
					panel1.repaint();
				}
				catch (Exception e1)
				{
					
				}

			}
		});
		
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		  location = MouseInfo.getPointerInfo().getLocation();
		  this.setBounds((int)(-frame1.getX()-panel1.getX()+location.getX()-this.getWidth()/2), (int)(-frame1.getY()-panel1.getY()+location.getY()-23-this.getHeight()/2),(int)(24*data[1]), (int)(24*data[1]));
		  actualSize = frame1.getSize();
		  screenWidth=(int) actualSize.getWidth();
		  screenHeight=(int) actualSize.getHeight();
		  buttony=(-frame1.getY()-panel1.getY()+location.getY()-23-this.getHeight()/2);
		  buttonx=(screenWidth-(-frame1.getX()-panel1.getX()+location.getX()-this.getWidth()/2));
		  distx=this.getX()-panel1.getWidth()/2;
		  disty=this.getY()-panel1.getHeight()/2;
		  dataloc[0]=this.getX();
		  dataloc[1]=this.getY();
		  infoex.modifyloc(dataloc);		  
		}
	}

	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
			
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
			if(data[3]==1.0)
			{
				infoex.getCRN(data);
			}
			else
			{
				infoex.modify(data);
			}
			if(data[0]>0)
			{
				this.setIcon(new ImageIcon(this.getClass().getResource("/positiveFixed.png")));
				icon=new ImageIcon(this.getClass().getResource("/positiveFixed.png"));
				this.setBackground(color);
				this.setForeground(color);
			}
			else
			{
				this.setIcon(new ImageIcon(this.getClass().getResource("/negativeFixed.png")));
				icon=new ImageIcon(this.getClass().getResource("/negativeFixed.png"));
				this.setBackground(color);
				this.setForeground(color);
			}
			
			Image newImage = icon.getImage().getScaledInstance((int)(24*data[1]),(int)(24*data[1]), Image.SCALE_DEFAULT);
			icon.setImage(newImage);
			this.setIcon(icon);
			this.setBounds(this.getX(),this.getY(),(int)(24*data[1]),(int)(24*data[1]));
			this.setBackground(color);
			this.setForeground(color);
			
		}
		
	}
}
	