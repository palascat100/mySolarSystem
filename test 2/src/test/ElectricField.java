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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ElectricField 
{
	/*
	 * Declares all needed global variables
	 */
	private JFrame frame;
	private JTextField numLine;
	JButton addLine;
	JButton subLine;
	JButton addcharge;
	JLabel labellines;
	JLabel labellines2;
	RealPanel panel=new RealPanel();
	int trackLines=4,screenWidth,screenHeight;
	Dimension actualSize;
	InfoEx infoex=new InfoEx();
	double keepTrack=1;
	ElectricField elec=this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElectricField window = new ElectricField();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ElectricField() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
	
		frame = new JFrame();
		frame.setBounds(0, 0, 678, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				try {
					actualSize = frame.getSize();
					screenWidth=(int) actualSize.getWidth();
					screenHeight=(int) actualSize.getHeight();
					addLine.setBounds((int)(screenWidth-167), (int)70, 20, 20);
					subLine.setBounds(screenWidth-45, 70, 20, 20);
					numLine.setBounds(screenWidth-135, 70, 78, 20);
					panel.setBounds(10, 10, screenWidth-189, screenHeight-40);
					labellines.setBounds(screenWidth-167,23,142,15);
					labellines2.setBounds(screenWidth-167,40,142,15);
					addcharge.setBounds(screenWidth-167,151,142,29);
					frame.repaint();
				}
				catch (Exception e1)
				{
					
				}

			}
		});
		
		addLine = new JButton("+");
		//when user wants more lines
		addLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					trackLines+=1;
					if(trackLines>=1)
					{
						
						numLine.setText(Integer.toString(trackLines));
					}
					else
					{
						trackLines=1;
						numLine.setText(Integer.toString(trackLines));
					}
				}
				catch (Exception e1)
				{
					
				}
			}
		});
		addLine.setBounds(511, 70, 20, 20);
		frame.getContentPane().add(addLine);
		
		//how user sees num of charges or can put in their own
		numLine = new JTextField();
		numLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					trackLines=Integer.parseInt(numLine.getText());
					if(trackLines<1)
					{
						trackLines=1;
						numLine.setText(Integer.toString(trackLines));
					}
				}
				catch (Exception e1)
				{
					
				}
			}
		});
		numLine.setBounds(543, 70, 78, 20);
		numLine.setText(Integer.toString(trackLines));
		frame.getContentPane().add(numLine);
		numLine.setColumns(10);
		
		//when user wants to subtract lines
		subLine = new JButton("-");
		subLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					trackLines-=1;
					if(trackLines>=1)
					{
						numLine.setText(Integer.toString(trackLines));
					}
					else
					{
						trackLines=1;
						numLine.setText(Integer.toString(trackLines));
					}
				}
				catch (Exception e1)
				{
					
				}
			}
		});
		subLine.setBounds(633, 70, 20, 20);
		frame.getContentPane().add(subLine);
		
		//when user wants to add charges
		addcharge = new JButton("Add Charge");
		addcharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Charges charge= new Charges(frame,panel,(panel.getWidth()/2), (panel.getHeight()/2), 24, 24,keepTrack,elec,infoex);
				charge.setBorderPainted(false);
				charge.setContentAreaFilled(false);
				charge.setFocusPainted(false);
				charge.setOpaque(false);
				panel.add(charge);
				keepTrack+=1;
				panel.repaint();
			}
		});
		addcharge.setBounds(511, 151, 142, 29);
		frame.getContentPane().add(addcharge);
		
		//labels to show what the buttons do
		labellines = new JLabel("Number of lines ");
		labellines.setHorizontalAlignment(SwingConstants.CENTER);
		labellines.setBounds(511, 23, 142, 15);
		frame.getContentPane().add(labellines);
		
		labellines2 = new JLabel("per e charge");
		labellines2.setHorizontalAlignment(SwingConstants.CENTER);
		labellines2.setBounds(511, 40, 142, 15);
		frame.getContentPane().add(labellines2);
		
		//panel that displays charges and electric field
		frame.getContentPane().add(panel);
		panel.setBounds(10, 10, 489, 462);
		panel.setBackground(new Color(204, 255, 255));
		panel.setLayout(null);
		
		JLabel scale = new JLabel("1 nm= 1 box");
		scale.setHorizontalAlignment(SwingConstants.CENTER);
		scale.setBounds(6, 31, 82, 16);
		panel.add(scale);
		panel.setVisible(true);
		panel.repaint();
		
	}
	
	public void setnum(boolean subtract)
	{
		if(subtract==true)
		{
			keepTrack-=1;
		}
	}
}
