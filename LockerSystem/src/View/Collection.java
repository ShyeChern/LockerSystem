// Locker Collect View
// Contain GUI of the Collect
// Done by Lim Shye Chern
package View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import Controller.LockerControllerClient;
import javax.swing.JPasswordField;

public class Collection extends JFrame {

	private JPanel contentPane;
	private JPasswordField textFieldUnlockPIN;
	private JTextField textFieldUserName;

	// Create the frame and receive controller
	public Collection(LockerControllerClient lockerControllerClient) {
		setTitle("Collect Item");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelNorth.add(panel);
		
		JLabel lblCollectItem = new JLabel("Collect Item");
		lblCollectItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblCollectItem.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblCollectItem.setBorder(new EmptyBorder(20, 0, 0, 0));
		panel.add(lblCollectItem);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		JLabel lblEnterYourUnlock = new JLabel("Enter Your Unlock PIN");
		lblEnterYourUnlock.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourUnlock.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEnterYourUnlock.setBounds(243, 191, 268, 46);
		panelCenter.add(lblEnterYourUnlock);
		
		textFieldUnlockPIN = new JPasswordField();
		textFieldUnlockPIN.setEditable(false);
		textFieldUnlockPIN.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textFieldUnlockPIN.setBounds(300, 253, 146, 35);
		panelCenter.add(textFieldUnlockPIN);
		textFieldUnlockPIN.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName.setBounds(243, 67, 268, 46);
		panelCenter.add(lblName);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textFieldUserName.setColumns(10);
		textFieldUserName.setBounds(300, 129, 146, 35);
		panelCenter.add(textFieldUserName);
		
		JInternalFrame internalFramePIN = new JInternalFrame("KeyPad");
		internalFramePIN.setClosable(true);
		internalFramePIN.setBounds(460, 16, 308, 340);
		panelCenter.add(internalFramePIN);
		
		JPanel internalPanelCenterPIN = new JPanel();
		internalFramePIN.getContentPane().add(internalPanelCenterPIN, BorderLayout.CENTER);
		internalPanelCenterPIN.setLayout(new GridLayout(0, 3, 0, 0));
		
		// Loop to add button (numberpad) to internal panel
		for(int i=1;i<10;i++) {
			JButton btn = new JButton(Integer.toString(i));
			// Add digit to textfield when button click
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldUnlockPIN.setText(textFieldUnlockPIN.getText() + e.getActionCommand()); 
				}
			});
			btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
			internalPanelCenterPIN.add(btn);
		}
		
		// Clear all input digit when clicked
		JButton btnClearNo2 = new JButton("X");
		btnClearNo2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnClearNo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldUnlockPIN.setText("");
			}
		});
		internalPanelCenterPIN.add(btnClearNo2);
		
		// add 0 to textfield when clicked
		JButton btnZero2 = new JButton("0");
		btnZero2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUnlockPIN.setText(textFieldUnlockPIN.getText() + e.getActionCommand()); 
			}
		});
		btnZero2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		internalPanelCenterPIN.add(btnZero2);
		
		// delete the last digit
		JButton btnDelete2 = new JButton("<");
		btnDelete2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldUnlockPIN.getText() != null && textFieldUnlockPIN.getText().length() > 0) {
					textFieldUnlockPIN.setText(textFieldUnlockPIN.getText().substring(0, textFieldUnlockPIN.getText().length() - 1));
			    }
			}
		});
		btnDelete2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		internalPanelCenterPIN.add(btnDelete2);
		internalFramePIN.setVisible(false);
		
		// display numberpad when clicked
		JToggleButton btnKeyPadPIN = new JToggleButton("KeyPad");
		btnKeyPadPIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnKeyPadPIN.isSelected()) {
					internalFramePIN.setVisible(true);
				}
				else if(!btnKeyPadPIN.isSelected()) {
					internalFramePIN.setVisible(false);
				}
			}
		});
		btnKeyPadPIN.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKeyPadPIN.setBounds(504, 253, 108, 36);
		panelCenter.add(btnKeyPadPIN);
		
		internalFramePIN.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				btnKeyPadPIN.setSelected(false);
			}
		});
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new EmptyBorder(0, 0, 20, 0));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		// Send data to controller when button clicked
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// send data to controller
				String result=lockerControllerClient.collectItem(textFieldUserName.getText(), textFieldUnlockPIN.getText());
				// Do action according to the result
				if(result.equals("success")) {
					JPanel optionPane = new JPanel();
					JOptionPane.showMessageDialog(optionPane, "Please proceed to payment");
					
					// start the client
					try {
						lockerControllerClient.startClient();
					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// clear all locker data method
					lockerControllerClient.clearLocker();
					// close collect view and open register view
					dispose();
					new Registration().setVisible(true);
				}
				else {
					JPanel optionPane = new JPanel();
					JOptionPane.showMessageDialog(optionPane, result);
				}
				

			}
		});
		panelSouth.add(btnConfirm);
		
		// Make screen in the center
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
