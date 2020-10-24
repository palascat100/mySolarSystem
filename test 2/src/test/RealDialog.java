package test;

import java.awt.Dimension;  
import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Point;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class RealDialog extends JDialog implements ActionListener
{
	private double [] data;
	private JTextField chargeText;
	private JTextField sizeText;
	JLabel chargeLabel;
	JLabel sizeLabel;
	JButton addCharge;
	JButton subCharge;
	JButton subRadius;
	JButton addRadius;
	JButton ok;
	JButton no;
	JButton beGONE;
	JDialog dialog= new JDialog();
	
	
	public RealDialog(JFrame parent,double num, Charges charge, double[] initial) 
	{
		
	    JPanel panel = new JPanel();
	    dialog.getContentPane().add(panel);
	    dialog.setVisible(true);
	    dialog.setLocationRelativeTo(null);
	    panel.setLayout(null);
	    dialog.setSize(450,300);
	    panel.setVisible(true);
		data=new double[4];
		data[0]=initial[0];
		data[1]=initial[1];
		data[2]=num;
		data[3]=initial[3];
		chargeLabel = new JLabel("Charge(nC)");
		chargeLabel.setBounds(6, 39, 84, 16);
		panel.add(chargeLabel);
		
		chargeText = new JTextField();
		chargeText.setBounds(134, 36, 249, 21);
		chargeText.setText(Double.toString(data[0]));
		panel.add(chargeText);
		chargeText.setColumns(10);
		
		sizeLabel = new JLabel("Radius(nm)");
		sizeLabel.setBounds(6, 131, 84, 16);
		panel.add(sizeLabel);
		
		sizeText = new JTextField();
		sizeText.setBounds(134, 128, 249, 21);
		sizeText.setText(Double.toString(data[1]));
		panel.add(sizeText);
		sizeText.setColumns(10);
		
		addCharge = new JButton("+");
		addCharge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(data[0]==-1)
				{
					data[0]+=2;
				}
				else
				{
					data[0]+=1;
				}
				chargeText.setText(Double.toString(data[0]));
			}
		});
		addCharge.setBounds(134, 69, 117, 29);
		panel.add(addCharge);
		
		subCharge = new JButton("-");
		subCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(data[0]==1)
				{
					data[0]-=2;
				}
				else
				{
					data[0]-=1;
				}
				chargeText.setText(Double.toString(data[0]));
			}
		});
		
		subCharge.setBounds(266, 69, 117, 29);
		panel.add(subCharge);
		
		subRadius = new JButton("-");
		subRadius.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					if(data[1]==1) 
					{
						
					}
					else
					{
						data[1]-=1;
					}
					sizeText.setText(Double.toString(data[1]));
				}
				catch (Exception e1)
				{
					
				}
			}
		});
		subRadius.setBounds(266, 161, 117, 29);
		panel.add(subRadius);
		
		addRadius = new JButton("+");
		addRadius.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					data[1]+=1;
					sizeText.setText(Double.toString(data[1]));
				}
				catch (Exception e1)
				{
					
				}
			}
		});
		addRadius.setBounds(134, 161, 117, 29);
		panel.add(addRadius);
		
	    ok = new JButton("Ok");
	    ok.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    	
	    		 data[0] = Double.parseDouble(chargeText.getText());
		         data[1] = Double.parseDouble(sizeText.getText());
		         charge.getStats(data);
		         data[3]+=1.0;
		         dispose();
		         dialog.dispose();
		       
		         
	    	}
	    });
		ok.setBounds(24, 218, 117, 29);
		panel.add(ok);
		
		no = new JButton("Cancel");
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				dialog.dispose();
			}
		});
		no.setBounds(172, 218, 117, 29);
		panel.add(no);
		
		beGONE = new JButton("Delete");
		beGONE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				data[0] = -1000.0;
		        data[1] = -1000.0;
		        charge.getStats(data);
		        dispose();
		        dialog.dispose();
		        
			}
		});
		beGONE.setBounds(308, 218, 117, 29);
		panel.add(beGONE);

	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
