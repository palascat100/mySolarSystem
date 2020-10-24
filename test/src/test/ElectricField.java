package test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
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
	int trackLines=4,maxcharge;
	int keepTrack=1;
	ElectricField elec=this;
	InfoEx infoex=new InfoEx(elec);
	JButton negative;
	JButton positive;
	JLabel lblNewLabel,lblNewLabel_1,lblNewLabel_2,lblNewLabel_3;
	RealPanel panel=new RealPanel(infoex);
	
	
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
	public ElectricField() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		infoex.getNumLines(trackLines);
		infoex.getNum(keepTrack-1);
		frame = new JFrame();
		frame.setBounds(0, 0, 678, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
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
						if(trackLines>maxcharge)
						{
							trackLines=maxcharge;
							numLine.setText(Integer.toString(trackLines));
						}
					}
					else
					{
						trackLines=1;
						numLine.setText(Integer.toString(trackLines));
	
					}
					infoex.getNumLines(trackLines);
					panel.repaint();
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
					if(trackLines>maxcharge)
					{
						trackLines=maxcharge;
						numLine.setText(Integer.toString(trackLines));
					}
					infoex.getNumLines(trackLines);
					panel.repaint();
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
					infoex.getNumLines(trackLines);
					panel.repaint();
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
				Charges charge= new Charges(frame,panel,keepTrack,elec,infoex);
				panel.add(charge);
				charge.setBorderPainted(false);
				charge.setContentAreaFilled(false);
				charge.setFocusPainted(false);
				charge.setOpaque(false);
				panel.repaint();
				infoex.getNumLines(trackLines);
				keepTrack+=1;
				infoex.getNum(keepTrack-1);
				panel.repaint();
				calcmax();
			}
		});
		addcharge.setBounds(511, 151, 142, 29);
		frame.getContentPane().add(addcharge);
		
		//labels to show what the buttons do
		labellines = new JLabel("Number of lines ");
		labellines.setHorizontalAlignment(SwingConstants.CENTER);
		labellines.setBounds(511, 23, 142, 15);
		frame.getContentPane().add(labellines);
		
		labellines2 = new JLabel("per nc");
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
		
		
	
		Image img2= new ImageIcon(this.getClass().getResource("/negativeFixed.png")).getImage();
		ImageIcon icon= new ImageIcon(img2);
		negative = new JButton("");
		negative.setIcon(icon);
		negative.setBounds(561, 252, 48, 48);
		frame.getContentPane().add(negative);
		
		Image img1= new ImageIcon(this.getClass().getResource("/positiveFixed.png")).getImage();
		ImageIcon icon1= new ImageIcon(img1);
		positive = new JButton("");
		positive.setIcon(icon1);
		positive.setBounds(561, 356, 48, 48);
		frame.getContentPane().add(positive);
		
		lblNewLabel = new JLabel("What a negative charge");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(507, 312, 165, 16);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("What a positive charge");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(507, 416, 165, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(" looks like");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(517, 330, 142, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("looks like");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(517, 434, 142, 16);
		frame.getContentPane().add(lblNewLabel_3);

		panel.setVisible(true);
		panel.repaint();
		calcmax();
		
	}
	
	public void setnum(boolean subtract)
	{
		if(subtract==true)
		{
			keepTrack-=1;
			infoex.getNum(keepTrack-1);
			
		}
	}
	
	public void calcmax()
	{
		double track=infoex.returnmax();
		if(track!=0 && keepTrack!=1)
		{
			maxcharge=(int)(Math.round(32/(track*(keepTrack-1))));
			if(maxcharge>10)
			{
				maxcharge=10;
			}
		}
		else
		{
			maxcharge=10;
		}
		trackLines=Integer.parseInt(numLine.getText());
		if(trackLines<1)
		{
			trackLines=1;
			numLine.setText(Integer.toString(trackLines));
		}
		if(trackLines>maxcharge)
		{
			trackLines=maxcharge;
			numLine.setText(Integer.toString(trackLines));
		}
	}
	
}
