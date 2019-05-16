package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VetDAO;
import model.Vet;

/**
 * @Servlet implementation class VetServlet
 */
@WebServlet("/VetServlet")
public class VetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VetServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Method method = null;
        String methodName = request.getParameter("method");
        try {
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException("Wrong Method£¡");
        }
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Method method = null;
        String methodName = request.getParameter("method");
        try {
            method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            throw new RuntimeException("Wrong Method£¡");
        }
	}
	
	private void insertVet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Vet vet = new Vet();
		vet.setName(name);
		VetDAO dao = new VetDAO();
		String message;
		if(dao.insertVet(vet)) message = "Ìí¼Ó³É¹¦£¡";
		else message = "Ìí¼ÓÊ§°Ü";
		request.setAttribute("message", message);
		request.getRequestDispatcher("VetServlet?method=showVets&message="+message).forward(request, response);
	}
	
	private void deleteVet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		VetDAO dao = new VetDAO();
		String message;
		if(dao.deleteVet(id)) message = "É¾³ý³É¹¦£¡";
		else message = "É¾³ýÊ§°Ü£¡";
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("message", message);
		request.getRequestDispatcher("VetServlet?method=showVets&message="+message).forward(request, response);
	}
	
	private void updateVet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Vet vet = new Vet();
		vet.setId(id);
		vet.setName(name);
		VetDAO dao = new VetDAO();
		String message;
		if(dao.updateVet(vet)) message = "ÐÞ¸Ä³É¹¦£¡";
		else message = "ÐÞ¸ÄÊ§°Ü£¡";
		request.setAttribute("message", message);
		request.getRequestDispatcher("VetServlet?method=showVets&message="+message).forward(request, response);
	}
	
	private void showVets (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VetDAO dao = new VetDAO();
		List<Vet> list = dao.getVets();
		request.setAttribute("list", list);
		request.getRequestDispatcher("vet.jsp").forward(request, response);
	}
	
	private void searchVets (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VetDAO dao = new VetDAO();
		String keyword = request.getParameter("keyword");
		List<Vet> list = dao.searchVets(keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("vet.jsp?keyword="+keyword).forward(request, response);
	}
}
