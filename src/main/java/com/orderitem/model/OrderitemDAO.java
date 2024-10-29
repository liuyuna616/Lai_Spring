package com.orderitem.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order.model.OrderVO;
import com.product.model.ProVO;

public class OrderitemDAO implements OrderitemDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/gosport");
		} catch (NamingException e) {
			e.printStackTrace();
		}
}
	//insert 訂單表 orders
	private static final String INSERT_ORDER = 
		"INSERT INTO orders (vendor_id,user_id,total_amount) VALUES (?, ?, ? )"; 
	
	//insert 訂單詳情表 orderitems
	private static final String INSERT_ORDERITEM = 
		"INSERT INTO orderitems (product_id,quantity,price) VALUES (?, ?, ? )"; 

	private static final String getAll = 
		"SELECT order_item_id,orderitems.order_id,product_id,quantity,price, orderitems.order_id,user_id,total_amount,created_datetime,pickup_date FROM orderitems INNER JOIN orders on orderitems.order_id = orders.order_id";	
	
	//下拉式選單：訂單流水號
	private static final String getorderitemID_HtoL = 
		"SELECT order_item_id,order_id,product_id,quantity,price FROM orderitems order by order_id desc";

	//下拉式選單：訂單時間
	private static final String getorderDate = 
		"SELECT order_item_id,orderitems.order_id,product_id,quantity,price, orderitems.order_id,user_id,total_amount,created_datetime,pickup_date FROM orderitems INNER JOIN orders on orderitems.order_id = orders.order_id order by created_datetime desc";	

	//下拉式選單：取貨時間
	private static final String getpickupDate = 
		"SELECT order_item_id,orderitems.order_id,product_id,quantity,price, orderitems.order_id,user_id,total_amount,created_datetime,pickup_date FROM orderitems INNER JOIN orders on orderitems.order_id = orders.order_id order by pickup_date desc";	
		
	//下拉式選單：廠商名稱
	private static final String getvendorsId = 
		"SELECT shop_name FROM vendors WHERE vendor_id = ?";
	
	//下拉式選單：價格高低
	private static final String getPrice_LtoH = 
		"SELECT order_item_id,orderitems.order_id,product_id,quantity,price, orderitems.order_id,user_id,total_amount,created_datetime,pickup_date FROM orderitems INNER JOIN orders on orderitems.order_id = orders.order_id order by total_amount desc";

		
	//insert 訂單表 orders
	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_ORDER);

			pstmt.setInt(1, orderVO.getVendorId());
			pstmt.setInt(2, orderVO.getUserId());
			pstmt.setInt(3, orderVO.getTotalAmount());
	
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
					
	}
	
	//insert 訂單詳情表 orderitems
	@Override
	public void insert(OrderitemVO orderitemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_ORDERITEM);

			pstmt.setInt(1, orderitemVO.getProductId());
			pstmt.setInt(2, orderitemVO.getQuantity());
			pstmt.setInt(3, orderitemVO.getPrice());
	
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
					
	}
	
	
	@Override
	public List<OrderitemVO> getAll() {
		List<OrderitemVO> list = new ArrayList<OrderitemVO>();
		OrderitemVO orderitemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll); //!!!!!
			rs = pstmt.executeQuery();


	
			while (rs.next()) {
				OrderitemVO orderItemVO = new OrderitemVO();
				orderItemVO.setOrderitemId(rs.getInt("orderitemId"));
				orderItemVO.setOrderId(rs.getInt("orderId"));
				orderItemVO.setProductId(rs.getInt("productId"));
				orderitemVO.setQuantity(rs.getInt("quantity"));
				orderitemVO.setPrice(rs.getInt("price"));
				orderitemVO.setUserId(rs.getInt("userId"));
				orderitemVO.setTotalAmount(rs.getInt("totalAmount"));
				orderitemVO.setCreatedAt(rs.getTimestamp("createdAt"));
				orderitemVO.setPickupTime(rs.getTimestamp("pickupTime"));


				list.add(orderitemVO); // Store the row in the list
				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return list;
	}
	
	//下拉式選單：廠商名稱		
		@Override
	public List<OrderitemVO> getVendorsId() {
		List<OrderitemVO> list = new ArrayList<OrderitemVO>();
		OrderitemVO orderitemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(getvendorsId); //!!!!!
			rs = pstmt.executeQuery();


	
			while (rs.next()) {
				OrderitemVO orderItemVO = new OrderitemVO();
				orderItemVO.setShopName(rs.getString("shopName"));
				

				list.add(orderitemVO); // Store the row in the list
				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return list;
	}
	
	//下拉式選單：訂單流水號	
	@Override
	public List<OrderitemVO> getorderitemID_HtoL() {
		List<OrderitemVO> list = new ArrayList<OrderitemVO>();
		OrderitemVO orderitemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(getorderitemID_HtoL); 
			rs = pstmt.executeQuery();


	
			while (rs.next()) {
				OrderitemVO orderItemVO = new OrderitemVO();
				orderItemVO.setOrderitemId(rs.getInt("orderitemId"));
				orderItemVO.setOrderId(rs.getInt("orderId"));
				orderItemVO.setProductId(rs.getInt("productId"));
				orderitemVO.setQuantity(rs.getInt("quantity"));
				orderitemVO.setPrice(rs.getInt("price"));
				orderitemVO.setUserId(rs.getInt("userId"));
				orderitemVO.setTotalAmount(rs.getInt("totalAmount"));
				orderitemVO.setCreatedAt(rs.getTimestamp("createdAt"));
				orderitemVO.setPickupTime(rs.getTimestamp("pickupTime"));

				list.add(orderitemVO); // Store the row in the list
				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return list;
	}
	
	//下拉式選單：訂單時間
	@Override
	public List<OrderitemVO> getorderDate() {
		List<OrderitemVO> list = new ArrayList<OrderitemVO>();
		OrderitemVO orderitemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(getorderDate); 
			rs = pstmt.executeQuery();


	
			while (rs.next()) {
				OrderitemVO orderItemVO = new OrderitemVO();
				orderItemVO.setOrderitemId(rs.getInt("orderitemId"));
				orderItemVO.setOrderId(rs.getInt("orderId"));
				orderItemVO.setProductId(rs.getInt("productId"));
				orderitemVO.setQuantity(rs.getInt("quantity"));
				orderitemVO.setPrice(rs.getInt("price"));
				orderitemVO.setUserId(rs.getInt("userId"));
				orderitemVO.setTotalAmount(rs.getInt("totalAmount"));
				orderitemVO.setCreatedAt(rs.getTimestamp("createdAt"));
				orderitemVO.setPickupTime(rs.getTimestamp("pickupTime"));
				list.add(orderitemVO); // Store the row in the list
				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return list;
	}
	
	//下拉式選單：取貨時間
	@Override
	public List<OrderitemVO> getpickupDate() {
		List<OrderitemVO> list = new ArrayList<OrderitemVO>();
		OrderitemVO orderitemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(getpickupDate); 
			rs = pstmt.executeQuery();


	
			while (rs.next()) {
				OrderitemVO orderItemVO = new OrderitemVO();
				orderItemVO.setOrderitemId(rs.getInt("orderitemId"));
				orderItemVO.setOrderId(rs.getInt("orderId"));
				orderItemVO.setProductId(rs.getInt("productId"));
				orderitemVO.setQuantity(rs.getInt("quantity"));
				orderitemVO.setPrice(rs.getInt("price"));
				orderitemVO.setUserId(rs.getInt("userId"));
				orderitemVO.setTotalAmount(rs.getInt("totalAmount"));
				orderitemVO.setCreatedAt(rs.getTimestamp("createdAt"));
				orderitemVO.setPickupTime(rs.getTimestamp("pickupTime"));

				list.add(orderitemVO); // Store the row in the list
				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return list;
	}
	
	//下拉式選單：訂單總額
	@Override
	public List<OrderitemVO> gettotalAmount() {
		List<OrderitemVO> list = new ArrayList<OrderitemVO>();
		OrderitemVO orderitemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(getPrice_LtoH); 
			rs = pstmt.executeQuery();


	
			while (rs.next()) {
				OrderitemVO orderItemVO = new OrderitemVO();
				orderItemVO.setOrderitemId(rs.getInt("orderitemId"));
				orderItemVO.setOrderId(rs.getInt("orderId"));
				orderItemVO.setProductId(rs.getInt("productId"));
				orderitemVO.setQuantity(rs.getInt("quantity"));
				orderitemVO.setPrice(rs.getInt("price"));
				orderitemVO.setUserId(rs.getInt("userId"));
				orderitemVO.setTotalAmount(rs.getInt("totalAmount"));
				orderitemVO.setCreatedAt(rs.getTimestamp("createdAt"));
				orderitemVO.setPickupTime(rs.getTimestamp("pickupTime"));

				list.add(orderitemVO); // Store the row in the list
				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return list;
	}
}
