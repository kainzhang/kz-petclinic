package servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import dao.OwnerDAO;
import dao.PetDAO;
import dao.SpeciesDAO;
import model.Pet;
import model.User;

/**
 * Servlet implementation class PetServlet
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
	            throw new RuntimeException("Wrong Method！");
	        }
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
	
	private void toInsertPet  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpeciesDAO sdao = new SpeciesDAO();
		request.setAttribute("splist", sdao.getSpecies());
		
		OwnerDAO odao = new OwnerDAO();
		request.setAttribute("ownerlist", odao.getAllOwners());
		request.setAttribute("flag", 1);
		request.getRequestDispatcher("petdetail.jsp").forward(request, response);
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
		request.getRequestDispatcher("PetServlet?method=showPets&pageIndex=1&message="+message).forward(request, response);
	}	
	private void updatePet (HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id=Integer.parseInt(request.getParameter("id"));
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
		pet.setId(id);
		PetDAO dao = new PetDAO();
		String message;
		if(dao.updatePet(pet)) message = "Update successfully!";
		else message = "Update Failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("PetServlet?method=showPets&pageIndex=1&message="+message).forward(request, response);
		
	}
	
	private void deletePet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		PetDAO dao = new PetDAO();
		String message;
		if(dao.deletePet(id)) message = "Delete successfully!";
		else message = "Delete failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("PetServlet?method=showPets&pageIndex=1&message="+message).forward(request, response);
	}
	
	private void showPet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetDAO dao = new PetDAO();
		Integer id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("aimPet", dao.getPet(id));
		
		SpeciesDAO sdao = new SpeciesDAO();
		request.setAttribute("splist", sdao.getSpecies());
		
		OwnerDAO odao = new OwnerDAO();
		request.setAttribute("ownerlist", odao.getAllOwners());
		request.setAttribute("flag", 0);
		request.getRequestDispatcher("petdetail.jsp").forward(request, response);
	}
	
	private void showPets (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetDAO dao = new PetDAO();
		Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		Integer maxPageIndex = (dao.getAmount()+17)/18;
		List<Pet> list = dao.getPets((pageIndex-1)*18, pageIndex*18);
		request.setAttribute("maxPageIndex", maxPageIndex);
		request.setAttribute("method", "showPets");
		request.setAttribute("list", list);
		request.getRequestDispatcher("pet.jsp?pageIndex="+pageIndex).forward(request, response);
	}
	
	private void searchPets (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PetDAO dao = new PetDAO();	
		Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		String keyword = request.getParameter("keyword");
		Integer maxPageIndex = (dao.getResultAmount(keyword)+17)/18;
		List<Pet> list = dao.searchPets(keyword, (pageIndex-1)*18, pageIndex*18);
		request.setAttribute("maxPageIndex", maxPageIndex);
		request.setAttribute("method", "searchPets");
		request.setAttribute("list", list);
		request.getRequestDispatcher("pet.jsp?keyword="+keyword+"&pageIndex="+pageIndex).forward(request, response);
	}
	
	private void updatePic (HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName="";
		Map<String, String> mp =new HashMap<String, String>();
		request.setCharacterEncoding("utf-8");
        DiskFileItemFactory factroy = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factroy);
        boolean isF = ServletFileUpload.isMultipartContent(request);
        if (isF) {
            //使用解析器解析上传的表单数据，每个FileItem对应一个表单项
        	RequestContext context=new ServletRequestContext(request);
            List<FileItem> fileItemList = upload.parseRequest(context);
            for (FileItem fileItem : fileItemList) {
                if (!fileItem.isFormField()) {
                    //不是普通的表单项，即上传的是文件
                    fileName = fileItem.getName();
                    String root=request.getServletContext().getRealPath("/media/");
                    fileName =root+fileName;
                    System.out.printf(fileName);
                    File file = new File(fileName);
                    fileItem.write(file);
                    System.out.println("  导入成功");
                } else {
                    //获取表单中的非文件值
                	mp.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
                    System.out.println(fileItem.getFieldName());
                    System.out.println(fileItem.getString("UTF-8"));
                }
            }
        }
	}
}
