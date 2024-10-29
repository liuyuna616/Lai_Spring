package com.orderitem.model;

import java.util.*;

import com.order.model.OrderVO;

public interface OrderitemDAO_interface {
	public void insert(OrderitemVO orderitemVO); //insert 訂單表 orders
	public void insert(OrderVO orderVO); //insert 訂單詳情表 orderitems
	
	public List<OrderitemVO> getAll();  //findbyid
	
	public List<OrderitemVO> getorderitemID_HtoL();  //下拉式選單：訂單流水號	
	public List<OrderitemVO> getorderDate();  //下拉式選單：訂單時間
	public List<OrderitemVO> getpickupDate();  //下拉式選單：取貨時間
//	public List<OrderitemVO> getPrice_LtoH();  //
	public List<OrderitemVO> getVendorsId();   //下拉式選單：廠商名稱	
	public List<OrderitemVO> gettotalAmount(); //下拉式選單：訂單總額
	
}
