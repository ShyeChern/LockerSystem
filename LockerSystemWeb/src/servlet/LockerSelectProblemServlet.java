// Get data from database and display problem to jsp
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

/**
 * Servlet implementation class LockerSelectProblemServlet
 */
@WebServlet("/LockerSelectProblemServlet")
public class LockerSelectProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LockerSelectProblemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // Send data to jsp file and allow user to choose the locker condition
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Get data from jsp file
		int lockerId = Integer.parseInt(request.getParameter("lockerId"));
		Locker locker=new Locker();
		locker.setLockerId(lockerId);
		
		LockerController lockerController=new LockerController();
		
		try {
			// get the list from database and set in the parameter
			request.setAttribute("problems", lockerController.selectStatusProblem(locker));
			request.setAttribute("locker", lockerController.selectLockerNoWithId(locker));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("LockerProblem.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
