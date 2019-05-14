package servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

/**
 * @Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Method method = null;
        String methodName = request.getParameter("method");
        try {
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException("调用方法出错！");
        }
	}
	
	private void signIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("# LoginAttempt， Username："+username+", Password："+password);
		
		UserDAO dao = new UserDAO();
		int id = dao.confirmUser(username,password);
		if(id > -1) {
			response.sendRedirect("hello.jsp?username="+username+"&greeting=Welcome");
		} else {
			request.setAttribute("message", "账号或密码错误，请重新输入<br>");
			request.getRequestDispatcher("SignIn.jsp").forward(request, response);
		}
	}
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String confpass = request.getParameter("confpass");
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
