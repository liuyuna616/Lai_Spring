package com.order.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDAO implements OrderDAO_interface {

	// 嚙瑾嚙踝蕭嚙踝蕭嚙諄程嚙踝蕭嚙踝蕭,嚙緩嚙踝蕭@嚙諉賂蕭w ,嚙瑾嚙諄一嚙踝蕭DataSource嚙磐嚙箠
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Jimmy");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	//private static final String INSERT_STMT = "INSERT INTO dept2 (order_id,loc) VALUES (?, ?)";  //嚙編嚙磕
	//private static final String GET_ALL_STMT = "SELECT order_id , dname, loc FROM dept2"; //嚙踝蕭嚙踝蕭嚙踝蕭
	//private static final String GET_ = "SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";

	
	private static final String GET_ALL = "SELECT orderId ,userId, totalAmount,orderStatus, createdDatetime FROM orders where orderStatus = ? order by orderId"; 
	//嚙瞎嚙緩嚙緬嚙賣的嚙踝蕭嚙踝蕭嚙踝蕭m 嚙緬嚙賣狀嚙璀NULL嚙箭嚙畿嚙緲嚙踝蕭 嚙踝蕭1 嚙緩嚙踝蕭嚙踝蕭 嚙踝蕭0嚙踝蕭嚙踝蕭O嚙箭嚙踝蕭嚙踝蕭嚙踝蕭嚙瞌嚙瞌嚙踝蕭嚙瞑
	private static final String UPDATE = "UPDATE orders set orderStatus=?, updatedDatetime = ? where orderId = ?"; //嚙踝蕭s嚙踝蕭嚙踝蕭嚙踝蕭q嚙踝蕭0/1 0(嚙踝蕭嚙踝蕭) 1(嚙確嚙緩)
	
	private static final String GET_BY_TIME ="SELECT orderId ,userId, orderStatus, totalAmount, createdDatetime, updatedDatetime FROM orders where orderStatus = ? "
			+ "and createdDatetime BETWEEN ? and ? order by order_id";
	//嚙踝蕭雂@嚙諉時塚蕭嚙範嚙踝蕭j嚙瞎嚙踝蕭嚙� 
	
	//private static final String DELETE_ORDER = "DELETE FROM dept2 where deptno = ?";	//嚙磋嚙踝蕭 嚙磐嚙踝蕭
	
	@Override
	public void delete(Integer orderId) {
	}
	

	@Override
	public void update(OrderVO orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderVO.getOrderStatus());
			pstmt.setTimestamp(2, orderVO.getUpdatedDatetime());
			pstmt.setInt(3, orderVO.getOrderId());

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
	public void insert(OrderVO orderVO) {
	}

	@Override
	public OrderVO findByPrimaryKey() {
		return null;
	}

	@Override
	public List<OrderVO> getAll(Integer orderStatus) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			pstmt.setInt(1, orderStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("orderId"));
				orderVO.setUserId(rs.getInt("userId"));
				orderVO.setOrderStatus(rs.getInt("orderStatus"));
				orderVO.setTotalAmount(rs.getInt("totalAmount"));
				orderVO.setCreatedDatetime(rs.getTimestamp("createdDatetime"));
				list.add(orderVO); // Store the row in the list
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

	@Override
	public Set<OrderVO> getOrdersByTime(Timestamp begin_time, Timestamp end_time, Integer order_status) {
	
		Set<OrderVO> set = new LinkedHashSet<OrderVO>();
		OrderVO orderVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_TIME);
			pstmt.setInt(1, order_status);
			pstmt.setTimestamp(2, begin_time);
			pstmt.setTimestamp(3, end_time);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrderId(rs.getInt("order_id"));
				orderVO.setUserId(rs.getInt("user_id"));
				orderVO.setOrderStatus(rs.getInt("order_status"));
				orderVO.setTotalAmount(rs.getInt("total_amount"));
				orderVO.setCreatedDatetime(rs.getTimestamp("created_datetime"));
				set.add(orderVO); // Store the row in the vector
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
		return set;
		
	}
}