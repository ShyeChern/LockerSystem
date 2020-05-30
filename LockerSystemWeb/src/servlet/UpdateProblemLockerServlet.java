// Get data from jsp according user input and update into database
// Author: Mujeeb Ali Najm Al-Qarah
package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LockerController;
import model.Locker;
import model.Status;

/**
 * Servlet implementation class UpdateProblemLockerServlet
 */
@WebServlet("/UpdateProblemLockerServlet")
public class UpdateProblemLockerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProblemLockerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // Update and fix condition of locker
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// get data from jsp
		int statusId = Integer.parseInt(request.getParameter("status"));
		int lockerId = Integer.parseInt(request.getParameter("lockerId"));
		
		Status status=new Status();
		status.setStatusId(statusId);
		
		Locker locker=new Locker();
		locker.setStatus(status);
		locker.setLockerId(lockerId);
		
		LockerController lockerController=new LockerController();
		
		// Get result value according to return value
		try {
			if(lockerController.updateLockerStatus(locker)==1) {
				request.setAttribute("result", "Update locker successfully");
			}
			else {
				request.setAttribute("result", "Fail to update locker");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// get list from database and set in parameter
			request.setAttribute("maintenances", lockerController.selectInMaintenanceLocker());
			request.setAttribute("repairs", lockerController.selectNeedToRepairLocker());
			request.setAttribute("defects", lockerController.selectDefectLocker());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("LockerReport.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
