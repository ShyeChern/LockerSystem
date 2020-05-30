// Get data from jsp to add locker
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
 * Servlet implementation class AddLockerServlet
 */
@WebServlet("/AddLockerServlet")
public class AddLockerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLockerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    // Add Locker
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Receive data from jsp
		int no = Integer.parseInt(request.getParameter("lockerNo"));
		Locker locker=new Locker();
		locker.setNo(no);
		
		LockerController lockerController=new LockerController();
		
		// Get result according to return value
		try {
			if(lockerController.insertNewLocker(locker)==1) {
				request.setAttribute("result", "Add locker successfully");
			}
			else {
				request.setAttribute("result", "Fail to add locker");
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
