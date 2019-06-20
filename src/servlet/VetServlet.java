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

import dao.SpecialtyDAO;
import dao.UserDAO;
import dao.VetDAO;
import model.User;
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
	
	private void toInsertVet  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpecialtyDAO sdao = new SpecialtyDAO();
		request.setAttribute("splist2", sdao.getSpecialties());
		
		request.setAttribute("flag", 1);
		request.getRequestDispatcher("vetdetail.jsp").forward(request, response);
	}
	
	private void insertVet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pic = request.getParameter("pic");
		
		Vet vet = new Vet();
		vet.setName(name);
		vet.setPic(pic);
		VetDAO dao = new VetDAO();
		String message;
		if(dao.insertVet(vet)) message = "Insert successful!";
		else message = "Insert failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("VetServlet?method=showVets&pageIndex=1&message="+message).forward(request, response);
	}
	
	private void insertVetSpecialty (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vetid = Integer.parseInt(request.getParameter("vetid"));
		Integer specid = Integer.parseInt(request.getParameter("specid"));
		
		SpecialtyDAO dao = new SpecialtyDAO();
		String message;
		if(dao.insertVetSpecialty(vetid, specid)) message = "Insert successful!";
		else message =  "Insert failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("VetServlet?method=showVet&id="+vetid).forward(request, response);
	}                 
	
	private void deleteVetSpecialty (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer vetid = Integer.parseInt(request.getParameter("vetid"));
		Integer specid = Integer.parseInt(request.getParameter("specid"));
		System.out.println(vetid+" "+specid);
		SpecialtyDAO dao = new SpecialtyDAO();
		String message;
		if(dao.deleteVetSpecialty(vetid, specid)) message = "Delete successful!";
		else message =  "Delete failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("VetServlet?method=showVet&id="+vetid).forward(request, response);
	}
	
	private void updateVet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String pic = request.getParameter("pic");
		
		Vet vet = new Vet();
		vet.setId(id);
		vet.setName(name);
		vet.setPic(pic);
		VetDAO dao = new VetDAO();
		String message;
		if(dao.updateVet(vet)) message = "Update successful!";
		else message = "Update failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("VetServlet?method=showVets&pageIndex=1&message="+message).forward(request, response);
	}
	
	private void deleteVet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		VetDAO dao = new VetDAO();
		String message;
		if(dao.deleteVet(id)) message = "Delete successful!";
		else message = "Delete failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("VetServlet?method=showVets&pageIndex=1&message="+message).forward(request, response);
	}
	
	private void showVet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VetDAO dao = new VetDAO();
		Integer id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("aimVet", dao.getVet(id));
		
		SpecialtyDAO sdao = new SpecialtyDAO();
		request.setAttribute("splist", sdao.getVetSpecialties(id));
		request.setAttribute("splist2", sdao.getSpecialties());
		request.setAttribute("flag", 0);
		request.getRequestDispatcher("vetdetail.jsp").forward(request, response);
	}
	
	private void showVets (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VetDAO dao = new VetDAO();
		
		Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		Integer maxPageIndex = (dao.getAmount()+17)/18;
		List<Vet> list = dao.getVets((pageIndex-1)*18, pageIndex*18);
		request.setAttribute("maxPageIndex", maxPageIndex);
		request.setAttribute("method", "showVets");
		request.setAttribute("list", list);
		request.getRequestDispatcher("vet.jsp?pageIndex="+pageIndex).forward(request, response);
	}
	
	private void searchVets (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VetDAO dao = new VetDAO();
		Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		String keyword = request.getParameter("keyword");
		Integer maxPageIndex = (dao.getResultAmount(keyword)+17)/18;
		List<Vet> list = dao.searchVets(keyword, (pageIndex-1)*18, pageIndex*18);
		request.setAttribute("maxPageIndex", maxPageIndex);
		request.setAttribute("method", "searchVets");
		request.setAttribute("list", list);
		request.getRequestDispatcher("vet.jsp?keyword="+keyword+"&pageIndex="+pageIndex).forward(request, response);
	}
	
	private void searchVetsBySpecid (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VetDAO dao = new VetDAO();
		Integer pageIndex =1;
		Integer specId = Integer.parseInt(request.getParameter("keyword"));
		String nam=request.getParameter("name");
		Integer maxPageIndex = (dao.getResultAmountBySpecid(specId)+17)/18;
		List<Vet> list = dao.getVetsBySpecid(specId, (pageIndex-1)*18, pageIndex*18);
		request.setAttribute("maxPageIndex", maxPageIndex);
		request.setAttribute("method", "searchVets");
		request.setAttribute("list", list);
		request.getRequestDispatcher("vet.jsp?keyword="+nam+"&pageIndex="+pageIndex).forward(request, response);
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
                    File file = new File(fileName);
                    fileItem.write(file);
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
