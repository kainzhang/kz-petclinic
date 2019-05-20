package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PetDAO;
import dao.RecordDAO;
import dao.VetDAO;
import model.Pet;
import model.Record;
import model.Vet;

/**
 * @Servlet implementation class VetServlet
 */
@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
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
	
	private void insertRecord (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vetid = Integer.parseInt(request.getParameter("vetid"));
		Integer petid = Integer.parseInt(request.getParameter("petid"));
		String descr = request.getParameter("descr");
		Record rec = new Record();
		rec.setDescr(descr);
		rec.setPetid(petid);
		rec.setVetid(vetid);
		RecordDAO dao = new RecordDAO();
		String message;
		if(dao.insertRecord(rec)) message = "Insert successful!";
		else message = "Insert failed";
		request.setAttribute("message", message);
		request.getRequestDispatcher("RecordServlet?method=showRecords").forward(request, response);
	}
	
	private void deleteRecord (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		RecordDAO sdao = new RecordDAO();
		String message="";
		if(sdao.deleteRecord(id)) {
				message = "Delete successful!";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("RecordServlet?method=showRecords").forward(request, response);
	}
	
	private void updateRecord (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer vetid = Integer.parseInt(request.getParameter("vetid"));
		Integer petid = Integer.parseInt(request.getParameter("petid"));
		String descr = request.getParameter("descr");
		Record rec = new Record();
		rec.setId(id);
		rec.setDescr(descr);
		rec.setPetid(petid);
		rec.setVetid(vetid);
		RecordDAO dao = new RecordDAO();
		String message;
		if(dao.updateRecord(rec)) message = "Update successful!";
		else message = "Update failed";
		request.setAttribute("message", message);
		request.getRequestDispatcher("RecordServlet?method=showRecords").forward(request, response);
	}
	
	private void showRecords (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecordDAO dao = new RecordDAO();
		List<Record> list = dao.getRecord();
		PetDAO pd=new PetDAO();
		List<Pet> petList=pd.getAllPet();
		VetDAO vd=new VetDAO();
		List<Vet> vetList=vd.getAllVets();
		request.setAttribute("list", list);
		request.setAttribute("pl", petList);
		request.setAttribute("vl", vetList);
		request.getRequestDispatcher("record.jsp").forward(request, response);
	}
	
	private void searchRecord (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecordDAO dao = new RecordDAO();
		String keyword = request.getParameter("keyword");
		List<Record> list = dao.searchRecord(keyword);
		request.setAttribute("list", list);
		request.getRequestDispatcher("record.jsp?keyword="+keyword).forward(request, response);
	}
}
