package com.order.controller;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.order.model.*;
import com.order.model.OrderDAO;
import com.order.model.OrderVO;
import com.order.model.OrderService;


@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


/*���o�����q�檬�A��1/0�����*/
		if ("getAll".equals(action)) { 
			    String status = req.getParameter("status");
			    Integer orderStatus = Integer.parseInt(status); //�n�d�ߪ��q�檬�A(�૬)
			    
			    OrderDAO orderDAO = new OrderDAO();
			    List<OrderVO> orderList = orderDAO.getAll(orderStatus);
			   
				req.setAttribute("OrderList", orderList); // ��Ʈw���X��empVO����,�s�Jreq
				
				String url;
				if(orderStatus == 1) {
				   url = "/emp/order2.jsp";/*�T�{�^�Ǩ�jsp����m�έ��Ҽ���*/
				}else {
				   url = "/emp/order.jsp";	
				}				
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
/*�ק�q�檬�A*/
		if ("update".equals(action)) {
			    OrderVO orderVO = new OrderVO();
			    
			    String id = req.getParameter("id");
			    String status = req.getParameter("status");
			    String datetime = req.getParameter("updateDatetime");
			    
			    Integer orderId = Integer.parseInt(id); 
			    Integer orderStatus = Integer.parseInt(status); 
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//���w�ഫ�ɶ��榡
			    LocalDateTime upDatetime = LocalDateTime.parse(datetime, formatter);//�N�r���ഫ��LocalDateTime
			    
			    Timestamp updateDatetime = Timestamp.valueOf(upDatetime);
			    
			    orderVO.setOrderId(orderId);
			    orderVO.setOrderStatus(orderStatus);
			    orderVO.setUpdatedDatetime(updateDatetime);
			    
			    OrderDAO orderDAO = new OrderDAO();
			    
			    orderDAO.update(orderVO);
			    
				String url = "/emp/listOneEmp.jsp";//�^���e��
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
		}
/*�̷Ӯɶ��d��*/
		if("search".equals(action)) {
			
			String time1 = req.getParameter("time1");
			String time2 = req.getParameter("time2");
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//���w�ഫ�ɶ��榡
		    LocalDateTime timeOne = LocalDateTime.parse(time1, formatter);//�N�r���ഫ��LocalDateTime
		    LocalDateTime timeTwo = LocalDateTime.parse(time2, formatter);
		    
		    Timestamp beginTime;
		    Timestamp endTime;
		    
		    if(timeOne.isBefore(timeTwo)) {
		    	beginTime = Timestamp.valueOf(timeOne);
		    	endTime = Timestamp.valueOf(timeOne);
		    }else {
		    	beginTime = Timestamp.valueOf(timeTwo);
		    	endTime = Timestamp.valueOf(timeOne);
		    }
		    
		    String status = req.getParameter("status");
		    Integer orderStatus = Integer.parseInt(status); //�n�d�ߪ��q�檬�A(�૬)
		    
		    OrderDAO orderDAO = new OrderDAO();
		    
		    Set<OrderVO> orderList = orderDAO.getOrdersByTime(beginTime, endTime, orderStatus);
		
			req.setAttribute("OrderList", orderList); // ��Ʈw���X��empVO����,�s�Jreq
			
			String url;
			if(orderStatus == 1) {
			   url = "/emp/listOneEmp.jsp";/*�T�{�^�Ǩ�jsp����m�έ��Ҽ���*/
			}else {
			   url = "/emp/listOneEmp.jsp";	
			}				
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		}
	}
}
