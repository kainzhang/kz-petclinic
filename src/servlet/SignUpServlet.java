package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import dao.UserDAO;

/**
 * @Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confpass = request.getParameter("confpass");
		boolean flag = true;
		UserDAO dao = new UserDAO();
		try {
			if (dao.userExist(username)) {
				flag = false;
				request.setAttribute("message-username", "用户名已存在<br>");
				request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			} else {
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setPosition(0);
				dao.insertUser(user);
			}
			
		} catch (ClassNotFoundException e) {
			flag = false;
			e.printStackTrace();
		}
		if(flag) {
			response.sendRedirect("hello.jsp?username="+username+"&greeting=Sign Up Success!");
		}
	}

}
