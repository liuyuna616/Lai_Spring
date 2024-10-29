package com.product.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ProDAO implements ProDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/gosport");
		} catch (NamingException e) {
			e.printStackTrace();
		}

}
		//新增商品資訊
		private static final String INSERT_STMT =  
			"INSERT INTO products (product_id,supplier_id,product_name,product_content,price,product_spec,stock,created_time,updated_time,product_type,updaped_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		
		//更新商品資訊
		private static final String UPDATE = 
			"UPDATE products set product_name=?, price=?, where product_id = ?";
		
		//刪除商品資訊
		private static final String DELETE = 
			"DELETE FROM products where product_id = ?";		
		
		//
		private static final String getProductById = 
			"SELECT product_id,product_name,product_content,price,product_spec FROM products where product_id = ?";
		
		//
		private static final String getAll = 
			"SELECT product_id,supplier_id,product_name,product_content,price,product_spec,stock,created_time,updated_time,product_type,updaped_at FROM products";
			
		//下拉式選單：熱門推薦
		private static final String getPopular =  
			"SELECT product_id,supplier_id,product_name,product_content,price,product_spec,stock,created_time,updated_time,product_type,updaped_at FROM products order by stock";
		
		//下拉式選單：價格高低
		private static final String getPrice_LtoH = 
			"SELECT product_id,supplier_id,product_name,product_content,price,product_spec,stock,created_time,updated_time,product_type,updaped_at FROM products order by price";
		
		//下拉式選單：最新商品
		private static final String getNew = 
			"SELECT product_id,supplier_id,product_name,product_content,price,product_spec,stock,created_time,updated_time,product_type,updaped_at FROM products order by updated_time desc";
		
		//搜尋欄：關鍵字查詢
		private static final String getSearchnam = 
			"SELECT product_id,supplier_id,product_name,product_content,price,product_spec,stock,created_time,updated_time,product_type,updaped_at FROM products where product_name LIKE concat ('%',?,'%') ";
		
		//獲取商品圖片
		private static final String getPicture = 
			"SELECT picture FROM pictures WHERE product_id = ?";
		
		//獲取廣告商品圖片：其他推薦商品
		private static final String getAd = 
			"SELECT product_id,product_name FROM products WHERE ad = 1";

		
		//新增商品資訊
		@Override
		public void insert(ProVO proVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, proVO.getProductId());
				pstmt.setInt(2, proVO.getSupplierId());
				pstmt.setString(3, proVO.getProductName());
				pstmt.setString(4, proVO.getProductContent());
				pstmt.setInt(5, proVO.getPrice());
				pstmt.setString(6, proVO.getProductSpec());
				pstmt.setInt(7, proVO.getStock());
				pstmt.setTimestamp(8, proVO.getCreatedTime());
				pstmt.setTimestamp(9, proVO.getUpdatedTime());
				pstmt.setInt(10, proVO.getProductType());
				pstmt.setTimestamp(11, proVO.getUpdatedAt());
