package com.product.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import javax.servlet.annotation.WebServlet;

//import com.group.model.GroupService;
//import com.group.model.GroupVO;
import com.user.model.UserService;
import com.user.model.UserVO;

//@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
   //【實際上應至資料庫搜尋比對】
//  protected boolean allowUser(String password) {
//	  LoginHandler lo =new LoginHandler();
//	
//	  if ( "tomcat".equals(password))
//      return true;
//    else
//      return false;
//  }
  
 
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("Big5");
    res.setContentType("text/html; charset=Big5");
    PrintWriter out = res.getWriter();

    // 【取得使用者 帳號(account) 密碼(password)】
    String account = req.getParameter("account");
    String password = req.getParameter("password");
    
    
    //////////////////從資料庫拿,使用 usersvc 的 compare 方法拿 user_id,user_name,user_password/////////////////////////
	UserService userSvc = new UserService();
	UserVO userVO = userSvc.compare(account);
	String DbPassword= userVO.getPassword();
	String username = userVO.getUsername();
	Integer userId = userVO.getUserId();
    

    // 【檢查該帳號 , 密碼是否有效】
    if (!password.equals(DbPassword)) {          //【帳號 , 密碼無效時】
      out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
      out.println("<BODY>你的帳號 , 密碼無效!<BR>");
      out.println("請按此重新登入 <A HREF="+req.getContextPath()+"/pro/login.html>重新登入</A>");
      out.println("</BODY></HTML>");
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
      HttpSession session = req.getSession();
      session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
//////////////////////////////從資料庫到 userId 使用 session 送至瀏覽器各頁///////////////////////////////////////
      session.setAttribute("userId", userId);
       try {                                                        
         String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
           res.sendRedirect(location);   
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/pro/login_success.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
    }
  }
}