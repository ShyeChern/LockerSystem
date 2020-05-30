// Locker Register View
// Contain GUI of the Register
// Done by See Di Ching
package View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.LockerControllerClient;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JPasswordField textFieldEnterPIN;
	private JPasswordField textFieldReenterPIN;

	// Run the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame
	public Registration() {
		// Create controller object
		LockerControllerClient lockerControllerClient=new LockerControllerClient();
		
		setTitle("Register Locker");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblLockerNo = new JLabel("Locker 101");
		lblLockerNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLockerNo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblLockerNo.setBorder(new EmptyBorder(20, 0, 0, 0));
		panelNorth.add(lblLockerNo);
		
		JLabel lblStatusAvailable = new JLabel("Status: Available");
		lblStatusAvailable.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStatusAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorth.add(lblStatusAvailable);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(0, 1, 10, 0));
		
		JPanel panelCenterRow1 = new JPanel();
		panelCenter.add(panelCenterRow1);
		
		JLabel lblNewLabel = new JLabel("Enter Your Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(600, 70));
		panelCenterRow1.add(lblNewLabel);
		
		JPanel panelCenterRow2 = new JPanel();
		panelCenter.add(panelCenterRow2);
		
		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelCenterRow2.add(textFieldName);
		textFieldName.setColumns(18);
		
		JPanel panelCenterRow3 = new JPanel();
		panelCenter.add(panelCenterRow3);
		
		JLabel lblEnterPIN = new JLabel("Enter 6 Digit PIN");
		lblEnterPIN.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPIN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEnterPIN.setPreferredSize(new Dimension(600, 70));
		panelCenterRow3.add(lblEnterPIN);
		
		JPanel panelCenterRow4 = new JPanel();
		panelCenter.add(panelCenterRow4);
		
		textFieldEnterPIN = new JPasswordField();
		textFieldEnterPIN.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEnterPIN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textFieldEnterPIN.setColumns(10);
		panelCenterRow4.add(textFieldEnterPIN);
		
		JPanel panelCenterRow5 = new JPanel();
		panelCenter.add(panelCenterRow5);
		
		JLabel lblReenterPIN = new JLabel("Re-enter 6 Digit PIN");
		lblReenterPIN.setHorizontalAlignment(SwingConstants.CENTER);
		lblReenterPIN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblReenterPIN.setPreferredSize(new Dimension(600, 70));
		panelCenterRow5.add(lblReenterPIN);
		
		JPanel panelCenterRow6 = new JPanel();
		panelCenter.add(panelCenterRow6);
		
		textFieldReenterPIN = new JPasswordField();
		textFieldReenterPIN.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReenterPIN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textFieldReenterPIN.setColumns(10);
		panelCenterRow6.add(textFieldReenterPIN);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new EmptyBorder(0, 0, 20, 0));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
		
		JButton btnConfirm = new JButton("Confirm");
		// add action when confirm button click
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Get value of username, password and reconfirm password
				String userName=textFieldName.getText();
				String password=String.valueOf(textFieldEnterPIN.getPassword());
				String password2=String.valueOf(textFieldReenterPIN.getPassword());
				
				// Send data to controller
				String result=lockerControllerClient.validateInput(userName, password, password2);
				// Do action according to result
				if(result.equals("success")) {
					// close register view and open collect view
					dispose();
					new Collection(lockerControllerClient).setVisible(true);
				}
				else {
					JPanel optionPane = new JPanel();
					JOptionPane.showMessageDialog(optionPane, result);
				}
				
			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelSouth.add(btnConfirm);
		
		
		// Make screen in center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

}
