package test;


import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Dialog extends JDialog implements ActionListener
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
	
	
	public Dialog(JFrame parent,double num, ButToN button, double[] initial) 
	{
		
	    JPanel panel = new JPanel();
	    dialog.getContentPane().add(panel);
	    dialog.setVisible(true);
	    dialog.setLocationRelativeTo(null);
	    panel.setLayout(null);
	    dialog.setSize(450,300);
	    panel.setVisible(true);
		data=new double[3];
		data[0]=initial[0];
		data[1]=initial[1];
		data[2]=num;
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
				data[0]+=1;
				chargeText.setText(Double.toString(data[0]));
			}
		});
		addCharge.setBounds(134, 69, 117, 29);
		panel.add(addCharge);
		
		subCharge = new JButton("-");
		subCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				data[0]-=1;
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
				data[1]-=1;
				sizeText.setText(Double.toString(data[1]));
			}
		});
		subRadius.setBounds(266, 161, 117, 29);
		panel.add(subRadius);
		
		addRadius = new JButton("+");
		addRadius.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				data[1]+=1;
				sizeText.setText(Double.toString(data[1]));
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
		        // button.getStats(data);
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
		        //button.getStats(data);
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
	
	public double[] run()
	{
		return data;
	}
}