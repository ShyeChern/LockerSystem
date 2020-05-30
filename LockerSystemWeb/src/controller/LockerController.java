// Locker Controller
// Contain all the process of the locker
// Author: Lim Shye Chern

package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnector;
import model.History;
import model.Locker;
import model.Status;

public class LockerController {
	
	// select all locker in database
	public List<Locker> selectLocker() throws SQLException{
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		
		List<Locker> lockers=new ArrayList<Locker>();
		
		try {
			conn=DBConnector.getConnection();
			preparedStatement=conn.prepareStatement("select locker.LockerId, locker.No, status.description from locker inner join status on locker.status=status.StatusId order by locker.LockerId");
			rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				Locker locker=new Locker();
				locker.setLockerId(rs.getInt(1));
				locker.setNo(rs.getInt(2));
				
				Status status=new Status();
				status.setDescription(rs.getString(3));
				
				locker.setStatus(status);
				
				lockers.add(locker);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			// close all the objects
			if(rs!=null) {
				rs.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
		
		return lockers;
	}
	
	// insert new locker to db
	public int insertNewLocker(Locker locker) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement=null;
		int affectedRow=0;
		try {
			conn = DBConnector.getConnection();
			preparedStatement = conn.prepareStatement("insert into locker (No) VALUES (?)");
			// set dynamic data to the sql statement
			preparedStatement.setInt (1, locker.getNo());
			// get the affected row
			affectedRow+=preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
		
		// return value
		return affectedRow;
	}
	
	// select all the locker history from database
	public List<History> selectLockerHistory(Locker locker) throws SQLException{
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		
		List<History> histories=new ArrayList<History>();
		
		try {
			conn=DBConnector.getConnection();
			preparedStatement=conn.prepareStatement("select history.CustomerUsername, history.Date, history.Time, history.Duration, history.Fee, locker.No from history inner join locker on history.LockerId=locker.LockerId where history.LockerId=?");
			preparedStatement.setInt (1, locker.getLockerId());
			rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				History history=new History();
				history.setCustomerUsername(rs.getString(1));
				history.setDate(rs.getDate(2));
				history.setTime(rs.getTime(3));
				history.setDuration(rs.getInt(4));
				history.setFee(rs.getInt(5));
				locker.setNo(rs.getInt(6));
				
				history.setLocker(locker);
				
				histories.add(history);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
			if(rs!=null) {
				rs.close();
			}
		}
		
		
		return histories;
		
	}
	
	// display the problem condition for locker from db
	public List<Status> selectStatusProblem(Locker locker) throws SQLException{
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		
		List<Status> problems=new ArrayList<Status>();
		
		try {
			conn=DBConnector.getConnection();
			preparedStatement=conn.prepareStatement("select StatusId, Description from status where description!='Good' AND description!='In Maintenance'");
			rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				Status status=new Status();
				status.setStatusId(rs.getInt(1));
				status.setDescription(rs.getString(2));
				
				problems.add(status);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
			if(rs!=null) {
				rs.close();
			}
		}
		
		return problems;
		
	}
	
	// select no with lockerid from db
	public Locker selectLockerNoWithId(Locker locker) throws SQLException {
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		
		try {
			conn=DBConnector.getConnection();
			preparedStatement=conn.prepareStatement("select No from locker where LockerId=?");
			preparedStatement.setInt (1, locker.getLockerId());
			rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				locker.setNo(rs.getInt(1));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
			if(rs!=null) {
				rs.close();
			}
		}
		
		return locker;
	}
	
	// update locker status to db
	public int updateLockerStatus(Locker locker) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement=null;
		int affectedRow=0;
		try {
			conn = DBConnector.getConnection();
			preparedStatement = conn.prepareStatement("update locker set status=? where LockerId=?");
			// set dynamic data to the sql statement
			preparedStatement.setInt (1, locker.getStatus().getStatusId());
			preparedStatement.setInt (2, locker.getLockerId());
			// get the affected row
			affectedRow+=preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
		
		// return value
		return affectedRow;
	}
	
	// select locker with defect condition
	public List<Locker> selectDefectLocker() throws SQLException {
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		List<Locker> lockers=new ArrayList<Locker>();
		
		try {
			conn=DBConnector.getConnection();
			preparedStatement=conn.prepareStatement("select locker.LockerId, locker.No, status.Description from locker inner join status on status.StatusId=locker.Status where status.StatusId=3");
			rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				Locker locker=new Locker();
				locker.setLockerId(rs.getInt(1));
				locker.setNo(rs.getInt(2));
				
				Status status=new Status();
				status.setDescription(rs.getString(1));
				
				locker.setStatus(status);
				
				lockers.add(locker);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
			if(rs!=null) {
				rs.close();
			}
		}
		
		return lockers;
	}
	
	// select locker with need to repair condition
	public List<Locker> selectNeedToRepairLocker() throws SQLException {
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		List<Locker> lockers=new ArrayList<Locker>();
		
		try {
			conn=DBConnector.getConnection();
			preparedStatement=conn.prepareStatement("select locker.LockerId, locker.No, status.Description from locker inner join status on status.StatusId=locker.Status where status.StatusId=4");
			rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				Locker locker=new Locker();
				locker.setLockerId(rs.getInt(1));
				locker.setNo(rs.getInt(2));
				
				Status status=new Status();
				status.setDescription(rs.getString(1));
				
				locker.setStatus(status);
				
				lockers.add(locker);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
			if(rs!=null) {
				rs.close();
			}
		}
		
		return lockers;
	}
	
	// select locker with in maintenance condition
	public List<Locker> selectInMaintenanceLocker() throws SQLException {
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		List<Locker> lockers=new ArrayList<Locker>();
		
		try {
			conn=DBConnector.getConnection();
			preparedStatement=conn.prepareStatement("select locker.LockerId, locker.No, status.Description from locker inner join status on status.StatusId=locker.Status where status.StatusId=2");
			rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				Locker locker=new Locker();
				locker.setLockerId(rs.getInt(1));
				locker.setNo(rs.getInt(2));
				
				Status status=new Status();
				status.setDescription(rs.getString(1));
				
				locker.setStatus(status);
				
				lockers.add(locker);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
			if(rs!=null) {
				rs.close();
			}
		}
		
		return lockers;
	}
	
	// select status condition for user to update from db
	public List<Status> selectStatusWorking() throws SQLException {
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		
		List<Status> works=new ArrayList<Status>();

		try {
			conn=DBConnector.getConnection();
			preparedStatement=conn.prepareStatement("select StatusId, Description from status where description!='Defect' AND description!='Need to Repair'");
			rs=preparedStatement.executeQuery();

			while(rs.next()) {
				Status status=new Status();
				status.setStatusId(rs.getInt(1));
				status.setDescription(rs.getString(2));

				works.add(status);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
			if(rs!=null) {
				rs.close();
			}
		}

		return works;
	}
	
	// delete defect locker in db
	public int deleteDefectLocker(Locker locker) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement=null;
		int affectedRow=0;
		try {
			conn = DBConnector.getConnection();
			preparedStatement = conn.prepareStatement("delete from locker where LockerId=?");
			// set dynamic data to the sql statement
			preparedStatement.setInt (1, locker.getLockerId());
			// get the affected row
			affectedRow+=preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close all the objects
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
		
		// return value
		return affectedRow;
	}
}


