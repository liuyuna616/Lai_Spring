package com.orderitem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JsonSerializerInterface;
import com.google.gson.Gson;
import com.orderitem.model.OrderitemVO;



@WebServlet("/OrderitemServlet")
public class OrderitemServlet extends HttpServlet{
    // JDBC 資料庫連接資訊
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "1234";
	
	
	// 處理 GET 請求
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 設置 CORS headers 允許跨域請求
        response.setHeader("Access-Control-Allow-Origin", "*");  // 允許來自所有來源的請求
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");  // 允許的 HTTP 方法
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");  // 允許的 headers
     
        
        // 取得請求中的參數
        String param1 = request.getParameter("param1");
        
        // 存到資料庫
//        if (param1 != null && !param1.isEmpty()) {
//            saveParamToDatabase(param1);
//        }
        
        // 使用 PrintWriter 輸出回應
        PrintWriter out = response.getWriter();
        if (param1 != null && !param1.isEmpty()) {
            // 將收到的參數印出來
            out.println("Received parameter: " + param1);
        } else {
            out.println("No parameter received");
        }
        out.close();
        
    }

    // 處理 POST 請求
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 設置 CORS headers 允許跨域請求
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // 設置回應的內容類型為 JSON
        response.setContentType("application/json;charset=UTF-8");

        // 讀取請求的 body 內容
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // 將讀取的 JSON 字符串轉換為物件
        String json = sb.toString();
        Gson gson = new Gson();
        OrderitemVO[] itemsList = gson.fromJson(json, OrderitemVO[].class);

        // 使用 PrintWriter 輸出回應
        PrintWriter out = response.getWriter();
        if (itemsList != null) {
            out.println("Received items list: ");
            for (OrderitemVO item : itemsList) {
                out.println(item);  // 假設 OrderitemVO 有正確的 toString() 方法
            }
        } else {
            out.println("No items received");
        }
        out.close();
    }
    
//    // 處理 POST 請求
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        // 設置 CORS headers 允許跨域請求
//        response.setHeader("Access-Control-Allow-Origin", "*");  // 允許來自所有來源的請求
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");  // 允許的 HTTP 方法
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");  // 允許的 headers
//        
//        // 取得請求中的參數
//        String param1 = request.getParameter("param1");
//        
//        // 存到資料庫
////        if (param1 != null && !param1.isEmpty()) {
////            saveParamToDatabase(param1);
////        }
//        
//        // 使用 PrintWriter 輸出回應
//        PrintWriter out = response.getWriter();
//        if (param1 != null && !param1.isEmpty()) {
//            // 將收到的參數印出來
//            out.println("Received parameter: " + param1);
//        } else {
//            out.println("No parameter received");
//        }
//        out.close();
//        
//        
//    }
    
   // =======================================================

    
    
    
}
