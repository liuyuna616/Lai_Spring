package com.user.model;


import java.sql.Timestamp;
import java.util.List;

public class UserService {
private UserDAO_interface dao;

public UserService() {
	dao = new UserJDBCDAO();
}

public UserVO addUser(String username,String password,byte[] avatar,String email,Integer enabled,String providerName,String accessToken,String refreshToken,Timestamp accessTokenExpiry,Timestamp refreshTokenExpiry,Integer newsletterSubscriptionConsentField,Integer groupPoints,Integer interestsTag) {
	
	UserVO userVO = new UserVO();
	

	userVO.setUsername(username);
	userVO.setPassword(password);
	userVO.setAvatar(avatar);
	userVO.setEmail(email);
	userVO.setEnabled(enabled);
	userVO.setProviderName(providerName);
	userVO.setAccessToken(accessToken);
	userVO.setRefreshToken(refreshToken);
	userVO.setAccessTokenExpiry(accessTokenExpiry);
	userVO.setRefreshTokenExpiry(refreshTokenExpiry);
	userVO.setNewsletterSubscriptionConsentField(newsletterSubscriptionConsentField);
	userVO.setGroupPoints(groupPoints);
	userVO.setInterestsTag(interestsTag);
	dao.insert(userVO);
	return userVO;
}
public UserVO updateUser(Integer userId,String username,String password,byte[] avatar,String email,Integer enabled,String providerName,String accessToken,String refreshToken,Timestamp accessTokenExpiry,Timestamp refreshTokenExpiry,Integer newsletterSubscriptionConsentField,Integer groupPoints,Integer interestsTag) {
	UserVO userVO = new UserVO();

	userVO.setUserId(userId);
	userVO.setUsername(username);
	userVO.setPassword(password);
	userVO.setAvatar(avatar);
	userVO.setEmail(email);
	userVO.setEnabled(enabled);
	userVO.setProviderName(providerName);
	userVO.setAccessToken(accessToken);
	userVO.setRefreshToken(refreshToken);
	userVO.setAccessTokenExpiry(accessTokenExpiry);
	userVO.setRefreshTokenExpiry(refreshTokenExpiry);
	userVO.setNewsletterSubscriptionConsentField(newsletterSubscriptionConsentField);
	userVO.setGroupPoints(groupPoints);
	userVO.setInterestsTag(interestsTag);
	dao.update(userVO);
	return userVO;
}
public void deleteUser(Integer userId) {
	dao.delete(userId);
}
public UserVO getOneUser(Integer userId) {
	return dao.findByPrimaryKey(userId);
}
public List<UserVO> getAll(){
	return dao.getAll();
}

public UserVO compare(String email) {
	return dao.compare(email);
}


}
