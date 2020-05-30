// Locker Payment View
// Contain GUI of the Payment
// Done by Mujeeb

package View;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.LockerControllerServer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Receipt extends JFrame {

	private JPanel contentPane;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Payment frame = new Payment();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	// Create the frame
	public Receipt(ArrayList<String> lockerData) {
		// Create controller object
		LockerControllerServer lockerControllerServer=new LockerControllerServer();
		setTitle("Confirm Payment");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblLocker = new JLabel("Locker 101");
		lblLocker.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLocker.setBounds(322, 42, 164, 46);
		contentPane.add(lblLocker);
		
		// Display the receive data from client
		JLabel lblName = new JLabel("Name: "+lockerData.get(0));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblName.setBounds(219, 133, 408, 74);
		contentPane.add(lblName);
		
		// Display the receive data from client
		JLabel lblDuration = new JLabel("Duration: "+lockerControllerServer.getDuration(lockerData.get(1),lockerData.get(2)));
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDuration.setBounds(219, 247, 408, 46);
		contentPane.add(lblDuration);
		
		// Display the receive data from client
		JLabel lblFee = new JLabel("Fee: RM "+lockerControllerServer.getFee()+".00");
		lblFee.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblFee.setBounds(219, 348, 355, 46);
		contentPane.add(lblFee);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog (null, "Confirm the Payment?", "Warning", JOptionPane.YES_NO_OPTION);
				// Clear locker data if the yes button click and close payment view
				if(result == JOptionPane.YES_OPTION) {
					lockerControllerServer.clearLockerData();
					dispose();
				}
			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnConfirm.setBounds(323, 492, 136, 41);
		contentPane.add(btnConfirm);
		
		// Display view in center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
