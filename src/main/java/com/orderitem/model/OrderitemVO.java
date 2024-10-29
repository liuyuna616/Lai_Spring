package com.orderitem.model;

import java.sql.Timestamp;

public class OrderitemVO implements java.io.Serializable {
	private Integer orderitemId;
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	private Integer price;	
	
	//  ====orders table for Jimmy ====
	private Integer totalAmount;
	private Timestamp createdAt;
	private Timestamp pickupTime;
	
    //  ==== venders table for Yuna ====
	private Integer vendorsId;
	private String  shopName;
	private Integer userId;
	private String  userName;
	private String  email;
	
	
	public Integer getOrderitemId() {
		return orderitemId;
	}
	public void setOrderitemId(Integer orderitemId) {
		this.orderitemId = orderitemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(Timestamp pickupTime) {
		this.pickupTime = pickupTime;
	}
	public Integer getVendorsId() {
		return vendorsId;
	}
	public void setVendorsId(Integer vendorsId) {
		this.vendorsId = vendorsId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	@Override
	public String toString() {
		return "OrderitemVO [orderitemId=" + orderitemId + 
				", orderId=" + orderId + 
				", productId=" + productId+ 
				", quantity=" + quantity + 
				", price=" + price + 
				", totalAmount=" + totalAmount + 
				", createdAt=" + createdAt + 
				", pickupTime=" + pickupTime + 
				", vendorsId=" + vendorsId + 
				", shopName=" + shopName +
				", userId=" + userId + 
				", userName=" + userName + "]";
	}


	
}


	
