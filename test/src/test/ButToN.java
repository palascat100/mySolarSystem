package test;

import java.awt.Dimension;   

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;
 
public class ButToN extends JButton implements MouseListener, ActionListener,MouseMotionListener,EventListener
{
	PointerInfo info;
	Point location;
	private JFrame frame1;
	private JPanel panel1;
	Dimension actualSize;
	int screenWidth,screenHeight;
	double buttonx,buttony;
	double num1;
	int click;
	double[] data;
	double keepTrack;
	BeginningTestingI begin;
	InfoExchange infoex;
	
	/**
	 * Create the application.
	 */
	public ButToN(JFrame frame, JPanel panel,int x,int y, int width,int height,double num, BeginningTestingI test,InfoExchange ex) 
	{
		frame1=frame;
		panel1=panel;
		this.setBounds(x,y,width,height);
		addMouseListener(this);
		addMouseMotionListener(this);
		addActionListener(this);
		num1=num;
		data= new double[3];
		data[0]=1.0;
		data[1]=1.0;
		begin=test;
		infoex=ex;
	
		
	}


	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
	
		//Dialog dialog= new Dialog(frame1,num1,this,data);
		
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
		  this.setBounds((int)(-frame1.getX()-panel1.getX()+location.getX()-this.getWidth()/2), (int)(-frame1.getY()-panel1.getY()+location.getY()-23-this.getHeight()/2), 75, 29);
		  actualSize = frame1.getSize();
		  screenWidth=(int) actualSize.getWidth();
		  screenHeight=(int) actualSize.getHeight();
		  buttony=(-frame1.getY()-panel1.getY()+location.getY()-23-this.getHeight()/2);
		  buttonx=(screenWidth-(-frame1.getX()-panel1.getX()+location.getX()-this.getWidth()/2));
		 
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
	
	//public void getStats(double[] array)
	//{
	//	data=array;
	//	System.out.println(data[0]);
	//	System.out.println(data[1]);
	//	System.out.println(data[2]);
	//	if(data[0]==-1000 && data[1]==-1000)
	//	{
	//		infoex.remove(data);
	//		begin.setnum(true);
	//		panel1.remove(this);
	//		panel1.repaint();
	//	}
	//	else
	//	{
	//		infoex.getCRN(data);
	//	}
	//	
	//}
	

	
	


	
	

	

}
