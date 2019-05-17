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

import dao.OwnerDAO;
import dao.PetDAO;
import dao.SpeciesDAO;
import model.Owner;
import model.Pet;
import model.Species;

/**
 * Servlet implementation class PatServlet
 */
@WebServlet("/PetServlet")
public class PetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetServlet() {
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
	
	private void insertPet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String bday = request.getParameter("bday");
		String speciesid = request.getParameter("speciesid");
		String ownerid = request.getParameter("ownerid");
		String pic = request.getParameter("pic");
		System.out.println(speciesid+"  "+ownerid);
		
		Pet pet = new Pet();
		pet.setName(name);
		pet.setBday(bday);
		pet.setOwnerId(Integer.parseInt(ownerid));
		pet.setSpeciesId(Integer.parseInt(speciesid));
		pet.setPic(pic);
		PetDAO dao = new PetDAO();
		String message;
		if(dao.insertPet(pet)) message = "Ìí¼Ó³É¹¦£¡";
		else message = "Ìí¼ÓÊ§°Ü£¡";
		request.setAttribute("message", message);
		request.getRequestDispatcher("PetServlet?method=showPets&message="+message).forward(request, response);
	}
	private void deletePet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		PetDAO dao = new PetDAO();
		String message;
		if(dao.deletePet(id)) message = "É¾³ý³É¹¦£¡";
		else message = "É¾³ýÊ§°Ü£¡";
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("message", message);
		request.getRequestDispatcher("PetServlet?method=showPets&message="+message).forward(request, response);
	}
	
	private void updatePet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Pet spec = new Pet();
		spec.setId(id);
		spec.setName(name);
		PetDAO dao = new PetDAO();
		String message;
		if(dao.updatePet(spec)) message = "ÐÞ¸Ä³É¹¦£¡";
		else message = "ÐÞ¸ÄÊ§°Ü£¡";
		request.setAttribute("message", message);
		request.getRequestDispatcher("PetServlet?method=showPets&message="+message).forward(request, response);
	}
	private void showPets (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetDAO dao = new PetDAO();
		List<Pet> list = dao.getPets();
		request.setAttribute("list", list);
		request.getRequestDispatcher("pet.jsp").forward(request, response);
	}
	
	private void showPet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetDAO dao = new PetDAO();
		Integer id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("aimPet", dao.getPet(id));
		
		SpeciesDAO sdao = new SpeciesDAO();
		request.setAttribute("splist", sdao.getSpecies());
		
		OwnerDAO odao = new OwnerDAO();
		request.setAttribute("ownerlist", odao.getOwners());
		request.getRequestDispatcher("petdetail.jsp").forward(request, response);
	}
	
	private void searchPet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetDAO dao = new PetDAO();
		String keyword = request.getParameter("keyword");
		List<Pet> list = dao.searchPets(keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("pet.jsp?keyword="+keyword).forward(request, response);
	}
	private void toUpdatePet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetDAO dao = new PetDAO();
		Integer id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		session.setAttribute("aimPet", dao.getPet(id));
		response.sendRedirect("updatepet.jsp");
	}
}