//				pstmt.setBytes(12, proVO.getPicture());
			
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
		//更新商品資訊
		@Override
		public void update(ProVO proVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
				
				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATE);

					pstmt.setString(1, proVO.getProductName());
					pstmt.setInt(2, proVO.getPrice());
					pstmt.setInt(3, proVO.getProductId());

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
		//刪除商品資訊
		@Override
		public void delete(Integer productId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1,productId);

				pstmt.executeUpdate();

				
			} catch (SQLException se) {
				if (con != null) {
					try {
	
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		//取得產品id		
		@Override 
		public ProVO getProductById(Integer productId) {
			ProVO proVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(getProductById);

				pstmt.setInt(1, productId);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// deptVO �]�٬� Domain objects
					proVO = new ProVO();
					proVO.setProductId(rs.getInt("product_id"));
					proVO.setProductName(rs.getString("product_name"));
					proVO.setProductContent(rs.getString("product_content"));
					proVO.setPrice(rs.getInt("price"));
					proVO.setProductSpec(rs.getString("product_spec"));

				}

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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

			return proVO;
		}
				
		@Override
		public List<ProVO> getAll() {
			List<ProVO> list = new ArrayList<ProVO>();
			ProVO proVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(getAll); //!!!!!
				rs = pstmt.executeQuery();



				
				while (rs.next()) {
					proVO = new ProVO();
					proVO.setProductId(rs.getInt("product_id"));
					proVO.setSupplierId(rs.getInt("supplier_id"));
					proVO.setProductName(rs.getString("product_name"));
					proVO.setProductContent(rs.getString("product_content"));
					proVO.setPrice(rs.getInt("price"));
					proVO.setProductSpec(rs.getString("product_spec"));
					proVO.setStock(rs.getInt("stock"));
					proVO.setCreatedTime(rs.getTimestamp("created_time"));
					proVO.setUpdatedTime(rs.getTimestamp("updated_time"));
					proVO.setProductType(rs.getInt("product_type"));
					proVO.setUpdatedAt(rs.getTimestamp("updaped_at"));
//					proVO.setPicture(rs.getBytes("picture"));
					list.add(proVO); // Store the row in the list
					
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
		//獲取商品圖片
		public List<ProVO> getOverviewPicture(Integer productId) {
			
			
			List<ProVO> list = new ArrayList<ProVO>();
			ProVO proVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(getPicture); 
				pstmt.setInt(1, productId);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					proVO = new ProVO();
					proVO.setPicture(rs.getBytes("picture"));
					
					list.add(proVO);// Store the row in the list
					
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
			
			
			return list ;
		}
		//獲取廣告商品圖片：其他推薦商品
		public List<ProVO> getAd() {
			
			
			List<ProVO> list = new ArrayList<ProVO>();
			ProVO proVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(getAd); 
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					proVO = new ProVO();
					proVO.setProductId(rs.getInt("product_id"));
					proVO.setProductName(rs.getString("product_name"));
					
					list.add(proVO);// Store the row in the list
					
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
			
			
			return list ;
		}
		
		//下拉式選單
		@Override
		public List<ProVO> getPopular() {
			List<ProVO> list = new ArrayList<ProVO>();
			ProVO proVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(getPopular);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					proVO = new ProVO();
					proVO.setProductId(rs.getInt("product_id"));
					proVO.setSupplierId(rs.getInt("supplier_id"));
					proVO.setProductName(rs.getString("product_name"));
					proVO.setProductContent(rs.getString("product_content"));
					proVO.setPrice(rs.getInt("price"));
					proVO.setProductSpec(rs.getString("product_spec"));
					proVO.setStock(rs.getInt("stock"));
					proVO.setCreatedTime(rs.getTimestamp("created_time"));
					proVO.setUpdatedTime(rs.getTimestamp("updated_time"));
					proVO.setProductType(rs.getInt("product_type"));
					proVO.setUpdatedAt(rs.getTimestamp("updaped_at"));
//					proVO.setPicture(rs.getBytes("picture"));
					list.add(proVO); // Store the row in the list
					
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
		public List<ProVO> getPrice_LtoH() {
			List<ProVO> list = new ArrayList<ProVO>();
			ProVO proVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(getPrice_LtoH);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					proVO = new ProVO();
					proVO.setProductId(rs.getInt("product_id"));
					proVO.setSupplierId(rs.getInt("supplier_id"));
					proVO.setProductName(rs.getString("product_name"));
					proVO.setProductContent(rs.getString("product_content"));
					proVO.setPrice(rs.getInt("price"));
					proVO.setProductSpec(rs.getString("product_spec"));
					proVO.setStock(rs.getInt("stock"));
					proVO.setCreatedTime(rs.getTimestamp("created_time"));
					proVO.setUpdatedTime(rs.getTimestamp("updated_time"));
					proVO.setProductType(rs.getInt("product_type"));
					proVO.setUpdatedAt(rs.getTimestamp("updaped_at"));
//					proVO.setPicture(rs.getBytes("picture"));
					list.add(proVO); // Store the row in the list
					
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
		public List<ProVO> getNew() {
			List<ProVO> list = new ArrayList<ProVO>();
			ProVO proVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(getNew);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					proVO = new ProVO();
					proVO.setProductId(rs.getInt("product_id"));
					proVO.setSupplierId(rs.getInt("supplier_id"));
					proVO.setProductName(rs.getString("product_name"));
					proVO.setProductContent(rs.getString("product_content"));
					proVO.setPrice(rs.getInt("price"));
					proVO.setProductSpec(rs.getString("product_spec"));
					proVO.setStock(rs.getInt("stock"));
					proVO.setCreatedTime(rs.getTimestamp("created_time"));
					proVO.setUpdatedTime(rs.getTimestamp("updated_time"));
					proVO.setProductType(rs.getInt("product_type"));
					proVO.setUpdatedAt(rs.getTimestamp("updaped_at"));
//					proVO.setPicture(rs.getBytes("picture"));
					list.add(proVO); // Store the row in the list
					
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
						
		//關鍵字搜尋
		@Override
		public List<ProVO> getSearchnam(String productName) {
			List<ProVO> list = new ArrayList<ProVO>();
			ProVO proVO = null;
			
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(getSearchnam);
				pstmt.setString(1,productName);  // �ҽk�d��
				rs = pstmt.executeQuery();
				
			while (rs.next()) {
				proVO = new ProVO();
				proVO.setProductId(rs.getInt("product_id"));
				proVO.setSupplierId(rs.getInt("supplier_id"));
				proVO.setProductName(rs.getString("product_name"));
				proVO.setProductContent(rs.getString("product_content"));
				proVO.setPrice(rs.getInt("price"));
				proVO.setProductSpec(rs.getString("product_spec"));
				proVO.setStock(rs.getInt("stock"));
				proVO.setCreatedTime(rs.getTimestamp("created_time"));
				proVO.setUpdatedTime(rs.getTimestamp("updated_time"));
				proVO.setProductType(rs.getInt("product_type"));
				proVO.setUpdatedAt(rs.getTimestamp("updaped_at"));
//				proVO.setPicture(rs.getBytes("picture"));
				list.add(proVO);
			}
				
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


		
