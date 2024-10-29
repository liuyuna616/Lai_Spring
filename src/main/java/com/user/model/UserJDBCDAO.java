package com.user.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAO implements UserDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_PSTMT = "INSERT INTO users (username,password,avatar,email,enabled,provider_name,access_token,refresh_token,access_token_expiry,refresh_token_expiry,newsletter_subscription_consent_field,group_points,interests_tag ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_PSTMT = "SELECT user_id,username,password,avatar,email,enabled,provider_name,access_token,refresh_token,access_token_expiry,refresh_token_expiry,newsletter_subscription_consent_field,created_datetime,updated_datetime,group_points,interests_tag FROM users order by user_id";
	private static final String GET_ONE_PSTMT = "SELECT user_id,username,password,avatar,email,"
			+ "enabled,provider_name,access_token,refresh_token,access_token_expiry,"
			+ "refresh_token_expiry,newsletter_subscription_consent_field,created_datetime,"
			+ "updated_datetime,group_points,interests_tag FROM users WHERE user_id = ?";
	private static final String DELETE = "DELETE FROM users WHERE user_id = ?";
	private static final String UPDATE = "UPDATE users SET username=?,password=?,avatar=?,email=?,enabled=?,provider_name=?,access_token=?,refresh_token=?,access_token_expiry=?,refresh_token_expiry=?,newsletter_subscription_consent_field=?,group_points=?,interests_tag=? WHERE user_id = ?";
	private static final String GET_ONE = "SELECT user_id,username,password FROM users WHERE email = ?";

	@Override
	public void insert(UserVO userVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_PSTMT);

			pstmt.setString(1, userVO.getUsername());
			pstmt.setString(2, userVO.getPassword());
			pstmt.setBytes(3, userVO.getAvatar());
			pstmt.setString(4, userVO.getEmail());
			pstmt.setInt(5, userVO.getEnabled());
			pstmt.setString(6, userVO.getProviderName());
			pstmt.setString(7, userVO.getAccessToken());
			pstmt.setString(8, userVO.getRefreshToken());
			pstmt.setTimestamp(9, userVO.getAccessTokenExpiry());
			pstmt.setTimestamp(10, userVO.getRefreshTokenExpiry());
			pstmt.setInt(11, userVO.getNewsletterSubscriptionConsentField());
			pstmt.setInt(12, userVO.getGroupPoints());
			pstmt.setInt(13, userVO.getInterestsTag());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(UserVO userVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, userVO.getUsername());
			pstmt.setString(2, userVO.getPassword());
			pstmt.setBytes(3, userVO.getAvatar());
			pstmt.setString(4, userVO.getEmail());
			pstmt.setInt(5, userVO.getEnabled());
			pstmt.setString(6, userVO.getProviderName());
			pstmt.setString(7, userVO.getAccessToken());
			pstmt.setString(8, userVO.getRefreshToken());
			pstmt.setTimestamp(9, userVO.getAccessTokenExpiry());
			pstmt.setTimestamp(10, userVO.getRefreshTokenExpiry());
			pstmt.setInt(11, userVO.getNewsletterSubscriptionConsentField());
			pstmt.setInt(12, userVO.getGroupPoints());
			pstmt.setInt(13, userVO.getInterestsTag());
			pstmt.setInt(14,userVO.getUserId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, userId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public UserVO findByPrimaryKey(Integer userId) {

		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_PSTMT);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getInt("user_id"));
				userVO.setUsername(rs.getString("username"));
				userVO.setPassword(rs.getString("password"));
				userVO.setAvatar(rs.getBytes("avatar"));
				userVO.setEmail(rs.getString("email"));
				userVO.setEnabled(rs.getInt("enabled"));
				userVO.setProviderName(rs.getString("provider_name"));
				userVO.setAccessToken(rs.getString("access_token"));
				userVO.setRefreshToken(rs.getString("refresh_token"));
				userVO.setAccessTokenExpiry(rs.getTimestamp("access_token_expiry"));
				userVO.setRefreshTokenExpiry(rs.getTimestamp("refresh_token_expiry"));
				userVO.setNewsletterSubscriptionConsentField(rs.getInt("newsletter_subscription_consent_field"));
