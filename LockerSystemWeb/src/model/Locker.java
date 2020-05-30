// Locker Model
// Contain all the attribute of the Locker
// Author: Mujeeb Ali Najm Al-Qarah
package model;

public class Locker {
	private int lockerId;
	private Status status;
	private int no;
	
	public int getLockerId() {
		return lockerId;
	}
	public void setLockerId(int lockerId) {
		this.lockerId = lockerId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
}
