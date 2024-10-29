package com.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class ProVO implements java.io.Serializable{
	private Integer productId;
	private Integer supplierId;
	private String productName;
	private String productContent;
	private Integer price;
	private String productSpec;
	private Integer stock;
	private Integer productType;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	private Timestamp updatedAt;
	
	
	//////////jimmy table//////////////
	private byte[] picture;
	private Integer ad;
		
	


	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
		
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}	
	
	
	public Integer getAd() {
		return ad;
	}
	public void setAd(Integer ad) {
		this.ad = ad;
	}
	
	
	@Override
	public String toString() {
		return "ProVO [productId=" + productId + 
				", supplierId=" + supplierId + 
				", productName=" + productName + 
				", productContent=" + productContent + 
				", price=" + price + 
				", productSpec=" + productSpec+ 
				", stock=" + stock + 
				", productType=" + productType + 
				", createdTime=" + createdTime + 
				", updatedTime=" + updatedTime + 
				", updatedAt=" + updatedAt + 
				"]";
	}	
	
}
