package com.order.model;

import java.sql.Timestamp;
import java.util.*;
import com.order.model.OrderVO;

public interface OrderDAO_interface {
	      public void insert(OrderVO orderVO);
          public void update(OrderVO orderVO);
          public void delete(Integer orderId);
          public OrderVO findByPrimaryKey();
	      public List<OrderVO> getAll(Integer orderStatus);
	      //�d�߬Y���������u(�@��h)(�^�� Set)
	      public Set<OrderVO> getOrdersByTime(Timestamp begintTime, Timestamp endTime, Integer orderStatus);
}