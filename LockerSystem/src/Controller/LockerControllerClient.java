// Locker Controller Client
// Contain all the function and process of Locker System in client
// Done by See Di Ching

package Controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import Model.LockerModel;

public class LockerControllerClient {
	private LockerModel lockerModel=new LockerModel();
	
	public LockerControllerClient(){
		
	}
	
	// Validate user input to register locker
	public String validateInput(String userName, String password, String password2) {
		
		// Check if it is empty
		if(userName.length()==0||password.length()==0||password2.length()==0) {
			return "Please enter your Name and PIN";
		}
		// Check if password are same
		else if(!password.equals(password2)) {
			return "The PIN doesn't match";
		}
		// Check if username is alphabet
		else if (!userName.matches("[a-zA-Z_]+")) {
			return "Please input an appropriate name";
		}
		// Check if password is digit
		else if(!password.matches("[0-9]+")) {
			return "The must be a digit number";
		}
		// Check if password length less than 6
		else if(password.length()!=6) {
			return "Please enter 6 digit PIN";
		}
		// If the restriction are pass, set the value to locker model
		else{
			lockerModel.setUserName(userName);
			lockerModel.setPassword(password);
			
			// Get user register time
			LocalTime startTime = LocalTime.now();
			lockerModel.setStartTime(startTime);
			
			return "success";
		}
	}
	
	// Validate user input to open locker 
	public String collectItem(String userName, String password) {
		
		// Get the username which previously saved in lockerModel and check if it match
		String username=lockerModel.getUserName();
		
		if(username.equals(userName)) {
			// If the username matched, continue with the PIN check
			String pass=lockerModel.getPassword();
			if(pass.equals(password)) {
				// Get the user open locker time
				LocalTime endTime = LocalTime.now();
				lockerModel.setEndTime(endTime);
				return "success";
			}
			else {
				return "Incorrect Password";
			}
		}
		else {
			return "Wrong username";
		}
		
	}
	
	// Client connect to server
	public void startClient() throws IOException, InterruptedException {
		// Get host address and port
		InetSocketAddress hostAddress = new InetSocketAddress("192.168.0.115", 9093);
		SocketChannel client = SocketChannel.open(hostAddress);
		
		// Data to be send
		String userName=lockerModel.getUserName();
		LocalTime startTime=lockerModel.getStartTime();
		LocalTime endTime=lockerModel.getEndTime();
		
		// Formatter which allow change time to string format
		DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
		
		// Messages to be send
		String[] messages = new String[] {userName, startTime.format(formatter), endTime.format(formatter) };
		
		// Loop the array data, convert to buffer and send data to server
		for (int i = 0; i < messages.length; i++) {
			System.out.println("Sending data to server..."+i);
			ByteBuffer buffer = ByteBuffer.allocate(74);
			buffer.put(messages[i].getBytes());
			buffer.flip();
			client.write(buffer);
			buffer.clear();
			Thread.sleep(1000);
		}
		
		client.close();
	}
	
	// Clear lockerModel data to null
	public void clearLocker() {
		lockerModel.setUserName(null);
		lockerModel.setPassword(null);
		lockerModel.setStartTime(null);
		lockerModel.setEndTime(null);
	}
	

}
