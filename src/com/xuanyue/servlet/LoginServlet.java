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


//import org.json.JSONException;
//import org.json.JSONObject;
import org.json.*;

import com.xuanyue.bean.UserInfo;
import com.xuanyue.dao.DBHandler;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, Object> LoginInfo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
        
        LoginInfo=new HashMap<String, Object>();
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
		boolean registered=dbh.checkUser("select * from user where user_name=? and user_psd=?", new String[]{name,psd});
		if(registered){
			LoginInfo.put("login", true);
			String uid=dbh.getUID(name);
			UserInfo info=dbh.getUserInfo(uid);
			LoginInfo.put("uid", info.uid);
			LoginInfo.put("nickname", info.nickname);
			LoginInfo.put("sex", info.sex);
			LoginInfo.put("birthday", info.birthday);
			LoginInfo.put("head_portrait", info.head_portrait);
			LoginInfo.put("signature", info.signature);
			
		}else{
			LoginInfo.put("login", false);
		}
		
		out.print(new JSONObject(LoginInfo).toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	

}
