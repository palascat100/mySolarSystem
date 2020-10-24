package test;

import java.awt.Color;       
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.MouseInfo;


public class BeginningTestingI extends JFrame 
{
	 
	PointerInfo info;
	Point location;
	private JFrame frame = new JFrame();
	private JTextField text;
	private int keepTrack=2,screenHeight,screenWidth;
	private double buttony,buttonx;
	private String string= new String("");
	Graphics2D g2d;
	Panel panel= new Panel();
	Dimension actualSize;
	JButton add;
	JButton subtract;
	double keepTrack2;
	BeginningTestingI test=this;
	InfoExchange infoex=new InfoExchange();

	
	

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() 
			{
				try {
					BeginningTestingI window = new BeginningTestingI();
					window.frame.setVisible(true);
					window.panel.setVisible(true);
					window.frame.setLocationRelativeTo(null);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BeginningTestingI()
	{
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		keepTrack2=1;
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				actualSize = frame.getSize();
				screenWidth=(int) actualSize.getWidth();
				screenHeight=(int) actualSize.getHeight();
				add.setBounds((int)(screenWidth-buttonx), (int)buttony, 75, 29);
				subtract.setBounds(screenWidth-94, 6, 75, 29);
				text.setBounds(screenWidth-236, 6, 130, 26);
				panel.setBounds(19, 50, screenWidth-50, screenHeight-100);
				panel.getCoord(19, screenWidth-31, screenHeight-120, screenHeight-120);

			}
		});
		
		
		
		
		//sets frame size 
		frame.setBounds(0, 0, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		actualSize = frame.getSize();
		screenWidth=(int) actualSize.getWidth();
		screenHeight=(int) actualSize.getHeight();
		buttonx=331;
		buttony=6;
		
		
		
		
		//button that adds icons
		JButton addButton = new JButton("add");
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				ButToN button= new ButToN(frame,panel,0, 0, 75, 29,keepTrack2,test,infoex);
				panel.add(button);
				keepTrack2+=1;
				panel.repaint();
			}
		});
		addButton.setBounds(19, 6, 75, 29);
		frame.getContentPane().add(addButton);
		
		
		//add button that controls number of lines
		add = new JButton("");
		add.setBounds(119, 6, 75, 29);
		add.addMouseListener(new MouseAdapter() 
		{
			
		});
		add.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
		    public void mouseDragged(MouseEvent e) 
			{
				final PointerInfo pointerInfo = MouseInfo.getPointerInfo();
			
				if ( pointerInfo != null ) // Mouse not present?
				{
				  //location = MouseInfo.getPointerInfo().getLocation();
				  //add.setBounds((int)(-frame.getX()+location.getX()-add.getWidth()/2), (int)(-frame.getY()+location.getY()-23-add.getHeight()/2), 75, 29);
				  //actualSize = frame.getSize();
				  //screenWidth=(int) actualSize.getWidth();
				  //screenHeight=(int) actualSize.getHeight();
				 // buttony=(-frame.getY()+location.getY()-23-add.getHeight()/2);
				  //buttonx=(screenWidth-(-frame.getX()+location.getX()-add.getWidth()/2));  
				
				  
				} 				
			}
		});
		add.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
				keepTrack+=1;
				string=Integer.toString(keepTrack);
				text.setText(string);
				panel.getMax(keepTrack);
				panel.repaint();
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Error");
				
				
			}
		}});
		
		
		frame.getContentPane().add(add);
		add.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		
		//subtract button that decreases number of lines
		subtract = new JButton("");
		subtract.setBounds(356, 6, 75, 29);
		subtract.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
				keepTrack-=1;
				string=Integer.toString(keepTrack);
				text.setText(string);
				panel.getMax(keepTrack);
				panel.repaint();
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		frame.getContentPane().add(subtract);
		
		text = new JTextField();
		text.setBounds(214, 6, 130, 26);
		text.setText(Integer.toString(keepTrack));
		text.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					keepTrack=Integer.parseInt(text.getText());
					string=text.getText();
					panel.getMax(keepTrack);
					panel.repaint();
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		frame.getContentPane().add(text);
		text.setColumns(10);
		Image img2= new ImageIcon(this.getClass().getResource("/subtract.png")).getImage();
		subtract.setIcon(new ImageIcon(img2));
		panel.setBounds(19, 50, 400, 200);
		panel.getMax(keepTrack);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		panel.setVisible(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel);
		
		
       }
	
	public void setnum(boolean subtract)
	{
		if(subtract==true)
		{
			keepTrack2-=1;
		}
	}
}



	
