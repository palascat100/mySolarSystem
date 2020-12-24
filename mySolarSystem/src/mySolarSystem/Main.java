package mySolarSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;


public class Main {

	private JFrame frame;
	SolarPanel panel;
	int length,height; //size of panel
	int bodiesNum;
	int determine;
	private JTextField body1mass;
	private JTextField body1posx;
	private JTextField body1posy;
	private JTextField body1velx;
	private JTextField body1vely;
	private JTextField body2mass;
	private JTextField body2posx;
	private JTextField body2posy;
	private JTextField body2velx;
	private JTextField body2vely;
	private JLabel mass;
	private JLabel posx;
	private JLabel posy;
	private JLabel velx;
	private JLabel vely;
	private JLabel position;
	private JLabel velocity;
	private JLabel numBodies;
	private JLabel initialSettings;
	private JRadioButton bodies_2;
	private JRadioButton bodies_3;
	private JRadioButton bodies_4;
	private ButtonGroup group;
	public Calculations calculations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/*
	 * The length and height variables are so I can change the length/height 
	 * and keep ratio of other objects relatively the same
	 */
	private void initialize() 
	{
		
		length=800;
		height=600;
		frame = new JFrame();
		frame.setBounds(100, 100, length, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.setResizable(false);
		frame.setVisible(true);
		
		panel= new SolarPanel(this, frame);
		calculations= new Calculations(this, panel);
		panel.getCalc(calculations);
		
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(0,0,length,height-22);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				determine=1;
				panel.getNum(determine);
				Thread t= new Thread() 
				{
					@Override
					public void run() 
					{
						panel.paintOrbits();
						try 
						{
							sleep(10);
						}
						catch(InterruptedException ex)
						{
							
						}
					}
					
				};
				t.start();
				System.out.println("Starting...");
			}
		});
		btnNewButton.setBounds(length-104, 43, 84, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Stop");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				determine=0;

			}
		});
		btnNewButton_1.setBounds(length-104, 82, 84, 29);
		panel.add(btnNewButton_1);
		
		initialSettings = new JLabel("Initial Settings:");
		initialSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		initialSettings.setForeground(Color.YELLOW);
		initialSettings.setBounds(81, 403, 145, 29);
		panel.add(initialSettings);
		
		numBodies = new JLabel("Number of bodies");
		numBodies.setForeground(Color.YELLOW);
		numBodies.setBounds(6, 444, 113, 51);
		panel.add(numBodies);
		
		
		
		bodies_2 = new JRadioButton("2");
		bodies_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(!bodies_2.isSelected())
				{
					bodiesNum=2;
				}
			}
		});
		bodies_2.setSelected(true);
		bodies_2.setForeground(Color.YELLOW);
		bodies_2.setBounds(126, 444, 47, 23);
		panel.add(bodies_2);
		bodiesNum=2;
		
		bodies_3 = new JRadioButton("3");
		bodies_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(!bodies_3.isSelected())
				{
					bodiesNum=3;
				}
			}
		});
		bodies_3.setSelected(false);
		bodies_3.setForeground(Color.YELLOW);
		bodies_3.setBounds(126, 472, 47, 23);
		panel.add(bodies_3);
		
		bodies_4 = new JRadioButton("4");
		bodies_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(!bodies_4.isSelected())
				{
					bodiesNum=4;
				}
			}
		});
		bodies_4.setSelected(false);
		bodies_4.setForeground(Color.YELLOW);
		bodies_4.setBounds(126, 503, 47, 23);
		panel.add(bodies_4);
		
		group=new ButtonGroup();
		group.add(bodies_2);
		group.add(bodies_3);
		group.add(bodies_4);
		
		body1mass = new JTextField();
		body1mass.setText("200");
		body1mass.setBackground(Color.YELLOW);
		body1mass.setBounds(240, 443, 47, 26);
		panel.add(body1mass);
		body1mass.setColumns(10);
		
		body1posx = new JTextField();
		body1posx.setColumns(10);
		body1posx.setBackground(Color.YELLOW);
		body1posx.setBounds(299, 443, 47, 26);
		panel.add(body1posx);
		
		body1posy = new JTextField();
		body1posy.setColumns(10);
		body1posy.setBackground(Color.YELLOW);
		body1posy.setBounds(359, 443, 47, 26);
		panel.add(body1posy);
		
		body1velx = new JTextField();
		body1velx.setColumns(10);
		body1velx.setBackground(Color.YELLOW);
		body1velx.setBounds(418, 443, 47, 26);
		panel.add(body1velx);
		
		body1vely = new JTextField();
		body1vely.setColumns(10);
		body1vely.setBackground(Color.YELLOW);
		body1vely.setBounds(477, 443, 47, 26);
		panel.add(body1vely);
		
		body2mass = new JTextField();
		body2mass.setText("10");
		body2mass.setColumns(10);
		body2mass.setBackground(new Color(255, 102, 255));
		body2mass.setBounds(240, 471, 47, 26);
		panel.add(body2mass);
		
		body2posx = new JTextField();
		body2posx.setColumns(10);
		body2posx.setBackground(new Color(255, 102, 255));
		body2posx.setBounds(299, 471, 47, 26);
		panel.add(body2posx);
		
		body2posy = new JTextField();
		body2posy.setColumns(10);
		body2posy.setBackground(new Color(255, 102, 255));
		body2posy.setBounds(359, 471, 47, 26);
		panel.add(body2posy);
		
		body2velx = new JTextField();
		body2velx.setColumns(10);
		body2velx.setBackground(new Color(255, 102, 255));
		body2velx.setBounds(418, 471, 47, 26);
		panel.add(body2velx);
		
		body2vely = new JTextField();
		body2vely.setColumns(10);
		body2vely.setBackground(new Color(255, 102, 255));
		body2vely.setBounds(477, 471, 47, 26);
		panel.add(body2vely);
		
		JLabel lblNewLabel_2 = new JLabel("body 1");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(173, 448, 61, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("body 2");
		lblNewLabel_2_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2_1.setBounds(173, 471, 61, 16);
		panel.add(lblNewLabel_2_1);
		
		mass = new JLabel("mass");
		mass.setForeground(Color.LIGHT_GRAY);
		mass.setBounds(244, 422, 61, 16);
		panel.add(mass);
		
		posx = new JLabel("x");
		posx.setForeground(Color.LIGHT_GRAY);
		posx.setBounds(317, 422, 13, 16);
		panel.add(posx);
		
		posy = new JLabel("y");
		posy.setForeground(Color.LIGHT_GRAY);
		posy.setBounds(378, 422, 13, 16);
		panel.add(posy);
		
		velx = new JLabel("x");
		velx.setForeground(Color.LIGHT_GRAY);
		velx.setBounds(435, 422, 13, 16);
		panel.add(velx);
		
		vely = new JLabel("y");
		vely.setForeground(Color.LIGHT_GRAY);
		vely.setBounds(494, 422, 13, 16);
		panel.add(vely);
		
		position = new JLabel("Position");
		position.setForeground(Color.LIGHT_GRAY);
		position.setBounds(317, 394, 61, 16);
		panel.add(position);
		
		velocity = new JLabel("Velocity");
		velocity.setForeground(Color.LIGHT_GRAY);
		velocity.setBounds(433, 394, 61, 16);
		panel.add(velocity);
		
		//JCheckBox chckbxNewCheckBox = new JCheckBox("Ruler");
		//chckbxNewCheckBox.addItemListener(new ItemListener() {
		//	public void itemStateChanged(ItemEvent e) 
		//	{
				
				
		//	}
		//});
		//chckbxNewCheckBox.setForeground(Color.WHITE);
		//chckbxNewCheckBox.setBounds(346, 124, 84, 23);
		//panel.add(chckbxNewCheckBox);
		panel.setVisible(true);
		
	}
	
	public int getNumBodies()
	{
		return bodiesNum;
	}
	
	public void checkNum()
	{
		if(determine==1)
		{
			panel.paintOrbits();
		}
	}
	}



