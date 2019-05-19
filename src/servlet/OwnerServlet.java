package servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
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
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import dao.OwnerDAO;
import dao.OwnerDAO;
import dao.UserDAO;
import model.Owner;
import model.User;

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
            throw new RuntimeException("Wrong Method！");
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
            throw new RuntimeException("Wrong Method！");
        }
	}
	
	private void toInsertOwner  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("flag", 1);
		request.getRequestDispatcher("ownerdetail.jsp").forward(request, response);
	}
	
	private void insertOwner (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		String pic = request.getParameter("pic");
		
		Owner owner = new Owner();
		owner.setName(name);
		owner.setTel(tel);
		owner.setAddr(addr);
		owner.setPic(pic);
		OwnerDAO dao = new OwnerDAO();
		String message;
		if(dao.insertOwner(owner)) message = "Insert successfully!";
		else message = "Insert Failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("OwnerServlet?method=showOwners&pageIndex=1&message="+message).forward(request, response);
	}	
	private void updateOwner (HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id=Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		String pic = request.getParameter("pic");
		
		Owner owner = new Owner();
		owner.setName(name);
		owner.setTel(tel);
		owner.setAddr(addr);
		owner.setPic(pic);
		owner.setId(id);
		OwnerDAO dao = new OwnerDAO();
		String message;
		if(dao.updateOwner(owner)) message = "Update successfully!";
		else message = "Update Failed!";
		request.setAttribute("message", message);
		request.getRequestDispatcher("OwnerServlet?method=showOwners&pageIndex=1&message="+message).forward(request, response);
		
	}
	private void deleteOwner (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		OwnerDAO dao = new OwnerDAO();
		String message;
		if(dao.deleteOwner(id)) message = "Delete successfully!";
		else message = "Delete failed!";
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("message", message);
		request.getRequestDispatcher("OwnerServlet?method=showOwners&pageIndex=1&message="+message).forward(request, response);
	}
	
	private void showOwner (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OwnerDAO dao = new OwnerDAO();
		Integer id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("aimOwner", dao.getOwner(id));
		
		request.setAttribute("flag", 0);
		request.getRequestDispatcher("ownerdetail.jsp").forward(request, response);
	}
	
	private void showOwners (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OwnerDAO dao = new OwnerDAO();
		Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		Integer maxPageIndex = (dao.getAmount()+17)/18;
		List<Owner> list = dao.getOwners((pageIndex-1)*18, pageIndex*18);
		System.out.println(pageIndex+" "+maxPageIndex);
		request.setAttribute("maxPageIndex", maxPageIndex);
		request.setAttribute("method", "showOwners");
		request.setAttribute("list", list);
		request.getRequestDispatcher("owner.jsp?pageIndex="+pageIndex).forward(request, response);
	}
	
	private void searchOwners (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OwnerDAO dao = new OwnerDAO();	
		String keyword = request.getParameter("keyword");
		Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		Integer maxPageIndex = (dao.getResultAmount(keyword)+17)/18;
		List<Owner> list = dao.searchOwners(keyword,(pageIndex-1)*18, pageIndex*18);
		request.setAttribute("maxPageIndex", maxPageIndex);
		request.setAttribute("method", "searchOwners");
		request.setAttribute("list", list);
		request.getRequestDispatcher("owner.jsp?keyword="+keyword+"&pageIndex="+pageIndex).forward(request, response);
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
