package com.xuanyue.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.xuanyue.bean.UserInfo;
import com.xuanyue.dao.DBHandler;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, Object> RegisterInfo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        RegisterInfo=new HashMap<String, Object>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("username");
		String psd=request.getParameter("password");
		
		DBHandler dbh=new DBHandler();
		
		boolean registered=dbh.checkUser("select * from user where user_name=?", new String[]{name});
		if(!registered){
			boolean b = dbh.insert("insert into user(user_name,user_psd)values(?,?)",
					new String[] { name, psd });
			RegisterInfo.put("register", b);
			UserInfo info=new UserInfo();
			info.init(dbh.getUID(name));
			System.out.print("mei you zhu ce,xian zai zhu ce"+name+"******");
			
			dbh.insert("insert into user_info(uid,nickname,sex,birthday,head_portrait,signature)values(?,?,?,?,?,?)",
					new String[]{info.uid,info.nickname,info.sex,info.birthday,info.head_portrait,info.signature} );
			
			
		}else{
			RegisterInfo.put("register", false);
			System.out.print("yi jing zhu ce,xian zai bu zhu ce"+name+"******");
		}
		out.print(new JSONObject(RegisterInfo));
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
