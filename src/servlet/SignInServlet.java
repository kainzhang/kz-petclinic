package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;


/**
 * @Servlet implementation class LoginServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("# LoginAttempt£¬ Username£º"+username+", Password£º"+password);
		
		UserDAO dao = new UserDAO();
		int id = dao.confirmUser(username,password);
		if(id > -1) {
			response.sendRedirect("hello.jsp?username="+username+"&greeting=Welcome");
		} else {
			request.setAttribute("message", "ÕËºÅ»òÃÜÂë´íÎó£¬ÇëÖØĞÂÊäÈë<br>");
			request.getRequestDispatcher("SignIn.jsp").forward(request, response);
		}
		
	}

}
 
