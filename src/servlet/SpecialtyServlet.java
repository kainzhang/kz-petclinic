package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SpecialtyDAO;
import model.Specialty;
import model.User;

/**
 * @Servlet implementation class SpecialtyServlet
 */
@WebServlet("/SpecialtyServlet")
public class SpecialtyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpecialtyServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(); 
		User user = (User) session.getAttribute("authenticated_user");
		if(user == null) {
			response.sendRedirect("signin.jsp");
		} else {
			Method method = null;
	        String methodName = request.getParameter("method");
	        try {
	            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
	            method.invoke(this, request, response);
	        } catch (Exception e) {
	            throw new RuntimeException("Wrong Method£¡");
	        }
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void insertSpecialty (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Specialty spec = new Specialty();
		spec.setName(name);
		SpecialtyDAO dao = new SpecialtyDAO();
		String message;
		if(dao.insertSpecialty(spec)) message = "Insert successful!";
		else message = "Insert failed";
		request.setAttribute("message", message);
		request.getRequestDispatcher("SpecialtyServlet?method=showSpecialties&message="+message).forward(request, response);
	}
	
	private void deleteSpecialty (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		SpecialtyDAO dao = new SpecialtyDAO();
		String message;
		if(dao.deleteSpecialty(id)) message = "Delete successful!";
		else message = "Insert failed!";
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("message", message);
		request.getRequestDispatcher("SpecialtyServlet?method=showSpecialties&message="+message).forward(request, response);
	}
	
	private void updateSpecialty (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Specialty spec = new Specialty();
		spec.setId(id);
		spec.setName(name);
		SpecialtyDAO dao = new SpecialtyDAO();
		String message;
		if(dao.updateSpecialty(spec)) message = "Update successful!";
		else message = "Update failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("SpecialtyServlet?method=showSpecialties&message="+message).forward(request, response);
	}
	
	private void showSpecialties (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpecialtyDAO dao = new SpecialtyDAO();
		List<Specialty> list = dao.getSpecialties();
		request.setAttribute("list", list);
		request.getRequestDispatcher("specialty.jsp").forward(request, response);
	}
	
	private void searchSpecialties (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpecialtyDAO dao = new SpecialtyDAO();
		String keyword = request.getParameter("keyword");
		List<Specialty> list = dao.searchSpecialties(keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("specialty.jsp?keyword="+keyword).forward(request, response);
	}
}
