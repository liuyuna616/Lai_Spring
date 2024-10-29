package com.order.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


public class OrderService {
	
	

	private OrderDAO_interface dao;

	public OrderService() {
		dao = new OrderDAO();
	}

	public List<OrderVO> getAll(Integer orderStatus) {
		return dao.getAll(orderStatus);
	}
	
//
//	public OrderVO getOneOrder(Integer orderno) {
//		return dao.findByPrimaryKey(orderno);
//	}

	public Set<OrderVO> getOrdersByTime(Timestamp beginTime, Timestamp endTime, Integer orderStatus) {
		return dao.getOrdersByTime(beginTime ,endTime ,orderStatus);
	}

	public void deleteDept(Integer orderno) {
		dao.delete(orderno);
	}
}

