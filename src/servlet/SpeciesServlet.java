package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SpeciesDAO;
import model.Species;

/**
 * @Servlet implementation class VetServlet
 */
@WebServlet("/SpeciesServlet")
public class SpeciesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpeciesServlet() {
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
	
	private void insertSpecies (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Species spec = new Species();
		spec.setName(name);
		SpeciesDAO dao = new SpeciesDAO();
		String message;
		if(dao.insertSpecies(spec)) message = "Ìí¼Ó³É¹¦£¡";
		else message = "Ìí¼ÓÊ§°Ü£¡";
		request.setAttribute("message", message);
		request.getRequestDispatcher("SpeciesServlet?method=showSpecies&message="+message).forward(request, response);
	}
	
	private void deleteSpecies (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		SpeciesDAO dao = new SpeciesDAO();
		String message;
		if(dao.deleteSpecies(id)) message = "É¾³ý³É¹¦£¡";
		else message = "É¾³ýÊ§°Ü£¡";
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("message", message);
		request.getRequestDispatcher("SpeciesServlet?method=showSpecies&message="+message).forward(request, response);
	}
	
	private void updateSpecies (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Species spec = new Species();
		spec.setId(id);
		spec.setName(name);
		SpeciesDAO dao = new SpeciesDAO();
		String message;
		if(dao.updateSpecies(spec)) message = "ÐÞ¸Ä³É¹¦£¡";
		else message = "ÐÞ¸ÄÊ§°Ü£¡";
		request.setAttribute("message", message);
		request.getRequestDispatcher("SpeciesServlet?method=showSpecies&message="+message).forward(request, response);
	}
	
	private void showSpecies (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpeciesDAO dao = new SpeciesDAO();
		List<Species> list = dao.getSpecies();
		request.setAttribute("list", list);
		request.getRequestDispatcher("species.jsp").forward(request, response);
	}
	
	private void searchSpecies (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpeciesDAO dao = new SpeciesDAO();
		String keyword = request.getParameter("keyword");
		List<Species> list = dao.searchSpecies(keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("species.jsp?keyword="+keyword).forward(request, response);
	}
}