//				userVO.setCreatedDatetime(rs.getDate("created_datetime"));
				userVO.setUpdatedDatetime(rs.getDate("updated_datetime"));
				userVO.setGroupPoints(rs.getInt("group_points"));
				userVO.setInterestsTag(rs.getInt("interests_tag"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return userVO;
	}

	@Override
	public List<UserVO> getAll() {
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO userVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getInt("user_id"));
	            userVO.setUsername(rs.getString("username"));
	            userVO.setPassword(rs.getString("password"));
	            userVO.setAvatar(rs.getBytes("avatar"));
	            userVO.setEmail(rs.getString("email"));
	            userVO.setEnabled(rs.getInt("enabled"));
	            userVO.setProviderName(rs.getString("provider_name"));
	            userVO.setAccessToken(rs.getString("access_token"));
	            userVO.setRefreshToken(rs.getString("refresh_token"));
	            userVO.setAccessTokenExpiry(rs.getTimestamp("access_token_expiry"));
	            userVO.setRefreshTokenExpiry(rs.getTimestamp("refresh_token_expiry"));
	            userVO.setNewsletterSubscriptionConsentField(rs.getInt("newsletter_subscription_consent_field"));
	            userVO.setCreatedDatetime(rs.getDate("created_datetime"));
	            userVO.setUpdatedDatetime(rs.getDate("updated_datetime"));
	            userVO.setGroupPoints(rs.getInt("group_points"));
	            userVO.setInterestsTag(rs.getInt("interests_tag"));
				list.add(userVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	public static void main(String args[]) {
		UserJDBCDAO dao = new UserJDBCDAO();
		
		// �憓�
//		UserVO userVO = new UserVO();
//		userVO.setUsername("peterWang");
//		userVO.setPassword("12345678");
//		userVO.setAvatar(null);
//		userVO.setEmail("godknows1239@gmail.com");
//		userVO.setEnabled(1);
//		userVO.setProviderName(null);
//		userVO.setAccessToken(null);
//		userVO.setRefreshToken(null);
//		userVO.setAccessTokenExpiry(null);
//		userVO.setRefreshTokenExpiry(null);
//		userVO.setNewsletterSubscriptionConsentField(1);
//		userVO.setGroupPoints(3);
//		userVO.setInterestsTag(2);
//		dao.insert(userVO);
		// 靽格
//		UserVO userVO2 = new UserVO();
//		userVO2.setUserId(10);
//		userVO2.setUsername("peterupdate");
//		userVO2.setPassword("12345678");
//		userVO2.setAvatar(null);
//		userVO2.setEmail("godknows123@gmail.com");
//		userVO2.setEnabled(1);
//		userVO2.setProviderName(null);
//		userVO2.setAccessToken(null);
//		userVO2.setRefreshToken(null);
//		userVO2.setAccessTokenExpiry(null);
//		userVO2.setRefreshTokenExpiry(null);
//		userVO2.setNewsletterSubscriptionConsentField(1);
//		userVO2.setGroupPoints(4);
//		userVO2.setInterestsTag(9);
//		dao.update(userVO2);
		// ��
//		dao.delete(10);
		// �閰�
//		UserVO userVO3 = dao.findByPrimaryKey(10);
//		System.out.print(userVO3.getUserId() + ",");
//		System.out.print(userVO3.getUsername() + ",");
//		System.out.print(userVO3.getPassword() + ",");
//		System.out.print(userVO3.getAvatar() + ",");
//		System.out.print(userVO3.getEmail() + ",");
//		System.out.print(userVO3.getEnabled() + ",");
//		System.out.print(userVO3.getProviderName() + ",");
//		System.out.print(userVO3.getAccessToken() + ",");
//		System.out.print(userVO3.getRefreshToken() + ",");
//		System.out.print(userVO3.getAccessTokenExpiry() + ",");
//		System.out.print(userVO3.getRefreshTokenExpiry() + ",");
//		System.out.print(userVO3.getNewsletterSubscriptionConsentField() + ",");
//		System.out.print(userVO3.getCreatedDatetime() + ",");
//		System.out.print(userVO3.getUpdatedDatetime() + ",");
//		System.out.print(userVO3.getGroupPoints() + ",");
//		System.out.print(userVO3.getInterestsTag() + ",");
//		System.out.println();
		// �閰�
		List<UserVO> list = dao.getAll();
		for (UserVO aUser : list) {
			System.out.print(aUser.getUserId() + ",");
			System.out.print(aUser.getUsername() + ",");
			System.out.print(aUser.getPassword() + ",");
			System.out.print(aUser.getAvatar() + ",");
			System.out.print(aUser.getEmail() + ",");
			System.out.print(aUser.getEnabled() + ",");
			System.out.print(aUser.getProviderName() + ",");
			System.out.print(aUser.getAccessToken() + ",");
			System.out.print(aUser.getRefreshToken() + ",");
			System.out.print(aUser.getAccessTokenExpiry() + ",");
			System.out.print(aUser.getRefreshTokenExpiry() + ",");
			System.out.print(aUser.getNewsletterSubscriptionConsentField() + ",");
			System.out.print(aUser.getCreatedDatetime() + ",");
			System.out.print(aUser.getUpdatedDatetime() + ",");
			System.out.print(aUser.getGroupPoints() + ",");
			System.out.print(aUser.getInterestsTag() + ",");
			System.out.println();
		}
	}

	@Override
	public UserVO compare(String email) {
		
		
		
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getInt("user_id"));
				userVO.setUsername(rs.getString("username"));
				userVO.setPassword(rs.getString("password"));
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return userVO;
		
		
		
	}
}
