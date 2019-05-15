package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OwnerDAO;
import dao.SpeciesDAO;
import model.Owner;
import model.Species;

/**
 * Servlet implementation class PatServlet
 */
@WebServlet("/OwnerServlet")
public class OwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	
	private void insertOwner (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String addr=request.getParameter("addr");
		String tel=request.getParameter("tel");
		Owner owner = new Owner();
		owner.setName(name);
		owner.setAddr(addr);
		owner.setTel(tel);
		OwnerDAO dao = new OwnerDAO();
		String message;
		if(dao.insertOwner(owner)) message = "Ìí¼Ó³É¹¦£¡";
		else message = "Ìí¼ÓÊ§°Ü£¡";
		request.setAttribute("message", message);
		request.getRequestDispatcher("OwnerServlet?method=showOwners&message="+message).forward(request, response);
	}
	private void deleteOwner (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		OwnerDAO dao = new OwnerDAO();
		String message;
		if(dao.deleteOwner(id)) message = "É¾³ý³É¹¦£¡";
		else message = "É¾³ýÊ§°Ü£¡";
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("message", message);
		request.getRequestDispatcher("OwnerServlet?method=showOwners&message="+message).forward(request, response);
	}
	
	private void updateOwner (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Owner spec = new Owner();
		spec.setId(id);
		spec.setName(name);
		OwnerDAO dao = new OwnerDAO();
		String message;
		if(dao.updateOwner(spec)) message = "ÐÞ¸Ä³É¹¦£¡";
		else message = "ÐÞ¸ÄÊ§°Ü£¡";
		request.setAttribute("message", message);
		request.getRequestDispatcher("OwnerServlet?method=showOwners&message="+message).forward(request, response);
	}
	private void showOwners (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OwnerDAO dao = new OwnerDAO();
		List<Owner> list = dao.getOwners();
		request.setAttribute("list", list);
		request.getRequestDispatcher("owner.jsp").forward(request, response);
	}
	private void searchOwner (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OwnerDAO dao = new OwnerDAO();
		String keyword = request.getParameter("keyword");
		List<Owner> list = dao.searchOwners(keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("owner.jsp?keyword="+keyword).forward(request, response);
	}

}
