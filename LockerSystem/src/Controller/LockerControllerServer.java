// Locker Controller Server
// Contain all the function and process of Locker System in server
// Done by Lim Shye Chern
package Controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import View.Receipt;

public class LockerControllerServer {

	private int duration;
	private Selector selector;
	private ArrayList<String> lockerData=new ArrayList<String>();
	
	public LockerControllerServer(){
		
	}

	// Server run to receive client response
	public void startServer() throws IOException {
		// Define server address and port
		int port=9093;
		String address="192.168.0.115";
		InetSocketAddress listenAddress = new InetSocketAddress(address, port);
		
		// Open selector for processes
		this.selector = Selector.open();
		
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		
		serverChannel.socket().bind(listenAddress);
		// Change the selector to accept connection status
		serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
		
		System.out.println("Server started on port >> " + port);
		
		while (true) {
			// wait for events
			int readyCount = selector.select();
			if (readyCount == 0) {
				continue;
			}

			// process selected keys...
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			Iterator iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = (SelectionKey) iterator.next();

				// Remove key from set so we don't process it twice
				iterator.remove();
				
				if (!key.isValid()) {
					continue;
				}
				// Go to accept method
				if (key.isAcceptable()) { 
					this.accept(key);
				}
				// Go to read method
				else if (key.isReadable()) { 
					this.read(key);
				} 
			}
		}
	}
	
	// Accept from connection from client
	private void accept(SelectionKey key) throws IOException {
		// Receive and set client channel
		ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
		SocketChannel channel = serverChannel.accept();
		channel.configureBlocking(false);
		Socket socket = channel.socket();
		SocketAddress remoteAddr = socket.getRemoteSocketAddress();
		System.out.println("Connected to: " + remoteAddr);

		// Change the selector to read status
		channel.register(this.selector, SelectionKey.OP_READ);
	}
	
	// Read client data
	private void read(SelectionKey key) throws IOException {
		// Set channel and initialize buffer to receive client buffer data
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int numRead = -1;
		numRead = channel.read(buffer);
		
		// Display if the connection close
		if (numRead == -1) {
			Socket socket = channel.socket();
			SocketAddress remoteAddr = socket.getRemoteSocketAddress();
			System.out.println("Connection closed by client: " + remoteAddr);
			channel.close();
			key.cancel();
			return;
		}
		
		byte[] data = new byte[numRead];
		System.arraycopy(buffer.array(), 0, data, 0, numRead);
		lockerData.add(new String(data));
		
		// Check if the data received complete 
		if(lockerData.size()%3==0 && lockerData.size()>0) {
			new Receipt(lockerData).setVisible(true);
			// Change the selector to ready status
			channel.register(this.selector, SelectionKey.OP_CONNECT);
			// Clear locker data method
			clearLockerData();
		}
		
	}
	
	// Calculate total time use of locker
	public String getDuration(String start, String end) {
		
		// parse time from string to LocalTime datatype
		LocalTime startTime = LocalTime.parse(start);
		LocalTime endTime = LocalTime.parse(end);
		
		String outputDuration="";
		// Calculate time in hours and minutes
		long hoursBetween = ChronoUnit.HOURS.between(startTime,endTime);
		long minutesBetween = ChronoUnit.MINUTES.between(startTime,endTime)%60;
		
		duration=(int) hoursBetween;
		
		if(hoursBetween==0) {
			outputDuration=minutesBetween+" Minutes";
		}
		else {
			outputDuration=hoursBetween+" Hours "+minutesBetween+" Minutes";
		}

		return outputDuration;
	}
	
	// Calculate total fee
	public String getFee() {
		String fee = ""; 
		
		// Check the fee according to duration
		switch (duration) { 
        case 0: 
        	fee = "0"; 
            break; 
        case 1: 
        	fee = "3";  
            break; 
        case 2: 
        	fee = "6"; 
            break; 
        case 3: 
        	fee = "9"; 
            break; 
        case 4: 
        	fee = "12";  
            break;
        case 5: 
        	fee = "15";  
            break;
        case 6: 
        	fee = "18";  
            break;
        default: 
        	fee = "30"; 
            break; 
        } 
		
		return fee;
	}
	
	// Clear lockerData ArrayList
	public void clearLockerData() {
		lockerData.clear();
	}
}
