// Get data from jsp to delete locker
// Author: Lim Shye Chern
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

/**
 * Servlet implementation class DeleteLockerServlet
 */
@WebServlet("/DeleteLockerServlet")
public class DeleteLockerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteLockerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // Delete Locker
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Receive data from jsp
		int lockerId = Integer.parseInt(request.getParameter("lockerId"));
		Locker locker=new Locker();
		locker.setLockerId(lockerId);
		
		LockerController lockerController=new LockerController();
		
		// Get result according to return value
		try {
			if(lockerController.deleteDefectLocker(locker)==1) {
				request.setAttribute("result", "Delete locker successfully");
			}
			else {
				request.setAttribute("result", "Fail to delete locker");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
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
