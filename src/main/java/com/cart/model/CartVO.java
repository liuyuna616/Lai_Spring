package com.cart.model;

import java.time.LocalDateTime;

public class CartVO {
	private Integer cartId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private String productStatus;
    private LocalDateTime createdTime;
    private Integer cartStatus;
    
    
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getCartStatus() {
		return cartStatus;
	}
	public void setCartStatus(Integer cartStatus) {
		this.cartStatus = cartStatus;
	}

    
}
