//package com.order.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.JsonSerializerInterface;
//import com.google.gson.Gson;
//
//import orderitem.model.OrderitemVO;
//
//@WebServlet("/pro/test")
//public class testController {
//
//	public class DBGifReaderController extends HttpServlet implements JsonSerializerInterface {
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自前端的請求
//			resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS"); // 允許的 HTTP 方法
//			resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // 允許的請求頭
//
//			System.out.print("123");
//
//			// 處理跨域預檢請求 (OPTIONS 方法)
//			if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
//				resp.setStatus(HttpServletResponse.SC_OK);
//				return;
//			}
//			doPost(req, resp);
//		}

//		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			res.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自前端的請求
//			res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS"); // 允許的 HTTP 方法
//			res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // 允許的請求頭
//
//			// 設置請求和響應的編碼
//			req.setCharacterEncoding("UTF-8");
//			res.setContentType("application/json");
//			res.setCharacterEncoding("UTF-8");
//
//			// 讀取前端傳來的 JSON 數據
//			BufferedReader reader = req.getReader();
//			Gson gson = new Gson();
//			OrderitemVO orderList = gson.fromJson(reader, OrderitemVO.class); // 將 JSON 轉換為 User 對象
//		System.out.println("123");
//		}
//	}
//}
