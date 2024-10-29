package com.orderitem.model;

import java.util.List;

import com.order.model.OrderVO;

public class OrderitemService {

	private OrderitemDAO_interface dao;
	
	public OrderitemService() {
		dao = new OrderitemDAO();
	}
	
	//�s�Worders�q���ƪ�
	public OrderVO addorder(Integer VendorId,Integer UserId,Integer TotalAmount) {
		OrderVO orderVO = new OrderVO();
		
		orderVO.setVendorId(VendorId);
		orderVO.setUserId(UserId);
		orderVO.setTotalAmount(TotalAmount);
		dao.insert(orderVO);
		
		return orderVO;
	}
	
	//�s�Worderitem�q���ƸԱ���
	public OrderitemVO adddorderitem(Integer productId,Integer quantity,Integer price) {
		OrderitemVO orderitemVO = new OrderitemVO();
		orderitemVO.setProductId(productId);
		orderitemVO.setQuantity(quantity);
		orderitemVO.setPrice(price);
		dao.insert(orderitemVO);
		
		return orderitemVO;
	};

	public List<OrderitemVO> getAll(){
		return dao.getAll();
	}
	
	 //�U�Ԧ����-�q��s��
	public List<OrderitemVO> getorderitemID_HtoL(){
		return dao.getorderitemID_HtoL();
	}
	
	//�U�Ԧ����-�q�ʤ��
	public List<OrderitemVO> getorderDate(){
		return dao.getorderDate();
	}
	
	//�U�Ԧ����-���f���
	public List<OrderitemVO> getpickupDate(){
		return dao.getpickupDate();
	}
			
	//�U�Ԧ����-�ӫ�
	public List<OrderitemVO> getVendorsId(){
		return dao.getVendorsId();
	}
	
	//�U�Ԧ����-���氪�C
	public List<OrderitemVO> gettotalAmount(){
		return dao.gettotalAmount();
	}

	
}
