// Locker Model
// Contain all the setter and getter of the locker system
// Done by Mujeeb

package Model;

import java.time.LocalTime;
import java.util.ArrayList;

public class LockerModel {
	private String userName;
	private String password;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public LockerModel() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName=userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password=password;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime=startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	
}
