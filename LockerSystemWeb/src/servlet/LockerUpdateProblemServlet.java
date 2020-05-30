// Get data from jsp and update locker problem in database
// Author: See Di Ching

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
 * Servlet implementation class LockerUpdateProblemServlet
 */
@WebServlet("/LockerUpdateProblemServlet")
public class LockerUpdateProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LockerUpdateProblemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // Update the locker condition
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Receive data from jsp
		int statusId = Integer.parseInt(request.getParameter("problem"));
		int lockerId = Integer.parseInt(request.getParameter("lockerId"));
		
		Status status=new Status();
		status.setStatusId(statusId);
		
		Locker locker=new Locker();
		locker.setStatus(status);
		locker.setLockerId(lockerId);
		
		LockerController lockerController=new LockerController();
		
		// Get result according to return value
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
			request.setAttribute("lockers", lockerController.selectLocker());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("LockerDisplay.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
