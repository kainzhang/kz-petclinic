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
import dao.PetDAO;
import dao.SpeciesDAO;
import model.Pet;

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
	
	private void toInsertPet  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpeciesDAO sdao = new SpeciesDAO();
		request.setAttribute("splist", sdao.getSpecies());
		
		OwnerDAO odao = new OwnerDAO();
		request.setAttribute("ownerlist", odao.getOwners());
		request.getRequestDispatcher("petdetail.jsp?flag=1").forward(request, response);
	}
	
	private void insertPet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String yy = request.getParameter("YY");
		String mm = request.getParameter("MM");
		String dd = request.getParameter("DD");
		String bday = yy+"-"+mm+"-"+dd;
		Integer speciesid = Integer.parseInt(request.getParameter("speciesid"));
		Integer ownerid = Integer.parseInt(request.getParameter("ownerid"));
		String pic = request.getParameter("pic");
		
		Pet pet = new Pet();
		pet.setName(name);
		pet.setBday(bday);
		pet.setSpeciesId(speciesid);
		pet.setOwnerId(ownerid);
		pet.setPic(pic);
		PetDAO dao = new PetDAO();
		String message;
		if(dao.insertPet(pet)) message = "Insert successfully!";
		else message = "Insert Failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("PetServlet?method=showPets&message="+message).forward(request, response);
	}	
	private void updatePet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String yy = request.getParameter("YY");
		String mm = request.getParameter("MM");
		String dd = request.getParameter("DD");
		String bday = yy+"-"+mm+"-"+dd;
		Integer speciesid = Integer.parseInt(request.getParameter("speciesid"));
		Integer ownerid = Integer.parseInt(request.getParameter("ownerid"));
		String pic = request.getParameter("pic");
		System.out.println(id+" "+name+" "+bday+" "+speciesid+" "+ownerid);
		
		Pet pet = new Pet();
		pet.setId(id);
		pet.setName(name);
		pet.setBday(bday);
		pet.setSpeciesId(speciesid);
		pet.setOwnerId(ownerid);
		pet.setPic(pic);
		PetDAO dao = new PetDAO();
		String message;
		if(dao.updatePet(pet)) message = "Update successfully!";
		else message = "Update failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("PetServlet?method=showPets&message="+message).forward(request, response);
	}
	private void deletePet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		PetDAO dao = new PetDAO();
		String message;
		if(dao.deletePet(id)) message = "Delete successfully!";
		else message = "Delete failed!";
		request.setCharacterEncoding("UTF-8");
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
		request.getRequestDispatcher("petdetail.jsp?flag=0").forward(request, response);
	}
	
	private void searchPet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetDAO dao = new PetDAO();
		String keyword = request.getParameter("keyword");
		List<Pet> list = dao.searchPets(keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("pet.jsp?keyword="+keyword).forward(request, response);
	}
}
