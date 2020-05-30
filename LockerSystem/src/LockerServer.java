// Locker Server
// Run this and wait the connection from client
// Done by Lim Shye Chern

import java.io.IOException;
import Controller.LockerControllerServer;

public class LockerServer {
	public static void main(String args[]) {
		LockerControllerServer lockerControllerServer=new LockerControllerServer();
		try {
			lockerControllerServer.startServer();
			// Start server method
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
