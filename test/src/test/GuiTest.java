package test;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class GuiTest {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTest window = new GuiTest();
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
	public GuiTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSubtract = new JButton("Subtract");
		btnSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int var1,var2,ans;
					var1=Integer.parseInt(textField.getText());
					var2=Integer.parseInt(textField_1.getText());
					ans=var1-var2;
					lblNewLabel_1.setText(Integer.toString(ans));
					
				}
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null, "Invalid values");
				}
				
			}
		});
		btnSubtract.setBounds(245, 105, 152, 63);
		frame.getContentPane().add(btnSubtract);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					int var1,var2,ans;
					var1=Integer.parseInt(textField.getText());
					var2=Integer.parseInt(textField_1.getText());
					ans=var1+var2;
					lblNewLabel_1.setText(Integer.toString(ans));
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Invalid values");
				}
			}
		});
		btnAdd.setBounds(36, 105, 152, 63);
		frame.getContentPane().add(btnAdd);
		
		textField = new JTextField();
		textField.setBounds(50, 27, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(257, 27, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel = new JLabel("The answer is");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel.setBounds(50, 205, 164, 32);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(257, 211, 130, 28);
		frame.getContentPane().add(lblNewLabel_1);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
